package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;
import static ru.netology.web.data.DataHelper.card0001;
import static ru.netology.web.data.DataHelper.card0002;

public class CheckBalance {
  private static final SelenideElement checkBalance0001 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
  private static final SelenideElement checkBalance0002 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

  public static int checkBalance(String card) {
    int balance = 0;
    if (card.equals(card0001)) {
      balance = DataHelper.parseIntFromString(checkBalance0001.getOwnText());
    } else if (card.equals(card0002)) {
      balance = DataHelper.parseIntFromString(checkBalance0002.getOwnText());
    } else return balance;
    return balance;
  }
}