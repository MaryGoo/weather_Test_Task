package weather;

import com.codeborne.selenide.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class WeatherComparisonTodayWithoutPOTest {

    private final String YANDEX_URL = "https://yandex.ru/pogoda/";
    private final String GISMETEO_URL = "https://www.gismeteo.ru/";

    static Stream<Arguments> method1DataProvider() {
        return Stream.of(
                arguments("Тула"),
                arguments("Кострома")
        );
    }

    @ParameterizedTest
    @MethodSource("method1DataProvider")
    public void weatherToday(String city) {
        open(YANDEX_URL);
        setCityName("Город или район", city);
        $(By.xpath(String.format("//li[@data-text='%s']/a", city))).click();
        String dayYandex = $(By.xpath("//div[@class='forecast-briefly__name'][text()='Сегодня']/..//span[text()='днём']/../span[@class='temp__value temp__value_with-unit']")).getText();

        open(GISMETEO_URL);
        setCityName("поиск — через пробел можно уточнить страну или регион", city);
        $(By.xpath(String.format("//span[@class='founditem__desc'][contains(b,'%s')]", city))).click();
        ElementsCollection temps = $$(By.xpath("//div[@class='tab-content']/div[contains(text(),'Сегодня')]/..//span[@class='unit unit_temperature_c']"));
        ArrayList<Integer> tempsList = new ArrayList<>();
        for (SelenideElement temp : temps) {
            tempsList.add(getIntTempFromString(temp.getText()));
        }
        assertEquals("На сайте указаны не только ночная и дневная темпаретура", tempsList.size(), 2);
        assertEquals("На сайтах указаны разные темпаратуры", getIntTempFromString(dayYandex), getDayTemp(tempsList));
    }

    private int getDayTemp(ArrayList<Integer> list) {
        if (list.get(0) >= list.get(1)) {
            return list.get(0);
        } else {
            return list.get(1);
        }
    }

    private void setCityName(String placeholder, String city) {
        $(By.xpath(String.format("//input[@placeholder='%s']", placeholder))).setValue(city);
    }

    private int getIntTempFromString(String temp) {
        if (temp.contains("+")) {
            return Integer.parseInt(temp.replaceAll("[+]", ""));
        } else if (temp.contains("−")) {
            String fg = temp.replaceAll("[−]", "");
            return 0 - Integer.parseInt(fg);
        } else {
            return Integer.parseInt(temp);
        }
    }
}