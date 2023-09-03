package com.example.selectivefilesaver;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;

// VM options changed from '-ea' to '--module-path "C:\Program Files\Java\javafx-sdk-20.0.2\lib" --add-modules=javafx.base,javafx.controls,javafx.fxml,javafx.graphics -ea'
@ExtendWith(ApplicationExtension.class)
class MainControllerTest {

    private MainController controller;

    // private Button button;

    // Constants.
    private final String file_handler_test_folder_1_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1";
    private final String file_handler_test_folder_2_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2";
    private final String file_handler_test_folder_3_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_3";
    private final String file_handler_test_folder_4_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_4";
    private final String file_handler_test_folder_5_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_5";
    private final String file_handler_test_folder_6_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_6";
    private final String file_handler_test_folder_7_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_7";
    private final String file_handler_test_folder_8_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_8";
    private final String file_handler_test_folder_9_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_9";
    private final String file_handler_test_folder_10_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_10";
    private final String file_handler_test_folder_11_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_11";
    private final String file_handler_test_folder_12_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_12";
    private final String file_handler_test_folder_13_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_13";
    private final String file_handler_test_folder_14_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_14";
    private final String file_handler_test_folder_15_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_15";
    private final String file_handler_test_folder_16_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_16";

    private final String copy_test_folder_1_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1";
    private final String copy_test_folder_1_FIRST_FILE_NAME = "Bio 218 Assignment 4 Drawing.jpg";
    private final String copy_test_folder_2_PATH = "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_2";

    private final String drive_letter_C_PATH = "C:\\";
    private final String drive_letter_D_PATH = "D:\\";

    // Commonly used Files.
    private File file_handler_test_folder_1;
    private File copy_test_folder_1;
    private File copy_test_folder_2;

    @BeforeEach
    void setup(){
        file_handler_test_folder_1 = new File(file_handler_test_folder_1_PATH);
        copy_test_folder_1 = new File(copy_test_folder_1_PATH);
        copy_test_folder_2 = new File(copy_test_folder_2_PATH);
    }

    @AfterEach
    void teardown(){
        file_handler_test_folder_1 = null;
        copy_test_folder_1 = null;
        copy_test_folder_2 = null;
    }

    /**
     * Will be called with {@code @Before} semantics, i.e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("File Handler 1.0");
        stage.setScene(scene);
        stage.show();

        controller = new MainController();

        controller = (MainController)fxmlLoader.getController();
        controller.setStage(stage);
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Helper functions --------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    /**
     * Sets up test directories for tests related to copying files.
     */
    void copyFileTestInitialize() throws IOException {
        if(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders").exists()){
            FileUtils.deleteDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
        }
        FileUtils.copyToDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\copy_test_folder_copies\\copy_test_folder_1"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
        FileUtils.copyToDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\copy_test_folder_copies\\copy_test_folder_2"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
    }

    /**
     * Gets a dialog pane.
     */
    public DialogPane getAlertDialogPane(FxRobot robot) {
        final Stage actualAlertDialog = getTopModalStage(robot);
        return (DialogPane) actualAlertDialog.getScene().getRoot();
    }

    /**
     * Get a list of windows but ordered from top[0] to bottom[n] ones. It is needed to get the first found modal window.
     */
    private Stage getTopModalStage(FxRobot robot) {
        final List<Window> allWindows = new ArrayList<>(robot.robotContext().getWindowFinder().listWindows());
        Collections.reverse(allWindows);

        return (Stage) allWindows.stream().filter(window -> window instanceof Stage).filter(window -> ((Stage) window).getModality() == Modality.APPLICATION_MODAL).findFirst().orElse(null);
    }

    /**
     * Returns the top stage.
     */
    private Stage getTopStage(FxRobot robot) {
        final List<Window> allWindows = new ArrayList<>(robot.robotContext().getWindowFinder().listWindows());
        Collections.reverse(allWindows);
        return (Stage) allWindows.get(0);
    }

