package ru.netology.web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static org.junit.Assert.assertEquals;
import static ru.netology.web.data.DataHelper.card0001;
import static ru.netology.web.data.DataHelper.card0002;


public class TemplateSteps {
  private static LoginPage loginPage;
  private static DashboardPage dashboardPage;
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
    dashboardPage = verificationPage.validVerify(verificationCode);
  }

  @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
  public void verifyDashboardPage() {
    dashboardPage.verifyIsDashboardPage();
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
    if (numberOfCard == 1) {
      MoneyTransfer.rechargeCard(card0001, card, value);
    }
    if (numberOfCard == 2) {
      MoneyTransfer.rechargeCard(card0002, card, value);
    }
  }

  @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей.")
  public void checkBalance(int numberOfCard, int value) {
    if (numberOfCard == 1) {
      assertEquals(value,CheckBalance.checkBalance(card0001));
    }
    if (numberOfCard == 2) {
      assertEquals(value,CheckBalance.checkBalance(card0002));
    }
  }
}