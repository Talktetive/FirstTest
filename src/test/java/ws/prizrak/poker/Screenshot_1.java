package ws.prizrak.poker;
/*--------------------------------------------------
Рабочее тело скрина для веб страниц
В теле теста где необходимо сделать скрин необходимо вставить строку:
takeScreenshot("Название скрина");
--------------------------------------------------*/
        import org.junit.AfterClass;
        import org.junit.BeforeClass;
        import org.junit.Rule;
        import org.junit.rules.TestWatcher;
        import org.junit.runner.Description;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.logging.LogEntries;
        import org.openqa.selenium.logging.LogEntry;
        import org.openqa.selenium.logging.LogType;

        import java.io.File;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;

        import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Screenshot_1 {



    @Rule
    public FailureTestWatcher testWatcher = new FailureTestWatcher();

    protected static WebDriver driver;

    @BeforeClass
    public static void setupChrome(){
        //следующие строки запускают хром без уведомлений
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public static void closeChrome(){
        driver.quit();
    }

    public void takeScreenshot(String fileName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            /*--Ниже написан метод создания категории если её нет и сохранение её в строке "pathDir"--*/
            String pathDir = "screenshot";
            Files.createDirectories(Paths.get(pathDir));

            //--Ниже написан метод для сохраннения даты и времени в названии скрина
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
            String dateStr = dateFormat.format(cal.getTime());

            //--Ниже указан путь, имя файла, дата и время, разрешение файла
            File destination = new File(pathDir + "\\Scr-" + fileName + dateStr + ".jpg");
            Files.copy(scrFile.toPath(), destination.toPath(), REPLACE_EXISTING);

            System.out.println("\n=================== SCREENSHOT ========================");
            System.out.println("Saved to: " + destination.getAbsolutePath());
            System.out.println("=======================================================\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logBrowserConsoleLogs() {
        System.out.println("================== BROWSER LOGS =======================");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        System.out.println("=======================================================");
    }

    public class FailureTestWatcher extends TestWatcher {

        protected void failed(Throwable e, Description description) {
            // Make the filename safe to write to disk
            String testName = description.getMethodName();
            String safeFileName = testName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
            takeScreenshot(safeFileName);

            logBrowserConsoleLogs();
        }
    }
}