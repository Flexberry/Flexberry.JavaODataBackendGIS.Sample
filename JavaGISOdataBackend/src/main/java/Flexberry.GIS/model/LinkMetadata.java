package Flexberry.GIS.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import java.util.List;

/**
 * Entity implementation class for Entity: LinkMetadata
 */
@Entity(name = "NewPlatformFlexberryGISLinkMetadata")
@Table(schema = "public", name = "LinkMetadata")
public class LinkMetadata {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "AllowShow")
    private Boolean allowShow;

    @Column(name = "CreateTime")
    private java.sql.Timestamp createTime;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "EditTime")
    private java.sql.Timestamp editTime;

    @Column(name = "Editor")
    private String editor;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "MapObjectSetting")
    @Convert("MapObjectSetting")
    @Column(name = "MapObjectSetting", length = 16, unique = true, nullable = false)
    private UUID _mapobjectsettingid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MapObjectSetting", insertable = false, updatable = false)
    private MapObjectSetting mapobjectsetting;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "Layer")
    @Convert("Layer")
    @Column(name = "Layer", length = 16, unique = true, nullable = false)
    private UUID _layerid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "Layer", insertable = false, updatable = false)
    private LayerMetadata layer;

    @OneToMany(mappedBy = "layerLink", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ParameterMetadata> parametermetadatas;


    public LinkMetadata() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
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


}