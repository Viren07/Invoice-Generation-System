

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {
    private Sample parent;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    public void setParent(Sample parent) {
        this.parent = parent;
    }

    @FXML
    public void registerButtonAction() {
        // Add registration logic here
    }

    @FXML
    public void loginButtonAction() {
        parent.openLoginView();
    }
}
