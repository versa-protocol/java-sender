// Receipt.java

package org.versa.sender;

/**
 * Sent to each authorized receiver returned by the registry
 */
public class WebhookEvent {
  public String event;
  public ReceiverPayload data;

  // constructor
  public WebhookEvent(ReceiverPayload data) {
    this.event = "receipt";
    this.data = data;
  }
}
