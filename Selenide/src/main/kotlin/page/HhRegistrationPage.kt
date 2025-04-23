package org.example.page

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Condition.visible
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

class HhRegistrationPage {
    fun switchToSingUp(){
        `$`("[data-qa='sign-up-button']").click()
    }

    fun switchToEmailSingUp(){
        `$`(By.xpath("//div[contains(@class, 'magritte-label___xwTC5_1-0-49')]//div[contains(text(), 'Почта')]")).shouldBe(visible).click()
    }

    fun inputRegistrationEmail(email: String){
        `$`("[name='username']").shouldBe(visible).sendKeys(email)
    }

    fun submitButtonRegistrationEmail(){
        `$`("[data-qa='submit-button']").shouldBe(visible).click()
    }

    fun inputRegistrationEmailCode(code: String): String{
        val field = `$`("[data-qa='applicant-login-input-otp']")
        field.sendKeys(code)
        return code
    }
}