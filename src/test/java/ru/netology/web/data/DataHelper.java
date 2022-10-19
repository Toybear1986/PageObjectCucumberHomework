package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.CheckBalance;


public class DataHelper {
  private DataHelper() {
  }

  public static String card0001 = "5559 0000 0000 0001";
  public static String card0002 = "5559 0000 0000 0002";
//  public static double randomValue = ThreadLocalRandom.current().nextInt(1, 1000 + 1); //закоментил, т.к. переводы работают не правильно

  @Value
  public static class AuthInfo {
    String login;
    String password;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }


/*  public static AuthInfo getOtherAuthInfo(AuthInfo original) {
    return new AuthInfo("petya", "123qwerty");
  }*/

  @Value
  public static class VerificationCode {
    String code;
  }

  public static VerificationCode getVerificationCode() {
    return new VerificationCode("12345");
  }

  public static int parseIntFromString(String card) {
    CheckBalance.checkBalance(card);
    int index1 = card.indexOf(": \n") + 3;
    int index2 = card.indexOf("\n р.");
    return Integer.parseInt(card, index1, index2, 10);
  }
}