package com.example.demo;

import com.twocaptcha.TwoCaptcha;
import com.twocaptcha.captcha.ReCaptcha;
import com.twocaptcha.exceptions.ApiException;
import com.twocaptcha.exceptions.NetworkException;
import com.twocaptcha.exceptions.TimeoutException;
import com.twocaptcha.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v109.input.Input;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

@RestController
@Slf4j
public class Controller {

    String secondPageScript = "Array.from(document.querySelectorAll('[class*=\"-arrow\"]')).forEach(a=> { \n" +
            "    if((a.checkVisibility()) === true){\n" +
            "    \n" +
            "\n" +
            "\t\t\t//APPOINTMENT CATEGORY SELECTION\n" +
            "\n" +
            "    Array.from(document.querySelectorAll('[id*=\"AppointmentCategoryId\"]')).forEach(b=> { \n" +
            "    if((b.textContent.includes('Appointment Category') && b.checkVisibility()) === true){\n" +
            "    \t\t\n" +
            "    \t\tb.click()\n" +
            "\t\t\tArray.from(document.getElementsByTagName(\"li\")).forEach(f=>{\n" +
            "\t\t\tif(f.textContent.toLocaleLowerCase() === 'Normal'.toLocaleLowerCase()){\n" +
            "\t\t\tf.click();\n" +
            "\t\t\t}\n" +
            "\t\t\t\t});\n" +
            "\n" +
            "    \t}\n" +
            "\t\t});\n" +
            "\n" +
            "\n" +
            "\n" +
            "\t\t\t//VISA TYPE SELECTION\n" +
            "\n" +
            "\t\tArray.from(document.querySelectorAll('[id*=\"visaType\" i]')).forEach(b=> { \n" +
            "    if((b.textContent.includes('Visa Type') && b.checkVisibility()) === true){\n" +
            "\n" +
            "\t\t\tb.click();\n" +
            "\tArray.from(document.getElementsByTagName(\"li\")).forEach(f=>{\n" +
            "\t\t\tif(f.textContent.toLocaleLowerCase() === 'schengen visa'){\n" +
            "\t\t\tf.click();\n" +
            "\t\t\t}\n" +
            "\t\t\t\t});\n" +
            "    \t}\n" +
            "\t\t});\n" +
            "\n" +
            "\n" +
            "\n" +
            "\t\t//VISA SUBTYPE SELECTION\n" +
            "\n" +
            "\t\tArray.from(document.querySelectorAll('[id*=\"visasubType\" i]')).forEach(b=> { \n" +
            "    if((b.textContent.toLocaleLowerCase().includes('visa sub type') && b.checkVisibility()) === true){\n" +
            "\n" +
            "\t\t\tb.click();\n" +
            "\tArray.from(document.getElementsByTagName(\"li\")).forEach(f=>{\n" +
            "\t\t\tif(f.textContent.toLocaleLowerCase() === 'schengen visa'.toLocaleLowerCase()){\n" +
            "\t\t\tf.click();\n" +
            "\t\t\t}\n" +
            "\t\t\t\t});\n" +
            "    \t}\n" +
            "\t\t});\n" +
            "\n" +
            "\n" +
            "\n" +
            "\t\t\t//MISSION SELECTION\n" +
            "\n" +
            "\t\tArray.from(document.querySelectorAll('[id*=\"location\" i]')).forEach(b=> { \n" +
            "    if((b.textContent.toLocaleLowerCase().includes('location') && b.checkVisibility()) === true){\n" +
            "\n" +
            "\t\t\tb.click();\n" +
            "\tArray.from(document.getElementsByTagName(\"li\")).forEach(f=>{\n" +
            "\t\t\tif(f.textContent.toLocaleLowerCase().includes(\"nador\")){\n" +
            "\t\t\tf.click();\n" +
            "\t\t\t}\n" +
            "\t\t\t\t});\n" +
            "    \t}\n" +
            "\t\t});\n" +
            "    }\n" +
            "});";
    @GetMapping("/BLS")
public void BLS() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        var email = "hisami27@gmail.com";
        var pass = "Jaimepas12@";
        ChromeOptions chrome_options = new ChromeOptions();
       // chrome_options.addArguments("--user-data-dir=C:/ChromeProfile/Profile1");
        chrome_options.addArguments("--disable-blink-features=AutomationControlled");
       // chrome_options.setExperimentalOption("excludeSwitches","enable-automation");
        chrome_options.setExperimentalOption("useAutomationExtension", false);



