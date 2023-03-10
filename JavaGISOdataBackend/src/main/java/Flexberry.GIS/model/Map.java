package Flexberry.GIS.model;

import Flexberry.GIS.utils.PGgeometryConverter;
import Flexberry.GIS.utils.UUIDToStringConverter;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

import java.util.List;

/**
 * Entity implementation class for Entity: Map
 */
@Entity(name = "NewPlatformFlexberryGISMap")
@Table(schema = "public", name = "Map")
public class Map {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;

    @Column(name = "CreateTime")
    private java.sql.Timestamp createTime;

    @Column(name = "Creator", length = 255)
    private String creator;

    @Column(name = "EditTime")
    private java.sql.Timestamp editTime;

    @Column(name = "Editor", length = 255)
    private String editor;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Description", length = -1)
    private String description;

    @Column(name = "KeyWords", length = -1)
    private String keyWords;

    @Column(name = "AnyText", length = -1)
    private String anyText;

    @Column(name = "Lat")
    private Double lat;

    @Column(name = "Lng")
    private Double lng;

    @Column(name = "Zoom")
    private Double zoom;

    @Column(name = "Public")
    private Boolean Public = false;

    @Column(name = "Scale")
    private Integer scale;

    @Column(name = "CoordinateReferenceSystem", length = 255)
    private String coordinateReferenceSystem;

    @Converter(converterClass = PGgeometryConverter.class, name = "BoundingBox")
    @Convert("BoundingBox")
    @Column(name = "BoundingBox", length = -1)
    private String boundingBox;

    @Column(name = "Owner", length = 255)
    private String owner;

    @Column(name = "Picture", length = 255)
    private String picture;

    @Column(name = "EditTimeMapLayers")
    private java.sql.Timestamp editTimeMapLayers;

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MapLayer> mapLayer;


    public Map() {
        super();
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey() {
        return primarykey;
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
      return Public;
    }

    public void setPublic(Boolean Public) {
      this.Public = Public;
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

    public java.sql.Timestamp getEditTimeMapLayers() {
      return editTimeMapLayers;
    }

    public void setEditTimeMapLayers(java.sql.Timestamp edittimemaplayers) {
      this.editTimeMapLayers = edittimemaplayers;
    }

    public List<MapLayer> getMapLayer() {
        return mapLayer;
    }

    public void setMapLayer(List<MapLayer> mapLayer) {
        this.mapLayer = mapLayer;
    }
}