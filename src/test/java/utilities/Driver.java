package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

    /*
    POM(Page Object Model)
           Test seneryolarının daha az kod ile yazılmasına ve bakımının daha kolay yapılmasına
       olanak sağlayan yazılım test yöntemidir. TestNG ve Cucumber frameworklerinde POM kalıbı kullanılır.
     */

public class Driver {
    private Driver(){
        /*
        Driver class'ından obje oluşturmanın önüne geçmek için
      default constructor'ı private yaparak bunun önüne geçebiliriz.
      Bu uygulamaya singleton patter denir
         */
    }
        static WebDriver driver;

    public static WebDriver getDriver(){
        /*
            Driver'i her çağırdığımızda yeni bir pencere açılmasının önüne geçmek için
         if bloğu içinde Eğer driver'a değer ATANMAMIŞSA değer ata, eğer değer atanmışsa
         driver'i aynı sayfada return et
        */
            /*
        Properties dosyasında key olarak belirttiğimiz browse'a aşağıdaki örnekteki gibi hangi değereri
        belirtirsek testlerimiz o browser ile çalışır
            */
        if (driver == null){//-->Driver'a değer atanmamışsa
            switch (ConfigReader.getProperty("browser")){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "explorer":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        return driver;
    }

    public static void closeDriver(){
        if (driver != null){ //-->driver'a değer ATANMIŞSA
            driver.close();
            driver = null; //-->driver'ı kapattıktan sonra boşalt
        }

    }

    public static void quitDriver(){
        if (driver != null){ //-->driver'a değer ATANMIŞSA
            driver.quit();
            driver = null; //-->driver'ı kapattıktan sonra boşalt
        }
    }





}
