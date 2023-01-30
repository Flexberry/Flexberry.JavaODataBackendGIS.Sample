package Flexberry.GIS.model;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import Flexberry.GIS.utils.UUIDConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;

/**
 * Entity implementation class for Entity: NewPlatformFlexberryFlexberryUserSetting
 */
@Entity(name = "NewPlatformFlexberryFlexberryUserSetting")
@Table(schema = "public", name = "usersetting")
public class NewPlatformFlexberryFlexberryUserSetting {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "AppName", nullable = true)
    private String appName;
	
	@Column(name = "UserName", nullable = true)
    private String userName;
	
	@Converter(converterClass = UUIDConverter.class, name = "UserGuid")
    @Convert("UserGuid")
	@Column(name = "UserGuid", length = 16, unique = true, nullable = true)
    private UUID userGuid;
	
	@Column(name = "ModuleName", nullable = true)
    private String moduleName;
	
	@Converter(converterClass = UUIDConverter.class, name = "ModuleGuid")
    @Convert("ModuleGuid")
	@Column(name = "ModuleGuid", length = 16, unique = true, nullable = true)
    private UUID moduleGuid;
	
	@Column(name = "SettName", nullable = true)
    private String settName;
	
	@Converter(converterClass = UUIDConverter.class, name = "SettGuid")
    @Convert("SettGuid")
	@Column(name = "SettGuid", length = 16, unique = true, nullable = true)
    private UUID settGuid;
	
	@Column(name = "SettLastAccessTime", nullable = true)
    private Date settLastAccessTime;
	
	@Column(name = "StrVal", nullable = true)
    private String strVal;
	
	@Column(name = "TxtVal", nullable = true)
    private String txtVal;
	
	@Column(name = "IntVal", nullable = true)
    private Integer intVal;
	
	@Column(name = "BoolVal", nullable = true)
    private Boolean boolVal;
	
	@Converter(converterClass = UUIDConverter.class, name = "GuidVal")
    @Convert("GuidVal")
	@Column(name = "GuidVal", length = 16, unique = true, nullable = true)
    private UUID guidVal;
	
	@Column(name = "DecimalVal", nullable = true)
    private Double decimalVal;
	
	@Column(name = "DateTimeVal", nullable = true)
    private Date dateTimeVal;

    public NewPlatformFlexberryFlexberryUserSetting() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
	
	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
	
	public UUID getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }
	
	public String getModuleName() {
        return userName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
	
	public UUID getModuleGuid() {
        return moduleGuid;
    }

    public void setModuleGuid(UUID moduleGuid) {
        this.moduleGuid = moduleGuid;
    }
	
	public String getSettName() {
        return settName;
    }

    public void setSettName(String settName) {
        this.settName = settName;
    }
	
	public UUID getSettGuid() {
        return settGuid;
    }

    public void setSettGuid(UUID settGuid) {
        this.settGuid = settGuid;
    }
	
	public Date getSettLastAccessTime() {
        return settLastAccessTime;
    }

    public void setSettLastAccessTime(Date settLastAccessTime) {
        this.settLastAccessTime = settLastAccessTime;
    }
	
	public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }
	
	public String getTxtVal() {
        return txtVal;
    }

    public void setTxtVal(String txtVal) {
        this.txtVal = txtVal;
    }
	
	public Integer getIntVal() {
        return intVal;
    }

    public void setTxtVal(Integer intVal) {
        this.intVal = intVal;
    }
	
	public Boolean getBoolVal() {
        return boolVal;
    }

    public void setBoolVal(Boolean boolVal) {
        this.boolVal = boolVal;
    }
	
	public UUID getGuidVal() {
        return guidVal;
    }

    public void setGuidVal(UUID guidVal) {
        this.guidVal = guidVal;
    }
	
	public Double getDecimalVal() {
        return decimalVal;
    }

    public void setDecimalVal(Double decimalVal) {
        this.decimalVal = decimalVal;
    }
	
	public Date getDateTimeVal() {
        return dateTimeVal;
    }

    public void setDateTimeVal(Date dateTimeVal) {
        this.dateTimeVal = dateTimeVal;
    }
}