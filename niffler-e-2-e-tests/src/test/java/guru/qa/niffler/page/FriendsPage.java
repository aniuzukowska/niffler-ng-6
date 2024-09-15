package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class FriendsPage {
    private final ElementsCollection tableRowsFriends = $("#friends").$$("tr");
    private final ElementsCollection tableRowsIncomeRequests = $("#requests").$$("tr");
    private final SelenideElement uiTabAllPeople = $("a[href='/people/all']");
    private final ElementsCollection tableRowsOutcomeRequests = $("#all").$$("tr");

    public FriendsPage checkThatTableContainsFriendName(String friendName) {
        tableRowsFriends.find(text(friendName)).should(visible);
        return this;
    }

    public FriendsPage checkThatTableFriendIsEmpty() {
        tableRowsFriends.shouldHave(size(0));
        return this;
    }

    public FriendsPage checkThatTableContainsNameInIncomeRequests(String name) {
        tableRowsIncomeRequests.find(text(name)).should(visible);
        return this;
    }

    public FriendsPage goToAllPeopleTab() {
        uiTabAllPeople.click();
        return this;
    }

    public FriendsPage checkThatTableContainsNameInOutcomeRequests(String name) {
        tableRowsOutcomeRequests.find(text(name)).should(visible).shouldHave(text("Waiting..."));
        return this;
    }
}
