package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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


    public void checkThatTableContainsActiveCategory(String categoryName) {
        categoryRows.find(text(categoryName)).should(visible);
    }

    public void checkThatTableNotContainsArchivedCategory(String categoryName) {
        categoryRows.find(text(categoryName)).shouldNot(visible);
    }
}
