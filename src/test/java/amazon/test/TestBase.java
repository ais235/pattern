package amazon.test;

import amazon.components.HamburgerMenu;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    HamburgerMenu hamburgerMenu = new HamburgerMenu();

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

}