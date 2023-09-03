package Pages;

import Utilities.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormsPage extends DriverManager {

    public FormsPage() {
        PageFactory.initElements(getAppiumDriver(), this);

    }

    public AppiumBy formsButton = (AppiumBy) AppiumBy.accessibilityId("Forms");

    // DROPDOWN LOCATORS

    public AppiumBy dropDownButton = (AppiumBy) AppiumBy.accessibilityId("Dropdown");
    public By secondOption = By.xpath("//*[@text='Appium is awesome']");
    public By actualSelectedOption = By.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.widget.EditText");


    //SWİTCH LOCATORS

    public AppiumBy switchText = (AppiumBy) AppiumBy.accessibilityId("switch-text");
    public AppiumBy switchButton = (AppiumBy) AppiumBy.accessibilityId("switch");
}

