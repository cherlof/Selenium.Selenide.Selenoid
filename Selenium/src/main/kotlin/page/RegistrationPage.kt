package org.example.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import java.util.*

class RegistrationPage(private val driver: WebDriver) {
    val wait = WebDriverWait(driver, Duration.ofSeconds(20))

    fun open(){
        driver.get("https://hh.ru/account/login?role=applicant&backurl=%2F")
    }

    fun switchToSingUp(){
        driver.findElement(By.cssSelector("[data-qa='sign-up-button']")).click()
    }

    fun switchToEmailSingUp(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'magritte-label___xwTC5_1-0-49')]//div[contains(text(), 'Почта')]"))).click()
    }

    fun inputRegistrationEmail(email: String){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='username']"))).sendKeys(email)
    }

    fun submitButtonRegistrationEmail(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='submit-button']"))).click()
    }

    fun inputRegistrationEmailCode(code: String): String{
        val field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa='applicant-login-input-otp']")))
        field.sendKeys(code)
        return code
    }
}