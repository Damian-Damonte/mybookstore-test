import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.facebook.com");

        driver.manage().window().maximize();

        driver.close();
        driver.quit();

    }
}
