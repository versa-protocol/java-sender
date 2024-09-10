package org.versa.sender;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HmacUtil {
  public static String generateHmac(String secret, byte[] payload) throws Exception {
    String algorithm = "HmacSHA1"; // For the purpose of webhook verification, SHA1 is perfectly sufficient

    // Create HMAC key using the provided secret
    SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), algorithm);

    // Get an instance of the Mac with the HMAC algorithm
    Mac mac = Mac.getInstance(algorithm);
    mac.init(secretKeySpec);

    // Generate the HMAC by hashing the payload
    byte[] hmacBytes = mac.doFinal(payload);

    // Encode the HMAC as a Base64 string (optional, depends on what format you
    // need)
    return Base64.getEncoder().encodeToString(hmacBytes);
  }
}
