package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDToStringConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: LinkParameter
 */
@Entity(name = "NewPlatformFlexberryGISLinkParameter")
@Table(schema = "public", name = "LinkParameter")
public class LinkParameter {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;

    @Column(name = "ObjectField", length = 255)
    private String objectField;

    @Column(name = "LayerField", length = 255)
    private String layerField;

    @Column(name = "Expression", length = 255)
    private String expression;

    @Column(name = "QueryKey", length = 255)
    private String queryKey;

    @Column(name = "LinkField")
    private Boolean linkField;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "LayerLink")
    @Convert("LayerLink")
    @Column(name = "LayerLink", unique = true, nullable = false)
    private String _layerlinkid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "LayerLink", insertable = false, updatable = false)
    private LayerLink layerLink;


    public LinkParameter() {
        super();
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey() {
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

    public LayerLink getLayerLink() {
        return layerLink;
    }

    public void setLayerLink(LayerLink layerLink) {
        this.layerLink = layerLink;

        if (layerLink != null) {
            this._layerlinkid = layerLink.getPrimarykey();
        }
    }
}