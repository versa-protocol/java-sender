package org.versa.sender;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;


@SpringBootApplication
public class SenderApplication {

  public static void main(String[] args) {
    Security.addProvider(new BouncyCastleProvider());
    SpringApplication.run(SenderApplication.class, args);
  }

}
