package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDToStringConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

import java.util.List;

/**
 * Entity implementation class for Entity: LinkMetadata
 */
@Entity(name = "NewPlatformFlexberryGISLinkMetadata")
@Table(schema = "public", name = "LinkMetadata")
public class LinkMetadata {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;

    @Column(name = "AllowShow")
    private Boolean allowShow;

    @Column(name = "CreateTime")
    private java.sql.Timestamp createTime;

    @Column(name = "Creator", length = 255)
    private String creator;

    @Column(name = "EditTime")
    private java.sql.Timestamp editTime;

    @Column(name = "Editor", length = 255)
    private String editor;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "MapObjectSetting")
    @Convert("MapObjectSetting")
    @Column(name = "MapObjectSetting", unique = true, nullable = false)
    private String _mapobjectsettingid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MapObjectSetting", insertable = false, updatable = false)
    private MapObjectSetting mapobjectsetting;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "Layer")
    @Convert("Layer")
    @Column(name = "Layer", unique = true, nullable = false)
    private String _layerid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "Layer", insertable = false, updatable = false)
    private LayerMetadata layer;

    @OneToMany(mappedBy = "layerLink", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ParameterMetadata> parametermetadatas;


    public LinkMetadata() {
        super();
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public Boolean getAllowShow() {
      return allowShow;
    }

    public void setAllowShow(Boolean allowshow) {
      this.allowShow = allowshow;
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

    public MapObjectSetting getMapObjectSetting() {
        return mapobjectsetting;
    }

    public void setMapObjectSetting(MapObjectSetting mapObjectSetting) {
        this.mapobjectsetting = mapObjectSetting;

        if (mapObjectSetting != null) {
            this._mapobjectsettingid = mapobjectsetting.getPrimarykey();
        }
    }

    public LayerMetadata getLayer() {
        return layer;
    }

    public void setLayer(LayerMetadata layer) {
        this.layer = layer;

        if (layer != null) {
            this._layerid = layer.getPrimarykey();
        }
    }
}