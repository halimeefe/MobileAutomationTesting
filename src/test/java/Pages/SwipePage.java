package Pages;

import Utilities.DriverManager;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class SwipePage extends DriverManager {

    public SwipePage(){
        PageFactory.initElements(getAppiumDriver(),this);

    }
    public AppiumBy swipeMenu =new AppiumBy.ByAccessibilityId("Swipe");
    public By swipePageText=By.xpath("//*[@text='Swipe horizontal']");

    public By ScrollDownVerify =By.xpath("//*[@text='You found me!!!']");

    public By scrollRightVerify =By.xpath("//*[@text='GREAT COMMUNITY']");

    public AppiumBy logo = new AppiumBy.ByAccessibilityId("WebdriverIO logo");

    public By allButtons=By.xpath("//android.view.ViewGroup[@content-desc=\"Carousel\"]/android.view.ViewGroup/android.view.ViewGroup");

    public By lastElement=By.xpath("//*[@text='COMPATIBLE']");


    }


