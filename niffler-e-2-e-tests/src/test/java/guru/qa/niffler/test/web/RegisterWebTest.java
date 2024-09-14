package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.generators.RandomData;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class RegisterWebTest {

    private static final Config CFG = Config.getInstance();

    @Test
    void shouldRegisterNewUser() {
        String username = RandomData.getUserName();
        String password = RandomData.getPassword();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .goToCreateNewAccount()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .goToSignIn()
                .login(username, password)
                .checkThatStatisticsHeaderIsVisible()
                .checkThatHistoryHeaderIsVisible();
    }

    @Test
    void shouldNotRegisterUserWithExistingUsername() {
        String username = "duck";
        String password = RandomData.getPassword();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .goToCreateNewAccount()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(password)
                .submitRegistration()
                .checkErrorText("Username `duck` already exists");
    }

    @Test
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        String username = RandomData.getUserName();
        String password = RandomData.getPassword();
        String passwordSubmit = RandomData.getPassword();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .goToCreateNewAccount()
                .setUsername(username)
                .setPassword(password)
                .setPasswordSubmit(passwordSubmit)
                .submitRegistration()
                .checkErrorText("Passwords should be equal");

    }
}
