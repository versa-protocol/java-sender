package org.versa.sender;

public class ReceiptRegistrationRequest {
  public String schema_version;
  public TransactionHandles handles;
  public String transaction_id;

  public ReceiptRegistrationRequest(String schema_version, TransactionHandles handles, String transaction_id) {
    this.schema_version = schema_version;
    this.handles = handles;
    this.transaction_id = transaction_id;
  }
}
