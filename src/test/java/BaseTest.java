import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class BaseTest {

    private AppiumDriver appiumDriver;
    @BeforeSuite
    public void beforeSuite() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Samsung s20");
        capabilities.setCapability("udid", "RF8N21BJSQM");
        capabilities.setCapability("appPackage", "com.meinvodafone.rn.dev"); // Example app
        capabilities.setCapability("appActivity", "com.meinvodafone.rn.dev.MainActivity");
        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub") , capabilities);
    }



    @Test
    public void tst() throws IOException {
        File file = new File("/Users/mahrous.ali/Documents/AddFileAndroid/src/test/resources/Data.txt");
        String deviceFilePath = "/storage/emulated/0/Download/file.txt";
        ((AndroidDriver) appiumDriver).pushFile(deviceFilePath, file);
        System.out.println("File uploaded successfully to the Downloads folder!");



        byte[] fileDataGET = appiumDriver.pullFile(deviceFilePath);
        File localFile = new File("/Users/mahrous.ali/Documents/AddFileAndroid/src/test/resources/StoredFile.txt");
        try (FileOutputStream fos = new FileOutputStream(localFile)) {
            fos.write(fileDataGET);
        }

    }

    @AfterSuite
    public void tearDown(){
        appiumDriver.quit();
    }
}
