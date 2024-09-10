// Receipt.java

package org.versa.sender;

/**
 * A Versa itemized receipt
 */
public class SendReceiptPayload {
  public String receipt_json;
  public String schema_version;
  public String transaction_id;
  public TransactionHandles transaction_handles;
}
