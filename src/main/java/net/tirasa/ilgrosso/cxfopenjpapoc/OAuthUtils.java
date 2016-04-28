package net.tirasa.ilgrosso.cxfopenjpapoc;

import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.rt.security.crypto.CryptoUtils;

public final class OAuthUtils {

    public static String generateRandomTokenKey() throws OAuthServiceException {
        return generateRandomTokenKey(16);
    }

    public static String generateRandomTokenKey(int byteSize) {
        if (byteSize < 16) {
            throw new OAuthServiceException();
        }
        return StringUtils.toHexString(CryptoUtils.generateSecureRandomBytes(byteSize));
    }

    public static long getIssuedAt() {
        return System.currentTimeMillis() / 1000L;
    }
}
