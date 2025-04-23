package org.example.page

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import org.openqa.selenium.By
import org.openqa.selenium.Keys

class HhMainPage {

    fun SearchField(profession : String){
        `$`(By.id("a11y-search-input")).sendKeys(profession, Keys.ENTER)
    }
    fun closeRegistrAccept(){
        `$`("body > div.bloko-modal-overlay.bloko-modal-overlay_visible > div > div.bloko-modal-close-button").click()
    }
    fun salaryPrice() : ElementsCollection {
        `$`(By.xpath("//span[contains(@class, 'magritte-text___pbpft_3-0-32') and contains(@class, 'magritte-text_style-primary___AQ7MW_3-0-32') and contains(@class, 'magritte-text_typography-label-1-regular___pi3R-_3-0-32')]")).shouldBe(visible)
        val elements = `$$`(By.xpath("//span[contains(@class, 'magritte-text___pbpft_3-0-32') and contains(@class, 'magritte-text_style-primary___AQ7MW_3-0-32') and contains(@class, 'magritte-text_typography-label-1-regular___pi3R-_3-0-32')]"))
        return elements
    }

}