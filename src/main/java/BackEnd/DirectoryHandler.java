package BackEnd;

import javafx.util.Pair;
import java.io.File;
import java.util.*;

/**
 * A DirectoryHandler object retrieves and manages the source and target directories selected by the user.
 */
public class DirectoryHandler{

    // Constants.
    private final String FILTER_1 = "Include by name";
    private final String FILTER_2 = "Include by extension";
    private final String FILTER_3 = "Exclude by name";
    private final String FILTER_4 = "Exclude by extension";

    // Directories and files.
    private File sourceDirectory, targetDirectory;
    private String sourceDirectoryName, sourceDirectoryPath, targetDirectoryName, targetDirectoryPath;
    private ArrayList<File> sourceFiles, targetFiles;
    private ArrayList<String> sourceFileNames, targetFileNames; // A list of the names of the files in the current source or target directory.
    private ArrayList<ArrayList<File>> sourceFileRecord, targetFileRecord; // The last entry should always equal sourceFiles or targetFiles.

    // Fields for filtering operations.
    public Stack<String> filterRecord; // An ordered record of filter applications.
    public Stack<Pair<String, String>> filterRecordUnformatted;

    // Flags.
    // Source directory specific flags.
    private boolean nullSourceDirectoryFlag, nonExistentSourceDirectoryFlag, sourceIsNotDirectoryFlag, sourceIsNotReadableFlag;

    // Target directory specific flags.
    private boolean nullTargetDirectoryFlag, nonExistentTargetDirectoryFlag, targetIsNotDirectoryFlag, targetIsNotReadableFlag;

    // Source and target directory flags.
    private boolean sourceEqualsTargetFlag;

    // sourceFiles and targetFiles flags.
    private boolean nullSourceFilesFlag, sourceFilesHasInvalidFileFlag, nullTargetFilesFlag, targetFilesHasInvalidFileFlag;

    // Filter flags.
    private boolean invalidFilterFlag, invalidUndoRequestFlag;

