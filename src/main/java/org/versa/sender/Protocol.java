package org.versa.sender;

import java.io.ByteArrayOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Protocol {

  public static byte[] encrypt(
      byte[] keyBytes,
      byte[] nonce,
      String input) {
    Key key;

    key = new SecretKeySpec(keyBytes, "AES");

    try {
      Cipher cipher = Cipher.getInstance("AES/GCM-SIV/NoPadding", "BC");
      // GCMParameterSpec for AES-GCM-SIV with IV and tag length
      // Note that the tag (or nonce) should always be of length 12
      GCMParameterSpec gcmSpec = new GCMParameterSpec(12 * 8, nonce);
      cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);
      byte[] encrypted = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
      return encrypted;
    } catch (Exception e) {
      e.printStackTrace();
      return new ByteArrayOutputStream().toByteArray();
    }
  }
}
