package ru.netology.web.data;

import lombok.Value;


public class DataHelper {
  private DataHelper() {
  }

  public static VerificationCode getVerificationCode() {
    return new VerificationCode("12345");
  }

  @Value
  public static class VerificationCode {
    String code;
  }

  @Value
  public static class CardInfo {
    String cardNumber;
    String testId;
  }

  @Value
  public static class AuthInfo {
    String login;
    String password;
  }
}


