package my.company;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Created by Acer7750g on 13.09.2017.
 */
@Title("Простой тестовый класс")
@Description("Пользователь должен иметь возможность выполнить вход с существующей парой логин и пароль")
public class SimpleTest {
    String link = "https://account.mail.ru/login/";
    String existingUserLogin = "existingUserLogin";
    String domain = "@mail.ru";
    String existingUserPassword = "password123";

    @BeforeMethod
    public void setup () {
        open(link);
        $(By.name("Username")).shouldBe(Condition.visible);
    }
    @Title("Существующий пользователь может выполнить вход")
    @Test
    public void userCanLoginWithExistingUsername (){
        fillForm();
        send();
        validate();
    }
    @Step("Заполним поля логин и пароль, нажмем Войти")
    public void fillForm() {
        $(By.name("Username")).setValue(existingUserLogin);
        $(".b-email__domain").shouldHave(Condition.text(domain));
        $(By.name("Password")).setValue(existingUserPassword);
        $(".b-login__submit-btn").click();
    }
    @Step("Убедимся, что пользователю представлена папка Входящие")
    public void send (){
        Assert.assertTrue(WebDriverRunner.url().contains("e.mail.ru/messages/inbox"));
    }
    @Step("Убедимся, что имя пользователя соответствует")
    public void validate(){
        $("#PH_user-email").shouldHave(Condition.text(existingUserLogin + domain));
    }
}
