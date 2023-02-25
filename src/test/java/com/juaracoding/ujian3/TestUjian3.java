//Jawaban Test Ujian Minggu 3 Bootcamp SQA JuaraCoding Batch 8
//Created By : Muhammad Ilham Sunardi
package com.juaracoding.ujian3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestUjian3 {
    WebDriver driver;
    String chromeDriver = "webdriver.chrome.driver";
    String driverPath = "C:\\juaracoding\\ChromeDriver\\chromedriver.exe";
    String mainURL = "https://shop.demoqa.com/";
    String accountURL = "https://shop.demoqa.com/my-account/";
    String cartURL = "https://shop.demoqa.com/cart/";

    @BeforeClass
    public void testSetup() {
        System.setProperty(chromeDriver, driverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(mainURL);
        System.out.println("GET URL : "+mainURL);
        driver.manage().window().maximize();
        System.out.println("Browser window maximized");
        String titleHeader = driver.getTitle();
        System.out.println("Title : "+titleHeader);
    }

    @Test(priority = 1,testName = "LoginTestCase")
    public void loginTest() {
        System.out.println();
        System.out.println("======================================");
        System.out.println("Login test case");
        System.out.println("======================================");
        driver.findElement(By.xpath("/html/body/p/a")).click();
        System.out.println("Cookie reminder dismissed");
        delay(1);

        driver.findElement(By.xpath("//a[normalize-space()='My Account']")).click();
        System.out.println("Login page accessed");
        delay(1);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        System.out.println("Page Scrolled for 500 pixels");
        delay(1);

        driver.findElement(By.id("username")).sendKeys("mistermaun1010");
        System.out.println("Usename inputted");
        delay(1);

        driver.findElement(By.id("password")).sendKeys("Trolldier_TF2");
        System.out.println("Password inputted");
        delay(1);

        driver.findElement(By.xpath("//button[@name='login']")).click();
        System.out.println("Login button clicked");
        delay(1);

        //Verify login action
        String actualURL,expectedURL;
        actualURL = driver.getCurrentUrl();
        expectedURL = accountURL;
        Assert.assertEquals(actualURL,expectedURL);
        System.out.println("Login test done");
        System.out.println("======================================");
        System.out.println();
    }

    @Test(priority = 2,testName = "ItemCartTestCase")
    public void itemCartTest() {
        System.out.println("======================================");
        System.out.println("Item Cart test case");
        System.out.println("======================================");

        driver.navigate().to(mainURL);
        System.out.println("Back to main page");

        delay(2);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1750)");
        System.out.println("Page scrolled for 1750 pixels");

        delay(1);
        driver.findElement(By.xpath("//a[normalize-space()='red satin round neck backless maxi dress']")).click();
        System.out.println("Picked item: RED SATIN ROUND NECK BACKLESS MAXI DRESS");
        System.out.println("Move to Product Description Page");

        delay(2);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        System.out.println("Page scrolled for 500 pixels");

        delay(1);
        WebElement color = driver.findElement(By.id("pa_color"));
        Select selectColor = new Select(color);
        selectColor.selectByIndex(1);
        WebElement txtColor = selectColor.getFirstSelectedOption();
        String actualColor = txtColor.getText();

        WebElement size = driver.findElement(By.id("pa_size"));
        Select selectSize = new Select(size);
        selectSize.selectByIndex(2);
        WebElement txtSize = selectSize.getFirstSelectedOption();
        String actualSize = txtSize.getText();
        System.out.println("Color picked");
        delay(1);

        selectSize.selectByIndex(2);
        System.out.println("Size picked");
        delay(1);

        driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).click();
        delay(1);
        System.out.println("Add to cart button clicked");

        //Cart dropdown verification
        delay(2);
        Assert.assertEquals(actualColor,"Mauve");
        Assert.assertEquals(actualSize,"Medium");
        System.out.println("Item verification done");

        //Cart URL Verification
        driver.findElement(By.xpath("//a[@class='button wc-forward wp-element-button']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,cartURL);
        System.out.println("Cart URL verification done");
        System.out.println("Item Cart test done");
        System.out.println("======================================");
        System.out.println();
    }

    @AfterClass
    public void quitBrowser() {
        delay(3);
        driver.quit();
        System.out.println("Browser closed");
        System.out.println("Testing finished");
    }

    static void delay(long secs) {
        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
