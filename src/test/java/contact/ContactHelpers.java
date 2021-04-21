package contact;

import base.BaseTestCase;
import org.openqa.selenium.JavascriptExecutor;

public class ContactHelpers extends BaseTestCase {
    static final JavascriptExecutor executor = (JavascriptExecutor) BaseTestCase.driver;
    static final String FORENAME = "Will";
    static final String EMAIL = "will@me.com";
    static final String BAD_EMAIL = "will@abc";
    static final String MESSAGE = "May I speak to the owner?";
}
