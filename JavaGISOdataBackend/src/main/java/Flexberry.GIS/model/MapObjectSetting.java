package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDToStringConverter;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MapObjectSetting
 */
@Entity(name = "NewPlatformFlexberryGISMapObjectSetting")
@Table(schema = "public", name = "MapObjectSetting")
public class MapObjectSetting {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;

    @Column(name = "TypeName", length = 255)
    private String typeName;

    @Column(name = "ListForm", length = 255)
    private String listForm;

    @Column(name = "EditForm", length = 255)
    private String editForm;

    @Column(name = "Title", length = 255)
    private String title;

    @Column(name = "MultEditForm", length = 255)
    private String multEditForm;

    @Column(name = "CreateTime")
    private java.sql.Timestamp createTime;

    @Column(name = "Creator", length = 255)
    private String creator;

    @Column(name = "EditTime")
    private java.sql.Timestamp editTime;

    @Column(name = "Editor", length = 255)
    private String editor;

    @EdmIgnore
    @Converter(converterClass = UUIDToStringConverter.class, name = "DefaultMap")
    @Convert("DefaultMap")
    @Column(name = "DefaultMap", unique = true, nullable = false)
    private String _defaultmapid;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "DefaultMap", insertable = false, updatable = false)
    private Map defaultMap;


    public MapObjectSetting() {
        super();
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public String getTypeName() {
      return typeName;
    }

    public void setTypeName(String typename) {
      this.typeName = typename;
    }

    public String getListForm() {
      return listForm;
    }

    public void setListForm(String listform) {
      this.listForm = listform;
    }

    public String getEditForm() {
      return editForm;
    }

    public void setEditForm(String editform) {
      this.editForm = editform;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getMultEditForm() {
      return multEditForm;
    }

    public void setMultEditForm(String multeditform) {
      this.multEditForm = multeditform;
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

    public Map getDefaultMap() {
        return defaultMap;
    }

    public void setDefaultMap(Map defaultMap) {
        this.defaultMap = defaultMap;

        if (defaultMap != null) {
            this._defaultmapid = defaultMap.getPrimarykey();
        }
    }
}