package BackEnd;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryHandlerTest {

    private DirectoryHandler directoryHandler;

    private final ArrayList<File> file_handler_test_folder_1_Files = new ArrayList<File>(Arrays.asList(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus attack (2).jpg"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus.png")));

    private final ArrayList<String> file_handler_test_folder_1_Names = new ArrayList<String>(Arrays.asList(
                                                                                                           "about_this_folder.txt",
                                                                                                           "Bio 218 Assignment 3.docx",
                                                                                                           "Bio 218 Assignment 4 Drawing.jpg",
                                                                                                           "localWorldTest.txt",
                                                                                                           "Octopus attack (2).jpg",
                                                                                                           "Octopus.png"));

    private final ArrayList<File> file_handler_test_folder_2_Files = new ArrayList<File>(Arrays.asList(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\about_this_folder.txt"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Bio 218 Assignment 3.docx"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Bio 218 Assignment 4 Drawing.jpg"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\localWorldTest.txt"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Octopus attack (2).jpg"),
                                                                                                       new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Octopus.png")));

    private final ArrayList<String> file_handler_test_folder_2_Names = new ArrayList<String>(Arrays.asList(
                                                                                                           "about_this_folder.txt",
                                                                                                           "Bio 218 Assignment 3.docx",
                                                                                                           "Bio 218 Assignment 4 Drawing.jpg",
                                                                                                           "localWorldTest.txt",
                                                                                                           "Octopus attack (2).jpg",
                                                                                                           "Octopus.png"));

    private ArrayList<File> file_handler_test_folder_1_Files_Copy(){
        return new ArrayList<File>(file_handler_test_folder_1_Files);
    }

    private ArrayList<String> file_handler_test_folder_1_Names_Copy(){
        return new ArrayList<String>(file_handler_test_folder_1_Names);
    }

    private ArrayList<File> file_handler_test_folder_2_Files_Copy(){
        return new ArrayList<File>(file_handler_test_folder_2_Files);
    }

    private ArrayList<String> file_handler_test_folder_2_Names_Copy(){
        return new ArrayList<String>(file_handler_test_folder_2_Names);
    }

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
        directoryHandler = new DirectoryHandler();

        file_handler_test_folder_1 = new File(file_handler_test_folder_1_PATH);
        copy_test_folder_1 = new File(copy_test_folder_1_PATH);
        copy_test_folder_2 = new File(copy_test_folder_2_PATH);
    }

    @AfterEach
    void teardown(){
        directoryHandler = null;

        file_handler_test_folder_1 = null;
        copy_test_folder_1 = null;
        copy_test_folder_2 = null;
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

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- arrayListContainsFile tests -------------------------------------
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    void arrayListContainsFile_DoesContainTest(){
        assertTrue(directoryHandler.arrayListContainsFile(file_handler_test_folder_1_Files, new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus.png")));
    }

    @Test
    void arrayListContainsFile_DoesNotContainTest(){
        assertFalse(directoryHandler.arrayListContainsFile(file_handler_test_folder_1_Files, new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_3\\Mishkin and Serletis 8Ce Chapter 1.pptx")));
    }

    @Test
    void arrayListContainsFile_NonNullArrayListNullFileTest(){
        assertFalse(directoryHandler.arrayListContainsFile(new ArrayList<File>(), null));
    }

    @Test
    void arrayListContainsFile_NullArrayListNonNullFileTest(){
        assertFalse(directoryHandler.arrayListContainsFile(null, new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Octopus.png")));
    }

    @Test
    void arrayListContainsFile_NullArrayListAndFileTest(){
        assertFalse(directoryHandler.arrayListContainsFile(null, null));
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- isSourceFilesValid tests --------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    @Test
    void isSourceFilesValid_ValidTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertEquals(file_handler_test_folder_1_Files, directoryHandler.sourceFilesCopy());
        directoryHandler.isSourceFilesValid();

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1_Files, directoryHandler.sourceFilesCopy()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceFilesFlag()),
                () -> assertFalse(directoryHandler.isSourceFilesHasInvalidFileFlag())
        );
    }

    @Test
    void isSourceFilesValid_InvalidTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        ArrayList<File> copy_test_folder_1_Files = new ArrayList<File>(Arrays.asList(
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\localWorldTest.txt"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Octopus attack (2).jpg"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Octopus.png")));
        assertEquals(copy_test_folder_1_Files, directoryHandler.sourceFilesCopy());
        FileUtils.deleteDirectory(directoryHandler.sourceDirectoryCopy());
        directoryHandler.isSourceFilesValid();

        assertAll(
                // Parameters
                () -> assertEquals(copy_test_folder_1_Files, directoryHandler.sourceFilesCopy()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceFilesFlag()),
                () -> assertTrue(directoryHandler.isSourceFilesHasInvalidFileFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- isValidSourceDirectoryFile tests ------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    @Test
    void isValidSourceDirectoryFile_ValidFileTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertTrue(directoryHandler.isValidSourceDirectoryFile(file_handler_test_folder_1_Files.get(0)));
    }

    @Test
    void isValidSourceDirectoryFile_NullFileTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidSourceDirectoryFile(null));
    }

    @Test
    void isValidSourceDirectoryFile_NonExistentFileTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidSourceDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\fake_file.docx")));
    }

    @Test
    void isValidSourceDirectoryFile_NonFileTest() {
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_17"));
        assertTrue(directoryHandler.isValidSourceDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_17\\18-virtual memory and paging.pdf")));
        assertFalse(directoryHandler.isValidSourceDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_17\\test_subdirectory")));
    }

    @Test
    void isValidSourceDirectoryFile_HiddenFileTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidSourceDirectoryFile(file_handler_test_folder_2_Files.get(1)));
    }

    @Test
    void isValidSourceDirectoryFile_UnreadableFileTest() { // Could possibly be explained by the File.canRead() documentation: https://docs.oracle.com/javase/8/docs/api/java/io/File.html#canRead--.
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_1"));
        assertFalse(directoryHandler.isValidSourceDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_1\\01 Ethical Theory Reading 1.pdf")));
    }

    @Test
    void isValidSourceDirectoryFile_FileNotInSourceDirectoryTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidSourceDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\about_this_folder.txt")));
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- replaceSourceFiles (array) tests ------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

//    @Test
//    void replaceSourceFilesArray_NonNullArrayNoFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        directoryHandler.replaceSourceFiles(testDirectory.listFiles());
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(file_handler_test_folder_1_Files, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(file_handler_test_folder_1_Names, directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void replaceSourceFilesArray_NullFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        File[] testDirectoryFiles = testDirectory.listFiles();
//        testDirectoryFiles[0] = null;
//
//        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
//        expectedSourceFiles.remove(0);
//        ArrayList<String> expectedSourceFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedSourceFileNames.remove(0);
//
//        directoryHandler.replaceSourceFiles(testDirectoryFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void replaceSourceFilesArray_HiddenFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2");
//
//        ArrayList<File> expectedSourceFiles = file_handler_test_folder_2_Files_Copy();
//        expectedSourceFiles.remove(1);
//        ArrayList<String> expectedSourceFileNames = file_handler_test_folder_2_Names_Copy();
//        expectedSourceFileNames.remove(1);
//
//        directoryHandler.replaceSourceFiles(testDirectory.listFiles());
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void replaceSourceFilesArray_NonExistentFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        File[] testDirectoryFiles = testDirectory.listFiles();
//        testDirectoryFiles[1] = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\fake file.png");
//
//        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
//        expectedSourceFiles.remove(1);
//        ArrayList<String> expectedSourceFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedSourceFileNames.remove(1);
//
//        directoryHandler.replaceSourceFiles(testDirectoryFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }

    // -----------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- replaceSourceFiles (ArrayList) tests ------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------

//    @Test
//    void replaceSourceFilesArrayList_NonNullArrayNoFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectory.listFiles())));
//        directoryHandler.replaceSourceFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(file_handler_test_folder_1_Files, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(file_handler_test_folder_1_Names, directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void replaceSourceFilesArrayList_NullFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectory.listFiles())));
//        listFiles.set(0, null);
//
//        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
//        expectedSourceFiles.remove(0);
//        ArrayList<String> expectedSourceFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedSourceFileNames.remove(0);
//
//        directoryHandler.replaceSourceFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void replaceSourceFilesArrayList_HiddenFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2");
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectory.listFiles())));
//
//        ArrayList<File> expectedSourceFiles = file_handler_test_folder_2_Files_Copy();
//        expectedSourceFiles.remove(1);
//        ArrayList<String> expectedSourceFileNames = file_handler_test_folder_2_Names_Copy();
//        expectedSourceFileNames.remove(1);
//
//        directoryHandler.replaceSourceFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void replaceSourceFilesArrayList_NonExistentFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        File[] testDirectoryFiles = testDirectory.listFiles();
//        testDirectoryFiles[1] = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\fake file.png");
//
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectoryFiles)));
//
//        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
//        expectedSourceFiles.remove(1);
//        ArrayList<String> expectedSourceFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedSourceFileNames.remove(1);
//
//        directoryHandler.replaceSourceFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- appendSourceFile tests ------------------------------------------
    // ---------------------------------------------------------------------------------------------------------------

//    @Test
//    void appendSourceFile_NonNullNonFilteredFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg");
//        final ArrayList<File> expectedSourceFiles = new ArrayList<File>(List.of(testFile));
//        final ArrayList<String> expectedSourceFileNames = new ArrayList<String>(List.of(testFile.getName()));
//        directoryHandler.appendSourceFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void appendSourceFile_NullFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg");
//        directoryHandler.appendSourceFile(null);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void appendSourceFile_HiddenFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Bio 218 Assignment 3.docx");
//        directoryHandler.appendSourceFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void appendSourceFile_NonFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2");
//        directoryHandler.appendSourceFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getSourceFileNames())
//        );
//    }
//
//    @Test
//    void appendSourceFile_NonExistentFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\fake_file.txt");
//        directoryHandler.appendSourceFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getSourceFileNames())
//        );
//    }

//    @Test
//    void appendSourceFile_DuplicateFileTest(){
//        directoryHandler.replaceSourceFiles(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1").listFiles());
//        final ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
//        final ArrayList<String> expectedSourceFileNames = file_handler_test_folder_1_Names_Copy();
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Octopus.png");
//        directoryHandler.appendSourceFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
//                () -> assertEquals(expectedSourceFileNames, directoryHandler.getSourceFileNames())
//        );
//    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- initializeSourceDirectory tests -------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // initializeSourceDirectory needs to be private and should be tested indirectly.
    @Test
    void initializeSourceDirectory_Test() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1,  directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(file_handler_test_folder_1.getName(),  directoryHandler.getSourceDirectoryName()),
                () -> assertEquals(file_handler_test_folder_1.getAbsolutePath(),  directoryHandler.getSourceDirectoryPath()),
                () -> assertEquals(file_handler_test_folder_1_Files_Copy(),  directoryHandler.sourceFilesCopy())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- initializeSourceRecords tests ---------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // initializeSourceRecords needs to be private and should be tested indirectly.
    @Test
    void initializeSourceRecords_Test() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFileRecord,  directoryHandler.getSourceFileRecord()),
                () -> assertEquals(new ArrayList<String>(), directoryHandler.getFilterRecord()),
                () -> assertEquals(new Stack<>(), directoryHandler.getFilterRecordUnformatted())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedSourceIsNotNullTest tests ----------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedSourceIsNotNullTest needs to be private and should be tested indirectly.
    @Test
    void requestedSourceIsNotNullTest_NullSourceTest() {
        directoryHandler.processNewSourceDirectory(null);

        assertAll(
                // Flags
                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void requestedSourceIsNotNullTest_NonNullSourceTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedSourceExistsTest tests -------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedSourceExistsTest needs to be private and should be tested indirectly.
    @Test
    void requestedSourceExistsTest_SourceExistsTest() {
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_1_PATH + "\\about_this_folder.txt"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void requestedSourceExistsTest_NonExistentTest() {
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_1_PATH + "\\fake_folder"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedSourceIsADirectoryTest tests -------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedSourceIsADirectoryTest needs to be private and should be tested indirectly.
    @Test
    void requestedSourceIsADirectoryTest_SourceIsDirectoryTest() {
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_3"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void requestedSourceIsADirectoryTest_SourceIsNotDirectoryTest() {
        directoryHandler.processNewSourceDirectory(new File(file_handler_test_folder_1_PATH + "\\about_this_folder.txt"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedSourceIsReadableTest tests ---------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedSourceIsReadableTest needs to be private and should be tested indirectly.
    @Test
    void requestedSourceIsReadableTest_readableSourceTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void requestedSourceIsReadableTest_UnreadableSourceTest() {
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_3"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- directoryIsValidSource tests ----------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // directoryIsValidSource needs to be private and should be tested indirectly.
    @Test
    void directoryIsValidSource_ValidSourceTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void directoryIsValidSource_InvalidSourceTest() {
        directoryHandler.processNewSourceDirectory(null);

        assertAll(
                // Flags
                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- processNewSourceDirectory tests ---------------------------------
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    void processNewSourceDirectory_NonNullDirectoryTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder");
        File expectedSourceDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder");
        directoryHandler.processNewSourceDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceDirectory, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(19, directoryHandler.sourceFilesSize()),
                () -> assertEquals("SelectiveFileSaverTestFolder", directoryHandler.getSourceDirectoryName()),
                () -> assertEquals("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder", directoryHandler.getSourceDirectoryPath()),
                () -> assertEquals(1, directoryHandler.getSourceFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewSourceDirectory_NullDirectoryTest() {
        ArrayList<File> expectedSourceFiles = directoryHandler.sourceFilesCopy();
        directoryHandler.processNewSourceDirectory(null);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(new ArrayList<File>(), directoryHandler.sourceFilesCopy()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryName()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getSourceFileRecord().size()),

                // Flags
                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewSourceDirectory_NonExistentDirectoryTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverFakeTestFolder");
        directoryHandler.processNewSourceDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(0, directoryHandler.sourceFilesSize()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryName()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getSourceFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewSourceDirectory_NonDirectoryTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder\\Bio 218 Assignment 3 Tree.png");
        directoryHandler.processNewSourceDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(0, directoryHandler.sourceFilesSize()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryName()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getSourceFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewSourceDirectory_NonExistentNonDirectoryTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder\\Bio 218 Assignment 4 Tree.png");
        directoryHandler.processNewSourceDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(0, directoryHandler.sourceFilesSize()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryName()),
                () -> assertEquals("", directoryHandler.getSourceDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getSourceFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewSourceDirectory_SourceEqualsTargetTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder");
        File expectedSourceDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder");
        directoryHandler.processNewTargetDirectory(testDirectory);
        directoryHandler.processNewSourceDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceDirectory, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(19, directoryHandler.sourceFilesSize()),
                () -> assertEquals("SelectiveFileSaverTestFolder", directoryHandler.getSourceDirectoryName()),
                () -> assertEquals("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder", directoryHandler.getSourceDirectoryPath()),
                () -> assertEquals(1, directoryHandler.getSourceFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- refreshSourceDirectory tests ------------------------------------
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    void refreshSourceDirectory_NoSourceSelectedTest() {
        directoryHandler.refreshSourceDirectory();

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(new ArrayList<>(), directoryHandler.sourceFilesCopy()),
                () -> assertEquals(new ArrayList<>(), directoryHandler.getSourceFileRecord()),
                () -> assertEquals(new Stack<>(), directoryHandler.filterRecord),

                // Flags
                () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void refreshSourceDirectory_ValidSourceSelectedTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.refreshSourceDirectory();
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(file_handler_test_folder_1_Files_Copy(), directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),
                () -> assertEquals(new Stack<>(), directoryHandler.filterRecord),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void refreshSourceDirectory_InvalidSourceSelectedTest() throws IOException {
        File rename = new File("new name3");
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        if(directoryHandler.sourceDirectoryCopy().renameTo(rename)){ // This will sometimes fail if run a few times, change name of 'rename' to fix.
            directoryHandler.refreshSourceDirectory();
            assertAll(
                    // Parameters
                    () -> assertNull(directoryHandler.sourceDirectoryCopy()),
                    () -> assertEquals(new ArrayList<>(), directoryHandler.sourceFilesCopy()),
                    () -> assertEquals(new ArrayList<>(), directoryHandler.getSourceFileRecord()),
                    () -> assertEquals(new Stack<>(), directoryHandler.filterRecord),

                    // Flags
                    () -> assertTrue(directoryHandler.isNullSourceDirectoryFlag()),
                    () -> assertTrue(directoryHandler.isNonExistentSourceDirectoryFlag()),
                    () -> assertTrue(directoryHandler.isSourceIsNotDirectoryFlag()),
                    () -> assertTrue(directoryHandler.isSourceIsNotReadableFlag())
            );
        }
        else {
            fail();
        }
        rename = null;
    }

    @Test
    void refreshSourceDirectory_ValidSourceOneFilterTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.filterSourceFiles("Include by name", "Octopus");
        directoryHandler.refreshSourceDirectory();

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt"));
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'" + "Include by name".toLowerCase(Locale.ROOT) + " " + "Octopus" + "' to "  + 4 + " files.");

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void refreshSourceDirectory_ValidSourceTwoFiltersTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.filterSourceFiles("Include by name", "Octopus");
        directoryHandler.filterSourceFiles("Exclude by extension", ".png");
        directoryHandler.refreshSourceDirectory();

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt"));
        expectedSourceFileRecord.add(new ArrayList<File>(expectedSourceFiles));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus.png"));
        expectedSourceFileRecord.add(expectedSourceFiles);
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'" + "Include by name".toLowerCase(Locale.ROOT) + " " + "Octopus" + "' to "  + 4 + " files.");
        expectedFilterRecord.push("'" + "Exclude by extension".toLowerCase(Locale.ROOT) + " " + ".png" + "' to "  + 1 + " file.");

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void refreshSourceDirectory_ValidSourceTwoFiltersUndoOneTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.filterSourceFiles("Include by name", "Octopus");
        directoryHandler.filterSourceFiles("Exclude by extension", ".png");
        directoryHandler.undoFilterSourceFiles();
        directoryHandler.refreshSourceDirectory();

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt"));
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'" + "Include by name".toLowerCase(Locale.ROOT) + " " + "Octopus" + "' to "  + 4 + " files.");

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void refreshSourceDirectory_ValidSourceOneFilterThenValidTargetTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.filterSourceFiles("Include by name", "Octopus");
        directoryHandler.processNewTargetDirectory(copy_test_folder_2);
        directoryHandler.refreshSourceDirectory();

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt"));
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'" + "Include by name".toLowerCase(Locale.ROOT) + " " + "Octopus" + "' to "  + 4 + " files.");

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    @Test
    void refreshSourceDirectory_ValidSourceOneFilterThenInvalidTargetTest() {
        directoryHandler.processNewSourceDirectory(file_handler_test_folder_1);
        directoryHandler.filterSourceFiles("Include by name", "Octopus");
        directoryHandler.processNewTargetDirectory(new File("fake folder"));
        directoryHandler.refreshSourceDirectory();

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"));
        expectedSourceFiles.remove(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt"));
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'" + "Include by name".toLowerCase(Locale.ROOT) + " " + "Octopus" + "' to "  + 4 + " files.");

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.sourceDirectoryCopy()),
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),

                // Flags
                () -> assertFalse(directoryHandler.isNullSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentSourceDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isSourceIsNotReadableFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- isTargetFilesValid tests --------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    @Test
    void isTargetFilesValid_ValidTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertEquals(file_handler_test_folder_1_Files, directoryHandler.targetFilesCopy());
        directoryHandler.isTargetFilesValid();

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1_Files, directoryHandler.targetFilesCopy()),

                // Flags
                () -> assertFalse(directoryHandler.isNullTargetFilesFlag()),
                () -> assertFalse(directoryHandler.isTargetFilesHasInvalidFileFlag())
        );
    }

    @Test
    void isTargetFilesValid_InvalidTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewTargetDirectory(copy_test_folder_1);
        ArrayList<File> copy_test_folder_1_Files = new ArrayList<File>(Arrays.asList(
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\localWorldTest.txt"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Octopus attack (2).jpg"),
                new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_copy_test_folders\\copy_test_folder_1\\Octopus.png")));
        assertEquals(copy_test_folder_1_Files, directoryHandler.targetFilesCopy());
        FileUtils.deleteDirectory(directoryHandler.targetDirectoryCopy());
        directoryHandler.isTargetFilesValid();

        assertAll(
                // Parameters
                () -> assertEquals(copy_test_folder_1_Files, directoryHandler.targetFilesCopy()),

                // Flags
                () -> assertFalse(directoryHandler.isNullTargetFilesFlag()),
                () -> assertTrue(directoryHandler.isTargetFilesHasInvalidFileFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- isValidTargetDirectoryFile tests ------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    @Test
    void isValidTargetDirectoryFile_ValidFileTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertTrue(directoryHandler.isValidTargetDirectoryFile(file_handler_test_folder_1_Files.get(0)));
    }

    @Test
    void isValidTargetDirectoryFile_NullFileTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidTargetDirectoryFile(null));
    }

    @Test
    void isValidTargetDirectoryFile_NonExistentFileTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidTargetDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\fake_file.docx")));
    }

    @Test
    void isValidTargetDirectoryFile_NonFileTest() {
        directoryHandler.processNewTargetDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_17"));
        assertTrue(directoryHandler.isValidTargetDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_17\\18-virtual memory and paging.pdf")));
        assertFalse(directoryHandler.isValidTargetDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_17\\test_subdirectory")));
    }

    @Test
    void isValidTargetDirectoryFile_HiddenFileTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidTargetDirectoryFile(file_handler_test_folder_2_Files.get(1)));
    }

    @Test
    void isValidTargetDirectoryFile_UnreadableFileTest() { // Could possibly be explained by the File.canRead() documentation: https://docs.oracle.com/javase/8/docs/api/java/io/File.html#canRead--.
        directoryHandler.processNewTargetDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_1"));
        assertFalse(directoryHandler.isValidTargetDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_1\\01 Ethical Theory Reading 1.pdf")));
    }

    @Test
    void isValidTargetDirectoryFile_FileNotInTargetDirectoryTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        assertFalse(directoryHandler.isValidTargetDirectoryFile(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\about_this_folder.txt")));
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- replaceTargetFiles (array) tests ------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

//    @Test
//    void replaceTargetFilesArray_NonNullArrayNoFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        directoryHandler.replaceTargetFiles(testDirectory.listFiles());
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(file_handler_test_folder_1_Files, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(file_handler_test_folder_1_Names, directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void replaceTargetFilesArray_NullFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        File[] testDirectoryFiles = testDirectory.listFiles();
//        testDirectoryFiles[0] = null;
//
//        ArrayList<File> expectedTargetFiles = file_handler_test_folder_1_Files_Copy();
//        expectedTargetFiles.remove(0);
//        ArrayList<String> expectedTargetFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedTargetFileNames.remove(0);
//
//        directoryHandler.replaceTargetFiles(testDirectoryFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(expectedTargetFileNames, directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void replaceTargetFilesArray_HiddenFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2");
//
//        ArrayList<File> expectedTargetFiles = file_handler_test_folder_2_Files_Copy();
//        expectedTargetFiles.remove(1);
//        ArrayList<String> expectedTargetFileNames = file_handler_test_folder_2_Names_Copy();
//        expectedTargetFileNames.remove(1);
//
//        directoryHandler.replaceTargetFiles(testDirectory.listFiles());
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(expectedTargetFileNames, directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void replaceTargetFilesArray_NonExistentFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        File[] testDirectoryFiles = testDirectory.listFiles();
//        testDirectoryFiles[1] = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\fake file.png");
//
//        ArrayList<File> expectedTargetFiles = file_handler_test_folder_1_Files_Copy();
//        expectedTargetFiles.remove(1);
//        ArrayList<String> expectedTargetFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedTargetFileNames.remove(1);
//
//        directoryHandler.replaceTargetFiles(testDirectoryFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(expectedTargetFileNames, directoryHandler.getTargetFileNames())
//        );
//    }

    // -----------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- replaceTargetFiles (ArrayList) tests ------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------

//    @Test
//    void replaceTargetFilesArrayList_NonNullArrayNoFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectory.listFiles())));
//        directoryHandler.replaceTargetFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(file_handler_test_folder_1_Files, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(file_handler_test_folder_1_Names, directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void replaceTargetFilesArrayList_NullFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectory.listFiles())));
//        listFiles.set(0, null);
//
//        ArrayList<File> expectedTargetFiles = file_handler_test_folder_1_Files_Copy();
//        expectedTargetFiles.remove(0);
//        ArrayList<String> expectedTargetFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedTargetFileNames.remove(0);
//
//        directoryHandler.replaceTargetFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(expectedTargetFileNames, directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void replaceTargetFilesArrayList_HiddenFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2");
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectory.listFiles())));
//
//        ArrayList<File> expectedTargetFiles = file_handler_test_folder_2_Files_Copy();
//        expectedTargetFiles.remove(1);
//        ArrayList<String> expectedTargetFileNames = file_handler_test_folder_2_Names_Copy();
//        expectedTargetFileNames.remove(1);
//
//        directoryHandler.replaceTargetFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(expectedTargetFileNames, directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void replaceTargetFilesArrayList_NonExistentFilesFilteredTest() {
//        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1");
//        File[] testDirectoryFiles = testDirectory.listFiles();
//        testDirectoryFiles[1] = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\fake file.png");
//        ArrayList<File> listFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(testDirectoryFiles)));
//
//        ArrayList<File> expectedTargetFiles = file_handler_test_folder_1_Files_Copy();
//        expectedTargetFiles.remove(1);
//        ArrayList<String> expectedTargetFileNames = file_handler_test_folder_1_Names_Copy();
//        expectedTargetFileNames.remove(1);
//
//        directoryHandler.replaceTargetFiles(listFiles);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(expectedTargetFileNames, directoryHandler.getTargetFileNames())
//        );
//    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- appendTargetFile tests ------------------------------------------
    // ---------------------------------------------------------------------------------------------------------------

//    @Test
//    void appendTargetFile_NonNullNonFilteredFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg");
//        final ArrayList<File> expectedTargetFiles = new ArrayList<File>(List.of(testFile));
//        final ArrayList<String> expectedTargetFileNames = new ArrayList<String>(List.of(testFile.getName()));
//        directoryHandler.appendTargetFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(expectedTargetFileNames, directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void appendTargetFile_NullFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg");
//        directoryHandler.appendTargetFile(null);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.targetFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void appendTargetFile_HiddenFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Bio 218 Assignment 3.docx");
//        directoryHandler.appendTargetFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.targetFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void appendTargetFile_NonFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2");
//        directoryHandler.appendTargetFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.targetFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getTargetFileNames())
//        );
//    }
//
//    @Test
//    void appendTargetFile_NonExistentFileTest(){
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\fake_file.txt");
//        directoryHandler.appendTargetFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(new ArrayList<File>(), directoryHandler.targetFilesCopy()),
//                () -> assertEquals(new ArrayList<String>(), directoryHandler.getTargetFileNames())
//        );
//    }

//    @Test
//    void appendTargetFile_DuplicateFileTest(){
//        directoryHandler.replaceTargetFiles(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1").listFiles());
//        File testFile = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Octopus.png");
//        directoryHandler.appendTargetFile(testFile);
//
//        assertAll(
//                // Parameters
//                () -> assertEquals(file_handler_test_folder_1_Files, directoryHandler.targetFilesCopy()),
//                () -> assertEquals(file_handler_test_folder_1_Names, directoryHandler.getTargetFileNames())
//        );
//    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- initializeTargetDirectory tests -------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // initializeTargetDirectory needs to be private and should be tested indirectly.
    @Test
    void initializeTargetDirectory_Test() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1,  directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(file_handler_test_folder_1.getName(),  directoryHandler.getTargetDirectoryName()),
                () -> assertEquals(file_handler_test_folder_1.getAbsolutePath(),  directoryHandler.getTargetDirectoryPath()),
                () -> assertEquals(file_handler_test_folder_1_Files_Copy(),  directoryHandler.targetFilesCopy())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- initializeTargetRecords tests ---------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // initializeTargetRecords needs to be private and should be tested indirectly.
    @Test
    void initializeTargetRecords_Test() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        ArrayList<ArrayList<File>> expectedTargetFileRecord = new ArrayList<>();
        expectedTargetFileRecord.add(file_handler_test_folder_1_Files_Copy());

        assertAll(
                // Parameters
                () -> assertEquals(expectedTargetFileRecord,  directoryHandler.getTargetFileRecord()),
                () -> assertEquals(new ArrayList<String>(), directoryHandler.getFilterRecord()),
                () -> assertEquals(new Stack<>(), directoryHandler.getFilterRecordUnformatted())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedTargetIsNotNullTest tests ----------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedTargetIsNotNullTest needs to be private and should be tested indirectly.
    @Test
    void requestedTargetIsNotNullTest_NullTargetTest() {
        directoryHandler.processNewTargetDirectory(null);

        assertAll(
                // Flags
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    @Test
    void requestedTargetIsNotNullTest_NonNullTargetTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedTargetExistsTest tests -------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedTargetExistsTest needs to be private and should be tested indirectly.
    @Test
    void requestedTargetExistsTest_TargetExistsTest() {
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_1_PATH + "\\about_this_folder.txt"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    @Test
    void requestedTargetExistsTest_NonExistentTest() {
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_1_PATH + "\\fake_folder"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedTargetIsADirectoryTest tests -------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedTargetIsADirectoryTest needs to be private and should be tested indirectly.
    @Test
    void requestedTargetIsADirectoryTest_TargetIsDirectoryTest() {
        directoryHandler.processNewTargetDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_3"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    @Test
    void requestedTargetIsADirectoryTest_TargetIsNotDirectoryTest() {
        directoryHandler.processNewTargetDirectory(new File(file_handler_test_folder_1_PATH + "\\about_this_folder.txt"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- requestedTargetIsReadableTest tests ---------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // requestedTargetIsReadableTest needs to be private and should be tested indirectly.
    @Test
    void requestedTargetIsReadableTest_readableTargetTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    @Test
    void requestedTargetIsReadableTest_UnreadableTargetTest() {
        directoryHandler.processNewTargetDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\gui_test_folders\\gui_test_folder_3"));

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    // -------------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- directoryIsValidTarget tests ----------------------------------------------
    // -------------------------------------------------------------------------------------------------------------------------

    // directoryIsValidTarget needs to be private and should be tested indirectly.
    @Test
    void directoryIsValidTarget_ValidTargetTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);

        assertAll(
                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    @Test
    void directoryIsValidTarget_InvalidTargetTest() {
        directoryHandler.processNewTargetDirectory(null);

        assertAll(
                // Flags
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- processNewTargetDirectory tests ---------------------------------
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    void processNewTargetDirectory_NullDirectoryTest() {
        ArrayList<File> expectedTargetFiles = directoryHandler.targetFilesCopy();
        directoryHandler.processNewTargetDirectory(null);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(expectedTargetFiles, directoryHandler.targetFilesCopy()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryName()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getTargetFileRecord().size()),

                // Flags
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewTargetDirectory_NonExistentDirectoryTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverFakeTestFolder");
        directoryHandler.processNewTargetDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(0, directoryHandler.targetFilesSize()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryName()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getTargetFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewTargetDirectory_NonDirectoryTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder\\Bio 218 Assignment 3 Tree.png");
        directoryHandler.processNewTargetDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(0, directoryHandler.targetFilesSize()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryName()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getTargetFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewTargetDirectory_NonExistentNonDirectoryTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder\\Bio 218 Assignment 4 Tree.png");
        directoryHandler.processNewTargetDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(0, directoryHandler.targetFilesSize()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryName()),
                () -> assertEquals("", directoryHandler.getTargetDirectoryPath()),
                () -> assertEquals(0, directoryHandler.getTargetFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    @Test
    void processNewTargetDirectory_SourceEqualsTargetTest() {
        File testDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder");
        File expectedTargetDirectory = new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder");
        directoryHandler.processNewTargetDirectory(testDirectory);
        directoryHandler.processNewSourceDirectory(testDirectory);

        assertAll(
                // Parameters
                () -> assertEquals(expectedTargetDirectory, directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(19, directoryHandler.targetFilesSize()),
                () -> assertEquals("SelectiveFileSaverTestFolder", directoryHandler.getTargetDirectoryName()),
                () -> assertEquals("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\SelectiveFileSaverTestFolder", directoryHandler.getTargetDirectoryPath()),
                () -> assertEquals(1, directoryHandler.getTargetFileRecord().size()),

                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isSourceEqualsTargetFlag())
        );
    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- refreshTargetDirectory tests ------------------------------------
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    void refreshTargetDirectory_NoTargetSelectedTest() {
        directoryHandler.processNewSourceDirectory(copy_test_folder_1);
        directoryHandler.refreshTargetDirectory();

        assertAll(
                // Parameters
                () -> assertNull(directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(new ArrayList<>(), directoryHandler.targetFilesCopy()),
                () -> assertEquals(new ArrayList<>(), directoryHandler.getTargetFileRecord()),

                // Flags
                () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    @Test
    void refreshTargetDirectory_ValidTargetSelectedTest() {
        directoryHandler.processNewTargetDirectory(file_handler_test_folder_1);
        directoryHandler.refreshTargetDirectory();
        ArrayList<ArrayList<File>> expectedTargetFileRecord = new ArrayList<>();
        expectedTargetFileRecord.add(file_handler_test_folder_1_Files_Copy());

        assertAll(
                // Parameters
                () -> assertEquals(file_handler_test_folder_1, directoryHandler.targetDirectoryCopy()),
                () -> assertEquals(file_handler_test_folder_1_Files_Copy(), directoryHandler.targetFilesCopy()),
                () -> assertEquals(expectedTargetFileRecord, directoryHandler.getTargetFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isNullTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isNonExistentTargetDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotDirectoryFlag()),
                () -> assertFalse(directoryHandler.isTargetIsNotReadableFlag())
        );
    }

    @Test
    void refreshTargetDirectory_InvalidTargetSelectedTest() throws IOException {
        copyFileTestInitialize();
        directoryHandler.processNewTargetDirectory(copy_test_folder_1);

        if(directoryHandler.targetDirectoryCopy().renameTo(new File("new name2"))){ // This will sometimes fail if run a few times, change name in quotation marks to fix.
            directoryHandler.refreshTargetDirectory();
            assertAll(
                    // Parameters
                    () -> assertNull(directoryHandler.targetDirectoryCopy()),
                    () -> assertEquals(new ArrayList<>(), directoryHandler.targetFilesCopy()),
                    () -> assertEquals(new ArrayList<>(), directoryHandler.getTargetFileRecord()),

                    // Flags
                    () -> assertTrue(directoryHandler.isNullTargetDirectoryFlag()),
                    () -> assertTrue(directoryHandler.isNonExistentTargetDirectoryFlag()),
                    () -> assertTrue(directoryHandler.isTargetIsNotDirectoryFlag()),
                    () -> assertTrue(directoryHandler.isTargetIsNotReadableFlag())
            );
        }
        else {
            fail();
        }
    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- getFileExtension tests -------------------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void getFileExtension_NonNullFileTest(){
        assertEquals(".png", directoryHandler.getFileExtension(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_2\\Octopus.png")));
    }

    @Test
    void getFileExtension_NullFileTest(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> directoryHandler.getFileExtension(null));
    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- addToSourceFilterRecord tests ------------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void addToSourceFilterRecord_Test(){

    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- removeMostRecentFromSourceFilterRecord tests ---------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void removeMostRecentFromSourceFilterRecord_Test(){

    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- applyFilterIncludeByName tests -----------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void applyFilterIncludeByName_Test(){

    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- applyFilterIncludeByExtension tests ------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void applyFilterIncludeByExtension_Test(){

    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- applyFilterExcludeByName tests -----------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void applyFilterExcludeByName_Test(){

    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- applyFilterExcludeByExtension tests ------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void applyFilterExcludeByExtension_Test(){

    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- isValidFilterRequest tests ---------------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void isValidFilterRequest_Test(){

    }

    // ----------------------------------------------------------------------------------------------------------
    // --------------------------------------------- filterSourceFiles tests ------------------------------------
    // ----------------------------------------------------------------------------------------------------------

    @Test
    void filterSourceFiles_IncludeByNameWithCapitalLettersTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = new ArrayList<File>(Arrays.asList(
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg")));
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'include by name " + "Assignment" + "' to "  + 4 + " files.");
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);

        directoryHandler.filterSourceFiles("Include by name", "Assignment");

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag())
        );
    }

    @Test
    void filterSourceFiles_IncludeByNameNoCapitalLettersTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        Stack<String> expectedFilterRecord = new Stack<String>();
        expectedFilterRecord.push("'include by name " + "assignment" + "' to "  + 6 + " files.");
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(new ArrayList<File>());

        directoryHandler.filterSourceFiles("Include by name", "assignment");

        assertAll(
                // Parameters
                () -> assertEquals(new ArrayList<File>(), directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag())
        );
    }

    @Test
    void filterSourceFiles_IncludeByExtensionTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = new ArrayList<File>(Arrays.asList(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt")));
        Stack<String> expectedFilterRecord = new Stack<String>();
        expectedFilterRecord.push("'include by extension " + ".txt" + "' to "  + 4 + " files.");
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);

        directoryHandler.filterSourceFiles("Include by extension", ".txt");

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag())
        );
    }

    @Test
    void filterSourceFiles_ExcludeByNameWithCapitalsTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = new ArrayList<File>(Arrays.asList(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\about_this_folder.txt"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\localWorldTest.txt"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus attack (2).jpg"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus.png")));
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'exclude by name " + "Assignment" + "' to "  + 2 + " files.");
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);

        directoryHandler.filterSourceFiles("Exclude by name", "Assignment");

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag())
        );
    }

    @Test
    void filterSourceFiles_ExcludeByNameNoCapitalsTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'exclude by name " + "assignment" + "' to "  + 0 + " files.");
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);

        directoryHandler.filterSourceFiles("Exclude by name", "assignment");

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag())
        );
    }

    @Test
    void filterSourceFiles_ExcludeByExtensionTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = new ArrayList<File>(Arrays.asList(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus attack (2).jpg"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Octopus.png")));
        Stack<String> expectedFilterRecord = new Stack<String>();
        expectedFilterRecord.push("'exclude by extension " + ".txt" + "' to "  + 2 + " files.");
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);

        directoryHandler.filterSourceFiles("Exclude by extension", ".txt");

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag())
        );
    }

    @Test
    void filterSourceFiles_InvalidFilterTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        Stack<String> expectedFilterRecord = new Stack<String>();
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());

        directoryHandler.filterSourceFiles("Exclude by height", "< 6'");

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertTrue(directoryHandler.isInvalidFilterFlag())
        );
    }

    @Test
    void filterSourceFiles_NullFilterInstructionTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        Stack<String> expectedFilterRecord = new Stack<String>();
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());

        directoryHandler.filterSourceFiles("Include by name", null);

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertTrue(directoryHandler.isInvalidFilterFlag())
        );
    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- undoFilterRequestIsValid tests ----------------------------------
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    void undoFilterRequestIsValid_Test(){

    }

    // ---------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- undoFilterSourceFiles tests -------------------------------------
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    void undoFilterSourceFiles_NoFiltersAppliedTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        Stack<String> expectedFilterRecord = new Stack<>();
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());

        directoryHandler.undoFilterSourceFiles();

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertTrue(directoryHandler.isInvalidUndoRequestFlag())
        );
    }

    @Test
    void undoFilterSourceFiles_OneFilterAppliedTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = file_handler_test_folder_1_Files_Copy();
        Stack<String> expectedFilterRecord = new Stack<>();
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());

        directoryHandler.filterSourceFiles("Include by name", "Assignment");
        directoryHandler.undoFilterSourceFiles();

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag()),
                () -> assertFalse(directoryHandler.isInvalidUndoRequestFlag())
        );
    }

    @Test
    void undoFilterSourceFiles_TwoFiltersAppliedTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));

        ArrayList<File> expectedSourceFiles = new ArrayList<File>(Arrays.asList(
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 3.docx"),
                                              new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1\\Bio 218 Assignment 4 Drawing.jpg")));
        Stack<String> expectedFilterRecord = new Stack<>();
        expectedFilterRecord.push("'include by name " + "Assignment" + "' to "  + 4 + " files.");
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        expectedSourceFileRecord.add(expectedSourceFiles);

        directoryHandler.filterSourceFiles("Include by name", "Assignment");
        directoryHandler.filterSourceFiles("Exclude by extension", ".docx");
        directoryHandler.undoFilterSourceFiles();

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord()),

                // Flags
                () -> assertFalse(directoryHandler.isInvalidFilterFlag()),
                () -> assertFalse(directoryHandler.isInvalidUndoRequestFlag())
        );
    }

    @Test
    void undoFilterSourceFiles_NoSourceSelectedTest(){
        Stack<String> expectedFilterRecord = new Stack<>();
        ArrayList<File> expectedSourceFiles = new ArrayList<>();
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        directoryHandler.undoFilterSourceFiles();

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFiles, directoryHandler.sourceFilesCopy()),
                () -> assertEquals(expectedFilterRecord, directoryHandler.filterRecord),
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord())
        );
    }

    @Test
    void undoFilterSourceFiles_FilterRecordErrorTest(){
        directoryHandler.processNewSourceDirectory(new File("C:\\Users\\gtkac\\Desktop\\Paperwork\\Program Test Files\\file_handler_test_folders\\file_handler_test_folder_1"));
        ArrayList<ArrayList<File>> expectedSourceFileRecord = new ArrayList<ArrayList<File>>();
        expectedSourceFileRecord.add(file_handler_test_folder_1_Files_Copy());
        directoryHandler.filterRecord.push("Placeholder_1");
        directoryHandler.filterRecord.push("Placeholder_2");
        directoryHandler.filterRecord.push("Placeholder_3");
        directoryHandler.undoFilterSourceFiles();

        assertAll(
                // Parameters
                () -> assertEquals(expectedSourceFileRecord, directoryHandler.getSourceFileRecord())
        );
    }
}
