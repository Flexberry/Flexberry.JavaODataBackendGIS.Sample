package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entity implementation class for Entity: FlexberryAdvLimit
 */
@Entity(name = "FlexberryAdvLimit")
@Table(schema = "public", name = "STORMAdvLimit")
public class FlexberryAdvLimit {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;
	
	@Column(name = "User", nullable = true)
    private String user;

    @Column(name = "Module", nullable = true)
    private String module;

    @Column(name = "Name", nullable = true)
    private String name;

    @Column(name = "Value", nullable = true, length = -1)
    private String value;
	
	@Column(name = "HotKeyData", nullable = true)
    private Integer hotKeyData;

    public FlexberryAdvLimit() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
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