    @Test
    void viewTextArea_InitialStateTest(FxRobot robot){
        Assertions.assertThat(controller.viewTextArea).hasText("Welcome to File Handler!\n" +
                "Please familiarize yourself with File Handler 1.0\n" +
                "behaviours and limitations by clicking 'Help' then\n" +
                "'About File Handler' and reading the first section.\n");
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- processNewSourceDirectoryGUI tests --------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void processNewSourceDirectoryGUI_VisibilitiesTest(FxRobot robot){
        controller.processNewSourceDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        // Visible objects.
        Assertions.assertThat(robot.lookup("#selectActionLabel").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#selectFilterLabel").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#selectActionComboBox").queryAs(ComboBox.class)).isVisible();
        Assertions.assertThat(robot.lookup("#selectFilterComboBox").queryAs(ComboBox.class)).isVisible();

        // Invisible objects.
        Assertions.assertThat(robot.lookup("#nothingSelectedLabel").queryAs(Label.class)).isInvisible();
        Assertions.assertThat(robot.lookup("#fileContainsFilterLabel").queryAs(Label.class)).isInvisible();
        Assertions.assertThat(robot.lookup("#runButton").queryAs(Button.class)).isInvisible();
        Assertions.assertThat(robot.lookup("#applyFilterButton").queryAs(Button.class)).isInvisible();
        Assertions.assertThat(robot.lookup("#undoFilterButton").queryAs(Button.class)).isInvisible();
        Assertions.assertThat(robot.lookup("#filterTextField").queryAs(TextField.class)).isInvisible();
    }

    @Test
    void processNewSourceDirectoryGUI_ValidSourceViewTextAreaTest(FxRobot robot) throws InterruptedException {
        controller.processNewSourceDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: file_handler_test_folder_1\n" +
                "Current target directory: <no target selected>\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (6)--------------------\n" +
                "about_this_folder.txt\n" +
                "Bio 218 Assignment 3.docx\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n\n");
    }

    @Test
    void processNewSourceDirectoryGUI_ValidSourceActionLogTextAreaTest(FxRobot robot) throws InterruptedException {
        controller.processNewSourceDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));
        Assertions.assertThat(controller.actionLogTextArea).hasText("Source directory selected: \"" + "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1" + "\"\n");
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- processNewSourceDirectoryGUI tests --------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void processNewTargetDirectoryGUI_VisibilitiesTest(FxRobot robot){
        controller.processNewTargetDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        // Visible objects.
        Assertions.assertThat(robot.lookup("#selectActionLabel").queryAs(Label.class)).isVisible();
        Assertions.assertThat(robot.lookup("#selectActionComboBox").queryAs(ComboBox.class)).isVisible();

        // Invisible objects.
        Assertions.assertThat(robot.lookup("#nothingSelectedLabel").queryAs(Label.class)).isInvisible();
        Assertions.assertThat(robot.lookup("#runButton").queryAs(Button.class)).isInvisible();
    }

