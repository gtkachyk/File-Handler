package BackEnd;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for ActionHandler.
 * Tests that are commented out take a long time to run.
 * Some tests will correctly fail without an external storage device.
 * The full path of every file and folder needed to run the tests is stored in a constant.
 * To run the tests, update these constants with the path to the files and folders on your device. This is the only code modification needed.
 * Folder locations can be changed, but do not modify the test folders and files in any other way.
 */
class ActionHandlerTest { // Precondition: device is running Windows 11 Home, and a USB is connected.

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

    private final ArrayList<File> copy_test_folder_1_Files = new ArrayList<File>(Arrays.asList(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"),
                                                                                            new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\localWorldTest.txt"),
                                                                                            new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Octopus attack (2).jpg"),
                                                                                            new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Octopus.png")));
    private ArrayList<File> copy_test_folder_1_Files_Copy(){
        return new ArrayList<File>(copy_test_folder_1_Files);
    }

    // BackEnd objects.
    private DirectoryHandler directoryHandler;
    private ActionHandler actionHandler;

    // Commonly used Files.
    private File file_handler_test_folder_1;
    private File copy_test_folder_1;
    private File copy_test_folder_2;

    @BeforeEach
    void setup(){
        directoryHandler = new DirectoryHandler();
        actionHandler = new ActionHandler(directoryHandler);

        file_handler_test_folder_1 = new File(file_handler_test_folder_1_PATH);
        copy_test_folder_1 = new File(copy_test_folder_1_PATH);
        copy_test_folder_2 = new File(copy_test_folder_2_PATH);
    }

    @AfterEach
    void teardown(){
        directoryHandler = null;
        actionHandler = null;

        file_handler_test_folder_1 = null;
        copy_test_folder_1 = null;
        copy_test_folder_2 = null;
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- directorySize tests ---------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------
    @Test
    void directorySize_NullArrayListTest(){
        long result = actionHandler.directorySize(null);
        assertEquals(0, result);
    }

    @Test
    void directorySize_EmptyArrayListTest(){
        long result = actionHandler.directorySize(new ArrayList<File>());
        assertEquals(0, result);
    }

    @Test
    void directorySize_NonEmptyArrayListTest(){
        ArrayList<File> testDirectoryFiles = new ArrayList<File>(Arrays.asList(new File(file_handler_test_folder_1_PATH + "\\about_this_folder.txt"),
                                                                               new File(file_handler_test_folder_1_PATH + "\\Bio 218 Assignment 3.docx"),
                                                                               new File(file_handler_test_folder_1_PATH + "\\Bio 218 Assignment 4 Drawing.jpg"),
                                                                               new File(file_handler_test_folder_1_PATH + "\\localWorldTest.txt"),
                                                                               new File(file_handler_test_folder_1_PATH + "\\Octopus attack (2).jpg"),
                                                                               new File(file_handler_test_folder_1_PATH + "\\Octopus.png")));
        long file_handler_test_folder_1_size_bytes = 5692254; // This is 'size' not 'size on disk'.
        long result = actionHandler.directorySize(testDirectoryFiles);
        assertEquals(file_handler_test_folder_1_size_bytes, result);
    }

    @Test
    void directorySize_BigDirectoryTest(){
        ArrayList<File> testDirectoryFiles = new ArrayList<File>(Arrays.asList(new File(file_handler_test_folder_5_PATH + "\\Astronomy"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\Astronomy copy"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\Captures"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\Captures copy"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\OSRS Inferno Attempts"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\OSRS Inferno Attempts copy"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\2021-10-15 19-39-31.mkv"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\2023-04-12 11-59-23.mkv"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\356641015_830205351804974_6709253885182984363_n.mp4"),
                                                                               new File(file_handler_test_folder_5_PATH + "\\about_this_folder.txt")));
        long file_handler_test_folder_5_size_bytes = 5753483007L; // This is 'size' not 'size on disk'.
        long result = actionHandler.directorySize(testDirectoryFiles);
        assertEquals(file_handler_test_folder_5_size_bytes, result);
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- isDiskSpaceSufficient tests ---------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void isDiskSpaceSufficient_NullTargetTest(){
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.isDiskSpaceSufficient()),

                // Flags
                () -> assertTrue(actionHandler.isNotEnoughDiskSpaceFlag())
        );
    }

    @Test
    void isDiskSpaceSufficient_TargetIsWindows11PCWithSpaceTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_2_PATH));
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);

        assertAll(
                // Parameters
                () -> assertTrue(actionHandler.isDiskSpaceSufficient()),

                // Flags
                () -> assertFalse(actionHandler.isNotEnoughDiskSpaceFlag())
        );
    }

