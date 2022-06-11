package demoqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @BeforeAll
    static void beforeAll(){
        Configuration.startMaximized = true;
    } // Окно браузера развернуто при запуске

    @Test
    public void fillOutFormWithRealDataTest() {

        String firstName = "Alexandr";
        String lastName = "Starostin";
        String userEmail = "test@gmail.com";
        String gender = "Male";
        String userNumber = "9998887766";
        String dateOfBirth = "11 May,1999";
        String subject = "Computer Science";
        String hobbySport = "Sports";
        String hobbyMusic = "Music";
        String uploadPicture = "Test.jpg";
        String currentAddress = "Somewhere";
        String state = "NCR";
        String city = "Noida";

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1999");
        $$(".react-datepicker__day").find(Condition.text("11")).click();

        $("#subjectsInput").sendKeys("c");
        $(byText(subject)).click();

        $(byText(hobbySport)).click();
        $(byText(hobbyMusic)).click();

        $("#uploadPicture").uploadFromClasspath(uploadPicture);
        $("#currentAddress").setValue(currentAddress);

        $(byText("Select State")).click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();

        $("#submit").click();

        //Проверяем что перешли в верное модальное окно
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        //Проверяем что все введенные данные верно записаны
        $$(byXpath("//td")).shouldHave(itemWithText(firstName + " " + lastName),itemWithText(userEmail),
                itemWithText(gender),itemWithText(userNumber),itemWithText(dateOfBirth),
                itemWithText(subject),itemWithText(hobbySport + ", " + hobbyMusic),itemWithText(uploadPicture),
                itemWithText(currentAddress),itemWithText(state + " " + city));
    }
}
