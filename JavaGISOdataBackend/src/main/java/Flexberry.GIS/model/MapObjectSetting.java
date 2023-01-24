package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

/**
 * Entity implementation class for Entity: MapObjectSetting
 */
@Entity(name = "NewPlatformFlexberry.GISMapObjectSetting")
@Table(schema = "public", name = "MapObjectSetting")
public class MapObjectSetting {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "TypeName")
    private String typename;

    @Column(name = "ListForm")
    private String listform;

    @Column(name = "EditForm")
    private String editform;

    @Column(name = "Title")
    private String title;

    @Column(name = "MultEditForm")
    private String multeditform;

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "DefaultMap")
    @Convert("DefaultMap")
    @Column(name = "DefaultMap", length = 16, unique = true, nullable = false)
    private UUID _defaultmapid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "DefaultMap", insertable = false, updatable = false)
    private Map defaultmap;


    public MapObjectSetting() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public String getTypeName() {
      return typename;
    }

    public void setTypeName(String typename) {
      this.typename = typename;
    }

    public String getListForm() {
      return listform;
    }

    public void setListForm(String listform) {
      this.listform = listform;
    }

    public String getEditForm() {
      return editform;
    }

    public void setEditForm(String editform) {
      this.editform = editform;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getMultEditForm() {
      return multeditform;
    }

    public void setMultEditForm(String multeditform) {
      this.multeditform = multeditform;
    }


}