//    @Test
//    void isDiskSpaceSufficient_TargetIsWindows11PCWithoutSpaceTest(){ // Takes about 2 minutes to execute.
//        directoryHandler.processNewSourceDirectory(new File(drive_letter_C_PATH));
//        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
//
//        assertAll(
//                // Parameters
//                () -> assertFalse(actionHandler.isDiskSpaceSufficient()),
//
//                // Flags
//                () -> assertTrue(actionHandler.isNotEnoughDiskSpaceFlag())
//        );
//    }

    @Test
    void isDiskSpaceSufficient_TargetIsUSBWithSpaceOnWindows11PCTest(){ // Precondition: USB is connected.
        File externalStorage = new File(drive_letter_D_PATH);

        if(externalStorage.exists()){
            directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
            directoryHandler.processNewTargetDirectory(externalStorage);

            assertAll(
                    // Parameters
                    () -> assertTrue(actionHandler.isDiskSpaceSufficient()),

                    // Flags
                    () -> assertFalse(actionHandler.isNotEnoughDiskSpaceFlag())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void isDiskSpaceSufficient_TargetIsUSBWithoutSpaceOnWindows11PCTest(){ // Precondition: USB is connected.
        File externalStorage = new File(drive_letter_D_PATH);

        if(externalStorage.exists()){
            directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_5_PATH));
            directoryHandler.processNewTargetDirectory(externalStorage);

            assertAll(
                    // Parameters
                    () -> assertFalse(actionHandler.isDiskSpaceSufficient()),

                    // Flags
                    () -> assertTrue(actionHandler.isNotEnoughDiskSpaceFlag())
            );
        }
        else{
            fail();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- isSourceFilesEmpty tests ------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void isSourceFilesEmpty_NullSourceTest(){
        assertAll(
                // Parameters
                () -> assertTrue(actionHandler.isSourceFilesEmpty()),

                // Flags
                () -> assertTrue(actionHandler.isNoSourceFilesFlag())
        );
    }

    @Test
    void isSourceFilesEmpty_EmptySourceTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_4_PATH));

        assertAll(
                // Parameters
                () -> assertTrue(actionHandler.isSourceFilesEmpty()),

                // Flags
                () -> assertTrue(actionHandler.isNoSourceFilesFlag())
        );
    }

    @Test
    void isSourceFilesEmpty_NonEmptySourceTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);

        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.isSourceFilesEmpty()),

                // Flags
                () -> assertFalse(actionHandler.isNoSourceFilesFlag())
        );
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- sourceTargetDuplicates tests --------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void sourceTargetDuplicates_NullSourceTest(){
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_NullTargetTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_NullSourceAndTargetTest(){
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_EmptySourceTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_4_PATH));
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.sourceFilesCopy().isEmpty()),
                () -> assertFalse(directoryHandler.targetFilesCopy().isEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_EmptyTargetTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_4_PATH));
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.sourceFilesCopy().isEmpty()),
                () -> assertTrue(directoryHandler.targetFilesCopy().isEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_EmptySourceAndTargetTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_4_PATH));
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_10_PATH));
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.sourceFilesCopy().isEmpty()),
                () -> assertTrue(directoryHandler.targetFilesCopy().isEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_FileNameAndExtensionDuplicatesTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_5_PATH));
        assertAll(
                // Parameters
                () -> assertTrue(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.sourceFilesCopy().isEmpty()),
                () -> assertFalse(directoryHandler.targetFilesCopy().isEmpty()),
                () -> assertTrue(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_FileNameAndExtensionAndContentDuplicatesTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_6_PATH));
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_7_PATH));
        assertAll(
                // Parameters
                () -> assertTrue(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.sourceFilesCopy().isEmpty()),
                () -> assertFalse(directoryHandler.targetFilesCopy().isEmpty()),
                () -> assertTrue(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_FileNameOnlyDuplicatesTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_8_PATH));
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_9_PATH));
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.sourceFilesCopy().isEmpty()),
                () -> assertFalse(directoryHandler.targetFilesCopy().isEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void sourceTargetDuplicates_ExtensionAndContentDuplicatesTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_11_PATH));
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_12_PATH));
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.sourceTargetDuplicates()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.sourceFilesCopy().isEmpty()),
                () -> assertFalse(directoryHandler.targetFilesCopy().isEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- copyActionPermitted tests -----------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    void copyActionPermittedTestInitialize() throws IOException {
        if(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders").exists()){
            FileUtils.deleteDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
        }
        FileUtils.copyToDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\copy_test_folder_copies\\copy_test_folder_1"),
                                  new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
        FileUtils.copyToDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\copy_test_folder_copies\\copy_test_folder_2"),
                                  new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
    }

    @Test
    void copyActionPermitted_NullSourceTest(){
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.copyActionPermitted()),

                // Flags
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(actionHandler.isDiskSpaceSufficient()),
                () -> assertTrue(actionHandler.isSourceFilesEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void copyActionPermitted_NullTargetTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.copyActionPermitted()),

                // Flags
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(actionHandler.isDiskSpaceSufficient()),
                () -> assertFalse(actionHandler.isSourceFilesEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void copyActionPermitted_SourceEqualsTargetTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.copyActionPermitted()),

                // Flags
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(actionHandler.isDiskSpaceSufficient()),
                () -> assertFalse(actionHandler.isSourceFilesEmpty()),
                () -> assertTrue(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

//    @Test
//    void copyActionPermitted_DiskSpaceNotSufficientTest(){ // Takes about 2 minutes to execute.
//        directoryHandler.processNewSourceDirectory(new File(drive_letter_C_PATH));
//        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
//        assertAll(
//                // Parameters
//                () -> assertFalse(actionHandler.copyActionPermitted()),
//
//                // Flags
//                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
//                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
//                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
//                () -> assertFalse(actionHandler.isDiskSpaceSufficient()),
//                () -> assertFalse(actionHandler.isSourceFilesEmpty()),
//                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
//        );
//    }

    @Test
    void copyActionPermitted_SourceFilesEmptyTest(){
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_4_PATH));
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.copyActionPermitted()),

                // Flags
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(actionHandler.isDiskSpaceSufficient()),
                () -> assertTrue(actionHandler.isSourceFilesEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void copyActionPermitted_SourceTargetDuplicatesTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_2_PATH));
        assertAll(
                // Parameters
                () -> assertFalse(actionHandler.copyActionPermitted()),

                // Flags
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(actionHandler.isDiskSpaceSufficient()),
                () -> assertFalse(actionHandler.isSourceFilesEmpty()),
                () -> assertTrue(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void copyActionPermitted_ActionPermittedTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_3_PATH));
        assertAll(
                // Parameters
                () -> assertTrue(actionHandler.copyActionPermitted()),

                // Flags
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(actionHandler.isDiskSpaceSufficient()),
                () -> assertFalse(actionHandler.isSourceFilesEmpty()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void copyActionPermitted_SourceFileDeletedBeforeCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());
        
        FileUtils.delete(directoryHandler.sourceFilesCopy().get(0));

        assertAll(
                // Flags
                () -> assertFalse(actionHandler.copyActionPermitted())
        );
    }

    @Test
    void copyActionPermitted_SourceDirectoryDeletedBeforeCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        FileUtils.deleteDirectory(directoryHandler.sourceDirectoryCopy());

        assertAll(
                // Flags
                () -> assertFalse(actionHandler.copyActionPermitted())
        );
    }

    @Test
    void copyActionPermitted_SourceFileMovedBeforeCheckTest(){
        // Low priority test, likely just repeats the deletion behaviour
    }

    @Test
    void copyActionPermitted_SourceDirectoryMovedBeforeCheckTest(){
        // Low priority test, likely just repeats the deletion behaviour
    }

    @Test
    void copyActionPermitted_SourceFileNameChangedBeforeCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        if(directoryHandler.sourceFilesCopy().get(0).renameTo(new File(copy_test_folder_1_PATH + "\\bio_thingy.jpg"))){
            assertAll(
                    // Flags
                    () -> assertFalse(actionHandler.copyActionPermitted())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyActionPermitted_SourceDirectoryNameChangedBeforeCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        if(directoryHandler.sourceDirectoryCopy().renameTo(new File(copy_test_folder_1_PATH + "_renamed"))){
            assertAll(
                    // Flags
                    () -> assertFalse(actionHandler.copyActionPermitted())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyActionPermitted_SourceFileExtensionChangedBeforeCheckTest(){
        // Low priority test, likely just repeats rename behaviour
    }

    @Test
    void copyActionPermitted_SourceFileContentChangedBeforeCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());
        String textToAdd = "hey hey hey";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(copy_test_folder_1_PATH + "\\localWorldTest.txt"), true));
        writer.append(textToAdd);
        writer.close();

        assertAll(
                // Flags
                () -> assertTrue(actionHandler.copyActionPermitted())
        );
    }

    @Test
    void copyActionPermitted_SourceFileHiddenPropertyChangedBeforeCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        DosFileAttributes dosFileAttributes = Files.readAttributes(Path.of(copy_test_folder_1_PATH + "\\localWorldTest.txt"), DosFileAttributes.class);
        Files.setAttribute(Path.of(copy_test_folder_1_PATH + "\\localWorldTest.txt"), "dos:hidden", true);

        assertAll(
                // Flags
                () -> assertFalse(actionHandler.copyActionPermitted())
        );
    }

    @Test
    void copyActionPermitted_SourceDirectoryHiddenPropertyChangedBeforeCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        DosFileAttributes dosFileAttributes = Files.readAttributes(Path.of(copy_test_folder_1_PATH), DosFileAttributes.class);
        Files.setAttribute(Path.of(copy_test_folder_1_PATH), "dos:hidden", true);
        actionHandler.copyActionPermitted();

        assertAll(
                // Flags
                () -> assertTrue(actionHandler.copyActionPermitted())
        );
    }

    @Test
    void copyActionPermitted_SourceFileOpenDuringCheckTest() throws IOException {
        copyActionPermittedTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());
        String textToAdd = "hey hey hey";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(copy_test_folder_1_PATH + "\\localWorldTest.txt"), true));
        writer.append(textToAdd);

        assertAll(
                // Flags
                () -> assertTrue(actionHandler.copyActionPermitted())
        );
        writer.close();
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- copyFiles tests ---------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    void copyFileTestInitialize() throws IOException {
        if(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders").exists()){
            FileUtils.deleteDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
        }
        FileUtils.copyToDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\copy_test_folder_copies\\copy_test_folder_1"),
                                  new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
        FileUtils.copyToDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\copy_test_folder_copies\\copy_test_folder_2"),
                                  new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders"));
    }

    // Source directory and file tests:
    @Test
    void copyFiles_SourceFileDeletedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        FileUtils.delete(directoryHandler.sourceFilesCopy().get(0));
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertEquals(copy_test_folder_1_FIRST_FILE_NAME, actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertTrue(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_SourceDirectoryDeletedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        FileUtils.deleteDirectory(directoryHandler.sourceDirectoryCopy());
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertEquals(copy_test_folder_1_FIRST_FILE_NAME, actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertTrue(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_SourceFileMovedBeforeCopyingTest(){
        // Low priority test, likely just repeats the deletion behaviour
    }

    @Test
    void copyFiles_SourceDirectoryMovedBeforeCopyingTest(){
        // Low priority test, likely just repeats the deletion behaviour
    }

    @Test
    void copyFiles_SourceFileNameChangedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        if(directoryHandler.sourceFilesCopy().get(0).renameTo(new File(copy_test_folder_1_PATH + "\\bio_thingy.jpg"))){
            actionHandler.copyFiles();
            assertAll(
                    // Parameters
                    () -> assertEquals(copy_test_folder_1_FIRST_FILE_NAME, actionHandler.getFileThatCausedException()),

                    // Flags
                    () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                    () -> assertTrue(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyFiles_SourceDirectoryNameChangedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        if(directoryHandler.sourceDirectoryCopy().renameTo(new File(copy_test_folder_1_PATH + "_renamed"))){
            actionHandler.copyFiles();
            assertAll(
                    // Parameters
                    () -> assertEquals(copy_test_folder_1_FIRST_FILE_NAME, actionHandler.getFileThatCausedException()),

                    // Flags
                    () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                    () -> assertTrue(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyFiles_SourceFileExtensionChangedBeforeCopyingTest(){
        // Low priority test, likely just repeats rename behaviour
    }

    @Test
    void copyFiles_SourceFileContentChangedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());
        String textToAdd = "hey hey hey";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(copy_test_folder_1_PATH + "\\localWorldTest.txt"), true));
        writer.append(textToAdd);
        writer.close();
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertNull(actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_SourceFileHiddenPropertyChangedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        DosFileAttributes dosFileAttributes = Files.readAttributes(Path.of(copy_test_folder_1_PATH + "\\localWorldTest.txt"), DosFileAttributes.class);
        Files.setAttribute(Path.of(copy_test_folder_1_PATH + "\\localWorldTest.txt"), "dos:hidden", true);
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertNull(actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_SourceDirectoryHiddenPropertyChangedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        DosFileAttributes dosFileAttributes = Files.readAttributes(Path.of(copy_test_folder_1_PATH), DosFileAttributes.class);
        Files.setAttribute(Path.of(copy_test_folder_1_PATH), "dos:hidden", true);
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertNull(actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_SourceFileOpenDuringCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());
        String textToAdd = "hey hey hey";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(copy_test_folder_1_PATH + "\\localWorldTest.txt"), true));
        writer.append(textToAdd);
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertNull(actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
        writer.close();
    }

    // Target directory and file tests:
    @Test
    void copyFiles_TargetFileDeletedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        FileUtils.delete(directoryHandler.targetFilesCopy().get(0));
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertNull(actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_TargetDirectoryDeletedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        FileUtils.deleteDirectory(directoryHandler.targetDirectoryCopy());
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertNull(actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_TargetFileMovedBeforeCopyingTest(){
        // Low priority
    }

    @Test
    void copyFiles_TargetDirectoryMovedBeforeCopyingTest(){
        // Low priority
    }

    @Test
    void copyFiles_TargetFileNameChangedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        if(directoryHandler.targetFilesCopy().get(0).renameTo(new File(copy_test_folder_1_PATH + "\\ECON 341 Random Notes Named Changed.docx"))){
            actionHandler.copyFiles();
            assertAll(
                    // Parameters
                    () -> assertNull(actionHandler.getFileThatCausedException()),

                    // Flags
                    () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyFiles_TargetDirectoryNameChangedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        if(directoryHandler.targetDirectoryCopy().renameTo(new File(copy_test_folder_2_PATH + "_renamed"))){
            actionHandler.copyFiles();
            assertAll(
                    // Parameters
                    () -> assertNull(actionHandler.getFileThatCausedException()),

                    // Flags
                    () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyFiles_TargetDirectoryFilledBeforeCopyingTest() throws IOException {
        File source = copy_test_folder_1;
        File target = new File(drive_letter_D_PATH);

        long file_handler_test_folder_16_byteSize = 4875686L;
        long Bio_218_Assignment_4_Drawing_byteSize = 3071075L;
        long targetMinFreeSpace = file_handler_test_folder_16_byteSize + 100;
        long targetMaxFreeSpace = (file_handler_test_folder_16_byteSize + Bio_218_Assignment_4_Drawing_byteSize) - 100;
        String Bio_218_Assignment_4_Drawing_pathInTarget = "D:\\Bio 218 Assignment 4 Drawing.jpg";

        if((target.exists()) && (!new File(Bio_218_Assignment_4_Drawing_pathInTarget).exists()) && (target.getUsableSpace() < targetMaxFreeSpace) && (target.getUsableSpace() > targetMinFreeSpace)){
            directoryHandler.processNewSourceDirectory(source);
            directoryHandler.processNewTargetDirectory(target);

            assertTrue(actionHandler.copyActionPermitted());

            FileUtils.copyDirectoryToDirectory(new File(file_handler_test_folder_16_PATH), target);
            actionHandler.copyFiles();

            assertAll(
                    // Parameters
                    () -> assertEquals(copy_test_folder_1_FIRST_FILE_NAME, actionHandler.getFileThatCausedException()),

                    // Flags
                    () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                    () -> assertTrue(actionHandler.isCopyFilesIOExceptionFlag())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyFiles_TargetDirectorySourceDuplicateAddedBeforeCopyingTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        FileUtils.copyToDirectory(new File(file_handler_test_folder_1_PATH + "\\Bio 218 Assignment 4 Drawing.jpg"), copy_test_folder_2);
        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertNull(actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    // Exception-specific tests:
//    @Test
//    void copyFiles_NullPointerExceptionTest() throws IOException {
//        copyFileTestInitialize();
//        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
//        directoryHandler.processNewTargetDirectory(copy_test_folder_2);
//
//        assertTrue(actionHandler.copyActionPermitted());
//
//        directoryHandler.sourceFilesCopy().set(0, null);
//        actionHandler.copyFiles();
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(copy_test_folder_1_FIRST_FILE_NAME, actionHandler.getFileThatCausedException()),
//
//                // Flags
//                () -> assertTrue(actionHandler.isCopyFilesNullPointerExceptionFlag()),
//                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
//                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
//                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
//        );
//    }

    @Test
    void copyFiles_IllegalArgumentExceptionTest() throws IOException {
        copyFileTestInitialize();
        File source = new File(copy_test_folder_1_PATH);
        File target = new File(file_handler_test_folder_13_PATH);
        directoryHandler.processNewSourceDirectory(source);
        directoryHandler.processNewTargetDirectory(target);

        actionHandler.copyFiles();

        assertAll(
                // Parameters
                () -> assertEquals("localWorldTest.txt", actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertTrue(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
        );
    }

    @Test
    void copyFiles_FileNotFoundExceptionTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);

        assertTrue(actionHandler.copyActionPermitted());

        if(directoryHandler.sourceFilesCopy().get(0).renameTo(new File(copy_test_folder_1_PATH + "\\bio_thingy.jpg"))){
            actionHandler.copyFiles();
            assertAll(
                    // Parameters
                    () -> assertEquals(copy_test_folder_1_FIRST_FILE_NAME, actionHandler.getFileThatCausedException()),

                    // Flags
                    () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                    () -> assertTrue(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                    () -> assertFalse(actionHandler.isCopyFilesIOExceptionFlag())
            );
        }
        else{
            fail();
        }
    }

    @Test
    void copyFiles_IOExceptionTest() throws IOException {
        // copyFileTestInitialize();
        File source = new File(file_handler_test_folder_14_PATH);
        File target = new File(file_handler_test_folder_15_PATH);
        directoryHandler.processNewSourceDirectory(source);
        directoryHandler.processNewTargetDirectory(target);

        String textToAdd = "hey hey hey";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file_handler_test_folder_15_PATH + "\\localWorldTest.txt"), true));
        writer.append(textToAdd);

        actionHandler.copyFiles();
        assertAll(
                // Parameters
                () -> assertEquals("localWorldTest.txt", actionHandler.getFileThatCausedException()),

                // Flags
                () -> assertFalse(actionHandler.isCopyFilesNullPointerExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesIllegalArgumentExceptionFlag()),
                () -> assertFalse(actionHandler.isCopyFilesFileNotFoundExceptionFlag()),
                () -> assertTrue(actionHandler.isCopyFilesIOExceptionFlag())
        );
        writer.close();
    }

    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- deleteFromSource tests --------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void deleteFromSource_SuccessTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        assertTrue(actionHandler.deleteFromSourceActionPermitted());

        ArrayList<File> expectSourceFiles = copy_test_folder_1_Files_Copy();
        assertEquals(expectSourceFiles, directoryHandler.sourceFilesCopy());
        expectSourceFiles.remove(0);
        expectSourceFiles.remove(0);
        expectSourceFiles.remove(0);
        expectSourceFiles.remove(0);

        actionHandler.deleteFromSource();

        assertAll(
                // Parameters
                () -> assertEquals(expectSourceFiles, directoryHandler.sourceFilesCopy()),

                // Flags
                () -> assertFalse(actionHandler.isDeleteFromSourceDidNotCompleteFlag())
        );

    }

    @Test
    void deleteFromSource_FailTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        assertTrue(actionHandler.deleteFromSourceActionPermitted());

        ArrayList<File> expectSourceFiles = new ArrayList<>();
        expectSourceFiles.add(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\bio_thingy.png"));

        if(directoryHandler.sourceFilesCopy().get(3).renameTo(new File(copy_test_folder_1_PATH + "\\bio_thingy.png"))){
            actionHandler.deleteFromSource();

            assertAll(
                    // Parameters
                    () -> assertEquals(expectSourceFiles, directoryHandler.sourceFilesCopy()),

                    // Flags
                    () -> assertTrue(actionHandler.isDeleteFromSourceDidNotCompleteFlag())
            );
        }
        else{
            fail();
        }
    }

    // Manuel tests completed:
    // Test 1: Delete a file that would be deleted by deleteFromSource before deleteFromSource is called, without refreshing first.
    // Result 1: Error code 3.0: invalid source files.
    // Test 2: Add a file that would be deleted by deleteFromSource before deleteFromSource is called, without refreshing first.
    // Result 2: Operation successful, additional file was not deleted.
    // Test 3: Delete a file contained in a directory with objects not handled by File Handler (directories were used).
    // Result 3: Operation successful, only the correct file was deleted (the subdirectories also contained unharmed copies of the file that was deleted).
    // Test 4: Run program from .jar file and delete it.
    // Result 4: Error code 3.1.
    // Test 5: Try to delete files without a source selected (only target selected).
    // Result 5: Error 3.0: invalid source directory.
    // Test 6: Try to delete files when source = target.
    // Result 6: Error code 3.1: source = target directory.
    // Test 7: Try to delete files when source = target and source is empty.
    // Result 7: Error code 3.1: source = target directory.
    // Test 8: Try to delete all files in source after reordering source in OS file explorer without refreshing first.
    // Result 8: Operation successful.
    // Test 9: Deleted a file with no extension (type 'File').
    // Result 9: Operation successful.
    // Test 10: Move a file that would be deleted by deleteFromSource before deleteFromSource is called, without refreshing first.
    // Result 10: Error code 3.0: invalid source files.


    // -----------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- generateCopyFilesErrorMessage tests -------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    @Test
    void generateCopyFilesErrorMessage_NoErrorsTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_3_PATH));
        actionHandler.copyActionPermitted();
        String result = actionHandler.generateCopyActionPermittedErrorMessage();

        assertAll(
                // Parameters
                () -> assertNull(result),

                // Flags
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(actionHandler.isNotEnoughDiskSpaceFlag()),
                () -> assertFalse(actionHandler.isNoSourceFilesFlag()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

    @Test
    void generateCopyFilesErrorMessage_OneErrorTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        String result = actionHandler.generateCopyActionPermittedErrorMessage();

        assertAll(
                // Parameters
                () -> assertEquals("The requested copy operation was not permitted because one or more safety checks failed. Errors: null target directory.", result),

                // Flags
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(actionHandler.isNotEnoughDiskSpaceFlag()),
                () -> assertFalse(actionHandler.isNoSourceFilesFlag()),
                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

//    @Test
//    void generateCopyFilesErrorMessage_TwoErrorsTest(){
//        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_10_PATH));
//        actionHandler.copyActionPermitted();
//        String result = actionHandler.generateCopyActionPermittedErrorMessage();
//
//        assertAll(
//                // Parameters
//                () -> assertEquals("The requested copy operation was not permitted because one or more safety checks failed. Errors: null target directory.", result),
//
//                // Flags
//                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
//                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
//                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
//                () -> assertFalse(actionHandler.isNotEnoughDiskSpaceFlag()),
//                () -> assertTrue(actionHandler.isNoSourceFilesFlag()),
//                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
//        );
//    }

    @Test
    void generateCopyFilesErrorMessage_SourceEqualsTargetAndDuplicateFilesTest(){
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        actionHandler.copyActionPermitted();
        String result = actionHandler.generateCopyActionPermittedErrorMessage();

        assertAll(
                // Parameters
                () -> assertEquals("The requested copy operation was not permitted because one or more safety checks failed. Errors: source = target directory, duplicate files between source and target directory.", result),

                // Flags
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag()),
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(actionHandler.isNotEnoughDiskSpaceFlag()),
                () -> assertFalse(actionHandler.isNoSourceFilesFlag()),
                () -> assertTrue(actionHandler.isSourceTargetDuplicatesFlag())
        );
    }

//    @Test
//    void generateCopyFilesErrorMessage_NullSourceEmptySourceTest(){
//        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_4_PATH));
//        actionHandler.copyActionPermitted();
//        String result = actionHandler.generateCopyActionPermittedErrorMessage();
//
//        assertAll(
//                // Parameters
//                () -> assertEquals("null source directory, empty source directory", result),
//
//                // Flags
//                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag()),
//                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
//                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
//                () -> assertFalse(actionHandler.isNotEnoughDiskSpaceFlag()),
//                () -> assertTrue(actionHandler.isNoSourceFilesFlag()),
//                () -> assertFalse(actionHandler.isSourceTargetDuplicatesFlag())
//        );
//    }
}