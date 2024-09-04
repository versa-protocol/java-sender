package org.versa.sender;
import java.util.Base64;

public class EncryptionTestPayload {
    public String receipt_json;
    public String key;
    public String nonce;

    public String get_receipt() {
        return receipt_json;
    }
    public byte[] get_key() {
        return Base64.getDecoder().decode(key);
    }
    public byte[] get_nonce() {
        return Base64.getDecoder().decode(nonce);
    }
}
