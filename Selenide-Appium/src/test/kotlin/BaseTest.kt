package org.example.tests

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.appium.SelenideAppium
import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.example.driverYM.AndroidDriverProvider
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class AppiumTest {

    @BeforeEach
    fun setUp() {
        closeWebDriver()
        Configuration.browserSize = null
        Configuration.browser = AndroidDriverProvider::class.java.name
        Configuration.fastSetValue = true // Ускорение ввода текста в Appium
        Configuration.timeout = 8000 // Увеличенное время ожидания

        SelenideAppium.launchApp()
        `$`(By.id("ru.yandex.yandexmaps:id/allow_location_button")).shouldBe(visible).click()
        `$`(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).shouldBe(visible).click()
        `$`(By.id("ru.yandex.yandexmaps:id/feature_screen_confirm_button")).shouldBe(visible).click()
        `$`(By.id("com.android.permissioncontroller:id/permission_allow_button")).shouldBe(visible).click()
    }

    @Test
    @Description("Тест построение маршрута")
    @Severity(SeverityLevel.CRITICAL)
    fun testRouteToSimbirsoft() {
        `$`(By.xpath("//android.widget.Button[@content-desc=\"Search here\"]")).click()
        `$`(By.xpath("//android.widget.EditText[@resource-id=\"ru.yandex.yandexmaps:id/search_line_edit_text\"]")).click()
        `$`(By.xpath("//android.widget.TextView[@text=\"Search\"]")).click()
        `$`(By.xpath("//android.widget.TextView[@text=\"Directions\"]")).click()
        `$`(By.id("ru.yandex.yandexmaps:id/from_waypoint")).click()
        `$`(By.xpath("//android.widget.TextView[@content-desc=\"From here: Current location\"]")).click()
        `$`(By.xpath("//android.widget.TextView[@text=\"Select on map\"]")).click()
    }
}
