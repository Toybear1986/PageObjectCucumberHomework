package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MoneyTransferPage {
  private final SelenideElement amountInput = $x("//div[@data-test-id='amount']//input[@class='input__control']");
  private final SelenideElement fromInput = $x("//span[@data-test-id='from']//input[@class='input__control']");
  private final SelenideElement submitRecharge = $x("//button[@data-test-id='action-transfer']");
  private final SelenideElement rechargeHead = $(byText("Пополнение карты"));

  public MoneyTransferPage() {
    rechargeHead.should(visible);
  }

  public DashboardPage makeRecharge(int amount, String numberOfCard) {
    rechargeCard(amount, numberOfCard);
    return new DashboardPage();
  }

  public void rechargeCard(int value, String numberOfCard) {
    amountInput.setValue(String.valueOf(value));
    fromInput.setValue(numberOfCard);
    submitRecharge.click();
  }
}
