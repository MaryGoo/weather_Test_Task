package weather.page;

import org.openqa.selenium.By;

public class MainPageGismeteo {
    public static final String GISMETEO_URL = "https://www.gismeteo.ru/";

    public final String DROP_DOWN_LIST_OF_CITIES = "//span[@class='founditem__desc'][contains(b,'%s')]";
    public final String SEARCH_FIELD_PLACEHOLDER = "поиск — через пробел можно уточнить страну или регион";

    public By tempsToday = By.xpath("//div[@class='tab-content']/div[contains(text(),'Сегодня')]/..//span[@class='unit unit_temperature_c']");
}
