package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;


class MoneyTransferPageTest {

  @BeforeEach
  void setup() {
    open("http://localhost:9999", LoginPage.class);
  }

  @Test
  void shouldTransferFromFirstToSecond() {
    var loginPage = new LoginPage();
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = getVerificationCode();
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalance = dashboardPage.checkBalance(firstCardInfo);
    var secondCardBalance = dashboardPage.checkBalance(secondCardInfo);
    var amount = generateRandomValueInBalanceRange(firstCardBalance);
    var moneyTransferPage = dashboardPage.selectCard(secondCardInfo);
    dashboardPage = moneyTransferPage.makeRecharge(amount, firstCardInfo);
    assertEquals(firstCardBalance - amount, dashboardPage.checkBalance(firstCardInfo));
    assertEquals(secondCardBalance + amount, dashboardPage.checkBalance(secondCardInfo));
  }

  @Test
  void shouldTransferFromSecondToFirst() {
    var loginPage = new LoginPage();
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = getVerificationCode();
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalance = dashboardPage.checkBalance(firstCardInfo);
    var secondCardBalance = dashboardPage.checkBalance(secondCardInfo);
    var amount = generateRandomValueInBalanceRange(secondCardBalance);
    var moneyTransferPage = dashboardPage.selectCard(firstCardInfo);
    dashboardPage = moneyTransferPage.makeRecharge(amount, secondCardInfo);
    assertEquals(secondCardBalance - amount, dashboardPage.checkBalance(secondCardInfo));
    assertEquals(firstCardBalance + amount, dashboardPage.checkBalance(firstCardInfo));
  }

  @Test
  void shouldShowErrorIfAmountOfChargeOverBalanceFirstToSecond() {
    var loginPage = new LoginPage();
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = getVerificationCode();
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalance = dashboardPage.checkBalance(firstCardInfo);
    var secondCardBalance = dashboardPage.checkBalance(secondCardInfo);
    var amount = generateRandomValueOverBalanceRange(firstCardBalance);
    var moneyTransferPage = dashboardPage.selectCard(secondCardInfo);
    dashboardPage = moneyTransferPage.makeRecharge(amount, firstCardInfo);
    moneyTransferPage.findErrorMessage(
            "На картe " + firstCardInfo.getCardNumber().substring(12, 16) +" недостаточно средств");
    assertEquals(firstCardBalance - amount, dashboardPage.checkBalance(firstCardInfo));
    assertEquals(secondCardBalance + amount, dashboardPage.checkBalance(secondCardInfo));
  }

  @Test
  void shouldShowErrorIfAmountOfChargeOverBalanceSecondToFirst() {
    var loginPage = new LoginPage();
    var authInfo = getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = getVerificationCode();
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var firstCardInfo = getFirstCardInfo();
    var secondCardInfo = getSecondCardInfo();
    var firstCardBalance = dashboardPage.checkBalance(firstCardInfo);
    var secondCardBalance = dashboardPage.checkBalance(secondCardInfo);
    var amount = generateRandomValueOverBalanceRange(secondCardBalance);
    var moneyTransferPage = dashboardPage.selectCard(firstCardInfo);
    dashboardPage = moneyTransferPage.makeRecharge(amount, secondCardInfo);
    moneyTransferPage.findErrorMessage(
            "На картe " + secondCardInfo.getCardNumber().substring(12, 16) +" недостаточно средств");
    assertEquals(secondCardBalance, dashboardPage.checkBalance(secondCardInfo));
    assertEquals(firstCardBalance, dashboardPage.checkBalance(firstCardInfo));
  }
}