package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class search {


    @BeforeAll
    static void beforeAll(){
        Configuration.startMaximized = true;
    } // Окно браузера развернуто при запуске

    @Test
    public void search() {

        open("https://github.com/");


    }
}
 