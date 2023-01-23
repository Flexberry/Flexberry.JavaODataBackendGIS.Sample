package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

/**
 * Entity implementation class for Entity: ParameterMetadata
 */
@Entity(name = "NewPlatformFlexberry.GISParameterMetadata")
@Table(schema = "public", name = "ParameterMetadata")
public class ParameterMetadata {

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

    @Column(name = "CreateTime")
    private String createtime;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "EditTime")
    private String edittime;

    @Column(name = "Editor")
    private String editor;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "LayerLink")
    @Convert("LayerLink")
    @Column(name = "LayerLink", length = 16, unique = true, nullable = false)
    private UUID _layerlinkid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "LayerLink", insertable = false, updatable = false)
    private LinkMetadata layerlink;


    public ParameterMetadata() {
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

    public String getCreateTime() {
      return createtime;
    }

    public void setCreateTime(String createtime) {
      this.createtime = createtime;
    }

    public String getCreator() {
      return creator;
    }

    public void setCreator(String creator) {
      this.creator = creator;
    }

    public String getEditTime() {
      return edittime;
    }

    public void setEditTime(String edittime) {
      this.edittime = edittime;
    }

    public String getEditor() {
      return editor;
    }

    public void setEditor(String editor) {
      this.editor = editor;
    }


}