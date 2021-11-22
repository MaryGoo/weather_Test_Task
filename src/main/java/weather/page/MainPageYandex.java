package weather.page;

import org.openqa.selenium.By;

public class MainPageYandex {
    public static final String YANDEX_URL = "https://yandex.ru/pogoda/";

    public final String DROP_DOWN_LIST_OF_CITIES = "//li[@data-text='%s']/a";
    public final String SEARCH_FIELD_PLACEHOLDER = "Город или район";

    public By daytimeTempToday = By.xpath("//div[@class='forecast-briefly__name'][text()='Сегодня']/..//span[text()='днём']/../span[@class='temp__value temp__value_with-unit']");

}