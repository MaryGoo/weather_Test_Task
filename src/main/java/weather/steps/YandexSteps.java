package weather.steps;

import io.qameta.allure.Step;
import weather.page.MainPageYandex;

import static weather.steps.CommonSteps.*;

public class YandexSteps {
    MainPageYandex mainPageYandex = new MainPageYandex();

    @Step("Выбор города {city} из выпадающего списка")
    public void chooseCityYandex(String city){
        chooseCity(mainPageYandex.DROP_DOWN_LIST_OF_CITIES,city);
    }

    @Step("Ввод названия города {city} в поиск")
    public void setCityNameYandex(String city){
        setCityName(mainPageYandex.SEARCH_FIELD_PLACEHOLDER, city);
    }

    @Step("Получение дневной температуры на сегодняшний день")
    public int getDaytimeTempToday(){
       return getIntTempFromString(getText(mainPageYandex.daytimeTempToday));
    }
}
