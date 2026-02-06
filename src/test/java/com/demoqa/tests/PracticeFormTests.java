package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.forms.PracticeFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class PracticeFormTests extends TestBase {

    PracticeFormPage practiceForm;

    @BeforeEach
    public void precondition() {
        practiceForm = new PracticeFormPage(driver);
        new HomePage(driver).getForms();
        new SidePanel(driver).getPracticeForm();
    }

    @Test
    public void createAccountPositiveTest() {
        practiceForm.enterPersonalData("Jamal","Musiala","jam@gm.com","1234567890")
                .selectGender("Male")
                .typeOfDate("16 Aug 1987")
                .addSubject(new String[]{"Maths","English"})
                .selectHobbies(new String[]{"Sports","Music"})
                .uploadFile("/Users/baker/Tools/1.png")
                .inputState("NCR")
                .inputCity("Delhi")
                .submit()
                .verifySuccessRegistration("Thanks for submitting the form")
                ;
    }

    @RepeatedTest(value = 3,name = "{displayName}{currentRepetition}/{totalRepetitions}")
    @DisplayName("Verify phone validation and try to get error ")
    public void createAccountWithInvalidPhoneNegativeTest() {
        practiceForm.enterPersonalData("Jamal","Musiala","jam@gm.com","1234")
                .selectGender("Male")
                .selectDate("August","1987","16")
                .addSubject(new String[]{"Maths","English"})
                .selectHobbies(new String[]{"Sports","Music"})
                .uploadFile("/Users/baker/Tools/1.png")
                .inputState("NCR")
                .inputCity("Delhi")
                .submit()
               // .verifyRedBorderColor("rgba(206, 212, 218, 1)")
                .verifyFormTitle("Student Registration Form")
        ;
    }
}
