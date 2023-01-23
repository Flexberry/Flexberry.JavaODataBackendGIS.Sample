package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

/**
 * Entity implementation class for Entity: DataLinkParameter
 */
@Entity(name = "NewPlatformFlexberry.GISDataLinkParameter")
@Table(schema = "public", name = "DataLinkParameter")
public class DataLinkParameter {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "ObjectField")
    private String objectfield;

    @Column(name = "LayerField")
    private String layerfield;

    @Column(name = "Expression")
    private String expression;

    @Column(name = "LinkField")
    private Boolean linkfield;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "Link")
    @Convert("Link")
    @Column(name = "Link", length = 16, unique = true, nullable = false)
    private UUID _linkid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "Link", insertable = false, updatable = false)
    private DataLink link;


    public DataLinkParameter() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public String getObjectField() {
      return objectfield;
    }

    public void setObjectField(String objectfield) {
      this.objectfield = objectfield;
    }

    public String getLayerField() {
      return layerfield;
    }

    public void setLayerField(String layerfield) {
      this.layerfield = layerfield;
    }

    public String getExpression() {
      return expression;
    }

    public void setExpression(String expression) {
      this.expression = expression;
    }

    public Boolean getLinkField() {
      return linkfield;
    }

    public void setLinkField(Boolean linkfield) {
      this.linkfield = linkfield;
    }


}