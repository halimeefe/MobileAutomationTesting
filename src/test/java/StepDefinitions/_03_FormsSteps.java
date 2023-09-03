package StepDefinitions;

import Pages.FormsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static Utilities.DriverManager.getAppiumDriver;

public class _03_FormsSteps {

    WebDriverWait wait=new WebDriverWait(getAppiumDriver(), Duration.ofSeconds(20));
    FormsPage formsPage=new FormsPage();

    @When("Navigate To Forms Page")
    public void navigateToFormsPage(){
        getAppiumDriver().findElement(formsPage.formsButton).click();
    }

    @When("User Turn On Switch")
    public void userTurnOnSwitch() {
        String switchTextBeforeClick =getAppiumDriver().findElement(formsPage.switchText).getText();
        Assert.assertEquals(switchTextBeforeClick,"Click to turn the switch ON"); // switch buttona tıklanmadan önceki texti assert ettik
        System.out.println("switch text before click :"+switchTextBeforeClick); // konsola yazdırdık
        getAppiumDriver().findElement(formsPage.switchButton).click(); // switch buttona tıkladık



    }

    @Then("The Switch Should Be  Turned On")
    public void theSwitchShouldBeTurnedOn() {
        String switchTextAfterClick= getAppiumDriver().findElement(formsPage.switchText).getText();
        Assert.assertEquals(switchTextAfterClick,"Click to turn the switch OFF"); // tıklamadan sonra switch text off konumuna geçmeli
        System.out.println("switch text after click:"+switchTextAfterClick); // after click ten sonra görünen switch texti konsolda yazdırmak istedik

    }
    @When("User Opens Dropdown Menu")
    public void userOpensDropdownMenu() {
        getAppiumDriver().findElement(formsPage.dropDownButton).click();
    }

    @And("Selects Second Option")
    public void selectsSecondOption() {
        getAppiumDriver().findElement(formsPage.secondOption).click();
    }

    @Then("Second Option Should Be Selected")
    public void secondOptionShouldBeSelected() {
        System.out.println("Selected option is = " + getAppiumDriver().findElement(formsPage.actualSelectedOption).getText());
        getAppiumDriver().findElement(formsPage.dropDownButton).click();
        String isSelected = getAppiumDriver().findElement(formsPage.secondOption).getAttribute("checked");
        Assert.assertEquals(isSelected,"true");
        System.out.println("Is element selected = " + isSelected);
    }
}



