package Flexberry.GIS.model;

import Flexberry.GIS.utils.PGgeometryConverter;
import Flexberry.GIS.utils.UUIDToStringConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

import java.util.List;

/**
 * Entity implementation class for Entity: MapLayer
 */
@Entity(name = "NewPlatformFlexberryGISMapLayer")
@Table(schema = "public", name = "MapLayer")
public class MapLayer {

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

    @Column(name = "Index")
    private Integer index;

    @Column(name = "Visibility")
    private Boolean visibility;

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

    @Column(name = "Public")
    private Boolean Public = false;

    @Column(name = "Owner", length = 255)
    private String owner;

    @Column(name = "SecurityKey", length = 255)
    private String securityKey;

    @Column(name = "CreateTime")
    private java.sql.Timestamp createTime;

    @Column(name = "Creator", length = 255)
    private String creator;

    @Column(name = "EditTime")
    private java.sql.Timestamp editTime;

    @Column(name = "Editor", length = 255)
    private String editor;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "Parent")
    @Convert("Parent")
    @Column(name = "Parent", unique = true, nullable = false)
    private String _parentid;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "Parent", insertable = false, updatable = false)
    private MapLayer parent;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "MapID")
    @Convert("MapID")
    @Column(name = "Map", unique = true, nullable = false)
    private String _mapid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "Map", insertable = false, updatable = false)
    private Map map;

    @OneToMany(mappedBy = "layer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LayerLink> layerLink;

    public MapLayer() {
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

    public Integer getIndex() {
      return index;
    }

    public void setIndex(Integer index) {
      this.index = index;
    }

    public Boolean getVisibility() {
      return visibility;
    }

    public void setVisibility(Boolean visibility) {
      this.visibility = visibility;
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

    public Boolean getPublic() {
      return Public;
    }

    public void setPublic(Boolean $public) {
      this.Public = $public;
    }

    public String getOwner() {
      return owner;
    }

    public void setOwner(String owner) {
      this.owner = owner;
    }

    public String getSecurityKey() {
      return securityKey;
    }

    public void setSecurityKey(String securitykey) {
      this.securityKey = securitykey;
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

    public MapLayer getParent() {
        return parent;
    }

    public void setParent(MapLayer parent) {
        this.parent = parent;

        if (parent != null) {
            this._parentid = parent.getPrimarykey();
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;

        if (map != null) {
            this._mapid = map.getPrimarykey();
        }
    }

    public List<LayerLink> getLayerLink() {
        return layerLink;
    }

    public void setLayerLink(List<LayerLink> layerLink) {
        this.layerLink = layerLink;
    }

}