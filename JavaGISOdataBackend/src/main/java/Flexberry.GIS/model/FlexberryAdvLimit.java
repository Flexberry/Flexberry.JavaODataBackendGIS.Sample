package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDToStringConverter;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FlexberryAdvLimit
 */
@Entity(name = "FlexberryAdvLimit")
@Table(schema = "public", name = "STORMAdvLimit")
public class FlexberryAdvLimit {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;
	
	@Column(name = "\"User\"", nullable = true, length = 255)
    private String user;

    @Column(name = "Module", nullable = true, length = 255)
    private String module;

    @Column(name = "Name", nullable = true, length = 255)
    private String name;

    @Column(name = "Value", nullable = true, length = -1)
    private String value;
	
	@Column(name = "HotKeyData", nullable = true)
    private Integer hotKeyData;

    public FlexberryAdvLimit() {
        super();
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey() {
        return primarykey;
    }
	
	public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
	
	public Integer getHotKeyData() {
        return hotKeyData;
    }

    public void setHotKeyData(Integer hotKeyData) {
        this.hotKeyData = hotKeyData;
    }
}
