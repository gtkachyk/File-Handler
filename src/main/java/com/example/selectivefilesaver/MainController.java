package com.example.selectivefilesaver;

import BackEnd.ActionHandler;
import BackEnd.BackEndErrors;
import BackEnd.DirectoryHandler;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainController {

    // Constants
    private final String DIRECTORY_ACTION_1 = "Copy to folder";
    private final String FILTER_1 = "Include by name";
    private final String FILTER_2 = "Include by extension";
    private final String FILTER_3 = "Exclude by name";
    private final String FILTER_4 = "Exclude by extension";
    private static final int MASTER_ANCHOR_PLANE_WIDTH = 250;
    private static final int ACTION_LOG_ANCHOR_PLANE_WIDTH = 280;
    private static final int STAGE_WIDTH_MINUS_VIEW_TEXT_AREA_WIDTH = 586;
    private static final int STAGE_HEIGHT_MINUS_VIEW_TEXT_AREA_HEIGHT = 161;
    private static final int VIEW_TEXT_AREA_INITIAL_WIDTH = 427;
    private static final int MAIN_STAGE_MIN_WIDTH = 800;
    private static final int MAIN_STAGE_MIN_HEIGHT = 650;

    // BackEnd objects.
    final DirectoryHandler directoryHandler = new DirectoryHandler();
    final ActionHandler actionHandler = new ActionHandler(directoryHandler);

    // Drop down menu options.
    private String[] selectActionComboBoxOptions;
    private String[] selectFilterOptions;

    // Tracking fields.
    private String currentAction;
    private String currentFilter;
    private boolean resetScrollBar = false; // If true, the vertical scroll bar of viewScrollPane will reset to the top whenever it reaches the bottom.

    // Miscellaneous variables.
    private Stage mainStage;

    // Miscellaneous objects.
    @FXML
    private VBox mainVBox;
    @FXML
    private SplitPane mainSplitPane;

    // Menu bar objects
    @FXML
    private MenuBar mainMenuBar;
    @FXML
    Menu fileMenu;
    @FXML
    Menu helpMenu;
    @FXML
    MenuItem selectSourceDirectoryMenuItem;
    @FXML
    private MenuItem refreshMenuItem;
    @FXML
    private MenuItem selectFileMenuItem;
    @FXML
    private MenuItem settingsMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private MenuItem aboutMenuItem;

    // HBox objects
    @FXML
    private HBox mainHBox;
    @FXML
    private Label leftStatusLabel;
    @FXML
    private Label rightStatusLabel;
    @FXML
    private Pane hBoxPane;

    // Master panel objects
    @FXML
    private AnchorPane masterAnchorPane;
    @FXML
    private Label nothingSelectedLabel;
    @FXML
    private ComboBox selectActionComboBox;
    @FXML
    private Label selectActionLabel;
    @FXML
    private ComboBox selectFilterComboBox;
    @FXML
    private Label selectFilterLabel;
    @FXML
    private Label fileContainsFilterLabel;
    @FXML
    private TextField filterTextField;
    @FXML
    private Button applyFilterButton;
    @FXML
    private Button undoFilterButton;
    @FXML
    private Button runButton;

    // View panel objects
    @FXML
    private ScrollPane viewScrollPane;
    ScrollBar verticalScrollBar;
    @FXML
    private AnchorPane viewAnchorPane;
    @FXML
    TextArea viewTextArea;

    // Action log objects
    @FXML
    private AnchorPane actionLogAnchorPane;
    @FXML
    TextArea actionLogTextArea;

    /**
     * Constructor.
     */
    public MainController(){

    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Miscellaneous methods ----------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Sets the initial properties of GUI objects and values of global data.
     */
    @FXML
    public void initialize(){
        mainStage = new Stage();

        // Clear text fields.
        filterTextField.clear();
        viewTextArea.clear();
        actionLogTextArea.clear();
        viewTextArea.setFont(Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        actionLogTextArea.setFont(Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 14));

        // Set sizes.
        masterAnchorPane.setMaxWidth(MASTER_ANCHOR_PLANE_WIDTH);
        masterAnchorPane.setMinWidth(MASTER_ANCHOR_PLANE_WIDTH);
        actionLogAnchorPane.setMaxWidth(ACTION_LOG_ANCHOR_PLANE_WIDTH);
        actionLogAnchorPane.setMinWidth(ACTION_LOG_ANCHOR_PLANE_WIDTH);
        viewTextArea.setPrefWidth(VIEW_TEXT_AREA_INITIAL_WIDTH);

        // Set global data.
        setSelectActionComboBoxOptions(new String[]{DIRECTORY_ACTION_1});
        setSelectFilterOptions(new String[]{FILTER_1, FILTER_2, FILTER_3, FILTER_4});
        setCurrentAction(null);
        setCurrentFilter(null);

        // Write starting message in view text area.
        viewTextArea.appendText("Welcome to File Handler!\n");
        viewTextArea.appendText("Please familiarize yourself with File Handler 1.0\n");
        viewTextArea.appendText("behaviours and limitations by clicking 'Help' then\n");
        viewTextArea.appendText("'About File Handler' and reading the first section.\n");
    }

    /**
     * Sets the initial properties of GUI objects.
     */
    public void setStage(Stage newStage){
        this.mainStage = newStage;

        // Resize scaling
        ChangeListener<Number> stageSizeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNumber, Number newNumber){ // Sets the size of objects when the size of the window changes.
                double scrollPaneWidth = mainStage.getWidth() - STAGE_WIDTH_MINUS_VIEW_TEXT_AREA_WIDTH;
                double scrollPaneHeight = mainStage.getHeight() - STAGE_HEIGHT_MINUS_VIEW_TEXT_AREA_HEIGHT;
                viewAnchorPane.setPrefWidth(scrollPaneWidth + 20);
                viewAnchorPane.setPrefHeight(scrollPaneHeight);
                viewTextArea.setMinWidth(scrollPaneWidth);
                viewTextArea.setMinHeight(scrollPaneHeight);
                viewTextArea.setMaxWidth(scrollPaneWidth);
                viewTextArea.setMaxHeight(scrollPaneHeight);
                actionLogTextArea.setPrefHeight(scrollPaneHeight);
            }
        };
        mainStage.widthProperty().addListener(stageSizeListener);
        mainStage.heightProperty().addListener(stageSizeListener);
        mainStage.setMinWidth(MAIN_STAGE_MIN_WIDTH);
        mainStage.setMinHeight(MAIN_STAGE_MIN_HEIGHT);

        // Add listener to vertical scroll bar of viewScrollPane.
        verticalScrollBar = (ScrollBar) viewScrollPane.lookup(".scroll-bar:vertical");
        verticalScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            double scrollPosition = newValue.doubleValue();
            if(scrollPosition == 1.0 && resetScrollBar){
                verticalScrollBar.valueProperty().setValue(0.0);
                resetScrollBar = false;
            }
        });
    }

    /**
     * Formats an Alert with predefined properties. The alert will have the default header text.
     */
    private Alert createAlert(Alert alert){
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        Scene scene = alert.getDialogPane().getScene();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("alertStyles.css")).toExternalForm());
        return alert;
        // alert.showAndWait();
    }

    /**
     * Formats an Alert with predefined properties. The alert will have custom header text.
     */
    private Alert createAlert(Alert alert, String headerText){
        alert.setHeaderText(headerText);
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        Scene scene = alert.getDialogPane().getScene();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("alertStyles.css")).toExternalForm());
        return alert;
