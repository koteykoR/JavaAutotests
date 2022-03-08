package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FiltersPage {
    private static WebDriver driver;

    public FiltersPage(WebDriver webDriver) {
        driver = webDriver;
    }

    final static int _minPriceValue = 25000;
    final static By _minPrice = By.xpath("//div[@data-prefix='от']/*");
    final static By _maxPrice = By.xpath("//div[@data-prefix='до']/*");
    final static By _dellCheckbox = By.xpath("//div[contains(text(),'DELL')]");
    //Коряво, сюда бы лучше регулярка подошла
    final static By _findNotebooks = By.xpath("//a[contains(text(),'предложений')]");
    final static By _searchLine = By.xpath("//input[@placeholder='Искать товары']");
    final static By _findBttn = By.xpath("//span[contains(text(),'Найти')]");

    @Step("Вводим минимальную и максимульные цены")
    public void fillMinMaxPrice(int maxprice){
        driver.findElement(_minPrice).sendKeys(String.valueOf(_minPriceValue));
        driver.findElement(_maxPrice).sendKeys(String.valueOf(maxprice));
    }

    @Step("Выбираем DELL")
    public void chooseDell(){
        driver.findElement(_dellCheckbox).click();
    }

    @Step("Нажимаем показать товары")
    public void showGoods(){
        driver.findElement(_findNotebooks).click();
    }

    @Step("Вводим в поисковую строку хар-ки ноутбука и ищем")
    public void findInSearchLine(String text){
        driver.findElement(_searchLine).sendKeys(text);
        driver.findElement(_findBttn).click();
    }

}
