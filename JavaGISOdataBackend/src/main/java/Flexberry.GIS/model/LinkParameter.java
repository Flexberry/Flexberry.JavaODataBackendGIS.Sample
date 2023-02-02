package Flexberry.GIS.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entity implementation class for Entity: LinkParameter
 */
@Entity(name = "NewPlatformFlexberryGISLinkParameter")
@Table(schema = "public", name = "LinkParameter")
public class LinkParameter {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "ObjectField")
    private String objectField;

    @Column(name = "LayerField")
    private String layerField;

    @Column(name = "Expression")
    private String expression;

    @Column(name = "QueryKey")
    private String queryKey;

    @Column(name = "LinkField")
    private Boolean linkField;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "LayerLink")
    @Convert("LayerLink")
    @Column(name = "LayerLink", length = 16, unique = true, nullable = false)
    private UUID _layerlinkid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "LayerLink", insertable = false, updatable = false)
    private LayerLink layerLink;


    public LinkParameter() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public String getObjectField() {
      return objectField;
    }

    public void setObjectField(String objectfield) {
      this.objectField = objectfield;
    }

    public String getLayerField() {
      return layerField;
    }

    public void setLayerField(String layerfield) {
      this.layerField = layerfield;
    }

    public String getExpression() {
      return expression;
    }

    public void setExpression(String expression) {
      this.expression = expression;
    }

    public String getQueryKey() {
      return queryKey;
    }

    public void setQueryKey(String querykey) {
      this.queryKey = querykey;
    }

    public Boolean getLinkField() {
      return linkField;
    }

    public void setLinkField(Boolean linkfield) {
      this.linkField = linkfield;
    }


}