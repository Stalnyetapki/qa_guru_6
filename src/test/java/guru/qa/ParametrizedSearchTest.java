package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ParametrizedSearchTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://ya.ru/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"Selenide", "Junit 5"})
    @ParameterizedTest(name = "\"Проверка отображения поисковых результатов в яндексе для запроса \"{0}\"")
    void commonSearchTest(String testData) {
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(testData)).shouldBe(Condition.visible);
    }
    @CsvSource({
            "Selenide, concise UI test in Java",
            "JUnit 5, IntelliJ IDEA"
    })
    @ParameterizedTest(name = "\"Проверка отображения поисковых результатов в яндексе для запроса \"{0}\"")
    void complexSearchTest(String testData, String expectedText) {
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").find(text(expectedText)).shouldBe(Condition.visible);
    }

    static  Stream<Arguments> mixedArgumentsTestDataProvider() {
        return Stream.of(
                Arguments.of("Selenide", List.of(1, 2, 4), true),
                Arguments.of("J Unit 5", List.of(5, 6, 7), false)
        );
    }

    @MethodSource(value = "mixedArgumentsTestDataProvider")
    @ParameterizedTest
    void mixedArgumentsTest(String firstArg, List<Integer> secondArg) {
        
    }


}
