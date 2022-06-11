package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FindContributors {

    @Test
    void firstContributorShouldBeSolntsev() {
        Configuration.headless = false;
        // открыть страничку selenide
        open("https://github.com/selenide/selenide");
        // подвести мышку к первому элементу в области Contributors
        $("li[data-test-selector='grid-mode-element']").hover();
//        $(".BorderGrid").$(byText("Contributors"))
//                .closest("div").$("li").hover();
        sleep(5000);
        // проверка: в появившемся окошечке (overlay)
        $$(".Popover-message").findBy(visible)
                .shouldHave(text("Andrei Solntsev"));
    }
}
