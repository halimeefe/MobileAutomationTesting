package Tests;

import Pages.DragPage;
import Pages.FormsPage;
import Pages.LoginPage;
import Pages.SwipePage;
import Utilities.DriverManager;
import Utilities.PageActionsHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class Tests extends DriverManager {

    AndroidDriver driver;
    WebDriverWait wait;
     LoginPage loginPage=new LoginPage();
    FormsPage formsPage =new FormsPage();
     SwipePage swipePage =new SwipePage();

     DragPage dragPage=new DragPage();

     PageActionsHelper pageActionsHelper =new PageActionsHelper();

   @BeforeTest
   public void setUp(){
       // getAppiumDriver() metodu çağrılarak AndroidDriver nesnesi alınır ve bekleme nesnesi oluşturulur.
       driver = getAppiumDriver();
       wait = new WebDriverWait(driver, Duration.ofSeconds(20));
   }

    // loginFunctionTest() metodu, oturum açma işlevselliğini test eder.
   @Test
    public void loginFunctionTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(getAppiumDriver(), Duration.ofSeconds(20));
       // Yüklenme sürelerini beklemek için 2 saniye beklenir.
        Thread.sleep(2000);



       // Menüye tıklamak için getAppiumDriver() metodu çağrılır ve findElement() yöntemi kullanılır.
       getAppiumDriver().findElement(loginPage.loginMenu).click();

       // Kullanıcı adı ve şifre alanlarına değerler göndermek için getAppiumDriver() metodu kullanılır ve findElement() yöntemiyle bulunur.

       getAppiumDriver().findElement(loginPage.usernameInputField).sendKeys("username123@gmail.com");

       getAppiumDriver().findElement(loginPage.passwordInputField).sendKeys("parola123!");

       // Giriş yapmak için giriş düğmesi tıklanır.

       getAppiumDriver().findElement(loginPage.loginButton).click();

       // login oldunuz mesajının locatorını yazdık ve görünür olana kadar bekletip textini aldık ve stringe atadık

       String actualText =wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.successfullLoginMesaj)).getText();

       // stringe atadıkdan sonra  konsolda texti görüp emin olmak istedik ve assert ile doğruladık
       System.out.println("Login message:"+ actualText);
       Assert.assertEquals(actualText,"You are logged in!");

       // Giriş başarılı olup olmadığını kontrol etmek için beklenir.

       wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginOkButton));

       // Başarılı giriş yapıldıktan sonra çıkış yapmak için düğmeye tıklanır.
       getAppiumDriver().findElement(loginPage.loginOkButton).click();

       // İşlemler tamamlandıktan sonra 3 saniye beklenir ve sürücü kapatılır.
       Thread.sleep(3000);



   }
   @Test
   public void DropDown(){


       getAppiumDriver().findElement(formsPage.formsButton).click();
       getAppiumDriver().findElement(formsPage.dropDownButton).click();
       getAppiumDriver().findElement(formsPage.secondOption).click();
       System.out.println("Selected option is="+ getAppiumDriver().findElement(formsPage.actualSelectedOption).getText());
       getAppiumDriver().findElement(formsPage.dropDownButton).click();  // dropdowna tekrar tıkladık 2. seçeneğin soluk renk olduğunu yani seçili olduğunu gördük -- checked = true
       String isSelected = getAppiumDriver().findElement(formsPage.secondOption).getAttribute("checked");
       Assert.assertEquals(isSelected,"true"); // true eşit olmalı çünkü öncesinde 2. seçeneği seçmiş olduk yani checked true olarak görünüyor
       System.out.println("Is element selected="+isSelected); //konsolda görmek istedik


   }
   @Test
   public  void Switch(){

       getAppiumDriver().findElement(formsPage.formsButton).click(); // forms a tıkladık
       String switchTextBeforeClick =getAppiumDriver().findElement(formsPage.switchText).getText();
       Assert.assertEquals(switchTextBeforeClick,"Click to turn the switch ON"); // switch buttona tıklanmadan önceki texti assert ettik
       System.out.println("switch text before click :"+switchTextBeforeClick); // konsola yazdırdık
       getAppiumDriver().findElement(formsPage.switchButton).click(); // switch buttona tıkladık
       String switchTextAfterClick= getAppiumDriver().findElement(formsPage.switchText).getText();
       Assert.assertEquals(switchTextAfterClick,"Click to turn the switch OFF"); // tıklamadan sonra switch text off konumuna geçmeli
       System.out.println("switch text after click:"+switchTextAfterClick); // after click ten sonra görünen switch texti konsolda yazdırmak istedik


   }
   @Test
   public  void SwitchButton(){
       AppiumBy formsButton = (AppiumBy) AppiumBy.accessibilityId("Forms");
       getAppiumDriver().findElement(formsButton).click();

       AppiumBy switchButtonClickFirst = (AppiumBy) AppiumBy.accessibilityId("switch");
       getAppiumDriver().findElement(switchButtonClickFirst).click();
       String firstClickText = getAppiumDriver().findElement(switchButtonClickFirst).getAttribute("checked");
       Assert.assertEquals(firstClickText,"true");
       System.out.println("switch button click first :"+switchButtonClickFirst);

       AppiumBy switchButtonClickSecond = (AppiumBy) AppiumBy.accessibilityId("switch");
       getAppiumDriver().findElement(switchButtonClickSecond).click();
       String secondClickText = getAppiumDriver().findElement(switchButtonClickFirst).getAttribute("checked");
       Assert.assertEquals(secondClickText,"false");
       System.out.println("switch button click second :"+switchButtonClickSecond);

   }

   @Test
   public  void ScrollDown(){
       getAppiumDriver().findElement(swipePage.swipeMenu).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.swipePageText));
        pageActionsHelper.performScroll("down");
        wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.logo)); // logo ile de doğrulamada yapabiliriz
      Assert.assertTrue(getAppiumDriver().findElement(swipePage.logo).isDisplayed());
       if(getAppiumDriver().findElement(By.xpath("//*[@text='You found me!!!']")).isDisplayed()){
           System.out.println("I found you!");
       }

