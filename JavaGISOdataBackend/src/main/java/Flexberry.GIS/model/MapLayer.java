package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import java.util.List;

/**
 * Entity implementation class for Entity: MapLayer
 */
@Entity(name = "NewPlatformFlexberryGISMapLayer")
@Table(schema = "public", name = "MapLayer")
public class MapLayer {

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

    @Column(name = "Index")
    private Integer index;

    @Column(name = "Visibility")
    private Boolean visibility;

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

    @Column(name = "Public")
    private Boolean $public;

    @Column(name = "Owner")
    private String owner;

    @Column(name = "SecurityKey")
    private String securitykey;

    @Column(name = "CreateTime")
    private String createtime;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "EditTime")
    private String edittime;

    @Column(name = "Editor")
    private String editor;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "Parent")
    @Convert("Parent")
    @Column(name = "Parent", length = 16, unique = true, nullable = false)
    private UUID _parentid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "Parent", insertable = false, updatable = false)
    private MapLayer parent;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "Map")
    @Convert("Map")
    @Column(name = "Map", length = 16, unique = true, nullable = false)
    private UUID _mapid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "Map", insertable = false, updatable = false)
    private Map map;

    @OneToMany(mappedBy = "layer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LayerLink> layerlinks;


    public MapLayer() {
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

    public Boolean getPublic() {
      return $public;
    }

    public void setPublic(Boolean $public) {
      this.$public = $public;
    }

    public String getOwner() {
      return owner;
    }

    public void setOwner(String owner) {
      this.owner = owner;
    }

    public String getSecurityKey() {
      return securitykey;
    }

    public void setSecurityKey(String securitykey) {
      this.securitykey = securitykey;
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