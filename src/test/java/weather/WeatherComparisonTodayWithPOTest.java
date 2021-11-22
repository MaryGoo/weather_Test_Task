package weather;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import weather.steps.GismeteoSteps;
import weather.steps.YandexSteps;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static weather.page.MainPageGismeteo.GISMETEO_URL;
import static weather.page.MainPageYandex.YANDEX_URL;

public class WeatherComparisonTodayWithPOTest {
    YandexSteps yandexSteps = new YandexSteps();
    GismeteoSteps gismeteoSteps = new GismeteoSteps();

    static Stream<Arguments> method1DataProvider() {
        return Stream.of(
                arguments("Тула"),
                arguments("Кострома")
        );
    }

    @Feature(value = "Сравнение температуры на сайтах yandex и gismeteo")
    @Story(value = "Сравнение температуры на сегодняшний день")
    @ParameterizedTest
    @MethodSource("method1DataProvider")
    public void weatherToday(String city) {
        open(YANDEX_URL);
        yandexSteps.setCityNameYandex(city);
        yandexSteps.chooseCityYandex(city);
        int daytimeTempTodayYandex = yandexSteps.getDaytimeTempToday();

        open(GISMETEO_URL);
        gismeteoSteps.setCityNameGismeteo(city);
        gismeteoSteps.chooseCityGismeteo(city);
        int daytimeTempTodayGismeteo = gismeteoSteps.getDaytimeTempToday();

        assertEquals("На сайтах указаны разные темпаратуры", daytimeTempTodayYandex, daytimeTempTodayGismeteo);
    }
}