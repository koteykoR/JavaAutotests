package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class YndxStartPage {
    private static WebDriver driver;
    private final String _yndxUrl = "https://yandex.ru/";
    private final By _yndxMarketBtn = By.xpath(
            "//div[contains(@class,'services-new__icon services-new__icon_market')]");

    public YndxStartPage(WebDriver webDriver) {
        driver = webDriver;
    }

    @Step("Заходим на яндекс браузер")
    public YndxMarketPage clickOnMarketBtn() {

        driver.findElement(_yndxMarketBtn).click();
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new YndxMarketPage(driver);
    }

    @Step("Открываем яндекс")
    public void openMainPage() {
        driver.navigate().to(_yndxUrl);
    }

}
