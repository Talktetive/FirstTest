package ws.prizrak.poker;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SimpleTest extends Screenshot_1 {

    protected static String login = "";    //для получения переменной во всех методах
    protected static String password = "";
    protected static long start;
    protected static long finish;
    protected static JavascriptExecutor jse = (JavascriptExecutor)driver;

    @BeforeClass
    public static void goToWebPage() throws Exception {
        driver.manage().window().maximize();
        //driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String url = "https://pinguin-studio.com.ua/";
        start = System.currentTimeMillis();
        driver.get(url);

        // убрать курсор в угол экрана
        Robot bot = new Robot();
        bot.mouseMove(0, 0);

        System.out.println("Заданы кофигурации тестирования" + "\n" + "Старт страницы: " + url);
        //---Ниже две строки для того что бы сохранить переменные и использовать их вызовом.---//
        login = "admin@example.com";
        password = "admin";
    }
        @Test
        public void userLogin () throws InterruptedException /*throws IOException, AWTException*/ {
            //Измерение загрузки страницы в секундах
            finish = System.currentTimeMillis();
            long totalTime = finish - start;
            Double secondLoad = (totalTime / 1000.0);
            System.out.println("Время загрузки главной страницы сраницы на данном ПК - " + secondLoad + " сек.");

            //Pinguin - закрытие выплывашки
            driver.findElement(By.cssSelector("#index > div.left-modal.active > button")).click();
            takeScreenshot("Start_Page");

            //Создание листа для вкладок
            ((JavascriptExecutor)driver).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            //Запуск новой вкладки
            driver.switchTo().window(tabs.get(1));
            long start2 = System.currentTimeMillis();
            driver.get("https://pinguin-studio.com.ua/portfolio.html");

            finish = System.currentTimeMillis();
            System.out.println("Время загрузки сраницы portfolio.html на данном ПК - " + ((finish - start2)/1000.0) + " сек.");

            driver.findElement(By.cssSelector("#portfolio > div:nth-child(4) > div.for-button > button")).click();
            Thread.sleep(3000);
            jse.executeScript("scroll(0, 800);");

            Thread.sleep(3000);

            /*ниже вызов метода создания скрина - в скобках написано название для файла*/
            takeScreenshot("Portfolio");

            //if (Assert.assertEquals("Андрей Покровский Админ", nameUser))

            //logBrowserConsoleLogs();
        }
    }