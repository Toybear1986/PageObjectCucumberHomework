package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransfer;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;
import static ru.netology.web.page.CheckBalance.checkBalance;

class MoneyTransferTest {

  @BeforeEach
  void setup() {
    open("http://localhost:9999", LoginPage.class);
  }

  @AfterEach
  void tearDown() {
    clearBrowserCookies();
    closeWebDriver();
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsCheckTargetCard() {
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCode();
    verificationPage.validVerify(String.valueOf(verificationCode));
    int checkBalanceTo = checkBalance(card0001);
    var value = 100;
    MoneyTransfer.rechargeCard(card0001, card0002, value);
    System.out.println(checkBalance(card0001) - (checkBalanceTo + value));
    assertEquals(checkBalanceTo + value, checkBalance(card0001));
  }


  @Test
  void shouldTransferMoneyBetweenOwnCardsWithKopecksCheckTargetCard() {
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCode();
    verificationPage.validVerify(String.valueOf(verificationCode));
    int checkBalanceTo = checkBalance(card0002);
    var value = 100;
    MoneyTransfer.rechargeCard(card0002, card0001, value);
    System.out.println(checkBalance(card0002) - (checkBalanceTo + value));
    assertEquals(checkBalanceTo + value, checkBalance(card0002));
  }

  @Test
  void shouldTransferMoneyBetweenOwnCardsCheckSourceCard() {
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCode();
    verificationPage.validVerify(String.valueOf(verificationCode));
    int checkBalanceFrom = checkBalance(card0002);
    var value = 100;
    MoneyTransfer.rechargeCard(card0001, card0002,value);
    System.out.println((checkBalanceFrom + value) - checkBalance(card0002));
    assertEquals(checkBalanceFrom + value, checkBalance(card0001));
  }


  @Test
  void shouldTransferMoneyBetweenOwnCardsWithKopecksCheckSourceCard() {
    var loginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCode();
    verificationPage.validVerify(String.valueOf(verificationCode));
    int checkBalanceFrom = checkBalance(card0001);
    var value = 100;
    MoneyTransfer.rechargeCard(card0002, card0001, value);
    System.out.println((checkBalanceFrom + value) - checkBalance(card0001));
    assertEquals(checkBalanceFrom + value, checkBalance(card0001));
  }
}