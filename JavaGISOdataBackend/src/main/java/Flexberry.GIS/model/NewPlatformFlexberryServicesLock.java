package Flexberry.GIS.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity implementation class for Entity: NewPlatformFlexberryServicesLock
 */
@Entity(name = "NewPlatformFlexberryServicesLock")
@Table(schema = "public", name = "stormnetlockdata")
public class NewPlatformFlexberryServicesLock {

    @Id
    @Column(name = "LockKey", unique = true, nullable = false, length = -1)
    private String lockKey;

    @Column(name = "UserName", nullable = false, length = -1)
    private String userName;
	
	@Column(name = "LockDate", nullable = true)
    private Date lockDate;

    public NewPlatformFlexberryServicesLock() {
        super();
    }

	public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
	
	public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }
}