package cn.com.conversant.weizi.crm.api.dto;

import java.io.Serializable;

/**
 * Created by conversant on 2017/1/11.
 */
public class UserPermissionJson implements Serializable {
    private long user_id;
    private String user_name;
    private int user_type;
    private long user_permission_id;
    private long metadata_id;
    private int hasPermission;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public long getUser_permission_id() {
        return user_permission_id;
    }

    public void setUser_permission_id(long user_permission_id) {
        this.user_permission_id = user_permission_id;
    }

    public long getMetadata_id() {
        return metadata_id;
    }

    public void setMetadata_id(long metadata_id) {
        this.metadata_id = metadata_id;
    }

    public int getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(int hasPermission) {
        this.hasPermission = hasPermission;
    }
}
