package Flexberry.GIS.model;

import Flexberry.GIS.utils.PGgeometryConverter;
import Flexberry.GIS.utils.UUIDToStringConverter;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

import java.util.List;

/**
 * Entity implementation class for Entity: LayerMetadata
 */
@Entity(name = "NewPlatformFlexberryGISLayerMetadata")
@Table(schema = "public", name = "LayerMetadata")
public class LayerMetadata {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;

    @Column(name = "Name", length = 255)
    private String name;

    @Column(name = "Description", length = -1)
    private String description;

    @Column(name = "KeyWords", length = -1)
    private String keyWords;

    @Column(name = "AnyText", length = -1)
    private String anyText;

    @Column(name = "Type", length = 255)
    private String type;

    @Column(name = "Settings", length = -1)
    private String settings;

    @Column(name = "Scale")
    private Integer scale;

    @Column(name = "CoordinateReferenceSystem", length = 255)
    private String coordinateReferenceSystem;

    @Converter(converterClass = PGgeometryConverter.class, name = "BoundingBox")
    @Convert("BoundingBox")
    @Column(name = "BoundingBox", length = -1)
    private String boundingBox;

    @Column(name = "AdditionalData", length = -1)
    private String additionaldata;

    @Column(name = "CreateTime")
    private java.sql.Timestamp createTime;

    @Column(name = "Creator", length = 255)
    private String creator;

    @Column(name = "EditTime")
    private java.sql.Timestamp editTime;

    @Column(name = "Editor", length = 255)
    private String editor;

    @OneToMany(mappedBy = "layer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LinkMetadata> linkMetadata;


    public LayerMetadata() {
        super();
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getKeyWords() {
      return keyWords;
    }

    public void setKeyWords(String keywords) {
      this.keyWords = keywords;
    }

    public String getAnyText() {
      return anyText;
    }

    public void setAnyText(String anytext) {
      this.anyText = anytext;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getSettings() {
      return settings;
    }

    public void setSettings(String settings) {
      this.settings = settings;
    }

    public Integer getScale() {
      return scale;
    }

    public void setScale(Integer scale) {
      this.scale = scale;
    }

    public String getCoordinateReferenceSystem() {
      return coordinateReferenceSystem;
    }

    public void setCoordinateReferenceSystem(String coordinatereferencesystem) {
      this.coordinateReferenceSystem = coordinatereferencesystem;
    }

    public String getBoundingBox() {
      return boundingBox;
    }

    public void setBoundingBox(String boundingbox) {
      this.boundingBox = boundingbox;
    }

    public String getAdditionalData() {
      return additionaldata;
    }

    public void setAdditionalData(String additionaldata) {
      this.additionaldata = additionaldata;
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

    public List<LinkMetadata> getLinkMetadata() {
        return linkMetadata;
    }

    public void setLinkMetadata(List<LinkMetadata> linkMetadata) {
        this.linkMetadata = linkMetadata;
    }
}