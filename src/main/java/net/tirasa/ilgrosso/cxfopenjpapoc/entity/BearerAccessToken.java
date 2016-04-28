package net.tirasa.ilgrosso.cxfopenjpapoc.entity;

import javax.persistence.Entity;
import net.tirasa.ilgrosso.cxfopenjpapoc.OAuthConstants;
import net.tirasa.ilgrosso.cxfopenjpapoc.OAuthUtils;

/**
 * Simple Bearer Access Token implementations
 */
@Entity
public class BearerAccessToken extends ServerAccessToken {

    private static final long serialVersionUID = -3614732043728799245L;

    public BearerAccessToken(Client client,
            long lifetime) {
        super(client,
                OAuthConstants.BEARER_TOKEN_TYPE,
                OAuthUtils.generateRandomTokenKey(),
                lifetime,
                OAuthUtils.getIssuedAt());
    }

    public BearerAccessToken(Client client,
            String tokenKey,
            long lifetime,
            long issuedAt) {
        super(client, OAuthConstants.BEARER_TOKEN_TYPE, tokenKey, lifetime, issuedAt);
    }

    public BearerAccessToken(ServerAccessToken token) {
        this(token, OAuthUtils.generateRandomTokenKey());
    }

    public BearerAccessToken(ServerAccessToken token, String newKey) {
        super(validateTokenType(token, OAuthConstants.BEARER_TOKEN_TYPE), newKey);
    }

    public BearerAccessToken() {

    }
}
