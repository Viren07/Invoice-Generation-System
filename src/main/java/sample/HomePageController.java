package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable
{
    public Button button_viewpastinvoice;

    @FXML
    private Button button_createinvoice;

    @FXML
    private Button button_logout;

       @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        button_viewpastinvoice.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                DBUtils.changeScene(event,"sample/ViewInvoice.fxml", "View Invoice", null);
            }
        });

        button_logout.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                DBUtils.changeScene(event,"sample/login.fxml", "Login", null);
            }
        });

        button_createinvoice.setOnAction((new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                DBUtils.changeScene(event, "sample/CreateInvoice.fxml", "Create Invoice", null);
            }
        }));
    }
}