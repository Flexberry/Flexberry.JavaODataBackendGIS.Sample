package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import java.util.List;

/**
 * Entity implementation class for Entity: Map
 */
@Entity(name = "NewPlatformFlexberry.GISMap")
@Table(schema = "public", name = "Map")
public class Map {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "CreateTime")
    private String createtime;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "EditTime")
    private String edittime;

    @Column(name = "Editor")
    private String editor;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "KeyWords")
    private String keywords;

    @Column(name = "AnyText")
    private String anytext;

    @Column(name = "Lat")
    private Double lat;

    @Column(name = "Lng")
    private Double lng;

    @Column(name = "Zoom")
    private Double zoom;

    @Column(name = "Public")
    private Boolean $public;

    @Column(name = "Scale")
    private Integer scale;

    @Column(name = "CoordinateReferenceSystem")
    private String coordinatereferencesystem;

    @Column(name = "BoundingBox")
    private String boundingbox;

    @Column(name = "Owner")
    private String owner;

    @Column(name = "Picture")
    private String picture;

    @Column(name = "EditTimeMapLayers")
    private String edittimemaplayers;

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MapLayer> maplayers;


    public Map() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
        return primarykey;
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

    public Double getLat() {
      return lat;
    }

    public void setLat(Double lat) {
      this.lat = lat;
    }

    public Double getLng() {
      return lng;
    }

    public void setLng(Double lng) {
      this.lng = lng;
    }

    public Double getZoom() {
      return zoom;
    }

    public void setZoom(Double zoom) {
      this.zoom = zoom;
    }

    public Boolean getPublic() {
      return $public;
    }

    public void setPublic(Boolean $public) {
      this.$public = $public;
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

    public String getOwner() {
      return owner;
    }

    public void setOwner(String owner) {
      this.owner = owner;
    }

    public String getPicture() {
      return picture;
    }

    public void setPicture(String picture) {
      this.picture = picture;
    }

    public String getEditTimeMapLayers() {
      return edittimemaplayers;
    }

    public void setEditTimeMapLayers(String edittimemaplayers) {
      this.edittimemaplayers = edittimemaplayers;
    }


}