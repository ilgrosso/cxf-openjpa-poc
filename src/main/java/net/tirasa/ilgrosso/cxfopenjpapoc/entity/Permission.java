package net.tirasa.ilgrosso.cxfopenjpapoc.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Base permission description
 *
 * @see OAuthAuthorizationData
 */
@MappedSuperclass
public class Permission implements Serializable {

    private static final long serialVersionUID = 8988574955042726083L;

    private String permission;

    private String description;

    private boolean isDefaultPermission;

    private boolean invisibleToClient;

    public Permission() {

    }

    public Permission(String permission, String description) {
        this.description = description;
        this.permission = permission;
    }

    /**
     * Gets the permission description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the permission description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the permission value such as "read_calendar"
     *
     * @return the value
     */
    @Id
    public String getPermission() {
        return permission;
    }

    /**
     * Sets the permission value such as "read_calendar"
     *
     * @param permission the permission value
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Indicates if this permission has been allocated by default or not.
     * Authorization View handlers may use this property to optimize the way the user selects the
     * scopes.
     * For example, assume that read', 'add' and 'update' scopes are supported and the
     * 'read' scope is always allocated. This can be presented at the UI level as follows:
     * the read-only check-box control will represent a 'read' scope and a user will be able to
     * optionally select 'add' and/or 'update' scopes, in addition to the default 'read' one.
     *
     * @param isDefault true if the permission has been allocated by default
     */
    public void setDefaultPermission(boolean isDefault) {
        this.isDefaultPermission = isDefault;
    }

    public boolean isDefaultPermission() {
        return isDefaultPermission;
    }

    @Deprecated
    @Transient
    public boolean isDefault() {
        return isDefaultPermission;
    }

    public boolean isInvisibleToClient() {
        return invisibleToClient;
    }

    /**
     * Set the visibility status; by default all the scopes approved by a user can
     * be optionally reported to the client in access token responses. Some scopes may need
     * to stay 'invisible' to client.
     *
     * @param invisibleToClient
     */
    public void setInvisibleToClient(boolean invisibleToClient) {
        this.invisibleToClient = invisibleToClient;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Permission)) {
            return false;
        }

        Permission that = (Permission) object;
        if (!this.getPermission().equals(that.getPermission())) {
            return false;
        }
        if (this.getDescription() != null && that.getDescription() == null
                || this.getDescription() == null && that.getDescription() != null
                || this.getDescription() != null && !this.getDescription().equals(that.getDescription())) {
            return false;
        }
        return !(this.isInvisibleToClient() != that.isInvisibleToClient()
                || this.isDefaultPermission() != that.isDefaultPermission());
    }

    @Override
    public int hashCode() {
        int hashCode = getPermission().hashCode();
        if (getDescription() != null) {
            hashCode = 31 * hashCode + getDescription().hashCode();
        }
        hashCode = 31 * hashCode + Boolean.valueOf(isInvisibleToClient()).hashCode();
        hashCode = 31 * hashCode + Boolean.valueOf(isDefaultPermission()).hashCode();

        return hashCode;
    }
}
