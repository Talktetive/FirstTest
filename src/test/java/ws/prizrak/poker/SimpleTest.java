package ws.prizrak.poker;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.concurrent.TimeUnit;

//import java.io.IOException;

public class SimpleTest extends Screenshot_1 {

    protected static String login = "";    //для получения переменной во всех методах
    protected static String password = "";

    protected static long start;
    protected static long finish;


    @BeforeClass
    public static void goToWebPage() throws Exception {
        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
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
        public void userLogin () /*throws IOException, AWTException*/ {

            finish = System.currentTimeMillis();
            long totalTime = finish - start;
            //int timeLog = (int) totalTime;
            System.out.println("Total Time for page load - "+ totalTime);

            //Pinguin - закрытие выплывашки
            driver.findElement(By.xpath("#index > div.left-modal.active > button")).click();




            /*ниже вызов метода создания скрина - в скобках написано название для файла*/
            takeScreenshot("EnterData");



            //if (Assert.assertEquals("Андрей Покровский Админ", nameUser))

            //logBrowserConsoleLogs();
        }
    }