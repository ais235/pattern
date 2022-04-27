package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class Snippets {

  //команды управления браузером
  void browser_command_examples() {

    open("https://google.com");
    open("/customer/orders");
    //идентификация которая появляется окошком сверху браузера с логином паролем
    open("/", AuthenticationType.BASIC, "user", "password");

    //вернуться назат
    Selenide.back();
    //перезагрузить страницу
    Selenide.refresh();

    //очищение куки и истории
    Selenide.clearBrowserCookies();
    Selenide.clearBrowserLocalStorage();

    //пример всплывающих окон от браузера https://the-internet.herokuapp.com/javascript_alerts
    Selenide.confirm(); // OK in alert dialogs
    Selenide.dismiss(); // Cancel in alert dialogs

    //закрывает активное окно
    Selenide.closeWindow(); // close active tab
    //закрывает весь браузер
    Selenide.closeWebDriver(); // close browser completely

    //пример перехода по фреймам https://the-internet.herokuapp.com/frames
    Selenide.switchTo().frame("new");
    Selenide.switchTo().defaultContent();

    //переключение между окнами 0,1,2...
    Selenide.switchTo().window("The Internet");
  }

  //селекторы
  void selectors_examples() {
    $("div").click();
    //element это аналог $ для языка Котлин
    element("div").click();

    //найти 2 div
    $("div", 2).click(); // the third div

    //xpath
    $x("//h1/div").click();
    $(byXpath("//h1/div")).click();

    //поиск по тексту
    $(byText("full text")).click();
    //поиск по части текста
    $(withText("ull tex")).click();

    //поиск родительского элемента
    $("").parent();       // find parent element
    //поиск соседнего элемента вниз
    $("").sibling(2);     // find down third sibling element
    //поиск соседнего элемента вверх
    $("").preceding(0);   // find up first sibling element
    $("").closest("div"); // find up the tree the next element with tag 

    $("div").$("h1").find(byText("abc")).click();

    // very optional
    $(byAttribute("abc", "x")).click();
    $("[abc=x]").click();

    $(byId("mytext")).click();
    $("#mytext").click();

    $(byClassName("red")).click();
    $(".red").click();
  }

  //действия экшены
  void actions_examples() {
    //левый клик
    $("").click();
    $("").doubleClick();
    //правый клик
    $("").contextClick();

    //подвести мышь
    $("").hover();

    //стерает текст в поле и пишет новый
    $("").setValue("text");
    //добавляет текст в поле
    $("").append("text");
    //стирает текст в поле
    $("").clear();
    $("").setValue("");

    //вызов
    $("div").sendKeys("c"); // hotkey c on element
    actions().sendKeys("c").perform(); //hotkey c on whole application
    actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
    $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

    $("").pressEnter();
    $("").pressEscape();
    $("").pressTab();

    // complex actions with keybord and mouse, example
    //нажимает на мышку и не отпускает clickAndHold
    //направление мыши moveByOffset
    //отпустить кнопку мыши release
    actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

    // old html actions don't work with many modern frameworks
    $("").selectOption("dropdown_option");
    $("").selectRadio("radio_options");

  }

  //проверки
  void assertions_examples() {
    //
    $("").shouldBe(visible);
    $("").shouldNotBe(visible);
    $("").shouldHave(text("abc"));
    $("").shouldNotHave(text("abc"));
    $("").should(appear);
    $("").shouldNot(appear);


    //longer timeouts
    $("").shouldBe(visible, Duration.ofSeconds(30));
    $("").waitUntil(visible, 30000);  //is deprecated

  }

  //
  void conditions_examples() {
    //виден селекор
    $("").shouldBe(visible);
    //не виден селектор
    $("").shouldBe(hidden);

    //не учитывает регистр
    $("").shouldHave(text("abc"));
    $("").shouldHave(exactText("abc"));
    //с учётом регистра
    $("").shouldHave(textCaseSensitive("abc"));
    $("").shouldHave(exactTextCaseSensitive("abc"));
    //регулярка
    $("").should(matchText("[0-9]abc$"));
    //цвет
    $("").shouldHave(cssClass("red"));
    $("").shouldHave(cssValue("font-size", "12"));

    //например текст внутри поля ввода
    $("").shouldHave(value("25"));
    $("").shouldHave(exactValue("25"));
    $("").shouldBe(empty);

    //проверка значения атрибута, например что кнопка не активна
    $("").shouldHave(attribute("disabled"));
    $("").shouldHave(attribute("name", "example"));
    $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

    //проверка чекбокса
    $("").shouldBe(checked); // for checkboxes
    $("").shouldNotBe(checked);

    // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
    $("").should(exist);

    // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
    $("").shouldBe(disabled);
    $("").shouldBe(enabled);
  }


  void collections_examples() {

    $$("div"); // does nothing!

    // selections
    $$("div").filterBy(text("123")).shouldHave(size(1));
    $$("div").excludeWith(text("123")).shouldHave(size(1));

    $$("div").first().click();
    elements("div").first().click();
    // $("div").click();
    $$("div").last().click();
    $$("div").get(1).click(); // the second! (start with 0)
    $("div", 1).click(); // same as previous
    $$("div").findBy(text("123")).click(); //  finds first

    // assertions
    $$("").shouldHave(size(0));
    $$("").shouldBe(CollectionCondition.empty); // the same

    $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
    $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

    $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
    $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

    $$("").shouldHave(itemWithText("Gamma")); // only one text

    $$("").shouldHave(sizeGreaterThan(0));
    $$("").shouldHave(sizeGreaterThanOrEqual(1));
    $$("").shouldHave(sizeLessThan(3));
    $$("").shouldHave(sizeLessThanOrEqual(2));

  }

  void file_operation_examples() throws FileNotFoundException {

    File file1 = $("a.fileLink").download(); // only for <a href=".."> links
    File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

    File file = new File("src/test/resources/readme.txt");
    $("#file-upload").uploadFile(file);
    $("#file-upload").uploadFromClasspath("readme.txt");
    // don't forget to submit!
    $("uploadButton").click();
  }

  void javascript_examples() {
    executeJavaScript("alert('selenide')");
    executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
    long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
  }
}
