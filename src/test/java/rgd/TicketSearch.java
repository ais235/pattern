package rgd;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TicketSearch {

    @BeforeAll
    static void beforeAll(){
        Configuration.startMaximized = true;
    } // Окно браузера развернуто при запуске

    @Test
    public void ticketSearch() {

        String rzdDirectionFrom = "Москва";
        String rzdDirectionTo = "Санкт-Петербург";
//        String From = "Откуда";
//        String To = "Куда";

        open("https://www.rzd.ru/");

        $("#direction-from").click();
        $("#direction-from").sendKeys(rzdDirectionFrom);

        $("#direction-to").click();
        $("#direction-to").sendKeys(rzdDirectionTo);

        $("#datepicker-from").click();
        $(".rzd-state-highlight").click();

        $("#datepicker-to").click();
        $(".rzd-state-hover").click();

        $(".rzd-go-to-result-button").click();

        $(".sidebar-favorites__wrapper").shouldHave(text("Избранные маршруты"));



    }
}
