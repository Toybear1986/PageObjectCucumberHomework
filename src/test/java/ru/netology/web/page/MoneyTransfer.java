package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class MoneyTransfer {
  private static final SelenideElement rechargeCard0001 = $x("//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']//button[@data-test-id='action-deposit']");
  private static final SelenideElement rechargeCard0002 = $x("//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']//button[@data-test-id='action-deposit']");
  private static final SelenideElement amountField = $x("//div[@data-test-id='amount']//input[@class='input__control']");
  private static final SelenideElement fromField = $x("//span[@data-test-id='from']//input[@class='input__control']");
  private static final SelenideElement submitButton = $x("//button[@data-test-id='action-transfer']");

  public static void rechargeCard(String card, double value) {
    if (card.equals(DataHelper.card0001)) {
      rechargeCard0001.click();
      amountField.setValue(String.valueOf(value));
      fromField.setValue(DataHelper.card0002);
      submitButton.click();
    } else {
      rechargeCard0002.click();
      amountField.setValue(String.valueOf(value));
      fromField.setValue(DataHelper.card0001);
      submitButton.click();
    }
  }
}
