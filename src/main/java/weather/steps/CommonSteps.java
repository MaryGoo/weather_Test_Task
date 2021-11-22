package weather.steps;

import org.openqa.selenium.By;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;

public class CommonSteps {
    public static int getDayTemp(ArrayList<Integer> list) {
        if (list.get(0) >= list.get(1)) {
            return list.get(0);
        } else {
            return list.get(1);
        }
    }

    public static void setCityName(String placeholder, String city) {
        $(By.xpath(String.format("//input[@placeholder='%s']", placeholder))).setValue(city);
    }

    public static int getIntTempFromString(String temp) {
        if (temp.contains("+")) {
            return Integer.parseInt(temp.replaceAll("[+]", ""));
        } else if (temp.contains("−")) {
            String fg = temp.replaceAll("[−]", "");
            return 0 - Integer.parseInt(fg);
        } else {
            return Integer.parseInt(temp);
        }
    }

    public static void chooseCity(String locator, String city){
        $(By.xpath(String.format(locator, city))).click();
    }

    public static String getText(By locator){
        return $(locator).getText();
    }
}
