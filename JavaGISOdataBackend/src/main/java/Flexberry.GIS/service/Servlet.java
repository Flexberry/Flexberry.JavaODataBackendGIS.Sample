package Flexberry.GIS.service;

import com.sap.olingo.jpa.metadata.api.JPAEntityManagerFactory;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDContextAccess;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDHandler;
import org.apache.olingo.commons.api.ex.ODataException;
import org.eclipse.persistence.internal.jpa.metamodel.ListAttributeImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.rmi.ServerException;
import java.util.*;

public class Servlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final String PUNIT_NAME = "Olingo4PG";
  private final EntityManagerFactory emf;

  public Servlet() throws ServerException {
    super();
    final DataSource ds = DataSourceProvider.createDataSource();
    emf = JPAEntityManagerFactory.getEntityManagerFactory(PUNIT_NAME, ds);
  }

  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException {

    EntityManager entityManager = null;
    try {
      final JPAODataCRUDContextAccess serviceContext =
          (JPAODataCRUDContextAccess) getServletContext().getAttribute("ServiceContext");
      entityManager = emf.createEntityManager();

      // Получаем список имен всех сущностей.
      List<String> entities = new ArrayList<String>();
      Set<EntityType<?>> managedEntities = entityManager.getMetamodel().getEntities();
      for (EntityType managedEntity : managedEntities) {
        String entityName = managedEntity.getJavaType().getSimpleName();
        entities.add(entityName);
      }

      // Добавляем в список аттрибуты сущностей, которые являются ссылками на другие сущности.
      for (EntityType managedEntity : managedEntities) {
        Set<Attribute> attributes = managedEntity.getAttributes();

        for (Attribute attribute : attributes) {
          String typeName = attribute.getJavaType().getSimpleName();

          if (attribute.getPersistentAttributeType() == Attribute.PersistentAttributeType.ONE_TO_MANY) {
            typeName = ((ListAttributeImpl) attribute).getBindableJavaType().getSimpleName();
          }

          if (entities.contains(typeName)) {
            String attributeName = attribute.getName();
            attributeName = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);

            if (!entities.contains(attributeName)) {
              entities.add(attributeName);
            }
          }
        }
      }

      // Передаем этот список в MutableHttpRequest для модификации запроса.
      ((MutableHttpRequest) req).setExistingEntitiesList(entities);

      final JPAODataCRUDHandler handler = new JPAODataCRUDHandler(serviceContext);
      handler.getJPAODataRequestContext().setEntityManager(entityManager);
	    handler.getJPAODataRequestContext().setCUDRequestHandler(new JPAExampleCUDRequestHandler());
      handler.process(req, resp);
    } catch (RuntimeException | ODataException e) {
      throw new ServletException(e);
    } finally {
      if (entityManager != null) {
        entityManager.close();
      }
    }
  }
}