//       wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.ScrollDownVerify)); // BU KISIM DA FARKLI BİR VERİFİCATİON
//       String swipeText =getAppiumDriver().findElement(swipePage.ScrollDownVerify).getText();
//       Assert.assertEquals(swipeText,"You found me!!!");
//       System.out.println("scroll down verify text :"+ swipeText);




   }

   @Test
   public void ScrollToRightEnd() {
       getAppiumDriver().findElement(swipePage.swipeMenu).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(swipePage.swipePageText));

       List<WebElement> listOffButtons = getAppiumDriver().findElements(swipePage.allButtons);

       for (int i = 0; i < listOffButtons.size(); i++) {
           pageActionsHelper.performScroll("right");

//           PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");  // BAŞKA BİR CLASSTA METODA ALDIK
//           Sequence sequence = new Sequence(finger1, 0)
//                   .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
//                   .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
//                   .addAction(new Pause(finger1, Duration.ofMillis(200)))
//                   .addAction(finger1.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), endX, endY))
//                   .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//           getAppiumDriver().perform(Collections.singletonList(sequence));


       }

       Assert.assertTrue(getAppiumDriver().findElement(swipePage.lastElement).isDisplayed());
   }

   @Test
   public void dragAndDrop() throws InterruptedException {

       getAppiumDriver().findElement(dragPage.dragMenu).click();

       String leftCenterRight = "lcr";
       for (int i = 1; i <= 3; i++) {
           for (int j = 0; j < leftCenterRight.length(); j++) {
               char letter = leftCenterRight.charAt(j);


               WebElement source = getAppiumDriver().findElement(AppiumBy.accessibilityId("drag-" + letter + i));
               WebElement target = getAppiumDriver().findElement(AppiumBy.accessibilityId("drop-" + letter + i));

               Point sourceElementCenter = new Point(source.getLocation().getX() + source.getSize().getWidth() / 2, source.getLocation().getY() + source.getSize().getHeight() / 2);
               Point targetElementCenter = new Point(target.getLocation().getX() + target.getSize().getWidth() / 2, target.getLocation().getY() + target.getSize().getHeight() / 2);

               PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
               Sequence sequence = new Sequence(finger1, 0)
                       .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
                       .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                       .addAction(new Pause(finger1, Duration.ofMillis(500)))
                       .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), targetElementCenter))
                       .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
               getAppiumDriver().perform(Collections.singletonList(sequence));

           }
       }
       Thread.sleep(3000);

       wait.until(ExpectedConditions.visibilityOfElementLocated(dragPage.successMessage));
       getAppiumDriver().findElement(dragPage.retryButton).click();
       Thread.sleep(3000);



   }


//       String rightVerify=getAppiumDriver().findElement(swipePage.scrollRightVerify).getText();
//       Assert.assertEquals(rightVerify,"GREAT COMMUNITY");
//       System.out.println( "scroll right verify:"+rightVerify);

       @AfterTest
       public void tearDown () {
           if (driver != null) {
               driver.quit();
           }
       }

   }