//        alert.showAndWait();
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Menu bar methods ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Sets the properties of various objects after a new source directory is selected.
     */
    private void setGUIAfterNewSourceDirectorySelected(){
        // Set visibilities and values for action related objects.
        nothingSelectedLabel.setVisible(false);
        selectActionLabel.setVisible(true);
        selectActionComboBox.setVisible(true);
        selectActionComboBox.getSelectionModel().clearSelection(); // This is to clear the select action dropdown menu text.
        selectActionComboBox.setValue(null); // Clear the value of select action dropdown menu.
        setCurrentAction(null);
        runButton.setVisible(false);

        // Set visibilities and values for filter related objects.
        selectFilterLabel.setVisible(true);
        selectFilterComboBox.setVisible(true);
        selectFilterComboBox.getSelectionModel().clearSelection(); // This is to clear the select filter dropdown menu text.
        selectFilterComboBox.setValue(null); // Clear the value of select filter dropdown menu.
        setCurrentFilter(null);
        fileContainsFilterLabel.setVisible(false);
        filterTextField.setVisible(false);
        filterTextField.setText("");
        applyFilterButton.setVisible(false);
        undoFilterButton.setVisible(false); // Filters that were applied to a previous source directory are not re applied to newly selected source directories.
    }

    /**
     * Sets the properties of various objects after a new target directory is selected.
     */
    private void setGUIAfterNewTargetDirectorySelected(){
        // Set visibilities and values for action related objects.
        nothingSelectedLabel.setVisible(false);
        selectActionLabel.setVisible(true);
        selectActionComboBox.setVisible(true);
        selectActionComboBox.getSelectionModel().clearSelection(); // This is to clear the select action dropdown menu text.
        selectActionComboBox.setValue(null); // Clear the value of select action dropdown menu.
        setCurrentAction(null);
        runButton.setVisible(false);
    }

    /**
     * Opens a file explorer and passes the selected directory to processNewSourceDirectoryGUI.
     * The purpose of this method is to isolate the code responsible for showing the operating system's file explorer window.
     */
    @FXML
    private void selectSourceDirectory(){
        // Open the file browser at the current directory.
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("."));
        directoryChooser.setTitle("Select a source directory...");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        processNewSourceDirectoryGUI(selectedDirectory);
    }

    /**
     * Prompts directoryHandler to process a new source directory, then uses directoryHandler flags to adjust the GUI.
     * @param selectedDirectory the new source directory to process.
     */
    void processNewSourceDirectoryGUI(File selectedDirectory){
        boolean sourceDirectoryIsValid = directoryHandler.processNewSourceDirectory(selectedDirectory);
        if(sourceDirectoryIsValid){
            // Set GUI.
            setGUIAfterNewSourceDirectorySelected();

            // Update action log.
            actionLogTextArea.appendText("Source directory selected: \"" + directoryHandler.getSourceDirectoryPath() + "\"\n");

            // Update view area.
            updateViewPanel();

            // Warn user if source and target directory are the same.
            if(directoryHandler.isSourceEqualsTargetFlag()){
                createAlert(new Alert(Alert.AlertType.WARNING, BackEndErrors.WARNING_2_0.toString(), ButtonType.OK), "Warning (code 2.0)").showAndWait();
            }
        }
    }

    /**
     * Opens a file explorer and passes the selected directory to processNewTargetDirectoryGUI.
     * The purpose of this method is to isolate the code responsible for showing the operating system's file explorer window.
     */
    @FXML
    private void selectTargetDirectory(){
        // Open the file browser at the current directory.
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("."));
        directoryChooser.setTitle("Select a target directory...");
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        processNewTargetDirectoryGUI(selectedDirectory);
    }

    /**
     * Prompts directoryHandler to process a new target directory, then uses directoryHandler flags to adjust the GUI.
     * @param selectedDirectory the new target directory to process.
     */
    void processNewTargetDirectoryGUI(File selectedDirectory){
        boolean targetDirectoryIsValid = directoryHandler.processNewTargetDirectory(selectedDirectory);
        if(targetDirectoryIsValid){
            // Set GUI.
            setGUIAfterNewTargetDirectorySelected();

            // Update action log.
            actionLogTextArea.appendText("Target directory selected: \"" + directoryHandler.getTargetDirectoryPath() + "\"\n");

            // Update view area.
            updateViewPanel();

            // Warn user if source and target directory are the same.
            if(directoryHandler.isSourceEqualsTargetFlag()){
                createAlert(new Alert(Alert.AlertType.WARNING, BackEndErrors.WARNING_2_0.toString(), ButtonType.OK), "Warning (code 2.0)").showAndWait();
            }
        }
    }

    /**
     * Reflects changes to sourceDirectory and targetDirectory in the GUI and directoryHandler.
     * @return true if the source and target directories are valid after refreshing, false otherwise.
     */
    @FXML
    boolean refreshDirectories(){
        boolean sourceRefreshSuccessful = directoryHandler.refreshSourceDirectory();
        boolean targetRefreshSuccessful = directoryHandler.refreshTargetDirectory();

        updateViewPanel();
        actionLogTextArea.appendText("Source and target directories refreshed.\n");

        if(!sourceRefreshSuccessful && !targetRefreshSuccessful){
            createAlert(new Alert(Alert.AlertType.WARNING, BackEndErrors.WARNING_1_2.toString(), ButtonType.OK), "Warning (code 1.2)").showAndWait();
            return false;
        }
        else if(sourceRefreshSuccessful && !targetRefreshSuccessful){
            createAlert(new Alert(Alert.AlertType.WARNING, BackEndErrors.WARNING_1_1.toString(), ButtonType.OK), "Warning (code 1.1)").showAndWait();
            return false;
        }
        else if(!sourceRefreshSuccessful){
            createAlert(new Alert(Alert.AlertType.WARNING, BackEndErrors.WARNING_1_0.toString(), ButtonType.OK), "Warning (code 1.0)").showAndWait();
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Handles the process of quiting the application.
     */
    @FXML
    private void quitApplication(){
        Platform.exit();
    }

    /**
     * Displays the stage containing the settings menu when 'Settings' is pressed.
     */
    @FXML
    private void setSettingsMenuItem(ActionEvent event){
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("Settings.fxml")).openStream());

            stage.setScene(new Scene(root, 600, 600));
            SettingsController settingsController = fxmlLoader.getController();
            stage.showAndWait();
            updateSettings(settingsController.setFontSizeButton(), settingsController.setCurrentTheme());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Displays the stage containing application information when 'About File Handler' is pressed.
     */
    @FXML
    private void setAboutMenuItem(ActionEvent event){
        try{
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("About.fxml")).openStream());

            stage.setScene(new Scene(root, 900, 700));
            AboutController aboutController = fxmlLoader.getController();
            stage.setTitle("About File Handler");
            stage.showAndWait();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Master panel methods  ----------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    // --------------------------------------------- Action related methods  --------------------------------------

    /**
     * Adds options to the Select Action drop down menu.
     */
    @FXML
    private void selectActionPopulate(MouseEvent event){
        selectActionComboBox.setItems(FXCollections.observableArrayList(selectActionComboBoxOptions));
    }

    /**
     * Handles the case where an action is selected from the Select Action drop down menu.
     */
    @FXML
    private void setCurrentAction(){
        if(Objects.equals(selectActionComboBox.getValue(), currentAction)){ // If the current action is selected again.
            // Do nothing
        }
        else { // If a new action is selected.
            if(selectActionComboBox.getValue() != null){
                currentAction = selectActionComboBox.getValue().toString();
                runButton.setVisible(true);
            }
            else{
                currentAction = null;
                runButton.setVisible(false);
            }
        }
    }

    /**
     * Appends each entry in actionHandler.copyRecord to the action log.
     */
    private void printCopyRecordToActionLog(){
        for(String copyAction: actionHandler.copyRecord){
            actionLogTextArea.appendText(copyAction + "\n");
        }
        actionHandler.copyRecord.clear();
    }

    /**
     * Applies the selected action when 'run' is pressed.
     */
    @FXML
    private void setRunButton() {
        String confirmationMessage = actionHandler.generateCopyConfirmationMessage();
        if(confirmationMessage != null){
            Alert confirmation = createAlert(new Alert(Alert.AlertType.CONFIRMATION, confirmationMessage), "Confirm copy operation");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                if(actionHandler.copyActionPermitted()){
                    if(!actionHandler.copyFiles()){
                        Pair<String, String> errorMessage = actionHandler.generateCopyFileErrorMessage();
                        createAlert(new Alert(Alert.AlertType.ERROR, errorMessage.getKey(), ButtonType.OK), errorMessage.getValue()).showAndWait();
                    }
                    printCopyRecordToActionLog();
                    refreshDirectories();
                }
                else{
                    createAlert(new Alert(Alert.AlertType.ERROR, actionHandler.generateCopyActionPermittedErrorMessage(), ButtonType.OK), "Error (code 1.0)").showAndWait();
                }
            }
            else{
                actionLogTextArea.appendText("Copy action cancelled.\n");
            }
        }
        else{
            createAlert(new Alert(Alert.AlertType.ERROR, actionHandler.generateCopyActionPermittedErrorMessage(), ButtonType.OK), "Error (code 1.0)").showAndWait();
        }
    }

    // --------------------------------------------- Filter related methods  --------------------------------------

    /**
     * Adds options to the Select Filter drop down menu.
     */
    @FXML
    private void selectFilterPopulate(MouseEvent event){
        selectFilterComboBox.setItems(FXCollections.observableArrayList(selectFilterOptions));
    }

    /**
     * Sets the value of currentFilter only when the value of selectFilterComboBox changes.
     */
    @FXML
    private void setCurrentFilter(){
        if(Objects.equals(selectFilterComboBox.getValue(), currentFilter)){
        }
        else {
            currentFilter = selectFilterComboBox.getValue().toString();
            filterInputInitialize();
        }
    }

    /**
     * Sets the text in fileContainsFilterLabel whenever a new filter is selected.
     */
    private void filterInputInitialize(){
        switch (currentFilter){
            case FILTER_1:
                fileContainsFilterLabel.setText("Include files that contain:");
                break;
            case FILTER_2:
                fileContainsFilterLabel.setText("Include files with the extension:");
                break;
            case FILTER_3:
                fileContainsFilterLabel.setText("Exclude files that contain:");
                break;
            case FILTER_4:
                fileContainsFilterLabel.setText("Exclude files with the extension:");
                break;
        }
        fileContainsFilterLabel.setVisible(true);
        filterTextField.setVisible(true);
        applyFilterButton.setVisible(true);
    }

    /**
     * Applies the selected filter to the current source directory files when the 'Apply filter' button is pressed.
     */
    @FXML
    private void setApplyFilterButton(){
        if(directoryHandler.filterSourceFiles(currentFilter, filterTextField.getText())){
            actionLogTextArea.appendText("Applied filter " + directoryHandler.filterRecord.peek() + "\n");
            undoFilterButton.setVisible(true);

            // Update view area.
            updateViewPanel();
        }
        else{
            createAlert(new Alert(Alert.AlertType.ERROR, BackEndErrors.ERROR_2_0.toString(), ButtonType.OK), "Error (code 2.0)").showAndWait();
        }
    }

    /**
     * Restores the most recent version of currentSourceFiles stored in fileRecord when 'undo' is pressed.
     */
    @FXML
    private void setUndoFilterButton(){
        String filterApplicationUndone = directoryHandler.undoFilterSourceFiles();

        if(filterApplicationUndone != null){
            actionLogTextArea.appendText("Undid application of " + filterApplicationUndone + "\n");
            updateViewPanel();
            if(directoryHandler.filterRecord.size() <= 0){
                undoFilterButton.setVisible(false);
            }
        }
        else{
            createAlert(new Alert(Alert.AlertType.ERROR, BackEndErrors.ERROR_2_1.toString(), ButtonType.OK), "Error (code 2.1)").showAndWait();
            undoFilterButton.setVisible(false);
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- View panel methods -------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Prints the current source directory name and file contents to the view panel.
     */
    private void updateViewPanel(){
        // Display source and target directory names.
        viewTextArea.clear();
        if(!directoryHandler.isNullSourceDirectoryFlag()){
            viewTextArea.appendText("Current source directory: " + directoryHandler.getSourceDirectoryName() + "\n");
        }
        else{
            viewTextArea.appendText("Current source directory: <no source selected>\n");
        }
        if(!directoryHandler.isNullTargetDirectoryFlag()){
            viewTextArea.appendText("Current target directory: " + directoryHandler.getTargetDirectoryName() + "\n\n");
        }
        else{
            viewTextArea.appendText("Current target directory: <no target selected>\n\n");
        }

        // Display source and target directory contents.
        if(!directoryHandler.isNullSourceDirectoryFlag()){
            viewTextArea.appendText("--------------------SOURCE DIRECTORY FILES (" + directoryHandler.getSourceFileNames().size() + ")--------------------\n");
            for(String fileName: directoryHandler.getSourceFileNames()){;
                if(fileName != null){
                    viewTextArea.appendText(fileName + "\n");
                }
            }
            viewTextArea.appendText("\n");
        }

        if(!directoryHandler.isNullTargetDirectoryFlag()){
            viewTextArea.appendText("--------------------TARGET DIRECTORY FILES (" + directoryHandler.getTargetFileNames().size() + ")--------------------\n");
            for(String fileName: directoryHandler.getTargetFileNames()){;
                if(fileName != null){
                    viewTextArea.appendText(fileName + "\n");
                }
            }
        }
        resetScrollBar = true; // Set vertical scroll bar to top.
    }

    public void updateSettings(int newFontSize, String theme){
        viewTextArea.setFont(Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, newFontSize));
        actionLogTextArea.setFont(Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, newFontSize));
        updateViewPanel();
        printActionLog();

        updateTheme(theme);

        actionLogTextArea.appendText("Settings updated" + "\n");

    }

    private void updateTheme(String theme) {
        if(Objects.equals(theme, "Dark")) {
            mainVBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            mainMenuBar.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            mainSplitPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            masterAnchorPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            viewAnchorPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            actionLogAnchorPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            mainHBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            setScrollBarStyle(viewScrollPane, "-fx-background-color: black; -fx-border-color: black");
            setCornerStyle(viewScrollPane, "-fx-background-color: black; -fx-border-color: black");
            setDividerStyle(mainSplitPane, "-fx-background-color: black; -fx-border-color: black");
            setTextAreaStyle(viewTextArea, "-fx-background-color: grey; -fx-border-color: white");
            setTextAreaStyle(actionLogTextArea, "-fx-background-color: grey; -fx-border-color: white");
        }
    }

    private void refreshObjectSize(){
        viewScrollPane.setPrefWidth(1000 - masterAnchorPane.getWidth() - actionLogAnchorPane.getWidth());
        viewScrollPane.setPrefHeight(masterAnchorPane.getHeight());
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Action log methods -------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    private void printActionLog(){
        if(actionLogTextArea.getText() != null){
            String[] actionLogLines = actionLogTextArea.getText().split("\n");
            actionLogTextArea.clear();
            for(String i: actionLogLines){
                actionLogTextArea.appendText(i);
            }
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Style setting methods ----------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    // Methods for setting the style of scroll pane objects
    private void setScrollBarStyle(ScrollPane scrollPane, String style){
        for(Node node: scrollPane.lookupAll(".scroll-bar")){
            if(node instanceof ScrollBar){
                ScrollBar scrollBar = (ScrollBar) node;
                scrollBar.setStyle(style);
            }
        }
    }

    private void setCornerStyle(ScrollPane scrollPane, String style){
        for(Node node: scrollPane.lookupAll(".corner")){
            if(node instanceof StackPane){
                StackPane corner = (StackPane) node;
                corner.setStyle(style);
            }
        }
    }

    // Methods for setting the style of split pane objects
    private void setDividerStyle(SplitPane splitPane, String style){
        for(Node divider: splitPane.lookupAll(".split-pane-divider")){
            if(divider != null){
                divider.setStyle(style);
            }
        }
    }

    private void setTextAreaStyle(TextArea textArea, String style){
        for(Node content: textArea.lookupAll(".content")){
            if(content != null){
                content.setStyle(style);
                content.setCursor(Cursor.DEFAULT);
            }
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Getters and setters ------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    protected String[] getSelectActionComboBoxOptions() {
        return selectActionComboBoxOptions;
    }

    protected void setSelectActionComboBoxOptions(String[] selectActionComboBoxOptions) {
        this.selectActionComboBoxOptions = selectActionComboBoxOptions;
    }

    protected String[] getSelectFilterOptions() {
        return selectFilterOptions;
    }

    protected void setSelectFilterOptions(String[] selectFilterOptions) {
        this.selectFilterOptions = selectFilterOptions;
    }

    protected String getCurrentAction() {
        return currentAction;
    }

    protected void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    protected String getCurrentFilter() {
        return currentFilter;
    }

    protected void setCurrentFilter(String currentFilter) {
        this.currentFilter = currentFilter;
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Debugging functions ------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * A helper function for displaying the size of various GUI objects.
     */
    public void getSizes(){
        System.out.println("Main split pane: ");
        System.out.println("Width: " + mainSplitPane.getWidth());
        System.out.println("Preferred Width: " + mainSplitPane.getPrefWidth());
        System.out.println("Height: " + mainSplitPane.getHeight());
        System.out.println("Preferred height: " + mainSplitPane.getPrefHeight());
        System.out.println("");

        System.out.println("View scroll pane: ");
        System.out.println("Width: " + viewScrollPane.getWidth());
        System.out.println("Preferred Width: " + viewScrollPane.getPrefWidth());
        System.out.println("Height: " + viewScrollPane.getHeight());
        System.out.println("Preferred height: " + viewScrollPane.getPrefHeight());
        System.out.println("");

        System.out.println("View anchor pane: ");
        System.out.println("Width: " + viewAnchorPane.getWidth());
        System.out.println("Preferred Width: " + viewAnchorPane.getPrefWidth());
        System.out.println("Height: " + viewAnchorPane.getHeight());
        System.out.println("Preferred height: " + viewAnchorPane.getPrefHeight());
        System.out.println("");

        System.out.println("View text area: ");
        System.out.println("Width: " + viewTextArea.getWidth());
        System.out.println("Preferred Width: " + viewTextArea.getPrefWidth());
        System.out.println("Height: " + viewTextArea.getHeight());
        System.out.println("Preferred height: " + viewTextArea.getPrefHeight());
        System.out.println("");
    }

    // To do (high to low priority):
    // Add settings pop-out menu with option to change font size and save settings.
    // Add ability to save session states.
    // Add filter for folders.
    // Add new action: Create list from file data.
    //      This will give the user the ability to take the data from a specific file type and/or with specific properties
    //      and convert it into a generic list (.csv, .txt, ect.) for easy use in File Handler or other programs.

    // Notes:
    // FileUtils.sizeOf(selectedDirectory) returns the size of selectedDirectory in bytes.
    // FileUtils.byteCountToDisplaySize(BigInteger size) can convert it to a more readable number.


}