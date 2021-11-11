package com.currency.automation;

import com.currency.ticker.xml.parser.CurrencyTickerXMLParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.HashMap;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class CurrencyAutomation {

    private final CurrencyTickerXMLParser currencyTickerXMLParser;

    private final String bankUrl = "https://www.lb.lt/lt/currency/daylyexport/?xml=1&class=Eu&type=day";

    private static String getDownloadPath() {
        File resourcesFolder = new File("src/main/resources/currency-data");
        return resourcesFolder.getAbsolutePath();
    }



//    @Scheduled(cron = "0 0 1 ? * *")
    @Scheduled(fixedDelay = 500000)
    private void automation() throws InterruptedException {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.default_directory", getDownloadPath());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("safebrowsing-disable-extension-blacklist");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.get(bankUrl);
        Thread.sleep(5000);
        driver.quit();
        // TODO: change to dynamic filename
        currencyTickerXMLParser.convertXMLToObject(getDownloadPath() + "/currency-2021-11-08.xml");


    }
}
