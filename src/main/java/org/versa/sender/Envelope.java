// Receipt.java

package org.versa.sender;

import java.util.Base64;

/**
 * An envelope for the receiver to decrypt
 */
public class Envelope {
    public String nonce; // base64 encoded
    public String encrypted; // base64 encoded
    public int hash;

    // constructor that base64 encodes the bytes
    public Envelope(byte[] nonce, byte[] encrypted) {
        this.nonce = Base64.getEncoder().encodeToString(nonce);
        this.encrypted = Base64.getEncoder().encodeToString(encrypted);
        this.hash = 0;
    }
}
