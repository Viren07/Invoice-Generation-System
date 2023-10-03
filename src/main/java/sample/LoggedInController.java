package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInController implements Initializable
{
    // Initializing the widgets inorder to interact with them.
    @FXML
    private Button button_BACK;

    @FXML
    private Button button_OK;

    @FXML
    private PasswordField passwordField_OTP;

    @FXML
    private Label label_registration_done;
    @FXML
    private Label label_provide_OTP_2;

    @FXML
    private Label label_provide_OTP;

    //for going back
    @Override
    public void initialize (URL location, ResourceBundle resources)
    {

        button_BACK.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
                public void handle(ActionEvent event)
            {
                DBUtils.changeScene(event, "sample/login.fxml", "Login", null);
            }

        });


    }

    //for the text
    public void setUserInformation()
    {
        label_registration_done.setText("REGISTRATION DONE");
        label_provide_OTP.setText("PLEASE PROVIDE OTP ");
        label_provide_OTP_2.setText("TO CONTINUE");
    }

}
