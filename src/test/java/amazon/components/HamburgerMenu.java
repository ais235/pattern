package amazon.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HamburgerMenu {
    private SelenideElement menu = $("#nav-hamburger-menu");


    public void Menu(String title, String item) {
        menu.click();
        $("#hmenu-content").hover();
        $x("//div[contains(text(), '"+title+"')]").scrollTo();

        //недоделал
        $x("//li/div[@class='hmenu-item hmenu-title '][contains(text(), '"+title+"')]/following::li/a/div[contains(text(), '"+item+"')]").click();
    }
}
