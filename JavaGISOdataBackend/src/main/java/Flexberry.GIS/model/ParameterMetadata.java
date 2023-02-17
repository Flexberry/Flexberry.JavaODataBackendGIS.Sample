package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDToStringConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ParameterMetadata
 */
@Entity(name = "NewPlatformFlexberryGISParameterMetadata")
@Table(schema = "public", name = "ParameterMetadata")
public class ParameterMetadata {

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

    @Column(name = "CreateTime")
    private java.sql.Timestamp createTime;

    @Column(name = "Creator", length = 255)
    private String creator;

    @Column(name = "EditTime")
    private java.sql.Timestamp editTime;

    @Column(name = "Editor", length = 255)
    private String editor;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "LayerLink")
    @Convert("LayerLink")
    @Column(name = "LayerLink", unique = true, nullable = false)
    private String _layerlinkid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "LayerLink", insertable = false, updatable = false)
    private LinkMetadata layerLink;


    public ParameterMetadata() {
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

    public java.sql.Timestamp getCreateTime() {
      return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createtime) {
      this.createTime = createtime;
    }

    public String getCreator() {
      return creator;
    }

    public void setCreator(String creator) {
      this.creator = creator;
    }

    public java.sql.Timestamp getEditTime() {
      return editTime;
    }

    public void setEditTime(java.sql.Timestamp edittime) {
      this.editTime = edittime;
    }

    public String getEditor() {
      return editor;
    }

    public void setEditor(String editor) {
      this.editor = editor;
    }

    public LinkMetadata getLayerLink() {
        return layerLink;
    }

    public void setLayerLink(LinkMetadata layerLink) {
        this.layerLink = layerLink;

        if (layerLink != null) {
            this._layerlinkid = layerLink.getPrimarykey();
        }
    }
}