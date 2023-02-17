package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDToStringConverter;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity implementation class for Entity: IISCaseberryLoggingObjectsApplicationLog
 */
@Entity(name = "IISCaseberryLoggingObjectsApplicationLog")
@Table(schema = "public", name = "applicationlog")
public class IISCaseberryLoggingObjectsApplicationLog {

    @Id
    @Converter(converterClass = UUIDToStringConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", unique = true, nullable = false)
    private String primarykey;

    @Column(name = "Category", nullable = true, length = 64)
    private String category;
		
	@Column(name = "EventId", nullable = true)
    private Integer eventId;
	
	@Column(name = "Priority", nullable = true)
    private Integer priority;
		
	@Column(name = "Severity", nullable = true, length = 32)
    private String severity;
	
	@Column(name = "Title", nullable = true, length = 256)
    private String title;
	
	@Column(name = "Timestamp", nullable = true)
    private Date timestamp;
	
	@Column(name = "MachineName", nullable = true, length = 32)
    private String machineName;
	
	@Column(name = "AppDomainName", nullable = true, length = 512)
    private String appDomainName;
	
	@Column(name = "ProcessId", nullable = true, length = 256)
    private String processId;
	
	@Column(name = "ProcessName", nullable = true, length = 512)
    private String processName;
	
	@Column(name = "ThreadName", nullable = true, length = 512)
    private String threadName;
		
	@Column(name = "Win32ThreadId", nullable = true, length = 128)
    private String win32ThreadId;
	
	@Column(name = "Message", nullable = true, length = 2500)
    private String message;
	
	@Column(name = "FormattedMessage", nullable = true, length = -1)
    private String formattedMessage;
	
	public IISCaseberryLoggingObjectsApplicationLog() {
        super();
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
	
	public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
	
	public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
	
	public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
	
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
	
	public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp =timestamp;
    }
	
	public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }
	
	public String getAppDomainName() {
        return appDomainName;
    }

    public void setAppDomainName(String appDomainName) {
        this.appDomainName = appDomainName;
    }
	
	public String getProcessId() {
        return processId;
    }

    public void setAppProcessId(String processId) {
        this.processId = processId;
    }
	
	public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
	
	public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
	
	public String getWin32ThreadId() {
        return win32ThreadId;
    }

    public void setWin32ThreadId(String win32ThreadId) {
        this.win32ThreadId = win32ThreadId;
    }
	
	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
	
	public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }
}