package scripts;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;
import pages.SignUpPage;

public class SignUpTests extends AbstractTest{
    public static String[][] successfulSignUpDataProvider(){
        return new String[][] {{"qwe@email.com", "123", "qwe"},
                                {"123@email.com", "qwe", "123"},
                                {"qwe123@email.com", "qwe123", "qwe123"},
                                {"123qwe@email.com", "123qwe", "123qwe"},
                                {"q.we@email.com", "q.we", "q.we"},
                                {"qwe%@email.com", "qwe%", "qwe%"},
                                {"qwe?@email.com", "qwe?", "qwe?"},
                                {"qwe/*@email.com", "qwe/*", "qwe/*"},
                                {"qwe#&@email.com", "qwe#&", "qwe#&"},
                                {"QWe@email.com", "QWe", "QWe"},
                                {"qwe@email.com", "123", ""}};
    }

    @ParameterizedTest
    @MethodSource("successfulSignUpDataProvider")
    public void successfulSignUp(String email, String pswd, String name){
        SignUpPage signUpPage = new SignUpPage(driver);
        LoginPage loginPage = (LoginPage)signUpPage
                .load()
                .checkPageElements(false)
                .inputSignUpData(email, pswd, name)
                .clickOnElement(SignUpPage.buttonSignUp);
        loginPage.checkPageElements(true)
                .clickOnElement(LoginPage.linkLogout)
                .checkPageElements(false);
    }

    public static String[] invalidEmailSignUpDataProvider(){
        return new String[] {"qwe", "qwe@", "@qwe", "qwe@qwe", "qwe@qwe.",
                                "qwe@.com", "qwe.@com", ".qwe@com", "qwe@qwe.com.", "qwe@qwe&.com",
                                "qwe&@qwe.com", "qwe@qwe.com&", "мыло@мыло.ру", "", " "};
    }

    @ParameterizedTest
    @MethodSource("invalidEmailSignUpDataProvider")
    public void invalidEmailSignUp(String email){
        String pswd = "123", name = "";
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.load()
                .checkPageElements(false)
                .inputSignUpData(email, pswd, name)
                .clickOnElement(SignUpPage.buttonSignUp)
                .checkURL("http://localhost:5000/signup");
    }

    public static String[] invalidPasswordSignUpDataProvider(){
        return new String[] {"", " ", "й", "qweмыло", "123 ", " 123", "123 123"};
    }

    @ParameterizedTest
    @MethodSource("invalidPasswordSignUpDataProvider")
    public void invalidPasswordSignUp(String password, int num){
        String email = "bad" + num + "@psw.com", name = "";
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.load()
                .checkPageElements(false)
                .inputSignUpData(email, password, name)
                .clickOnElement(SignUpPage.buttonSignUp)
                .checkURL("http://localhost:5000/signup");
    }
}
