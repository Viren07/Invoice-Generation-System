package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewInvoiceController implements Initializable
{
    @FXML
    private RadioButton radiobutton_jan;

    @FXML
    private RadioButton radiobutton_feb;

    @FXML
    private RadioButton radiobutton_mar;

    @FXML
    private RadioButton radiobutton_apr;

    @FXML
    private RadioButton radiobutton_may;

    @FXML
    private RadioButton radiobutton_june;

    @FXML
    private RadioButton radiobutton_july;

    @FXML
    private RadioButton radiobutton_aug;

    @FXML
    private RadioButton radiobutton_sept;

    @FXML
    private RadioButton radiobutton_oct;

    @FXML
    private RadioButton radiobutton_nov;

    @FXML
    private RadioButton radiobutton_dec;

    @FXML
    private RadioButton radiobutton_2023;

    @FXML
    private RadioButton radiobutton_2024;

    @FXML
    private RadioButton radiobutton_2025;

    @FXML
    private RadioButton radiobutton_2026;

    @FXML
    private RadioButton radiobutton_2027;

    @FXML
    private RadioButton radiobutton_2028;

    @FXML
    private Button openButton;

    private ToggleGroup yearGroup;
    private ToggleGroup monthGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        yearGroup = new ToggleGroup();
        monthGroup = new ToggleGroup();

        // Add radio buttons for year selection to the yearGroup
        radiobutton_2023.setToggleGroup(yearGroup);
        radiobutton_2024.setToggleGroup(yearGroup);
        radiobutton_2025.setToggleGroup(yearGroup);
        radiobutton_2026.setToggleGroup(yearGroup);
        radiobutton_2027.setToggleGroup(yearGroup);
        radiobutton_2028.setToggleGroup(yearGroup);

        // Add radio buttons for month selection to the monthGroup
        radiobutton_jan.setToggleGroup(monthGroup);
        radiobutton_feb.setToggleGroup(monthGroup);
        radiobutton_mar.setToggleGroup(monthGroup);
        radiobutton_apr.setToggleGroup(monthGroup);
        radiobutton_may.setToggleGroup(monthGroup);
        radiobutton_june.setToggleGroup(monthGroup);
        radiobutton_july.setToggleGroup(monthGroup);
        radiobutton_aug.setToggleGroup(monthGroup);
        radiobutton_sept.setToggleGroup(monthGroup);
        radiobutton_oct.setToggleGroup(monthGroup);
        radiobutton_nov.setToggleGroup(monthGroup);
        radiobutton_dec.setToggleGroup(monthGroup);

        // Set the "Open File Explorer" button's action
        openButton.setOnAction(e -> openFileExplorer());
    }

    private void openFileExplorer()
    {
        RadioButton selectedYearButton = (RadioButton) yearGroup.getSelectedToggle();
        RadioButton selectedMonthButton = (RadioButton) monthGroup.getSelectedToggle();

        if (selectedYearButton != null && selectedMonthButton != null)
        {
            String year = selectedYearButton.getId().replace("radiobutton_", "");
            String month = selectedMonthButton.getId().replace("radiobutton_", "");

            String folderPath = "C:\\Your\\Base\\Path\\" + year + "\\" + month;
            File folder = new File(folderPath);

            if (folder.exists() && folder.isDirectory())
            {
                try
                {
                    Desktop.getDesktop().open(folder);
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                System.out.println("Folder does not exist: " + folderPath);
            }
        }
    }
}
