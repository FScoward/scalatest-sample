import org.scalatest.{ BeforeAndAfterAll, FunSpec }
import org.scalatest.Matchers._
import org.scalatest.selenium.WebBrowser

import org.openqa.selenium.{By, Keys, WebDriver}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.{ ExpectedConditions, WebDriverWait }

class SampleSpec extends FunSpec
    with WebBrowser
    with BeforeAndAfterAll {
  implicit val webDriver: WebDriver = new FirefoxDriver

  describe("Stanby") {
    it("Take Screenshot") {
      setCaptureDir("./pic")

      go to ("https://jp.stanby.com/")

      pageTitle should be ("スタンバイ 日本最大級の求人検索エンジン")

      val query = "エンジニア"
      click on "q"
//      enter("エン")
      textField("q").value = "エン"
      val element = webDriver.findElement(By.name("q"))
      element.sendKeys(Keys.ARROW_LEFT)
      capture to "captureX"
      enter(s"$query")
      capture to "capture3"
      submit()

      (new WebDriverWait(webDriver, 10))
        .until(ExpectedConditions.titleContains(s"${query}の求人"))

      pageTitle should be (s"${query}の求人| スタンバイ")

      capture to "capture2.png"
    }
  }

  override def afterAll(): Unit = webDriver.quit()
}
