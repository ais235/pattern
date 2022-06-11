package amazon.test;

import amazon.components.HamburgerMenu;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OpenProductCard extends TestBase {

    @Test
    void fillFormTest() {
        //Откройте https://www.amazon.in/
        open("https://www.amazon.in/");
        //Нажмите на hamburger menuв верхнем левом углу.
        //Прокрутите, а затем нажмите на TV, Appliances and Electronicsссылку под Shop by Departmentразделом.
        hamburgerMenu.Menu("shop by department","TV, Appliances, Electronics" );
        //Затем нажмите на Televisionsпод Tv, Audio & Camerasподраздел.
        $x("//a[@class='hmenu-item'][contains(text(), 'Televisions')]").click();
        //Прокрутите вниз и отфильтруйте результаты по бренду «Samsung».
        $("#s-refinements").hover();
        $x("//span[@class='a-size-base a-color-base'][contains(text(), 'Samsung')]").scrollTo().click();
        //Отсортируйте результаты Samsung по цене от высокой к низкой.
        $("#a-autoid-0-announce").click();
        $x("//ul[@class='a-nostyle a-list-link']//a[contains(text(), 'Price: High to Low')]").click();
        //Нажмите на второй самый дорогой предмет (независимо от того, что может быть во время автоматизации).
        $x("//div[@data-component-type='s-search-result'][2]//h2/a").click();
        //Переключить окно
        Selenide.switchTo().window(1);
        //Подтвердите наличие раздела «Об этом элементе» и запишите текст этого раздела в консоль/отчет.
        $("h1[class='a-size-base-plus a-text-bold']").shouldHave(text("About this item"));
        String text = $("ul[class='a-unordered-list a-vertical a-spacing-mini']").text();
        System.out.println(text);
    }
}
