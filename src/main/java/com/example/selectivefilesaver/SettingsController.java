package com.example.selectivefilesaver;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class SettingsController {

    // Constants
    private final String DARK = "Dark";
    private final int DEFAULT_FONT_SIZE = 12;


    // Fields
    private String[] themeSelectorOptions = {DARK};
    private String currentTheme;

    // Theme setting objects
    @FXML
    private ComboBox themeSelectorComboBox;


    // Font settings objects
    @FXML
    private TextField fontSizeTextField;

    @FXML
    private Button fontSizeButton;


    // Miscellaneous methods
    @FXML
    private void initialize(){
        if(fontSizeTextField.getText() == null){
            fontSizeTextField.setText("12");
        }
    }


    // Font settings methods
    @FXML
    public int setFontSizeButton(){
        if(fontSizeTextField.getText() != null && !Objects.equals(fontSizeTextField.getText(), "")){
            return Integer.parseInt(fontSizeTextField.getText());
        }
        else{
            return DEFAULT_FONT_SIZE;
        }
    }


    // Theme setting methods
    @FXML
    private void selectThemePopulate(MouseEvent event){
        themeSelectorComboBox.setItems(FXCollections.observableArrayList(themeSelectorOptions));
    }

    @FXML
    public String setCurrentTheme(){
        if(!Objects.equals(themeSelectorComboBox.getValue(), currentTheme)){
            currentTheme = themeSelectorComboBox.getValue().toString();
        }
        return currentTheme;
    }

}
