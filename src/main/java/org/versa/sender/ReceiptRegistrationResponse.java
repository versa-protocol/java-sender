package org.versa.sender;

import java.util.List;

public class ReceiptRegistrationResponse {
  public VersaEnv env;
  public String receipt_id;
  public String transaction_id;
  public List<Receiver> receivers;
  public String encryption_key;
}
