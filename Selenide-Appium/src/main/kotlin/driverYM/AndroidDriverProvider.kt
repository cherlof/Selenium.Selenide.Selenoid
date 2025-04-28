package org.example.driverYM

import com.codeborne.selenide.WebDriverProvider
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.Capabilities
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

class AndroidDriverProvider : WebDriverProvider {
    override fun createDriver(capabilities: Capabilities): AndroidDriver {
        val capabilities = mutableMapOf("platformName" to "Android")
        val desiredCapabilities = DesiredCapabilities(capabilities)
        desiredCapabilities.setCapability("appium:platformName", "Android")
        desiredCapabilities.setCapability("appium:deviceName", "Medium Phone API 35")
        desiredCapabilities.setCapability("appium:automationName", "UIAutomator2")
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\Honta\\Downloads\\ru.yandex.yandexmaps_737993770_rs.apk")


        return AndroidDriver(URL("http://localhost:4723/wd/hub"), desiredCapabilities)
    }
}
