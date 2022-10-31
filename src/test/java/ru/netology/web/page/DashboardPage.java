package ru.netology.web.page;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {

  private final SelenideElement heading = $("[data-test-id=dashboard]");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";
  private final ElementsCollection cards = $$(".list__item div");

  public void verifyIsDashboardPage() {
    heading.shouldBe(visible);
  }

  public int checkBalance(int index) {
    var text = cards.get(index - 1).getText();
    return extractBalance(text);
  }

  public MoneyTransferPage selectCard(int index) {
    cards.get(index - 1).$x("button[@data-test-id='action-deposit']").click();
    return new MoneyTransferPage();
  }

  private int extractBalance(String text) {
    var start = text.indexOf(balanceStart);
    var finish = text.indexOf(balanceFinish);
    var value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }
}