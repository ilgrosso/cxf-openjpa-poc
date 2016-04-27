package net.tirasa.ilgrosso.cxfopenjpapoc.entity;

import net.tirasa.ilgrosso.cxfopenjpapoc.OAuthConstants;

/**
 * Represents the extended client view of {@link AccessToken}.
 * It may contain the actual scope value assigned to the access token,
 * the refresh token key, and other properties such as when this token
 * will expire, etc.
 */
public class ClientAccessToken extends AccessToken {

    private static final long serialVersionUID = 831870452726298523L;

    private String scope;

    public ClientAccessToken() {

    }

    public ClientAccessToken(String tokenType, String tokenKey) {
        super(tokenType, tokenKey);
    }

    /**
     * Sets the actual scope assigned to the access token.
     * For example, it can be down-scoped in which case the client
     * may need to adjust the way it works with the end user.
     *
     * @param approvedScope the actual scope
     */
    public void setApprovedScope(String approvedScope) {
        this.scope = approvedScope;
    }

    /**
     * Gets the actual scope assigned to the access token.
     *
     * @return the scope
     */
    public String getApprovedScope() {
        return scope;
    }

    @Override
    public String toString() {
        if (OAuthConstants.BEARER_AUTHORIZATION_SCHEME.equalsIgnoreCase(super.getTokenType())) {
            return OAuthConstants.BEARER_AUTHORIZATION_SCHEME + " " + super.getTokenKey();
        } else {
            return super.toString();
        }
    }
}
