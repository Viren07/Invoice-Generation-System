package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private Sample parent;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void setParent(Sample parent) {
        this.parent = parent;
    }

    @FXML
    public void loginButtonAction() {
        // Add login logic here
    }

    @FXML
    public void registerButtonAction() {
        parent.openRegistrationView();
    }
}
