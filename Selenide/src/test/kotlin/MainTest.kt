import com.codeborne.selenide.Selenide.`open`
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun test(){
        open("https://hh.ru/")
    }

}