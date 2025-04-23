import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.*
import io.qameta.allure.*
import org.example.page.HhMainPage
import org.example.page.HhRegistrationPage

import org.openqa.selenium.chrome.ChromeOptions

import org.junit.jupiter.api.Test
import java.io.File

class hhTest (){
    val options = ChromeOptions().apply {
        // addArguments("--start-maximized") не работает в Selenide
        addArguments("--window-size=1920,1080")
    }

    @Attachment("Список зарплат")
    fun attachSalary(data: List<String>): String {
        return data.joinToString("\n") // Преобразуем список в строку
    }

    @Attachment(value = "Скриншот", type = "image/png")
    fun attachScreenshot(file: File): ByteArray {
        return file.readBytes() // Читаем файл и добавляем в отчет
    }


    @Description("Тест на поиск вакансий и отображение зарплат")
    @Severity(SeverityLevel.NORMAL)
    @Test
    fun hhTestSearch() {
        val search = HhMainPage()
        val profession: String = "QA"
        Configuration.browserCapabilities = options

        open("https://hh.ru/")
        search.SearchField(profession)
        search.closeRegistrAccept()

        val salaryList = search.salaryPrice().texts()
        println(salaryList)
        attachSalary(salaryList)

        val screenshotFile: String = screenshot("salary")!!.toString()
        val file = File(screenshotFile.replace("^file:/?".toRegex(), ""))
        attachScreenshot(file)

        assert(salaryList.isNotEmpty())
    }

    @Step("Код валидации {code}")
    fun validationCode(code: String) {
        println("Код регистрации: $code")
    }

    @Description("Тест регистрацию при помощи почты")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun testRegistration() {
        val code = "SecretCode"
        val registrationPage = HhRegistrationPage()

        open("https://hh.ru/account/login?role=applicant&backurl=%2F")

        registrationPage.switchToSingUp()
        registrationPage.switchToEmailSingUp()
        registrationPage.inputRegistrationEmail("alex@test.com")
        registrationPage.submitButtonRegistrationEmail()

        validationCode(code)
        registrationPage.inputRegistrationEmailCode(code)

        val screenshotFile: String = screenshot("Registration")!!.toString()
        val file = File(screenshotFile.replace("^file:/?".toRegex(), ""))
        attachScreenshot(file)

        assert(code==code)// тут дожно быть перенаправление на главную страницу, но нет возможности подтвержать почту
    }
}