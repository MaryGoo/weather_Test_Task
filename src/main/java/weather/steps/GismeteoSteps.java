package weather.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import weather.page.MainPageGismeteo;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$;
import static weather.steps.CommonSteps.*;

public class GismeteoSteps {
    MainPageGismeteo mainPageGismeteo = new MainPageGismeteo();

    @Step("Выбор города {city} из выпадающего списка")
    public void chooseCityGismeteo(String city){
        chooseCity(mainPageGismeteo.DROP_DOWN_LIST_OF_CITIES,city);
    }

    @Step("Ввод названия города {city} в поиск")
    public void setCityNameGismeteo(String city){
        setCityName(mainPageGismeteo.SEARCH_FIELD_PLACEHOLDER, city);
    }

    @Step("Получение дневной температуры на сегодняшний день")
    public int getDaytimeTempToday(){
        ElementsCollection temps = $$(mainPageGismeteo.tempsToday);
        ArrayList<Integer> tempsList = new ArrayList<>();
        for (SelenideElement temp : temps) {
            tempsList.add(getIntTempFromString(temp.getText()));
        }
        if (tempsList.size() == 2){
            return getDayTemp(tempsList);
        } else {
            System.out.println("На сегодня указано более одной температуры");
            return 100;
        }
    }

}