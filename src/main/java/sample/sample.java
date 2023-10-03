import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.LoginController;

public class Sample extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        openLoginView();
    }

    public void openRegistrationView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
            Parent root = loader.load();

            RegistrationController registrationController = loader.getController();
            registrationController.setParent(this);

            primaryStage.setTitle("Registration");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setParent(this);

            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
