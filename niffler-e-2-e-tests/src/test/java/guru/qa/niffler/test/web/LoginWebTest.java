package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.generators.RandomData;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginWebTest {

    private static final Config CFG = Config.getInstance();

    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin() {
        String username = "duck";
        String password = "12345";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .login(username, password)
                .checkThatStatisticsHeaderIsVisible()
                .checkThatHistoryHeaderIsVisible();
    }

    @Test
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
        String username = RandomData.getUserName();
        String password = RandomData.getPassword();

        Selenide.open(CFG.frontUrl(), LoginPage.class)
                .loginWithBadCredentials(username, password)
                .checkErrorText("Неверные учетные данные пользователя");
    }
}
