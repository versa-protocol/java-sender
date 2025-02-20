// Receipt.java

package org.versa.sender;

/**
 * Sent to each authorized receiver returned by the registry
 */
public class WebhookEvent {
  public String event;
  public String event_id;
  public ReceiverPayload data;

  // constructor
  public WebhookEvent(ReceiverPayload data, String event_id) {
    this.event = "receipt";
    this.event_id = event_id;
    this.data = data;
  }
}
