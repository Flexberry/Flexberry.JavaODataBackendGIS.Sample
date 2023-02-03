package Flexberry.GIS.model;

import Flexberry.GIS.utils.UUIDConverter;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity(name = "FavoriteFeature")
@Table(schema = "public", name = "FavoriteFeature")
public class FavoriteFeature {

    @Id
    @Converter(converterClass = UUIDConverter.class, name = "primarykey")
    @Convert("primarykey")
    @Column(name = "primarykey", length = 16, unique = true, nullable = false)
    private UUID primarykey;

    @Column(name = "CreateTime", nullable = true)
    private Date createTime;

    @Column(name = "Creator", nullable = true)
    private String creator;

    @Column(name = "EditTime", nullable = true)
    private Date editTime;

    @Column(name = "Editor", nullable = true)
    private String editor;

    @Column(name = "ObjectKey", nullable = false)
    private UUID objectKey;

    @Column(name = "ObjectLayerKey", nullable = false)
    private UUID objectLayerKey;

    @Column(name = "UserKey", nullable = false)
    private String userKey;

    public FavoriteFeature() {
        super();
    }

    public void setPrimarykey(UUID primarykey) {
        this.primarykey = primarykey;
    }

    public UUID getPrimarykey() {
        return primarykey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public UUID getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(UUID objectKey) {
        this.objectKey = objectKey;
    }

    public UUID getObjectLayerKey() {
        return objectLayerKey;
    }

    public void setObjectLayerKey(UUID objectLayerKey) {
        this.objectLayerKey = objectLayerKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
