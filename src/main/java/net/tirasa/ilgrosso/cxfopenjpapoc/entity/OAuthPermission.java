package net.tirasa.ilgrosso.cxfopenjpapoc.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Provides the complete information about a given opaque permission.
 * For example, a scope parameter such as "read_calendar" will be
 * translated into the instance of this class in order to provide
 * the human readable description and optionally restrict it to
 * a limited set of HTTP verbs and request URIs
 */
@XmlRootElement
@Entity
public class OAuthPermission extends Permission {

    private static final long serialVersionUID = -6486616235830491290L;

    private List<String> httpVerbs = new LinkedList<>();

    private List<String> uris = new LinkedList<>();

    public OAuthPermission() {

    }

    public OAuthPermission(String permission) {
        this(permission, null);
    }

    public OAuthPermission(String permission, String description) {
        super(permission, description);
    }

    /**
     * Sets the optional list of HTTP verbs, example,
     * "GET" and "POST", etc
     *
     * @param httpVerbs the list of HTTP verbs
     */
    public void setHttpVerbs(List<String> httpVerbs) {
        this.httpVerbs = httpVerbs;
    }

    /**
     * Gets the optional list of HTTP verbs
     *
     * @return the list of HTTP verbs
     */
    @ElementCollection
    public List<String> getHttpVerbs() {
        return httpVerbs;
    }

    /**
     * Sets the optional list of relative request URIs
     *
     * @param uri the list of URIs
     */
    public void setUris(List<String> uri) {
        this.uris = uri;
    }

    /**
     * Gets the optional list of relative request URIs
     *
     * @return the list of URIs
     */
    @ElementCollection
    public List<String> getUris() {
        return uris;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OAuthPermission) || !super.equals(object)) {
            return false;
        }
        OAuthPermission that = (OAuthPermission) object;
        if (getHttpVerbs() != null && that.getHttpVerbs() == null
                || getHttpVerbs() == null && that.getHttpVerbs() != null
                || getHttpVerbs() != null && !getHttpVerbs().equals(that.getHttpVerbs())) {
            return false;
        }
        if (getUris() != null && that.getUris() == null // NOPMD
                || getUris() == null && that.getUris() != null // NOPMD
                || getUris() != null && !getUris().equals(that.getUris())) { // NOPMD
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        if (getHttpVerbs() != null) {
            hashCode = 31 * hashCode + getHttpVerbs().hashCode();
        }
        if (getUris() != null) {
            hashCode = 31 * hashCode + getUris().hashCode();
        }
        return hashCode;
    }
}
