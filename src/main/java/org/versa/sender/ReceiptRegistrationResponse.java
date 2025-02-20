package org.versa.sender;

import java.util.List;

public class ReceiptRegistrationResponse {
  public String env;
  public VersaMode mode;
  public String event_id;
  public String receipt_id;
  public String transaction_id;
  public List<Receiver> receivers;
  public String encryption_key;
  /// DEPRECATED: use 'mode' instead
}
