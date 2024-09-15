package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
  private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");
  private final SelenideElement statisticsHeader = $("#stat h2");
  private final SelenideElement historyHeader = $("#spendings h2");
  private final SelenideElement profileButton = $("button[type='button']");
  private final SelenideElement itemMenuProfile = $("a[href='/profile']");
  private final SelenideElement itemMenuFriends = $("a[href='/people/friends']");

  public EditSpendingPage editSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).$$("td").get(5).click();
    return new EditSpendingPage();
  }

  public void checkThatTableContainsSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).should(visible);
  }

  public MainPage checkThatStatisticsHeaderIsVisible() {
    statisticsHeader.should(visible);
    return this;
  }

  public MainPage checkThatHistoryHeaderIsVisible() {
    historyHeader.should(visible);
    return this;
  }

  public ProfilePage goToProfilePage() {
    profileButton.click();
    itemMenuProfile.click();
    return new ProfilePage();
  }

  public FriendsPage goToFriendsPage() {
    profileButton.click();
    itemMenuFriends.click();
    return new FriendsPage();
  }

}