        WebDriver driver = new ChromeDriver(chrome_options);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        driver.get("https://api.zenrows.com/v1/?apikey=aaaa9121aaed4c4ea0d2138d7bf3110e2c501acc&url=https%3A%2F%2Fwww.blsspainmorocco.net%2FMAR%2FAccount%2FLogIn%3FReturnUrl%3D%252FMAR%252Fbls%252FVisaTypeVerification&premium_proxy=true&proxy_country=ma");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnVerify")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnVerify")));

        //LOGIN
        String loginScript = "Array.from(document.getElementsByClassName(\"form-control entry-disabled\")).forEach((asd)=> (asd.id.toLowerCase().includes(\"password\")) ? asd.value = \"Bounida12@\" : asd.value = \"sbounida@gmail.com\");\n" +
                "\t\tdocument.getElementById(\"btnVerify\").click();";
        js.executeScript(loginScript);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSubmit")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmit")));
        driver.findElement(By.id("btnSubmit")).click();

        Thread.sleep(1500);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnVerify")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnVerify")));

        driver.findElement(By.id("btnVerify")).click();
//
        Thread.sleep(1400);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSubmit")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmit")));
        Thread.sleep(1400);


        js.executeScript(secondPageScript);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnVerify")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnVerify")));

        log.info("ok");

    }

    @GetMapping("/VFS")
    public String VFS() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        var email = "hisami27@gmail.com";
        var pass = "Jaimepas12@";
        WebDriver driver = new ChromeDriver();

        driver.get("https://visa.vfsglobal.com/mar/en/nld/login");
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-0")));

        driver.findElement(By.id("mat-input-0")).sendKeys(email);
        driver.findElement(By.id("mat-input-1")).sendKeys(pass);

        TwoCaptcha solver = new TwoCaptcha("c310d56e44b72dfa4b8ff7c836e9ecd5");

        ReCaptcha captcha = new ReCaptcha();
        captcha.setSiteKey("qsq");
        captcha.setUrl("https://visa.vfsglobal.com/mar/en/nld/login");
        captcha.setInvisible(true);
        //captcha.setAction("verify");
        //captcha.setProxy("HTTPS", "login:password@IP_address:PORT");
//
        try {
            solver.solve(captcha);
            System.out.println("Captcha solved: " + captcha.getCode());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
        //  solver.solve(captcha);

        //  Thread.sleep(20 * 1000);

        //String code = solver.getResult(captchaId);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("document.getElementById('g-recaptcha-response').innerHTML = '" + captcha.getCode() + "';");
        //   Thread.sleep(500);
        //   js.executeScript("document.getElementsByClassName('recaptcha-checkbox-border')[0].click();");

//        WebElement revealed = driver.findElement(By.className("captcha-solver-info"));
//
//        wait.until(d -> revealed.getText().equals("Captcha solved!"));

        String jas = "___grecaptcha_cfg.clients[0].I.I.callback('" + captcha.getCode() + "')";
        js.executeScript(jas);


//        var submit_btn = driver.findElement(By.cssSelector("button[type='submit']"));
//        submit_btn.click();
        String acptBtn = "document.querySelectorAll('button[class*=\"mat-focus-indicator\"]')[0].click();";
        js.executeScript(acptBtn);


        String startBtn = "document.getElementsByClassName(\"mat-focus-indicator btn mat-btn-lg btn-block btn-brand-orange mat-raised-button mat-button-base\")[0].click();";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mat-tab-label-content")));

        js.executeScript(startBtn);


        //PART 2
        wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-select-0")));
Thread.sleep(3000);
//        VISA CENTER
        driver.findElement(By.id("mat-select-0")).click();

        //RABAT
        driver.findElement(By.id("mat-option-0")).click();
        //TANGER
        //driver.findElement(By.id("mat-option-1")).click();

//        CATEGORY (No CLICK NEEDED)
      //  driver.findElement(By.id("mat-select-2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-select-4")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-select-4")));
        Thread.sleep(3000);
//        SUBCATEGORY
        driver.findElement(By.id("mat-select-4")).click();

//        BUSINNES PROFESSIONAL VISA
        //driver.findElement(By.id("mat-option-3")).click();
//        TOURIST VISA
        driver.findElement(By.id("mat-option-4")).click();
//        VISITING FAMILY AND FRIEND
        //driver.findElement(By.id("mat-option-5")).click();

        String submitBtn = "document.getElementsByTagName(\"button\")[1].click();";
        js.executeScript(submitBtn);


        return "ok";
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            if (element.isDisplayed())
                return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }
}
