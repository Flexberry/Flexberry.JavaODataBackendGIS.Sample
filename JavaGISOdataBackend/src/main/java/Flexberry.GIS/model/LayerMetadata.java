package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import java.util.List;

/**
 * Entity implementation class for Entity: LayerMetadata
 */
@Entity(name = "NewPlatformFlexberry.GISLayerMetadata")
@Table(schema = "public", name = "LayerMetadata")
public class LayerMetadata {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "KeyWords")
    private String keywords;

    @Column(name = "AnyText")
    private String anytext;

    @Column(name = "Type")
    private String type;

    @Column(name = "Settings")
    private String settings;

    @Column(name = "Scale")
    private Integer scale;

    @Column(name = "CoordinateReferenceSystem")
    private String coordinatereferencesystem;

    @Column(name = "BoundingBox")
    private String boundingbox;

    @Column(name = "AdditionalData")
    private String additionaldata;

    @Column(name = "CreateTime")
    private String createtime;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "EditTime")
    private String edittime;

    @Column(name = "Editor")
    private String editor;

    @OneToMany(mappedBy = "layer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LinkMetadata> linkmetadatas;


    public LayerMetadata() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
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
      return keywords;
    }

    public void setKeyWords(String keywords) {
      this.keywords = keywords;
    }

    public String getAnyText() {
      return anytext;
    }

    public void setAnyText(String anytext) {
      this.anytext = anytext;
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
      return coordinatereferencesystem;
    }

    public void setCoordinateReferenceSystem(String coordinatereferencesystem) {
      this.coordinatereferencesystem = coordinatereferencesystem;
    }

    public String getBoundingBox() {
      return boundingbox;
    }

    public void setBoundingBox(String boundingbox) {
      this.boundingbox = boundingbox;
    }

    public String getAdditionalData() {
      return additionaldata;
    }

    public void setAdditionalData(String additionaldata) {
      this.additionaldata = additionaldata;
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