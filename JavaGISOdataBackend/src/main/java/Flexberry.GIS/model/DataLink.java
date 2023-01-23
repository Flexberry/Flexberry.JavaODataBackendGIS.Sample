package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import java.util.List;

/**
 * Entity implementation class for Entity: DataLink
 */
@Entity(name = "NewPlatformFlexberry.GISDataLink")
@Table(schema = "public", name = "DataLink")
public class DataLink {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "ClearWithoutLink")
    private Boolean clearwithoutlink;

    @Column(name = "LayerTable")
    private String layertable;

    @Column(name = "CreateObject")
    private Boolean createobject;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "MapObjectSetting")
    @Convert("MapObjectSetting")
    @Column(name = "MapObjectSetting", length = 16, unique = true, nullable = false)
    private UUID _mapobjectsettingid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MapObjectSetting", insertable = false, updatable = false)
    private MapObjectSetting mapobjectsetting;

    @OneToMany(mappedBy = "link", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DataLinkParameter> datalinkparameters;


    public DataLink() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public Boolean getClearWithoutLink() {
      return clearwithoutlink;
    }

    public void setClearWithoutLink(Boolean clearwithoutlink) {
      this.clearwithoutlink = clearwithoutlink;
    }

    public String getLayerTable() {
      return layertable;
    }

    public void setLayerTable(String layertable) {
      this.layertable = layertable;
    }

    public Boolean getCreateObject() {
      return createobject;
    }

    public void setCreateObject(Boolean createobject) {
      this.createobject = createobject;
    }


}