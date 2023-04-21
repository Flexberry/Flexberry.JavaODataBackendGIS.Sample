package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDToStringConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

import java.util.List;

/**
 * Entity implementation class for Entity: LayerLink
 */
@Entity(name = "NewPlatformFlexberryGISLayerLink")
@Table(schema = "public", name = "LayerLink")
public class LayerLink {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;

    @Column(name = "AllowShow")
    private Boolean allowShow;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "MapObjectSetting")
    @Convert("MapObjectSetting")
    @Column(name = "MapObjectSetting", unique = true, nullable = false)
    private String _mapobjectsettingid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MapObjectSetting", insertable = false, updatable = false)
    private MapObjectSetting mapObjectSetting;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "Layer")
    @Convert("Layer")
    @Column(name = "Layer", unique = true, nullable = false)
    private String _layerid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "Layer", insertable = false, updatable = false)
    private MapLayer layer;

    @OneToMany(mappedBy = "layerLink", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LinkParameter> parameters;

    public LayerLink() {
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

    public MapObjectSetting getMapObjectSetting() {
        return mapObjectSetting;
    }

    public void setMapObjectSetting(MapObjectSetting mapObjectSetting) {
        this.mapObjectSetting = mapObjectSetting;

        if (mapObjectSetting != null) {
            this._mapobjectsettingid = mapObjectSetting.getPrimarykey();
        }
    }

    public MapLayer getLayer() {
        return layer;
    }

    public void setLayer(MapLayer layer) {
        this.layer = layer;

        if (layer != null) {
            this._layerid = layer.getPrimarykey();
        }
    }

    public List<LinkParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<LinkParameter> parameters) {
        this.parameters = parameters;
    }

}