package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateInvoiceController implements Initializable {

    @FXML
    private Button button_upload;

    @FXML
    private TextField textField_rate;

    @FXML
    private TextField textField_nightAllw;

    @FXML
    private TextField textField_weekendCoeff;

    @FXML
    private TextField textField_CompName;

    @FXML
    private TextField textField_CompAddrs;

    @FXML
    private TextField textField_CompBank;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_upload.setOnAction(e -> uploadFile());
    }

    private void uploadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(button_upload.getScene().getWindow());

        if (selectedFile != null) {
            try {
                parseAndInsertData(selectedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void parseAndInsertData(File file) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header row
                }

                String[] data = line.split(",");
                String dayHours = data[1];
                String nightHours = data[2];
                String empName = data[3];
                String empID = data[4];

                insertDataIntoTimesheetData(dayHours, nightHours, empName, empID);
                insertDataIntoEmployeeData(empName, empID);
            }
        }
    }

    private void insertDataIntoTimesheetData(String dayHours, String nightHours, String empName, String empID) {
        String sql = "INSERT INTO Timesheet_Data (Employee_Day_Hours, Employee_Night_Hours, Employee_Name, Employee_Id) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:1234/postgres", "postgres", "123");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dayHours);
            statement.setString(2, nightHours);
            statement.setString(3, empName);
            statement.setString(4, empID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertDataIntoEmployeeData(String empName, String empID) {
        String sql = "INSERT INTO Employee_Data (Employee_Name, Employee_Id) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:1234/postgres", "postgres", "123");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, empName);
            statement.setString(2, empID);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void saveCompanyData() {
        String compName = textField_CompName.getText();
        String compAddrs = textField_CompAddrs.getText();
        String compBank = textField_CompBank.getText();
        String nightAllowance = textField_nightAllw.getText();
        String weekendCoeff = textField_weekendCoeff.getText();

        // Insert data into Rules table
        insertDataIntoRules(nightAllowance, weekendCoeff);

        // Insert data into Company_Data table
        insertDataIntoCompanyData(compName, compAddrs, compBank);
    }

    private void insertDataIntoRules(String nightAllowance, String weekendCoeff) {
        String sql = "INSERT INTO Rules (Nightshift_Coefficient, Weekend_Coefficient) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:1234/postgres", "postgres", "123");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nightAllowance);
            statement.setString(2, weekendCoeff);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertDataIntoCompanyData(String compName, String compAddrs, String compBank) {
        String sql = "INSERT INTO Company_Data (Company_Name, Company_Address, Banking_Info) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:1234/postgres", "postgres", "123");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, compName);
            statement.setString(2, compAddrs);
            statement.setString(3, compBank);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
