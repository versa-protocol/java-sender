package org.versa.sender;

public class ReceiptRegistrationRequest {
  public ClientMetadata client_metadata;
  public String event_type;
  public String schema_version;
  public String transaction_id;
  public TransactionHandles handles;

  public ReceiptRegistrationRequest(String schema_version, String event_type, TransactionHandles handles, String transaction_id) {
    this.schema_version = schema_version;
    this.event_type = event_type;
    this.handles = handles;
    this.transaction_id = transaction_id;
    this.client_metadata = new ClientMetadata("java-sender/1.0.0");
  }
}
