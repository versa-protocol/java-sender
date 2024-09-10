// Receipt.java

package org.versa.sender;

/**
 * Sent to each authorized receiver returned by the registry
 */
public class ReceiverPayload {
    public String sender_client_id;
    public String receipt_id;
    public String transaction_id;
    public Envelope envelope;

    // constructor
    public ReceiverPayload(String sender_client_id, String receipt_id, String transaction_id, Envelope envelope) {
        this.sender_client_id = sender_client_id;
        this.receipt_id = receipt_id;
        this.transaction_id = transaction_id;
        this.envelope = envelope;
    }
}
