import Pages.FiltersPage;
import Pages.YndxMarketPage;
import Pages.YndxStartPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class NotebookTest {

    static YndxMarketPage marketPage;
    static YndxStartPage mainPage;
    static FiltersPage filtersPage;
    static ChromeDriver driver;

    NotebookTest() {
        //По хорошему это надо бы вынести в метод,который запускается перед всеми тестами
        // но ради одного теста не вижу смысла
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(Arguments.of("Тест на ноутбуке за 60000", 60000),
                //Практического смысла в этом нет особо, но раз надо сделать тест параметризованным-я делаю
                Arguments.of("Тест на ноутбуке за 90000", 90000)
        );
    }

    @DisplayName("Тест покупки ноутбуков")
    @MethodSource("provideParameters")
    @ParameterizedTest(name = "{0}'")
    public void yndxNotebookTest(String name, int maxPrice) throws InterruptedException {
        mainPage = new YndxStartPage(driver);
        mainPage.openMainPage();
        marketPage = mainPage.clickOnMarketBtn();
        marketPage.clickOnCatalog();
        marketPage.clickOnComputers();
        marketPage.clickOnNotebooks();
        //Это,конечно, лучше вынести в отдельный пейджОбжект, но ради одной кнопки...
        filtersPage = clickOnFilters();
        filtersPage.fillMinMaxPrice(maxPrice);
        driver.executeScript("window.scrollTo(100, document.body.scrollHeight);");
        filtersPage.chooseDell();
        filtersPage.showGoods();
        String notebook = driver.findElement(By.xpath(
                "//*[@data-autotest-id='product-snippet'][2]//span[contains(text(),\"Ноутбук\")]")).getText();
        filtersPage.findInSearchLine(notebook);
        try {
            driver.findElement(By.xpath("//*[@data-autotest-id='product-snippet']"));
            Assertions.assertTrue(true);
        } catch (NoSuchElementException e) {
            Assertions.fail();
        }
    }

    @Step("Выбираем все фильтры")
    private FiltersPage clickOnFilters() {
        driver.findElement(By.xpath("//span[contains(text(),'Все фильтры')]")).click();
        return new FiltersPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
