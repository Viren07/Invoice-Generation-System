package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class DBUtils {
        //public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
//        Parent root = null;
//        try {
//            FXMLLoader loader = new FXMLLoader(SceneUtils.class.getResource(fxmlFile));
//            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
//            root = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setTitle(title);
//        stage.setScene(new Scene(root, 600, 400));  // Set the loaded root as the scene content
//        stage.show();
//    }
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        try {
            FXMLLoader loader = new FXMLLoader();
            InputStream fxmlStream = DBUtils.class.getResourceAsStream(fxmlFile);

            if (fxmlStream == null) {
                throw new NullPointerException("FXML file not found: " + fxmlFile);
            }
            Parent root = loader.load(fxmlStream);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        }

        // Registration
    public static void RegisterUser(ActionEvent event, String User_Name, String User_Password, String phone_number, String First_Name, String Last_Name) {
        Connection connection = null;
        PreparedStatement psInset = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            // Connecting to Database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:1234/postgres", "postgres", "Reset@2302");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM UserData WHERE User_Name = ?");
            psCheckUserExists.setString(1, User_Name);
            resultSet = psCheckUserExists.executeQuery();

            // Alert if username is already taken
            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();

            } else {
                // Putting values in DBMS
                psInset = connection.prepareStatement("INSERT INTO UserData (User_Name, User_Password, First_Name, Last_Name, phone_number) VALUES (?, ?, ?, ?, ?)");
                psInset.setString(1, User_Name);
                psInset.setString(2, User_Password);
                psInset.setString(3, First_Name);
                psInset.setString(4, Last_Name);
                psInset.setString(5, phone_number);
                psInset.executeUpdate();

                changeScene(event, "/sample/logged-in.fxml", "OTP", null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInset != null) {
                try {
                    psInset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Logging in
    public static void loginUser(ActionEvent event, String User_Name, String User_Password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:1234/postgres", "postgres", "230207");
            preparedStatement = connection.prepareStatement("SELECT User_Password FROM UserData WHERE User_NAME = ?");
            preparedStatement.setString(1, User_Name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User not found in database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedUser_Password = resultSet.getString("User_Password");

                    if (retrievedUser_Password.equals(User_Password)) {
                        changeScene(event, "/sample/logged-in.fxml", "OTP", null);
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are wrong!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
