package org.versa.sender;

public class Nonce {
    /// generates a 12-byte nonce
    public static byte[] generate() {
        byte[] nonce = new byte[12];
        for (int i = 0; i < 12; i++) {
            nonce[i] = (byte) (Math.random() * 256);
        }
        return nonce;
    }
}
