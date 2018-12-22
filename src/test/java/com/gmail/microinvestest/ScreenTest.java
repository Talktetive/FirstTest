package com.gmail.microinvestest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import ru.yandex.qatools.ashot.AShot;
//import ru.yandex.qatools.ashot.Screenshot;
//import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ScreenTest {
    private static WebDriver driver;
    private static String login = "";    //для получения переменной во всех методах
    private static String password = "";


    @BeforeClass //выполнение перед тестом
    public static void setup() throws IOException, HeadlessException, AWTException{
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = "http://kodaki-b2b.lan/login";
        driver.get(url);

        // убрать курсор в угол экрана
        Robot bot = new Robot();
        bot.mouseMove(0,0);

        System.out.println("Заданы кофигурации тестирования"+ "\n"+ "Старт страницы: " + url);
        login = "admin@example.com";
        password = "admin";
    }

    @Test
    public void userLogin() throws IOException, AWTException {
        WebElement loginField = driver.findElement(By.cssSelector("#loginform-email"));                         //01 поиск поля для ввода данных
        loginField.sendKeys(login);                                                                 //ввод данных
        WebElement passwordField = driver.findElement(By.cssSelector("#loginform-password"));                   //01
        passwordField.sendKeys(password);                                                           //ввод данных
        System.out.println("e-mail: "+login+"\n"+"password: "+password);

        WebElement loginButton = driver.findElement(By.cssSelector("#login-form > button"));                    //поиск кнопки входа
        loginButton.click();                                                                                    //нажатие на найденный елемент
        WebElement profileUser = driver.findElement(By.cssSelector("body > div > header > ul > li > a > b"));   //Поиск эллемент
        String nameUser = profileUser.getText();                                                                //Сохранение текста из еллемент
        System.out.println("Успешный вход - " + nameUser);
        Assert.assertEquals("Андрей Покровский Админ", nameUser);                                       //Сравнивание сохранённого с нужным текстом

//        Screenshot screenshot1 = new AShot().takeScreenshot(driver);
 //       Screenshot screenshot = new
//                AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
            /* В самом верху метод для создания скрина
            ScreenShot.Scr();
            */
    }

    @AfterClass //после завершения всех тестов
    public static void tearDown() {
        WebElement Dropmenu = driver.findElement(By.xpath("/html/body/div/header/ul/li/a"));
        Dropmenu.click();
        WebElement logoutButton = driver.findElement(By.xpath("/html/body/div/header/ul/li/ul/li[5]/a"));
        logoutButton.click();

        //WebElement LogOutPage = driver.findElement(By.xpath("/html/body/div/div[1]/section[1]/h1"));


        driver.quit();
    }

}
