package org.versa.sender;

import java.util.List;

public class ReceiptRegistrationResponse {
    private VersaEnv env;
    private String receiptId;
    private String transactionId;
    private List<Receiver> receivers;
    private String encryptionKey;

    // Getters and Setters
    public VersaEnv getEnv() {
        return env;
    }

    public void setEnv(VersaEnv env) {
        this.env = env;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
