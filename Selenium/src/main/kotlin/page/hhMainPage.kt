package org.example.page
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


class hhMainPage (private val driver: WebDriver) {
    val wait = WebDriverWait(driver, Duration.ofSeconds(10))

    fun open() {
        driver.get("https://hh.ru/")
    }

    fun SearchField(text : String){
        driver.findElement(By.id("a11y-search-input")).sendKeys(text)
    }

    fun SearchFieldConfirm(){
        driver.findElement(By.ByXPath("//*[@id=\"supernova_search_form\"]/div/div[2]/button/div/span")).click()
    }

    fun closeRegistrAccept(){
        driver.findElement(By.cssSelector("body > div.bloko-modal-overlay.bloko-modal-overlay_visible > div > div.bloko-modal-close-button")).click()
    }

    fun salaryPrice() : List<WebElement>{
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'magritte-text___pbpft_3-0-32') and contains(@class, 'magritte-text_style-primary___AQ7MW_3-0-32') and contains(@class, 'magritte-text_typography-label-1-regular___pi3R-_3-0-32')]")))
        return driver.findElements(By.xpath("//span[contains(@class, 'magritte-text___pbpft_3-0-32') and contains(@class, 'magritte-text_style-primary___AQ7MW_3-0-32') and contains(@class, 'magritte-text_typography-label-1-regular___pi3R-_3-0-32')]"))
    }
}