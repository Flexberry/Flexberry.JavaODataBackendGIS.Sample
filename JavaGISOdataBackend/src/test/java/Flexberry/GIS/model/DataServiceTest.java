package Flexberry.GIS.model;

import Flexberry.GIS.service.DataSourceProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.rmi.ServerException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataServiceTest {
    private static final String ENTITY_MANAGER_DATA_SOURCE = "javax.persistence.nonJtaDataSource";
    private static EntityManagerFactory emf;

    private EntityManager em;
    private CriteriaBuilder cb;

    @BeforeAll
    public static void setupClass() throws ServerException {
        final DataSource ds = DataSourceProvider.createDataSourceForTests();
        final Map<String, Object> properties = new HashMap<>();
        properties.put(ENTITY_MANAGER_DATA_SOURCE, ds);
        emf = Persistence.createEntityManagerFactory(DataSourceProvider.PERSISTENCE_UNIT_NAME, properties);
    }

    @BeforeEach
    void setup() {
        em = emf.createEntityManager();
        assertNotNull(em);
        cb = em.getCriteriaBuilder();
        assertNotNull(cb);
    }

    @Test
    @DisplayName("FlexberryAdvLimit model test.")
    void FlexberryAdvLimit_ModelTest() {
        // Создаем объект.
        em.getTransaction().begin();

        FlexberryAdvLimit DataObject = new FlexberryAdvLimit();
        DataObject.setPrimarykey(UUID.fromString("211867cf-5555-4caa-8223-e347eda465e0"));
        DataObject.setUser("Test_User");
        DataObject.setModule("Test_Module");
        DataObject.setName("Test_Name");
        DataObject.setValue("Test_Value");
        DataObject.setHotKeyData(123);

        // Сохраняем его в БД.
        em.persist(DataObject);
        em.getTransaction().commit();

        assertNotNull(DataObject);

        // Создаем запрос на обновление.
        CriteriaUpdate<FlexberryAdvLimit> update = cb.createCriteriaUpdate(FlexberryAdvLimit.class);
        Root e = update.from(FlexberryAdvLimit.class);

        update.set("User", "Test_User_Updated");
        update.where(cb.greaterThanOrEqualTo(e.get("User"), "Test_User"));

        // Обновляем объект.
        em.getTransaction().begin();
        int execRes = this.em.createQuery(update).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);

        // Создаем запрос на удаление.
        CriteriaDelete<FlexberryAdvLimit> delete = cb.createCriteriaDelete(FlexberryAdvLimit.class);
        delete.where(cb.equal(e.get("primarykey"), UUID.fromString("211867cf-5555-4caa-8223-e347eda465e0")));

        // Удаляем объект.
        em.getTransaction().begin();
        execRes = this.em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);
    }

    @Test
    @DisplayName("IISCaseberryLoggingObjectsApplicationLog model test.")
    void IISCaseberryLoggingObjectsApplicationLog_ModelTest() {
        // Создаем объект.
        em.getTransaction().begin();

        IISCaseberryLoggingObjectsApplicationLog DataObject = new IISCaseberryLoggingObjectsApplicationLog();
        DataObject.setPrimarykey(UUID.fromString("211867cf-5555-4caa-8223-e347eda465e0"));
        DataObject.setCategory("Test_Category");
        DataObject.setEventId(123);
        DataObject.setPriority(321);
        DataObject.setSeverity("Test_Severity");
        DataObject.setTitle("Test_Title");

        // Сохраняем его в БД.
        em.persist(DataObject);
        em.getTransaction().commit();

        assertNotNull(DataObject);

        // Создаем запрос на обновление.
        CriteriaUpdate<IISCaseberryLoggingObjectsApplicationLog> update = cb.createCriteriaUpdate(IISCaseberryLoggingObjectsApplicationLog.class);
        Root e = update.from(IISCaseberryLoggingObjectsApplicationLog.class);

        update.set("Title", "Test_Title_Updated");
        update.where(cb.greaterThanOrEqualTo(e.get("Title"), "Test_Title"));

        // Обновляем объект.
        em.getTransaction().begin();
        int execRes = this.em.createQuery(update).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);

        // Создаем запрос на удаление.
        CriteriaDelete<IISCaseberryLoggingObjectsApplicationLog> delete = cb.createCriteriaDelete(IISCaseberryLoggingObjectsApplicationLog.class);
        delete.where(cb.equal(e.get("primarykey"), UUID.fromString("211867cf-5555-4caa-8223-e347eda465e0")));

        // Удаляем объект.
        em.getTransaction().begin();
        execRes = this.em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);
    }

    @Test
    @DisplayName("Map model test.")
    void Map_ModelTest() {
        // Создаем объект.
        em.getTransaction().begin();

        Flexberry.GIS.model.Map DataObject = new Flexberry.GIS.model.Map();
        DataObject.setPrimarykey(UUID.fromString("211867cf-5555-4caa-8223-e347eda465e0"));
        DataObject.setName("Test_Name");

        // Сохраняем его в БД.
        em.persist(DataObject);
        em.getTransaction().commit();

        assertNotNull(DataObject);

        // Создаем запрос на обновление.
        CriteriaUpdate<Flexberry.GIS.model.Map> update = cb.createCriteriaUpdate(Flexberry.GIS.model.Map.class);
        Root e = update.from(Flexberry.GIS.model.Map.class);

        update.set("Name", "Test_Name_Updated");
        update.where(cb.greaterThanOrEqualTo(e.get("Name"), "Test_Name"));

        // Обновляем объект.
        em.getTransaction().begin();
        int execRes = this.em.createQuery(update).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);

        // Создаем запрос на удаление.
        CriteriaDelete<Flexberry.GIS.model.Map> delete = cb.createCriteriaDelete(Flexberry.GIS.model.Map.class);
        delete.where(cb.equal(e.get("primarykey"), UUID.fromString("211867cf-5555-4caa-8223-e347eda465e0")));

        // Удаляем объект.
        em.getTransaction().begin();
        execRes = this.em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);
    }

    @Test
    @DisplayName("NewPlatformFlexberryServicesLock model test.")
    void NewPlatformFlexberryServicesLock_ModelTest() {
        // Создаем объект.
        em.getTransaction().begin();

        NewPlatformFlexberryServicesLock DataObject = new NewPlatformFlexberryServicesLock();
        DataObject.setUserName("Test_Name");
        DataObject.setLockKey("Test_LockKey");
        DataObject.setLockDate(new Date(2023, 1, 1, 12, 1, 59));

        // Сохраняем его в БД.
        em.persist(DataObject);
        em.getTransaction().commit();

        assertNotNull(DataObject);

        // Создаем запрос на обновление.
        CriteriaUpdate<NewPlatformFlexberryServicesLock> update = cb.createCriteriaUpdate(NewPlatformFlexberryServicesLock.class);
        Root e = update.from(NewPlatformFlexberryServicesLock.class);

        update.set("UserName", "Test_Name_Updated");
        update.where(cb.greaterThanOrEqualTo(e.get("UserName"), "Test_Name"));

        // Обновляем объект.
        em.getTransaction().begin();
        int execRes = this.em.createQuery(update).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);

        // Создаем запрос на удаление.
        CriteriaDelete<NewPlatformFlexberryServicesLock> delete = cb.createCriteriaDelete(NewPlatformFlexberryServicesLock.class);
        delete.where(cb.equal(e.get("UserName"), "Test_Name_Updated"));

        // Удаляем объект.
        em.getTransaction().begin();
        execRes = this.em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();

        assertEquals(execRes, 1);
    }
}