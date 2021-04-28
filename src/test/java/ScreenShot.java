import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class ScreenShot  {


    public String takeScreenshot(WebDriver webDriver) throws IOException  {
        try {

            File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

            Random random = new Random();
            String fileName = "Screenshot" + random.nextInt(10000);

            System.out.println(1);
            Files.move
                    (Paths.get(screenshot.getAbsolutePath()),
                            Paths.get(System.getProperty("user.dir") + "/Report/" + fileName + ".png"));
            System.out.println(2);
            return fileName + ".png";

        }
        catch(Exception e)
        {
            System.out.println(3);
            return null;
        }
    }
}
