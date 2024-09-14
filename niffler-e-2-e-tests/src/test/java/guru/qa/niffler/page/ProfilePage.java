package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.model.CategoryJson;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
    private final SelenideElement usernameInput = $("input[name='username']");
    private final SelenideElement nameInput = $("input[name='name']");
    private final SelenideElement submitButton = $("button[type='submit']");
    private final SelenideElement uploadButton = $("label[for='image__input']");

    private final SelenideElement showArchivedSwitch = $("input[type='checkbox']");
    private final SelenideElement categoryInput = $("input[name='category']");
    private final ElementsCollection categoryRows = $$(".css-17u3xlq");
    private final SelenideElement archiveSubmitButton = $(byText("Archive"));
    private final SelenideElement unArchiveSubmitButton = $(byText("Unarchive"));


    public ProfilePage checkThatTableContainsCategory(String categoryName) {
        categoryRows.find(text(categoryName)).should(visible);
        return this;
    }

    public ProfilePage checkThatTableNotContainsCategory(String categoryName) {
        categoryRows.find(text(categoryName)).shouldNot(visible);
        return this;
    }

    public ProfilePage archiveCategory(String categoryName) {
        categoryRows.find(text(categoryName)).$("button[aria-label='Archive category']").click();
        archiveSubmitButton.click();
        return this;
    }

    public ProfilePage unarchiveCategory(String categoryName) {
        categoryRows.find(text(categoryName)).$("button[aria-label='Unarchive category']").click();
        unArchiveSubmitButton.click();
        return this;
    }

    public ProfilePage showArchivedSwitch() {
        showArchivedSwitch.click();
        return this;
    }
}
