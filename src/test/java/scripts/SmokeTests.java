package scripts;

import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;
import pages.SignUpPage;

import java.util.Random;

public class SmokeTests extends AbstractTest{

    @Test
    public void signUpLoginLogout(){
        String email = "email" + new Random().nextLong() + "@email.com", pswd = "123", name = "name";
        LoginPage loginPage = (LoginPage)new HomePage(driver)
                .load()// 1
                .checkPageElements(false)
                .goToURL("http://localhost:5000/profile")// 2
                .checkText("Please log in to access this page.")
                .checkURL("http://localhost:5000/login?next=%2Fprofile");
        SignUpPage signUpPage = (SignUpPage)loginPage
                .checkPageElements(false)
                .clickOnElement(LoginPage.linkSignUp);// 3
        signUpPage.checkPageElements(false);
        // 4
        loginPage = (LoginPage)signUpPage
                .inputSignUpData(email,pswd, name)
                .clickOnElement(SignUpPage.buttonSignUp);
        ProfilePage profilePage = (ProfilePage)loginPage
                .checkPageElements(false)
                .inputLoginData(email, pswd, false)// 5
                .clickOnElement(LoginPage.buttonLogin);
        HomePage homePage = (HomePage)profilePage
                .checkPageElements(true)
                .checkText("Welcome, " + name + "!")
                .clickOnElement(ProfilePage.linkLogout);// 6
        homePage.checkPageElements(false);
    }

    @Test
    public void doubleLogin(){
        String email1 = "email" + new Random().nextLong() + "@email.com",
                email2 = "email" + new Random().nextLong() + "@email.com",
                pswd = "123", name1 = "name1", name2 = "name2";
        ProfilePage profilePage = (ProfilePage) new LoginPage(driver)
                .load()// 1
                .checkPageElements(false)
                .inputLoginData(email1, pswd, false)// 2
                .clickOnElement(LoginPage.buttonLogin);
        LoginPage loginPage = (LoginPage) profilePage
                .checkPageElements(true)
                .checkText("Welcome, " + name1 + "!")
                .goToURL("http://localhost:5000/login");// 3
        HomePage homePage = (HomePage) loginPage.inputLoginData(email2, pswd, false)// 4
                .clickOnElement(LoginPage.buttonLogin)
                .checkText("Welcome, " + name2 + "!")
                .clickOnElement(ProfilePage.linkLogout);// 5
        homePage.checkPageElements(false);
    }

    @Test
    public void loginWithRememberMe(){
        String email = "rememberme@email.com", pswd = "123", name = "name";
        ProfilePage profilePage = (ProfilePage) new LoginPage(driver)
                .load()// 1
                .checkPageElements(false)
                .inputLoginData(email, pswd, true)// 2
                .clickOnElement(LoginPage.buttonLogin);
        profilePage.checkPageElements(true)
                .checkText("Welcome, " + name + "!")
                .closeTab();// 3
        HomePage homePage = (HomePage) new HomePage(driver)// 4
                .load()
                .clickOnElement(LoginPage.linkProfile)// 5
                .checkText("Welcome, " + name + "!")
                .checkPageElements(true)
                .clickOnElement(ProfilePage.linkLogout);// 6
        homePage.checkPageElements(false);
    }

    @Test
    public void signUpWithSignedUpEmail(){
        String email = "already@sign.up", pswd = "123", name = "name";
        SignUpPage signUpPage = (SignUpPage) new SignUpPage(driver)
                .load()// 1
                .checkPageElements(false)
                .inputSignUpData(email, pswd, name)// 2
                .clickOnElement(SignUpPage.buttonSignUp);
        signUpPage.checkText("Email address already exists. Go to")
                .checkText("login page");
    }
}
