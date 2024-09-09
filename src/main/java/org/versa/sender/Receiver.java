package org.versa.sender;

public class Receiver {
    private String name;
    private String address;
    private String receiver_secret;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver_secret() {
        return receiver_secret;
    }
}
