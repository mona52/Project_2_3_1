package ru.netology;

import com.codeborne.selenide.conditions.ExactOwnText;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.*;


public class FormDeliveryTest {

    private final DataSet client = DataGenerator.FormRequest.clientInfo("ru");

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSendFormWithReappointmentWithCloseWindow() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(client.getCity());
        $("[placeholder='Дата встречи']").sendKeys(SHIFT, ARROW_UP, DELETE);
        String futureDate = DataGenerator.getFutureDate(4);
        $("[placeholder='Дата встречи']").setValue(futureDate);
        $("[name=phone]").setValue(client.getPhone());
        $("[data-test-id=name] [type=text]").setValue(client.getName());
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        System.out.println("вывод значения " + $(".notification__content").getOwnText());
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactOwnText("Встреча успешно запланирована на " + futureDate));

        closeWindow();

        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(client.getCity());
        $("[placeholder='Дата встречи']").sendKeys(SHIFT, ARROW_UP, DELETE);
        String newFutureDate = DataGenerator.getFutureDate(7);
        $("[placeholder='Дата встречи']").setValue(newFutureDate);
        $("[name=phone]").setValue(client.getPhone());
        $("[data-test-id=name] [type=text]").setValue(client.getName());
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $(withText("Необходимо")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content>[type=button]").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactOwnText("Встреча успешно запланирована на " + newFutureDate));

    }


    @Test
    void shouldSendFormWithReappointmentInSameWindow() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue(client.getCity());
        $("[placeholder='Дата встречи']").sendKeys(SHIFT, ARROW_UP, DELETE);
        String futureDate = DataGenerator.getFutureDate(4);
        $("[placeholder='Дата встречи']").setValue(futureDate);
        $("[name=phone]").setValue(client.getPhone());
        $("[data-test-id=name] [type=text]").setValue(client.getName());
        $(".checkbox__box").click();
        $("div.form-field>[type=button]").submit();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactOwnText("Встреча успешно запланирована на " + futureDate));

        $("[placeholder='Дата встречи']").sendKeys(SHIFT, ARROW_UP, DELETE);
        String newFutureDate = DataGenerator.getFutureDate(7);
        $("[placeholder='Дата встречи']").setValue(newFutureDate);
        $("div.form-field>[type=button]").submit();
        $(withText("Необходимо")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content>[type=button]").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactOwnText("Встреча успешно запланирована на " + newFutureDate));

    }

}


