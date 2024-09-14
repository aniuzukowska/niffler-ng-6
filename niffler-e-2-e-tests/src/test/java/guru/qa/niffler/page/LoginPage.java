package guru.qa.niffler.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
  private final SelenideElement usernameInput = $("input[name='username']");
  private final SelenideElement passwordInput = $("input[name='password']");
  private final SelenideElement submitButton = $("button[type='submit']");
  private final SelenideElement createNewAccountLink = $("a[href='/register']");
  private final SelenideElement errorText = $(".form__error");

  public MainPage login(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    submitButton.click();
    return new MainPage();
  }

  public RegisterPage goToCreateNewAccount() {
    createNewAccountLink.click();
    return new RegisterPage();
  }

  public LoginPage loginWithBadCredentials(String username, String password) {
    usernameInput.setValue(username);
    passwordInput.setValue(password);
    submitButton.click();
    return this;
  }

  public LoginPage checkErrorText(String errorText) {
    this.errorText.should(visible);
    this.errorText.shouldHave(text(errorText));
    return this;
  }

}
