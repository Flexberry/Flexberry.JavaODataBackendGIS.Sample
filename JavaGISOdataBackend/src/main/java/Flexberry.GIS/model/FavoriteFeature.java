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
    private java.sql.Timestamp createTime;

    @Column(name = "Creator", nullable = true)
    private String creator;

    @Column(name = "EditTime", nullable = true)
    private java.sql.Timestamp editTime;

    @Column(name = "Editor", nullable = true)
    private String editor;

    @Converter(converterClass = UUIDConverter.class, name = "ObjectKey")
    @Convert("ObjectKey")
    @Column(name = "ObjectKey", nullable = false)
    private UUID objectKey;

    @Converter(converterClass = UUIDConverter.class, name = "ObjectLayerKey")
    @Convert("ObjectLayerKey")
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

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
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

    public void setEditTime(java.sql.Timestamp editTime) {
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
