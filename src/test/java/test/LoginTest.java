package test;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


public class LoginTest {
    @AfterAll
    public static void CleanAllTables() {
        DataHelper.cleanTables();
    }

    @Test
    void shouldCorrectLogin() {
        val loginPage = open("http://localhost:7777", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldErrorIfInvalidLogin() {
        val loginPage = open("http://localhost:7777", LoginPage.class);
        val authInfo = DataHelper.getInvalidLogin();
        loginPage.invalidLogin(authInfo);
    }

    @Test
    void shouldErrorIfInvalidPassword() {
        val loginPage = open("http://localhost:7777", LoginPage.class);
        val authInfo = DataHelper.getInvalidPassword();
        loginPage.invalidLogin(authInfo);
    }
}