    @Test
    void processNewTargetDirectoryGUI_ValidTargetViewTextAreaTest(FxRobot robot) throws InterruptedException {
        controller.processNewTargetDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: <no source selected>\n" +
                "Current target directory: file_handler_test_folder_1\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (6)--------------------\n" +
                "about_this_folder.txt\n" +
                "Bio 218 Assignment 3.docx\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n");
    }

    @Test
    void processNewTargetDirectoryGUI_ValidTargetActionLogTextAreaTest(FxRobot robot) throws InterruptedException {
        controller.processNewTargetDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));
        Assertions.assertThat(controller.actionLogTextArea).hasText("Target directory selected: \"" + "C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1" + "\"\n");
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- refreshDirectories tests ------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void refreshDirectories_InvalidSourceTest(FxRobot robot){
        controller.processNewTargetDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        robot.clickOn("#fileMenu");
        robot.clickOn("Refresh directories...");

        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Warning (code 1.0)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
    }

    @Test
    void refreshDirectories_InvalidTargetTest(FxRobot robot){
        controller.processNewSourceDirectoryGUI(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        robot.clickOn("#fileMenu");
        robot.clickOn("Refresh directories...");

        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Warning (code 1.1)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
    }

    @Test
    void refreshDirectories_InvalidSourceAndTargetTest(FxRobot robot){
        robot.clickOn("#fileMenu");
        robot.clickOn("Refresh directories...");

        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Warning (code 1.2)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- setAboutMenuItem tests --------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

//    @Test
//    void setAboutMenuItem_Test(FxRobot robot){
//        robot.clickOn("#helpMenu");
//        robot.clickOn("About File Handler");
//        assertEquals("About File Handler", getTopStage(robot).getTitle());
//    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- setRunButton tests ------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void setRunButton_CopyActionPermittedTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (4)--------------------\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        robot.clickOn("#selectActionComboBox");
        robot.clickOn("Copy to folder");
        robot.clickOn("#runButton");
        robot.clickOn("OK");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (4)--------------------\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (6)--------------------\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "ECON 341 Random Notes.docx\n" +
                "localWorldTest.txt\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n");
    }

    @Test
    void setRunButton_CopyActionWithFilterPermittedTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (4)--------------------\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        robot.clickOn("#selectFilterComboBox");
        robot.clickOn("Include by name");
        robot.clickOn("#filterTextField").write("Octopus");
        robot.clickOn("#applyFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        robot.clickOn("#selectActionComboBox");
        robot.clickOn("Copy to folder");
        robot.clickOn("#runButton");
        robot.clickOn("OK");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (4)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n");
    }

    @Test
    void setRunButton_CopyActionNotPermittedBeforeConfirmationTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        FileUtils.delete(new File(Objects.requireNonNull(source.listFiles())[0].getAbsolutePath()));

        robot.clickOn("#selectActionComboBox");
        robot.clickOn("Copy to folder");
        robot.clickOn("#runButton");
        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Error (code 1.0)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
    }

    @Test
    void setRunButton_CopyActionNotPermittedAfterConfirmationTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        robot.clickOn("#selectActionComboBox");
        robot.clickOn("Copy to folder");
        robot.clickOn("#runButton");
        FileUtils.delete(new File(Objects.requireNonNull(source.listFiles())[0].getAbsolutePath()));
        robot.clickOn("OK");
        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Error (code 1.0)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
    }

    @Test
    void setRunButton_CopyFilesIOExceptionTest(FxRobot robot) {
        File source = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_1");
        File target = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_2");
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        robot.clickOn("#selectActionComboBox");
        robot.clickOn("Copy to folder");
        robot.clickOn("#runButton");
        robot.clickOn("OK");
        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Error (code 1.4)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- setApplyFilterButton tests ----------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void setApplyFilterButton_ValidFilterTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        robot.clickOn("#selectFilterComboBox");
        robot.clickOn("Include by name");
        robot.clickOn("#filterTextField").write("Octopus");
        robot.clickOn("#applyFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
    }

    @Test
    void setApplyFilterButton_InvalidFilterTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        robot.clickOn("#selectFilterComboBox");
        robot.clickOn("Include by name");
        robot.clickOn("#filterTextField").write("");
        robot.clickOn("#applyFilterButton");
        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Error (code 2.0)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (4)--------------------\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- setUndoFilterButton tests ----------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void setUndoFilterButton_ValidRequestTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        robot.clickOn("#selectFilterComboBox");
        robot.clickOn("Include by name");
        robot.clickOn("#filterTextField").write("Octopus");
        robot.clickOn("#applyFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        robot.clickOn("#undoFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (4)--------------------\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        Assertions.assertThat(robot.lookup("#undoFilterButton").queryAs(Button.class)).isInvisible();
    }

    @Test
    void setUndoFilterButton_UndoTwoFiltersTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        robot.clickOn("#selectFilterComboBox");
        robot.clickOn("Include by name");
        robot.clickOn("#filterTextField").write("Octopus");
        robot.clickOn("#applyFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        robot.clickOn("#selectFilterComboBox");
        robot.clickOn("Exclude by extension");
        robot.clickOn("#filterTextField").type(KeyCode.BACK_SPACE);
        robot.clickOn("#filterTextField").write(".jpg");
        robot.clickOn("#applyFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (1)--------------------\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        robot.clickOn("#undoFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        robot.clickOn("#undoFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (4)--------------------\n" +
                "Bio 218 Assignment 4 Drawing.jpg\n" +
                "localWorldTest.txt\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        Assertions.assertThat(robot.lookup("#undoFilterButton").queryAs(Button.class)).isInvisible();
    }

    @Test
    void setUndoFilterButton_InvalidRequestTest(FxRobot robot) throws IOException {
        copyFileTestInitialize();

        File source = new File(copy_test_folder_1_PATH);
        File target = new File(copy_test_folder_2_PATH);
        controller.processNewSourceDirectoryGUI(source);
        controller.processNewTargetDirectoryGUI(target);

        robot.clickOn("#selectFilterComboBox");
        robot.clickOn("Include by name");
        robot.clickOn("#filterTextField").write("Octopus");
        robot.clickOn("#applyFilterButton");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        controller.directoryHandler.filterRecord.clear();
        robot.clickOn("#undoFilterButton");
        verifyThat("OK", NodeMatchers.isVisible());
        assertEquals("Error (code 2.1)", getAlertDialogPane(robot).getHeaderText());
        robot.clickOn("OK");
        Assertions.assertThat(controller.viewTextArea).hasText("Current source directory: copy_test_folder_1\n" +
                "Current target directory: copy_test_folder_2\n" +
                "\n" +
                "--------------------SOURCE DIRECTORY FILES (2)--------------------\n" +
                "Octopus attack (2).jpg\n" +
                "Octopus.png\n" +
                "\n" +
                "--------------------TARGET DIRECTORY FILES (2)--------------------\n" +
                "ECON 341 Random Notes.docx\n" +
                "Mishkin and Serletis 8Ce Chapter 1.pptx\n");
        Assertions.assertThat(robot.lookup("#undoFilterButton").queryAs(Button.class)).isInvisible();
    }
}