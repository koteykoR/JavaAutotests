package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class YndxMarketPage {
    private static WebDriver driver;
    final static By _catalogBtn = By.id("catalogPopupButton");
    final static By _computers = By.xpath("//img[@alt='Компьютеры']");
    final static By _notebooks = By.xpath("//a[@href=\"/catalog--noutbuki/54544/list?hid=91013\"]");

    public YndxMarketPage(WebDriver webDriver) {
        driver = webDriver;
    }

    @Step("Нажимаем на \"Каталог\"")
    public void clickOnCatalog() {
        driver.findElement(_catalogBtn).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step("Нажимаем на \"Компьютеры\"")
    public void clickOnComputers() {
        movetoElement(_computers,driver);
    }

    @Step("Нажимаем на \"Ноутбуки\"")
    public void clickOnNotebooks() {
        driver.findElement(_notebooks).click();
    }

    @Step("Наводим мышь на элемент")
    private void movetoElement(By locator, WebDriver driver) {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(locator)).perform();
    }

}