    /**
     * Constructor.
     */
    public DirectoryHandler(){
        resetVariables();
        resetFlags();
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Helper functions ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Restores the original value of all DirectoryHandler variables.
     */
    private void resetVariables(){
        sourceDirectory = targetDirectory = null;
        sourceDirectoryName = sourceDirectoryPath = targetDirectoryName = targetDirectoryPath = "";
        sourceFiles = new ArrayList<File>();
        targetFiles = new ArrayList<File>();
        sourceFileNames = new ArrayList<String>();
        targetFileNames = new ArrayList<String>();
        sourceFileRecord = new ArrayList<ArrayList<File>>();
        targetFileRecord = new ArrayList<ArrayList<File>>();
        filterRecord = new Stack<String>();
        filterRecordUnformatted = new Stack<Pair<String, String>>();
    }

    /**
     * Restores the original value of all DirectoryHandler flags.
     */
    private void resetFlags(){
        nullSourceDirectoryFlag = nonExistentSourceDirectoryFlag = sourceIsNotDirectoryFlag = sourceIsNotReadableFlag = true;
        nullTargetDirectoryFlag = nonExistentTargetDirectoryFlag = targetIsNotDirectoryFlag = targetIsNotReadableFlag = true;
        nullSourceFilesFlag = sourceFilesHasInvalidFileFlag = nullTargetFilesFlag = targetFilesHasInvalidFileFlag = false;
        sourceEqualsTargetFlag = true;
        invalidFilterFlag = invalidUndoRequestFlag = false;
    }

    /**
     * Checks if sourceDirectory and targetDirectory are the same object. Updates sourceEqualsTargetFlag.
     * @return true if sourceDirectory and targetDirectory are the same, false otherwise.
     */
    private boolean sourceEqualsTargetTest(){
        if(Objects.equals(sourceDirectory, targetDirectory)){
            setSourceEqualsTargetFlag(true);
            return true;
        }
        else{
            setSourceEqualsTargetFlag(false);
            return false;
        }
    }

    /**
     * A helper function that determines if the given ArrayList contains any file with the same name as the given File.
     * @param arrayList the ArrayList to search.
     * @param fileToFind the File to search for.
     * @return true if the File is found, false otherwise.
     */
    boolean arrayListContainsFile(ArrayList<File> arrayList, File fileToFind){
        if(arrayList != null && fileToFind != null){
            for(File file: arrayList){
                if(file != null){
                    if(file.getName().equals(fileToFind.getName())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Source directory functions -----------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Restores the original value of parameters related to the source directory.
     */
    void resetSourceDirectoryParameters(){
        sourceDirectory = null;
        sourceDirectoryName = "";
        sourceDirectoryPath = "";
        sourceFiles = new ArrayList<>();
        sourceFileNames = new ArrayList<>();
        sourceFileRecord = new ArrayList<>();
        filterRecord = new Stack<>();
        filterRecordUnformatted = new Stack<>();

        nullSourceDirectoryFlag = true;
        nonExistentSourceDirectoryFlag = true;
        sourceIsNotDirectoryFlag = true;
        sourceIsNotReadableFlag = true;
    }

    /**
     * Determines if sourceFiles is a valid source files set.
     * @return true if sourceFiles is a valid source files set, false otherwise.
     */
    boolean isSourceFilesValid(){
        setSourceFilesHasInvalidFileFlag(false);
        setNullSourceFilesFlag(false);
        if(sourceFiles == null){
            setNullSourceFilesFlag(true);
            return false;
        }
        if(sourceFiles.size() == 0){
            return true;
        }
        for(int i = 0; i < sourceFiles.size(); i++){
            if(!isValidSourceDirectoryFile(sourceFiles.get(i))){
                setSourceFilesHasInvalidFileFlag(true);
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if a given file meets the requirements to be added to sourceFiles.
     * Checks in order: not null, exists, is file, not hidden, readable, is in source directory.
     * @param file the file to test.
     * @return true if file can be added to sourceFiles, false otherwise.
     */
    boolean isValidSourceDirectoryFile(File file){
        if(file != null){
            File sourcePathWithFileName = new File(sourceDirectory.getAbsolutePath() + "\\" + file.getName());
            if(file.exists() && file.isFile() && !file.isHidden() && file.canRead() && sourcePathWithFileName.getAbsolutePath().equals(file.getAbsolutePath())){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Updates sourceFiles by replacing the entire set of files with a new set.
     * Invalid source directory files are filtered out of newFiles prior to replacement.
     * Precondition: sourceDirectory is valid.
     * @param newFiles An array of files to replace the current set.
     */
    private void replaceSourceFiles(File[] newFiles){
        ArrayList<File> newSourceFiles = new ArrayList<>();
        ArrayList<String> newSourceFileNames = new ArrayList<>();
        for(int i = 0; i < newFiles.length; i++){
            if(isValidSourceDirectoryFile(newFiles[i])){
                newSourceFiles.add(newFiles[i]);
                newSourceFileNames.add(newFiles[i].getName());
            }
        }
        setSourceFiles(newSourceFiles);
        setSourceFileNames(newSourceFileNames);
    }

    /**
     * Updates sourceFiles by replacing the entire set of files with a new set.
     * Invalid source directory files are filtered out of newFiles prior to the replacement.
     * Precondition: sourceDirectory is valid.
     * @param newFiles An ArrayList of files to replace the current set.
     */
    private void replaceSourceFiles(ArrayList<File> newFiles){
        ArrayList<File> newSourceFiles = new ArrayList<>();
        ArrayList<String> newSourceFileNames = new ArrayList<>();
        for(int i = 0; i < newFiles.size(); i++){
            if(isValidSourceDirectoryFile(newFiles.get(i))){
                newSourceFiles.add(newFiles.get(i));
                newSourceFileNames.add(newFiles.get(i).getName());
            }
        }
        setSourceFiles(newSourceFiles);
        setSourceFileNames(newSourceFileNames);
    }

    /**
     * Updates sourceFiles by appending a new file to the end.
     * Invalid source directory files and duplicate files are filtered out prior to appending.
     * @param newFile The file to append to the current set.
     */
    private void appendSourceFile(File newFile){
        if(isValidSourceDirectoryFile(newFile) && !arrayListContainsFile(sourceFiles, newFile)){
            sourceFiles.add(newFile);
            sourceFileNames.add(newFile.getName());
        }
    }

    /**
     * Updates sourceDirectory, sourceDirectoryName, sourceDirectoryPath, and sourceFiles with information from given directory.
     * Precondition: newSourceDirectory is a valid source directory.
     */
    private void initializeSourceDirectory(File newSourceDirectory){
        setSourceDirectory(newSourceDirectory);
        setSourceDirectoryName(newSourceDirectory.getName());
        setSourceDirectoryPath(newSourceDirectory.getAbsolutePath());
        replaceSourceFiles(Objects.requireNonNull(newSourceDirectory.listFiles()));
    }

    /**
     * Prepares sourceFileRecord, filterRecord, and filterRecordUnformatted for use by a new source directory.
     * Preconditions:
     *      1) sourceDirectory is a valid source directory.
     *      2) sourceFiles is a validated set of files taken from sourceDirectory.
     */
    private void initializeSourceRecords(){
        sourceFileRecord.clear();
        sourceFileRecord.add(sourceFiles);
        filterRecord.clear();
        filterRecordUnformatted.clear();
    }

    /**
     * Checks if a potential new source directory meets the requirement that the source directory is not null.
     * Updates source directory flags if the potential source directory fails the test.
     * Precondition: requestedSource is intended to immediately replace the current source directory.
     * @param requestedSource the directory to test.
     * @return true if requestedSource is not null, false otherwise.
     */
    private boolean requestedSourceIsNotNullTest(File requestedSource){
        if(requestedSource == null){
            setNullSourceDirectoryFlag(true);
            setNonExistentSourceDirectoryFlag(true);
            setSourceIsNotDirectoryFlag(true);
            setSourceIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a potential new source directory meets the requirement that the source directory exists.
     * Updates source directory flags if the potential source directory fails the test.
     * Precondition: requestedSource is not null and is intended to immediately replace the current source directory.
     * @param requestedSource the directory to test.
     * @return true if requestedSource exists, false otherwise.
     */
    private boolean requestedSourceExistsTest(File requestedSource){
        if(!requestedSource.exists()){
            setNullSourceDirectoryFlag(false);
            setNonExistentSourceDirectoryFlag(true);
            setSourceIsNotDirectoryFlag(true);
            setSourceIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a potential new source directory meets the requirement that the source directory is a directory.
     * Updates source directory flags if the potential source directory fails the test.
     * Precondition: requestedSource is not null, exists, and is intended to immediately replace the current source directory.
     * @param requestedSource the directory to test.
     * @return true if requestedSource is a directory, false otherwise.
     */
    private boolean requestedSourceIsADirectoryTest(File requestedSource){
        if(!requestedSource.isDirectory()){
            setNullSourceDirectoryFlag(false);
            setNonExistentSourceDirectoryFlag(false);
            setSourceIsNotDirectoryFlag(true);
            setSourceIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a potential new source directory meets the requirement that the source directory is readable.
     * Updates source directory flags if the potential source directory fails the test.
     * Precondition: requestedSource is not null, exists, is a directory, and is intended to immediately replace the current source directory.
     * @param requestedSource the directory to test.
     * @return true if requestedSource is readable, false otherwise.
     */
    private boolean requestedSourceIsReadableTest(File requestedSource){
        if(!requestedSource.canRead() || requestedSource.listFiles() == null){ // Checking for null list files can catch an unreadable directory when canRead() doesn't work as expected.
            setNullSourceDirectoryFlag(false);
            setNonExistentSourceDirectoryFlag(false);
            setSourceIsNotDirectoryFlag(false);
            setSourceIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a directory meets the requirements to be a source directory.
     * Updates source directory flags if the potential source directory passes the test.
     * Precondition: 'directory' is an object intended by the program to immediately replace the current source directory if it qualifies.
     * @param directory the directory to test.
     * @return true if directory is valid, false otherwise.
     */
    boolean directoryIsValidSource(File directory){
        if(requestedSourceIsNotNullTest(directory) && requestedSourceExistsTest(directory) && requestedSourceIsADirectoryTest(directory) && requestedSourceIsReadableTest(directory)){
            setNullSourceDirectoryFlag(false);
            setNonExistentSourceDirectoryFlag(false);
            setSourceIsNotDirectoryFlag(false);
            setSourceIsNotReadableFlag(false);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Handles the process of setting a new source directory.
     * @param selectedDirectory the directory to process.
     * @return true if selectedDirectory successfully replaced sourceDirectory, false otherwise.
     */
    public boolean processNewSourceDirectory(File selectedDirectory){
        if(directoryIsValidSource(selectedDirectory)){
            // Set source directory related fields.
            initializeSourceDirectory(selectedDirectory);
            initializeSourceRecords();

            // Check if the new source equals the existing target.
            sourceEqualsTargetTest();

            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Handles the process of refreshing sourceDirectory. Previously applied filters are applied to updated sourceFiles.
     * @return true if the source directory was successfully refreshed, false otherwise.
     */
    public boolean refreshSourceDirectory(){
        int filterRecordUnformattedSize = filterRecordUnformatted.size();
        Stack<Pair<String, String>> filterRecordUnformattedReversed = new Stack<>();
        for(int i = 0; i < filterRecordUnformattedSize; i++){
            filterRecordUnformattedReversed.push(filterRecordUnformatted.pop());
        }

        if(processNewSourceDirectory(sourceDirectory)){
            int filterRecordUnformattedReversedSize = filterRecordUnformattedReversed.size();
            for(int i = 0; i < filterRecordUnformattedReversedSize; i++){
                Pair<String, String> pair = filterRecordUnformattedReversed.pop();
                filterSourceFiles(pair.getKey(), pair.getValue());
            }
            return true;
        }
        else{
            resetSourceDirectoryParameters();
            return false;
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Target directory functions -----------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Restores the original value of parameters related to the target directory.
     */
    void resetTargetDirectoryParameters(){
        targetDirectory = null;
        targetDirectoryName = "";
        targetDirectoryPath = "";
        targetFiles = new ArrayList<>();
        targetFileNames = new ArrayList<>();
        targetFileRecord = new ArrayList<>();

        nullTargetDirectoryFlag = true;
        nonExistentTargetDirectoryFlag = true;
        targetIsNotDirectoryFlag = true;
        targetIsNotReadableFlag = true;
    }

    /**
     * Determines if targetFiles is a valid source files set.
     * @return true if targetFiles is a valid target files set, false otherwise.
     */
    boolean isTargetFilesValid(){
        setNullTargetFilesFlag(false);
        setTargetFilesHasInvalidFileFlag(false);
        if(targetFiles == null){
            setNullTargetFilesFlag(true);
            return false;
        }
        if(targetFiles.size() == 0){
            return true;
        }
        for(int i = 0; i < targetFiles.size(); i++){
            if(!isValidTargetDirectoryFile(targetFiles.get(i))){
                setTargetFilesHasInvalidFileFlag(true);
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if a given file meets the requirements to be added to targetFiles.
     * Checks in order: not null, exists, is file, not hidden, readable, is in target directory.
     * @param file the file to test.
     * @return true if file can be added to targetFiles, false otherwise.
     */
    boolean isValidTargetDirectoryFile(File file){
        if(file != null){
            File targetPathWithFileName = new File(targetDirectory.getAbsolutePath() + "\\" + file.getName());
            if(file.exists() && file.isFile() && !file.isHidden() && file.canRead() && targetPathWithFileName.getAbsolutePath().equals(file.getAbsolutePath())){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Updates targetFiles by replacing the entire set of files with a new set.
     * Invalid target directory files are filtered out of newFiles prior to the replacement.
     * Precondition: targetDirectory is valid.
     * @param newFiles An array of files to replace the current set.
     */
    private void replaceTargetFiles(File[] newFiles){
        ArrayList<File> newTargetFiles = new ArrayList<>();
        ArrayList<String> newTargetFileNames = new ArrayList<>();
        for(int i = 0; i < newFiles.length; i++){
            if(isValidTargetDirectoryFile(newFiles[i])){
                newTargetFiles.add(newFiles[i]);
                newTargetFileNames.add(newFiles[i].getName());
            }
        }
        setTargetFiles(newTargetFiles);
        setTargetFileNames(newTargetFileNames);
    }

    /**
     * Updates targetFiles by replacing the entire set of files with a new set.
     * Invalid target directory files are filtered out of newFiles prior to the replacement.
     * Precondition: targetDirectory is valid.
     * @param newFiles An ArrayList of files to replace the current set.
     */
    private void replaceTargetFiles(ArrayList<File> newFiles){
        ArrayList<File> newTargetFiles = new ArrayList<>();
        ArrayList<String> newTargetFileNames = new ArrayList<>();
        for(int i = 0; i < newFiles.size(); i++){
            if(isValidTargetDirectoryFile(newFiles.get(i))){
                newTargetFiles.add(newFiles.get(i));
                newTargetFileNames.add(newFiles.get(i).getName());
            }
        }
        setTargetFiles(newTargetFiles);
        setTargetFileNames(newTargetFileNames);
    }

    /**
     * Updates targetFiles by appending a new file to the end.
     * Invalid target directory files and duplicate files are filtered out prior to appending.
     * @param newFile The file to append to the current set.
     */
    private void appendTargetFile(File newFile){
        if(isValidTargetDirectoryFile(newFile) && !arrayListContainsFile(targetFiles, newFile)){
            targetFiles.add(newFile);
            targetFileNames.add(newFile.getName());
        }
    }

    /**
     * Updates targetDirectory, targetDirectoryName, targetDirectoryPath, and targetFiles with information from given directory.
     * Precondition: newTargetDirectory is a valid Target directory.
     */
    private void initializeTargetDirectory(File newTargetDirectory){
        setTargetDirectory(newTargetDirectory);
        setTargetDirectoryName(newTargetDirectory.getName());
        setTargetDirectoryPath(newTargetDirectory.getAbsolutePath());
        replaceTargetFiles(Objects.requireNonNull(newTargetDirectory.listFiles()));
    }

    /**
     * Prepares targetFileRecord, filterRecord, and filterRecordUnformatted for use by a new target directory.
     * Preconditions:
     *      1) targetDirectory is a valid Target directory.
     *      2) targetFiles is a validated set of files taken from targetDirectory.
     */
    private void initializeTargetRecords(){
        targetFileRecord.clear();
        targetFileRecord.add(targetFiles);
    }

    /**
     * Checks if a potential new target directory meets the requirement that the target directory is not null.
     * Updates target directory flags if the potential target directory fails the test.
     * Precondition: requestedTarget is intended to immediately replace the current target directory.
     * @param requestedTarget the directory to test.
     * @return true if requestedTarget is not null, false otherwise.
     */
    private boolean requestedTargetIsNotNullTest(File requestedTarget){
        if(requestedTarget == null){
            setNullTargetDirectoryFlag(true);
            setNonExistentTargetDirectoryFlag(true);
            setTargetIsNotDirectoryFlag(true);
            setTargetIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a potential new target directory meets the requirement that the target directory exists.
     * Updates target directory flags if the potential target directory fails the test.
     * Precondition: requestedTarget is not null and is intended to immediately replace the current target directory.
     * @param requestedTarget the directory to test.
     * @return true if requestedTarget exists, false otherwise.
     */
    private boolean requestedTargetExistsTest(File requestedTarget){
        if(!requestedTarget.exists()){
            setNullTargetDirectoryFlag(false);
            setNonExistentTargetDirectoryFlag(true);
            setTargetIsNotDirectoryFlag(true);
            setTargetIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a potential new target directory meets the requirement that the target directory is a directory.
     * Updates target directory flags if the potential target directory fails the test.
     * Precondition: requestedTarget is not null, exists, and is intended to immediately replace the current target directory.
     * @param requestedTarget the directory to test.
     * @return true if requestedTarget is a directory, false otherwise.
     */
    private boolean requestedTargetIsADirectoryTest(File requestedTarget){
        if(!requestedTarget.isDirectory()){
            setNullTargetDirectoryFlag(false);
            setNonExistentTargetDirectoryFlag(false);
            setTargetIsNotDirectoryFlag(true);
            setTargetIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a potential new target directory meets the requirement that the target directory is readable.
     * Updates target directory flags if the potential target directory fails the test.
     * Precondition: requestedTarget is not null, exists, is a directory, and is intended to immediately replace the current target directory.
     * @param requestedTarget the directory to test.
     * @return true if requestedTarget is readable, false otherwise.
     */
    private boolean requestedTargetIsReadableTest(File requestedTarget){
        if(!requestedTarget.canRead() || requestedTarget.listFiles() == null){ // Checking for null list files can catch an unreadable directory when canRead() doesn't work as expected.
            setNullTargetDirectoryFlag(false);
            setNonExistentTargetDirectoryFlag(false);
            setTargetIsNotDirectoryFlag(false);
            setTargetIsNotReadableFlag(true);
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Checks if a directory meets the requirements to be a target directory.
     * Updates target directory flags if the potential target directory passes the test.
     * Precondition: 'directory' is an object intended by the program to immediately replace the current target directory if it qualifies.
     * @param directory the directory to test.
     * @return true if directory is valid, false otherwise.
     */
    boolean directoryIsValidTarget(File directory){
        if(requestedTargetIsNotNullTest(directory) && requestedTargetExistsTest(directory) && requestedTargetIsADirectoryTest(directory) && requestedTargetIsReadableTest(directory)){
            setNullTargetDirectoryFlag(false);
            setNonExistentTargetDirectoryFlag(false);
            setTargetIsNotDirectoryFlag(false);
            setTargetIsNotReadableFlag(false);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Handles the process of setting a new target directory.
     * @param selectedDirectory the directory to process.
     * @return true if selectedDirectory successfully replaced targetDirectory, false otherwise.
     */
    public boolean processNewTargetDirectory(File selectedDirectory){
        if(directoryIsValidTarget(selectedDirectory)){
            // Set target directory related fields.
            initializeTargetDirectory(selectedDirectory);
            initializeTargetRecords();

            // Check if the new target equals the existing source.
            sourceEqualsTargetTest();

            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Handles the process of refreshing targetDirectory.
     * @return true if the target directory was successfully refreshed, false otherwise.
     */
    public boolean refreshTargetDirectory(){
        if(processNewTargetDirectory(targetDirectory)){
            return true;
        }
        else{
            resetTargetDirectoryParameters();
            return false;
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Filtering functions ------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * A helper function that returns the type of file.
     * Precondition: file is not null.
     */
    String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    /**
     * Updates filterRecord, filterRecordUnformatted, and sourceFiles with the given information.
     * Precondition: the method is executed immediately after a successful application of a valid filter to sourceFiles.
     * @param filter the filter of the new entry, should match one of the source filter constants.
     * @param filterInstruction the filter instruction of the new entry.
     * @param filteredFiles the number of files filtered from sourceFiles after an application of 'filter' with 'filterInstruction.'
     */
    private void addToSourceFilterRecord(String filter, String filterInstruction, int filteredFiles){
        if(filteredFiles == 1){
            filterRecord.push("'" + filter.toLowerCase(Locale.ROOT) + " " + filterInstruction + "' to "  + filteredFiles + " file.");
        }
        else{
            filterRecord.push("'" + filter.toLowerCase(Locale.ROOT) + " " + filterInstruction + "' to "  + filteredFiles + " files.");
        }
        filterRecordUnformatted.push(new Pair<>(filter, filterInstruction));
        sourceFileRecord.add(sourceFiles);
    }

    /**
     * Removes the most recently added object from filterRecord, filterRecordUnformatted, and sourceFileRecord.
     * @return the most recently added object in filterRecord.
     */
    private String removeMostRecentFromSourceFilterRecord(){
        filterRecordUnformatted.pop();
        sourceFileRecord.remove(sourceFileRecord.size() - 1);
        return filterRecord.pop();
    }

    /**
     * Applies the filter 'Include by name' to sourceFiles. Updates filterRecord, filterRecordUnformatted, and sourceFileRecord.
     */
    private void applyFilterIncludeByName(String filterInstruction){
        ArrayList<File> sourceFilesUpdated = new ArrayList<>();
        int filteredFiles = 0;
        for(int i = 0; i < sourceFiles.size(); i++){
            if(sourceFiles.get(i).getName().contains(filterInstruction)){
                sourceFilesUpdated.add(sourceFiles.get(i));
            }
            else{
                filteredFiles++;
            }
        }

        // Update the current source files and filter records.
        replaceSourceFiles(sourceFilesUpdated);
        addToSourceFilterRecord(FILTER_1, filterInstruction, filteredFiles);
    }

    /**
     * Applies the filter 'Include by extension' to sourceFiles. Updates filterRecord, filterRecordUnformatted, and sourceFileRecord.
     */
    private void applyFilterIncludeByExtension(String filterInstruction){
        ArrayList<File> sourceFilesUpdated = new ArrayList<>();
        int filteredFiles = 0;
        for(int i = 0; i < sourceFiles.size(); i++){
            if(getFileExtension(sourceFiles.get(i)).equals(filterInstruction)){
                sourceFilesUpdated.add(sourceFiles.get(i));
            }
            else{
                filteredFiles++;
            }
        }

        // Update the current source files and filter records.
        replaceSourceFiles(sourceFilesUpdated);
        addToSourceFilterRecord(FILTER_2, filterInstruction, filteredFiles);
    }

    /**
     * Applies the filter 'Exclude by name' to sourceFiles. Updates filterRecord, filterRecordUnformatted, and sourceFileRecord.
     */
    private void applyFilterExcludeByName(String filterInstruction){
        ArrayList<File> sourceFilesUpdated = new ArrayList<>();
        int filteredFiles = 0;
        for(int i = 0; i < sourceFiles.size(); i++){
            if(!sourceFiles.get(i).getName().contains(filterInstruction)){
                sourceFilesUpdated.add(sourceFiles.get(i));
            }
            else{
                filteredFiles++;
            }
        }

        // Update the current source files and filter records.
        replaceSourceFiles(sourceFilesUpdated);
        addToSourceFilterRecord(FILTER_3, filterInstruction, filteredFiles);
    }

    /**
     * Applies the filter 'Exclude by extension' to sourceFiles. Updates filterRecord, filterRecordUnformatted, and sourceFileRecord.
     */
    private void applyFilterExcludeByExtension(String filterInstruction){
        ArrayList<File> sourceFilesUpdated = new ArrayList<>();
        int filteredFiles = 0;
        for(int i = 0; i < sourceFiles.size(); i++){
            if(!getFileExtension(sourceFiles.get(i)).equals(filterInstruction)){
                sourceFilesUpdated.add(sourceFiles.get(i));
            }
            else{
                filteredFiles++;
            }
        }

        // Update the current source files and filter records.
        replaceSourceFiles(sourceFilesUpdated);
        addToSourceFilterRecord(FILTER_4, filterInstruction, filteredFiles);
    }

    /**
     * Determines if a filter request is valid.
     * @param filter the filter of the request.
     * @param filterInstruction the filter instruction of the request.
     * @return true if the requested operation is valid, false otherwise.
     */
    private boolean isValidFilterRequest(String filter, String filterInstruction){
        if(filter != null && filterInstruction != null && !filterInstruction.trim().equals("")){
            return filter.equals(FILTER_1) || filter.equals(FILTER_2) || filter.equals(FILTER_3) || filter.equals(FILTER_4);
        }
        return false;
    }

    /**
     * Applies a given filter to sourceFiles.
     * @param filter the filter to be applied.
     * @param filterInstruction specified the exact filter operation to perform.
     * @return true if the operation was completed successfully, false otherwise.
     */
    public boolean filterSourceFiles(String filter, String filterInstruction){
        if(isValidFilterRequest(filter, filterInstruction)){
            switch(filter){
                case FILTER_1:
                    applyFilterIncludeByName(filterInstruction);
                    break;
                case FILTER_2:
                    applyFilterIncludeByExtension(filterInstruction);
                    break;
                case FILTER_3:
                    applyFilterExcludeByName(filterInstruction);
                    break;
                case FILTER_4:
                    applyFilterExcludeByExtension(filterInstruction);
                    break;
            }
            setInvalidFilterFlag(false);
            return true;
        }
        else{
            setInvalidFilterFlag(true);
            return false;
        }
    }

    /**
     * Determines if it is possible to perform an 'undo filter application' operation in the current state.
     * @return true if the operation is possible, false otherwise.
     */
    private boolean undoFilterRequestIsValid(){
        if(sourceFileRecord.size() > 1 && filterRecord.size() > 0 && filterRecordUnformatted.size() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Restores the most recent version of sourceFiles stored in sourceFileRecord. Does not update filterRecord.
     * @return the formatted entry in filterRecord removed by the operation if successful, null otherwise.
     */
    public String undoFilterSourceFiles(){
        if(undoFilterRequestIsValid()){
            String filterRecordLastEntry = removeMostRecentFromSourceFilterRecord(); // Remove the last set of files, which = sourceFiles, from sourceFileRecord.
            replaceSourceFiles(sourceFileRecord.get(sourceFileRecord.size() - 1)); // Replace sourceFiles with the most recently saved unfiltered version.
            setInvalidUndoRequestFlag(false);
            return filterRecordLastEntry;
        }
        else{
            setInvalidUndoRequestFlag(true);
            return null;
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Helper functions ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    public int stringToASCII(String string){
        int sum = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            sum += c;
        }
        return sum;
    }

    /**
     * A brute force method for sorting sourceFiles and sourceFileNames by ASCII value. Offers customization flexibility.
     */
    public void sortSourceByASCIIValue(){
        ArrayList<Pair<String, Integer>> nameASCIIPairs = new ArrayList<>();
        for(int i = 0; i < sourceFiles.size(); i++){
            nameASCIIPairs.add(new Pair<>(sourceFileNames.get(i), stringToASCII(sourceFileNames.get(i))));
        }

        printArrayListOfStringIntegerPairs(nameASCIIPairs);

        // Bubble sort nameASCIIPairs, sourceFiles, and sourceFileNames.
        int i, j;
        Pair<String, Integer> tempPair;
        File tempFile;
        String tempString;
        boolean swapped;
        int n = nameASCIIPairs.size();

        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (nameASCIIPairs.get(j).getValue() > nameASCIIPairs.get(j + 1).getValue()) {
                    // Swap index j and j + 1.
                    tempPair = nameASCIIPairs.get(j);
                    tempFile = sourceFiles.get(j);
                    tempString = sourceFileNames.get(j);
                    nameASCIIPairs.set(j, nameASCIIPairs.get(j + 1));
                    sourceFiles.set(j, sourceFiles.get(j + 1));
                    sourceFileNames.set(j, sourceFileNames.get(j + 1));
                    nameASCIIPairs.set(j + 1, tempPair);
                    sourceFiles.set(j + 1, tempFile);
                    sourceFileNames.set(j + 1, tempString);
                    swapped = true;
                }
            }
            // Break if no two elements were swapped by inner loop.
            if (!swapped)
                break;
        }
        printArrayListOfStringIntegerPairs(nameASCIIPairs);
    }

    /**
     * A brute force method for sorting targetFiles and targetFileNames by ASCII value. Offers customization flexibility.
     */
    public void sortTargetByASCIIValue(){
        ArrayList<Pair<String, Integer>> nameASCIIPairs = new ArrayList<>();
        for(int i = 0; i < targetFiles.size(); i++){
            nameASCIIPairs.add(new Pair<>(targetFileNames.get(i), stringToASCII(targetFileNames.get(i))));
        }

        // Bubble sort nameASCIIPairs, targetFiles, and targetFileNames.
        int i, j;
        Pair<String, Integer> tempPair;
        File tempFile;
        String tempString;
        boolean swapped;
        int n = nameASCIIPairs.size();

        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (nameASCIIPairs.get(j).getValue() > nameASCIIPairs.get(j + 1).getValue()) {
                    // Swap index j and j + 1.
                    tempPair = nameASCIIPairs.get(j);
                    tempFile = targetFiles.get(j);
                    tempString = targetFileNames.get(j);
                    nameASCIIPairs.set(j, nameASCIIPairs.get(j + 1));
                    targetFiles.set(j, targetFiles.get(j + 1));
                    targetFileNames.set(j, targetFileNames.get(j + 1));
                    nameASCIIPairs.set(j + 1, tempPair);
                    targetFiles.set(j + 1, tempFile);
                    targetFileNames.set(j + 1, tempString);
                    swapped = true;
                }
            }
            // Break if no two elements were swapped by inner loop.
            if (!swapped)
                break;
        }
    }

    /**
     * Returns a copy of sourceDirectory.
     * @return sourceDirectory copy if sourceDirectory != null, null otherwise.
     */
    File sourceDirectoryCopy(){
        if(sourceDirectory == null){
            return null;
        }
        else{
            return new File(sourceDirectory.getAbsolutePath());
        }
    }

    /**
     * Returns a copy of sourceFiles.
     * @return sourceFiles copy if sourceFiles != null, null otherwise.
     */
    ArrayList<File> sourceFilesCopy(){
        if(sourceFiles == null){
            return null;
        }
        else{
            return new ArrayList<File>(sourceFiles);
        }
    }

    /**
     * Returns the size of sourceFiles.
     * @return sourceFiles.size() if sourceFiles != null, 0 otherwise.
     */
    int sourceFilesSize(){
        if(sourceFiles == null){
            return 0;
        }
        else{
            return sourceFiles.size();
        }
    }

    /**
     * Returns a copy of targetDirectory.
     * @return targetDirectory copy if targetDirectory != null, null otherwise.
     */
    File targetDirectoryCopy(){
        if(targetDirectory == null){
            return null;
        }
        else{
            return new File(targetDirectory.getAbsolutePath());
        }
    }

    /**
     * Returns a copy of targetFiles.
     * @return targetFiles copy if targetFiles != null, null otherwise.
     */
    ArrayList<File> targetFilesCopy(){
        if(targetFiles == null){
            return null;
        }
        else{
            return new ArrayList<File>(targetFiles);
        }
    }

    /**
     * Returns the size of targetFiles.
     * @return targetFiles.size() if targetFiles != null, 0 otherwise.
     */
    int targetFilesSize(){
        if(targetFiles == null){
            return 0;
        }
        else{
            return targetFiles.size();
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Getters and setters ------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    // --------------------------------------------- Variables ----------------------------------------------------

    private File getSourceDirectory() {
        return sourceDirectory;
    }

    private void setSourceDirectory(File sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    private File getTargetDirectory() {
        return targetDirectory;
    }

    private void setTargetDirectory(File targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public String getSourceDirectoryName() {
        return sourceDirectoryName;
    }

    private void setSourceDirectoryName(String sourceDirectoryName) {
        this.sourceDirectoryName = sourceDirectoryName;
    }

    public String getSourceDirectoryPath() {
        return sourceDirectoryPath;
    }

    private void setSourceDirectoryPath(String sourceDirectoryPath) {
        this.sourceDirectoryPath = sourceDirectoryPath;
    }

    public String getTargetDirectoryName() {
        return targetDirectoryName;
    }

    private void setTargetDirectoryName(String targetDirectoryName) {
        this.targetDirectoryName = targetDirectoryName;
    }

    public String getTargetDirectoryPath() {
        return targetDirectoryPath;
    }

    private void setTargetDirectoryPath(String targetDirectoryPath) {
        this.targetDirectoryPath = targetDirectoryPath;
    }

    private ArrayList<File> getSourceFiles() {
        return sourceFiles;
    }

    private void setSourceFiles(ArrayList<File> sourceFiles) {
        this.sourceFiles = sourceFiles;
    }

    private ArrayList<File> getTargetFiles() {
        return targetFiles;
    }

    private void setTargetFiles(ArrayList<File> targetFiles) {
        this.targetFiles = targetFiles;
    }

    public ArrayList<String> getSourceFileNames() {
        return sourceFileNames;
    }

    private void setSourceFileNames(ArrayList<String> sourceFileNames) {
        this.sourceFileNames = sourceFileNames;
    }

    public ArrayList<String> getTargetFileNames() {
        return targetFileNames;
    }

    private void setTargetFileNames(ArrayList<String> targetFileNames) {
        this.targetFileNames = targetFileNames;
    }

    ArrayList<ArrayList<File>> getSourceFileRecord() {
        return sourceFileRecord;
    }

    private void setSourceFileRecord(ArrayList<ArrayList<File>> sourceFileRecord) {
        this.sourceFileRecord = sourceFileRecord;
    }

    ArrayList<ArrayList<File>> getTargetFileRecord() {
        return targetFileRecord;
    }

    private void setTargetFileRecord(ArrayList<ArrayList<File>> targetFileRecord) {
        this.targetFileRecord = targetFileRecord;
    }

    public Stack<String> getFilterRecord() {
        return filterRecord;
    }

    private void setFilterRecord(Stack<String> filterRecord) {
        this.filterRecord = filterRecord;
    }

    public Stack<Pair<String, String>> getFilterRecordUnformatted() {
        return filterRecordUnformatted;
    }

    private void setFilterRecordUnformatted(Stack<Pair<String, String>> filterRecordUnformatted) {
        this.filterRecordUnformatted = filterRecordUnformatted;
    }

    // --------------------------------------------- Flags --------------------------------------------------------

    public boolean isNullSourceDirectoryFlag(){
        return nullSourceDirectoryFlag;
    }

    private void setNullSourceDirectoryFlag(Boolean nullSourceDirectoryFlag){
        this.nullSourceDirectoryFlag = nullSourceDirectoryFlag;
    }

    public boolean isNonExistentSourceDirectoryFlag() {
        return nonExistentSourceDirectoryFlag;
    }

    private void setNonExistentSourceDirectoryFlag(boolean nonExistentSourceDirectoryFlag) {
        this.nonExistentSourceDirectoryFlag = nonExistentSourceDirectoryFlag;
    }

    public boolean isSourceIsNotDirectoryFlag() {
        return sourceIsNotDirectoryFlag;
    }

    private void setSourceIsNotDirectoryFlag(boolean sourceIsNotDirectoryFlag) {
        this.sourceIsNotDirectoryFlag = sourceIsNotDirectoryFlag;
    }

    public boolean isSourceIsNotReadableFlag() {
        return sourceIsNotReadableFlag;
    }

    private void setSourceIsNotReadableFlag(boolean sourceIsNotReadableFlag) {
        this.sourceIsNotReadableFlag = sourceIsNotReadableFlag;
    }

    public boolean isNullTargetDirectoryFlag(){
        return nullTargetDirectoryFlag;
    }

    private void setNullTargetDirectoryFlag(Boolean nullTargetDirectoryFlag){
        this.nullTargetDirectoryFlag = nullTargetDirectoryFlag;
    }

    public boolean isNonExistentTargetDirectoryFlag() {
        return nonExistentTargetDirectoryFlag;
    }

    private void setNonExistentTargetDirectoryFlag(boolean nonExistentTargetDirectoryFlag) {
        this.nonExistentTargetDirectoryFlag = nonExistentTargetDirectoryFlag;
    }

    public boolean isTargetIsNotDirectoryFlag() {
        return targetIsNotDirectoryFlag;
    }

    private void setTargetIsNotDirectoryFlag(boolean targetIsNotDirectoryFlag) {
        this.targetIsNotDirectoryFlag = targetIsNotDirectoryFlag;
    }

    public boolean isTargetIsNotReadableFlag() {
        return targetIsNotReadableFlag;
    }

    private void setTargetIsNotReadableFlag(boolean targetIsNotReadableFlag) {
        this.targetIsNotReadableFlag = targetIsNotReadableFlag;
    }

    public boolean isNullSourceFilesFlag() {
        return nullSourceFilesFlag;
    }

    private void setNullSourceFilesFlag(boolean nullSourceFilesFlag) {
        this.nullSourceFilesFlag = nullSourceFilesFlag;
    }

    public boolean isSourceFilesHasInvalidFileFlag() {
        return sourceFilesHasInvalidFileFlag;
    }

    private void setSourceFilesHasInvalidFileFlag(boolean sourceFilesHasInvalidFileFlag) {
        this.sourceFilesHasInvalidFileFlag = sourceFilesHasInvalidFileFlag;
    }

    public boolean isNullTargetFilesFlag() {
        return nullTargetFilesFlag;
    }

    private void setNullTargetFilesFlag(boolean nullTargetFilesFlag) {
        this.nullTargetFilesFlag = nullTargetFilesFlag;
    }

    public boolean isTargetFilesHasInvalidFileFlag() {
        return targetFilesHasInvalidFileFlag;
    }

    private void setTargetFilesHasInvalidFileFlag(boolean targetFilesHasInvalidFileFlag) {
        this.targetFilesHasInvalidFileFlag = targetFilesHasInvalidFileFlag;
    }

    public boolean isSourceEqualsTargetFlag(){
        return sourceEqualsTargetFlag;
    }

    private void setSourceEqualsTargetFlag(Boolean sourceEqualsTargetFlag){
        this.sourceEqualsTargetFlag = sourceEqualsTargetFlag;
    }

    public boolean isInvalidFilterFlag() {
        return invalidFilterFlag;
    }

    private void setInvalidFilterFlag(boolean invalidFilterFlag) {
        this.invalidFilterFlag = invalidFilterFlag;
    }

    public boolean isInvalidUndoRequestFlag() {
        return invalidUndoRequestFlag;
    }

    private void setInvalidUndoRequestFlag(boolean invalidUndoRequestFlag) {
        this.invalidUndoRequestFlag = invalidUndoRequestFlag;
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Debugging functions ------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    public void printSourceFiles(){
        System.out.println("-------------------- sourceFiles --------------------\n");
        for(File file: sourceFiles){
            System.out.println(file.getName());
        }

        System.out.println("\n\n");
    }

    public void printArrayListOfFilesAsNames(ArrayList<File> arrayList){
        System.out.println("-------------------- arrayList of Files --------------------\n");
        for(File file: arrayList){
            System.out.println(file.getName());
        }
        System.out.println("\n\n");
    }

    public void printArrayListOfStringIntegerPairs(ArrayList<Pair<String, Integer>> arrayList){
        System.out.println("-------------------- arrayList of Pair<String, Integer> --------------------\n");
        for(int i = 0; i < arrayList.size(); i++){
            System.out.println("Index " + i + ": <" + arrayList.get(i).getKey() + ", " + arrayList.get(i).getValue() + ">");
        }
        System.out.println("\n\n");
    }

    public void printTargetFiles(){
        System.out.println("-------------------- targetFiles --------------------\n");
        for(File file: targetFiles){
            System.out.println(file.getName());
        }
        System.out.println("\n\n");
    }

    public void printFilterRecord(){
        System.out.println("-------------------- filterRecord --------------------\n");
        for(String filterApplication: filterRecord){
            System.out.println(filterApplication);
        }
        System.out.println("\n\n");
    }

    public void printSourceFileRecord(){
        System.out.println("-------------------- sourceFileRecord --------------------\n");
        for(ArrayList<File> fileList: sourceFileRecord){
            for(File file: fileList){
                System.out.println(file.getName());
            }
            System.out.println("");
        }
        System.out.println("\n\n");
    }

    public void printFilterRecordUnformatted(){
        System.out.println("-------------------- filterRecordUnformatted --------------------\n");
        for(Pair<String, String> fileList: filterRecordUnformatted){
            for(int i = 0; i < filterRecordUnformatted.size(); i++){
                System.out.println("Index " + i + ": <" + filterRecordUnformatted.get(i).getKey() + ", " + filterRecordUnformatted.get(i).getValue() + ">");
            }
            System.out.println("");
        }
        System.out.println("\n\n");
    }
}
