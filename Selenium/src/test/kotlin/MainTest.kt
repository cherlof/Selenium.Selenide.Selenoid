import io.qameta.allure.*
import org.example.page.RegistrationPage
import org.example.page.hhMainPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.ByteArrayInputStream

class hhTest {
    val options = ChromeOptions().apply {
        addArguments("--start-maximized")
    }
    val driver: WebDriver = ChromeDriver(options)

    @Attachment(value = "Скриншот", type = "image/png")
    fun attachScreenshot(driver: WebDriver): ByteArray {
        return (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    }

    @Attachment("Список зарплат")
    fun attachSalary(data: List<String>): String {
        return data.joinToString("\n") // Преобразуем список в строку
    }

    @Step("Поиск вакансий для {profession}")
    fun searchJob(profession: String) {
        println("Запущен тест для: $profession")
    }

    @Description("Тест на поиск вакансий и отображение зарплат")
    @Severity(SeverityLevel.NORMAL)
    @Test
    fun testSearch(){
        val profession = "QA"
        val mainPage = hhMainPage(driver = driver)

        mainPage.open()

        searchJob(profession)
        mainPage.SearchField(profession)
        mainPage.SearchFieldConfirm()
        mainPage.closeRegistrAccept()
        val  elements: List<WebElement> = mainPage.salaryPrice()
        println(elements.size)
        for (element in elements){
            println(element.text)
        }
        attachScreenshot(driver)
        attachSalary(elements.map { it.text })

        assert(elements.isNotEmpty())
    }

    @Step("Поиск вакансий для {code}")
    fun validationCode(code: String) {
        println("Код регистрации: $code")
    }

    @Description("Тест регистрацию при помощи почты")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun testRegistration() {
        val registrationPage = RegistrationPage(driver = driver)
        registrationPage.open()
        registrationPage.switchToSingUp()
        registrationPage.switchToEmailSingUp()
        registrationPage.inputRegistrationEmail("alex@test.com")
        registrationPage.submitButtonRegistrationEmail()
        val code: String = registrationPage.inputRegistrationEmailCode("SecretCode")
        attachScreenshot(driver)
        validationCode(code)

        assert(code==code)// тут дожно быть перенаправление на главную страницу, но нет возможности подтвержать почту
    }



    @AfterEach
    fun destroyDriver(){
        driver.quit()
    }

}