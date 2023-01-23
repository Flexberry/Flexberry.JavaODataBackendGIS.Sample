package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

/**
 * Entity implementation class for Entity: LinkParameter
 */
@Entity(name = "NewPlatformFlexberry.GISLinkParameter")
@Table(schema = "public", name = "LinkParameter")
public class LinkParameter {

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

    @Column(name = "QueryKey")
    private String querykey;

    @Column(name = "LinkField")
    private Boolean linkfield;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "LayerLink")
    @Convert("LayerLink")
    @Column(name = "LayerLink", length = 16, unique = true, nullable = false)
    private UUID _layerlinkid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "LayerLink", insertable = false, updatable = false)
    private LayerLink layerlink;


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

    public String getQueryKey() {
      return querykey;
    }

    public void setQueryKey(String querykey) {
      this.querykey = querykey;
    }

    public Boolean getLinkField() {
      return linkfield;
    }

    public void setLinkField(Boolean linkfield) {
      this.linkfield = linkfield;
    }


}