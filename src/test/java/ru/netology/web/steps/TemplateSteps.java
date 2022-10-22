package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static org.junit.Assert.assertEquals;
import static ru.netology.web.data.DataHelper.*;


public class TemplateSteps {
  private static LoginPage loginPage;
  private static VerificationPage verificationPage;

  @Пусть("открыта страница с формой авторизации {string}")
  public void openAuthPage(String url) {
    loginPage = Selenide.open(url, LoginPage.class);
  }

  @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
  public void loginWithNameAndPassword(String login, String password) {
    verificationPage = loginPage.validLogin(new DataHelper.AuthInfo(login, password));
  }

  @И("пользователь вводит проверочный код 'из смс' {string}")
  public void setValidCode(String verificationCode) {
    verificationPage.validVerify(getVerificationCode());
  }

  @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
  public void verifyDashboardPage() {
    new DashboardPage();
  }

  @Тогда("появляется ошибка о неверном коде проверки")
  public void verifyCodeIsInvalid() {
    verificationPage.verifyCodeIsInvalid();
  }

  @Пусть("пользователь залогинен с именем {string} и паролем {string}")
  public void loginUser(String login, String password) {
    loginWithNameAndPassword(login, password);
    setValidCode(String.valueOf(DataHelper.getVerificationCode()));
    verifyDashboardPage();
  }

  @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
  public void userRechargeCard(int value, String card, int numberOfCard) {
    CardInfo cardTo = null;
    CardInfo cardFrom = null;
    if (numberOfCard == 1) {
      cardTo = getFirstCardInfo();
      cardFrom = getSecondCardInfo();
    } else if (numberOfCard == 2) {
      cardTo = getSecondCardInfo();
      cardFrom =  getFirstCardInfo();
    }
      DashboardPage dashboardPage = new DashboardPage();
      dashboardPage.selectCard(cardTo);
      MoneyTransferPage moneyTransferPage = new MoneyTransferPage();
      moneyTransferPage.rechargeCard(value, cardFrom);
  }

  @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей.")
  public void checkBalance(int numberOfCard, int value) {
    DashboardPage dashboardPage = new DashboardPage();
    CardInfo checkCard = null;
    if (numberOfCard == 1) {
      checkCard = getFirstCardInfo();
    } else if (numberOfCard == 2) {
      checkCard = getSecondCardInfo();
    }
    assertEquals(value, dashboardPage.checkBalance(checkCard));
  }
}