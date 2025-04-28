import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.qameta.allure.Attachment
import io.qameta.allure.Description
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction


class AppiumTest {

    companion object {
        lateinit var driver: AppiumDriver<MobileElement>

        @BeforeAll
        @JvmStatic
        fun initDriver() {
            val capabilities = DesiredCapabilities()
            capabilities.setCapability("appium:platformName", "Android")
            capabilities.setCapability("appium:deviceName", "Medium Phone API 35")
            capabilities.setCapability("appium:automationName", "UIAutomator2")
            capabilities.setCapability("appium:app","C:\\Users\\Honta\\Downloads\\ru.yandex.yandexmaps_737993770_rs.apk")

            try {
                driver = AppiumDriver(URL("http://localhost:4723/wd/hub"), capabilities)
                println("Driver initialized successfully.")
            } catch (e: Exception) {
                println("Error initializing driver: ${e.message}")
            }
            val wait = WebDriverWait(driver, 30)
            val allowLocation: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ru.yandex.yandexmaps:id/allow_location_button"))) as MobileElement
            allowLocation.click()
            val allowLocationSystem: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button"))) as MobileElement
            allowLocationSystem.click()
            val confirmButton: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ru.yandex.yandexmaps:id/feature_screen_confirm_button"))) as MobileElement
            confirmButton.click()
            val allowNotification: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.permissioncontroller:id/permission_allow_button"))) as MobileElement
            allowNotification.click()
        }
    }


    @Attachment(value = "Inline Screenshot", type = "build/image/png", fileExtension = ".png")
    fun takeScreenshot(): ByteArray {
        return (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    }

    @Test
    @Description("Тест построение маршрута")
    @Severity(SeverityLevel.CRITICAL)
    fun testRouteToSimbirsoft() {
        val wait = WebDriverWait(driver, 30)

        val moveTo: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@content-desc=\"Search here\"]"))) as MobileElement
        takeScreenshot()
        moveTo.click()

        val search : MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id=\"ru.yandex.yandexmaps:id/search_line_edit_text\"]"))) as MobileElement
        search.setValue("Simbirsoft")
        takeScreenshot()

        val searchButton: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Search\"]"))) as MobileElement
        searchButton.click()
        takeScreenshot()

        val direction: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Directions\"]"))) as MobileElement
        direction.click()

        val routeFrom: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ru.yandex.yandexmaps:id/from_waypoint"))) as MobileElement
        routeFrom.click()
        val routeFromField: MobileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@content-desc=\"From here: Current location\"]"))) as MobileElement
        routeFromField.click()
        val routeFromMap = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Select on map\"]"))) as MobileElement
        routeFromMap.click()
        val routeFromMapDone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Done\"]"))) as MobileElement
        routeFromMapDone.click()
        val DirectionDone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\"Done\"]"))) as MobileElement
        DirectionDone.click()
        takeScreenshot()
        val buttumLetsGo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ru.yandex.yandexmaps:id/lets_go_button"))) as MobileElement
        buttumLetsGo.click()
    }




}
