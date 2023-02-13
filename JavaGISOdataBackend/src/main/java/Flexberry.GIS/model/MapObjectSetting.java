package Flexberry.GIS.model;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entity implementation class for Entity: MapObjectSetting
 */
@Entity(name = "NewPlatformFlexberryGISMapObjectSetting")
@Table(schema = "public", name = "MapObjectSetting")
public class MapObjectSetting {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

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

    @EdmIgnore
    @Converter(converterClass = UUIDConverter.class, name = "DefaultMap")
    @Convert("DefaultMap")
    @Column(name = "DefaultMap", length = 16, unique = true, nullable = false)
    private UUID _defaultmapid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "DefaultMap", insertable = false, updatable = false)
    private Map defaultMap;


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


}