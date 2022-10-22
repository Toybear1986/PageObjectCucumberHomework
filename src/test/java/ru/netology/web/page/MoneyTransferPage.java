package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MoneyTransferPage {
  private final SelenideElement amountInput = $x("//div[@data-test-id='amount']//input[@class='input__control']");
  private final SelenideElement fromInput = $x("//span[@data-test-id='from']//input[@class='input__control']");
  private final SelenideElement submitRecharge = $x("//button[@data-test-id='action-transfer']");
  private final SelenideElement rechargeHead = $(byText("Пополнение карты"));
  private final SelenideElement errorMessage = $("[data-test-id='error-message']");


  public MoneyTransferPage() {
    rechargeHead.should(visible);
  }

  public DashboardPage makeRecharge(double amount, DataHelper.CardInfo cardInfo) {
    rechargeCard(amount, cardInfo);
    return new DashboardPage();
  }

  public void rechargeCard(double value, DataHelper.CardInfo cardInfo) {
    amountInput.setValue(String.valueOf(value));
    fromInput.setValue(cardInfo.getCardNumber());
    submitRecharge.click();
  }

  public void findErrorMessage(String expectedText) {
    errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
  }
}
