package BackEnd;

import javafx.util.Pair;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * An ActionHandler object manages action requests.
 */
public class ActionHandler {

    // BackEnd objects.
    private final DirectoryHandler directoryHandler; // Contains directory and file information used by an ActionHandler.

    // Fields for copying operations.
    public Stack<String> copyRecord;
    public Stack<String> deleteFromSourceRecord;
    private String fileThatCausedException; // The name of the file that was being copied when an exception was thrown in copyFiles().

    // Flags.
    // copyActionPermitted flags.
    private boolean notEnoughDiskSpaceFlag, noSourceFilesFlag, sourceTargetDuplicatesFlag;

    // copyFiles flags.
    private boolean copyFilesNullPointerExceptionFlag, copyFilesIllegalArgumentExceptionFlag, copyFilesFileNotFoundExceptionFlag, copyFilesIOExceptionFlag;

    // deleteFromSource flags.
    private boolean deleteFromSourceDidNotCompleteFlag;

    /**
     * Constructor sets the default values of all variables and flags.
     * @param directoryHandler the DirectoryHandler to be registered with the new ActionHandler.
     */
    public ActionHandler(DirectoryHandler directoryHandler){
        this.directoryHandler = directoryHandler;
        resetVariables();
        resetFlags();
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Helper functions ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Restores the original value of all ActionHandler variables.
     */
    private void resetVariables(){
        copyRecord = new Stack<>();
        deleteFromSourceRecord = new Stack<>();
        fileThatCausedException = null;
    }

    /**
     * Restores the original value of all ActionHandler flags.
     */
    private void resetFlags(){
        notEnoughDiskSpaceFlag = noSourceFilesFlag = sourceTargetDuplicatesFlag = false;
        copyFilesNullPointerExceptionFlag = copyFilesIllegalArgumentExceptionFlag = copyFilesFileNotFoundExceptionFlag = copyFilesIOExceptionFlag = false;
        deleteFromSourceDidNotCompleteFlag = false;
    }

    /**
     * Returns the combined size of all files in a directory in bytes.
     * @param directoryFiles an ArrayList of Files in a directory.
     * @return the sum of all file sizes in directoryFiles if directoryFiles is not null or empty, 0 otherwise.
     */
    long directorySize(ArrayList<File> directoryFiles){
        if(directoryFiles == null || directoryFiles.isEmpty()){
            return 0;
        }
        else{
            long size = 0;
            for(File file: directoryFiles){
                if(file != null){
                    size += FileUtils.sizeOf(file);
                }
            }
            return size;
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Copy action screening functions ------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Checks if the current source and target directory are valid.
     * @return true if both are valid, false otherwise.
     */
    private boolean sourceAndTargetDirectoriesAreValid(){
        return directoryHandler.directoryIsValidSource(directoryHandler.sourceDirectoryCopy()) && directoryHandler.directoryIsValidTarget(directoryHandler.targetDirectoryCopy());
    }

    /**
     * Checks if the current source directory is valid.
     * @return true if source is valid, false otherwise.
     */
    private boolean sourceDirectoryIsValid(){
        return directoryHandler.directoryIsValidSource(directoryHandler.sourceDirectoryCopy());
    }

    /**
     * Checks if the current source and target files are valid sets.
     * @return true if both are valid, false otherwise.
     */
    private boolean sourceAndTargetFilesAreValid(){
        return directoryHandler.isSourceFilesValid() && directoryHandler.isTargetFilesValid();
    }

    /**
     * Checks if the current source files is a valid set.
     * @return true if source files is valid, false otherwise.
     */
    private boolean sourceFilesAreValid(){
        return directoryHandler.isSourceFilesValid();
    }

    /**
     * Checks if there is enough usable storage space in the device containing targetDirectory to create copies of each file in sourceFiles.
     * Currently only works with windows and unix based systems.
     * Precondition: target directory is valid.
     * @return true if target has enough space for copy operation, false otherwise.
     */
    boolean isDiskSpaceSufficient(){
        if(!directoryHandler.isNullTargetDirectoryFlag() && directoryHandler.targetDirectoryCopy().isDirectory()){
            try{
                String targetPathPrefix = FilenameUtils.getPrefix(directoryHandler.getTargetDirectoryPath()); // The 'drive letter' of the device containing targetDirectory.
                File disk = new File(targetPathPrefix); // disk.getTotalSpace() = total disk space in bytes. disk.getFreeSpace() = free space available to all users in bytes.
                long usableSpace = disk.getUsableSpace(); // Free disk space available to the current user in bytes.
                long sourceFilesSize = directorySize(directoryHandler.sourceFilesCopy());

                if(usableSpace > sourceFilesSize){
                    setNotEnoughDiskSpaceFlag(false);
                    return true;
                }
                else {
                    setNotEnoughDiskSpaceFlag(true);
                    return false;
                }
            }
            catch(IllegalArgumentException e){
                setNotEnoughDiskSpaceFlag(true);
                return false;
            }
        }
        setNotEnoughDiskSpaceFlag(true);
        return false;
    }

    /**
     * Checks if sourceFiles contains any files.
     * @return true if directoryHandler.sourceFiles contains no files, false otherwise.
     */
    boolean isSourceFilesEmpty(){
        if(directoryHandler.isNullSourceDirectoryFlag() || directoryHandler.sourceFilesCopy().isEmpty()){
            setNoSourceFilesFlag(true);
            return true;
        }
        else {
            setNoSourceFilesFlag(false);
            return false;
        }
    }

    /**
     * Checks if sourceFiles and targetFiles contain any files with the same name and extension.
     * Preconditions:
     *      1) source and target directories are valid.
     *      2) source and target files are valid sets.
     * @return true if a file in directoryHandler.sourceFiles shares the same name and extension with a file in directoryHandler.targetFiles, false otherwise.
     */
    boolean sourceTargetDuplicates(){
        for(int i = 0; i < directoryHandler.sourceFilesSize(); i++){
            for(int j = 0; j < directoryHandler.targetFilesSize(); j++){
                if (Objects.equals(directoryHandler.sourceFilesCopy().get(i).getName(), directoryHandler.targetFilesCopy().get(j).getName())) {
                    setSourceTargetDuplicatesFlag(true);
                    return true;
                }
            }
        }
        setSourceTargetDuplicatesFlag(false);
        return false;
    }

    /**
     * Checks the following are true: current source and target directories and files are valid, source and target are not the same directory,
     * target directory has sufficient usable space, source directory files contains at least one usable file,
     * and no duplicate files between the source and target directories.
     * @return true if all the above are true, false otherwise.
     */
    public boolean copyActionPermitted(){
        if(sourceAndTargetDirectoriesAreValid() && sourceAndTargetFilesAreValid() && !directoryHandler.sourceEqualsTargetTest() && isDiskSpaceSufficient() && !isSourceFilesEmpty() && !sourceTargetDuplicates()){
            return true;
        }
        return false;
    }

    /**
     * Checks the following are true: current source directory and files are valid, source and target are not the same directory,
     * source directory files contains at least one usable file.
     * @return true if all the above are true, false otherwise.
     */
    public boolean deleteFromSourceActionPermitted(){
        if(sourceDirectoryIsValid() && sourceFilesAreValid() && !directoryHandler.sourceEqualsTargetTest() && !isSourceFilesEmpty()){
            return true;
        }
        return false;
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Action functions ---------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Copies the files in sourceFiles to targetFiles.
     * Precondition: all requirements needed for copyActionPermitted() to evaluate to true are met.
     * Documentation for FileUtils.copyToDirectory: https://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/FileUtils.html#copyToDirectory-java.io.File-java.io.File-
     * @return true if the operation was successful, false otherwise.
     */
    public boolean copyFiles() { // make it so this function (and all others) return boolean and sets a variable to hold stuff related to error messages and error message displaying.
        int i = 0;
        ArrayList<File> sourceFilesCopy = directoryHandler.sourceFilesCopy();
        File targetDirectoryCopy = directoryHandler.targetDirectoryCopy();
        try {
            for (i = 0; i < directoryHandler.sourceFilesSize(); i++) {
                FileUtils.copyToDirectory(sourceFilesCopy.get(i), targetDirectoryCopy); // Copy files from source to target.
                copyRecord.add("Copied file '" + sourceFilesCopy.get(i).getName() + "' to directory " + directoryHandler.getTargetDirectoryName());
            }
            directoryHandler.refreshTargetDirectory();
            return true;
        } catch (NullPointerException e) { // If any of the given Files are null.
            setCopyFilesNullPointerExceptionFlag(true);
            setFileThatCausedException(directoryHandler.getSourceFileNames().get(i));
            directoryHandler.refreshTargetDirectory();
            return false;
        } catch (IllegalArgumentException e) { // If the source or destination is invalid.
            // Known causes:
            // - Attempting to overwrite a read-only file in target.
            setCopyFilesIllegalArgumentExceptionFlag(true);
            setFileThatCausedException(directoryHandler.getSourceFileNames().get(i));
            directoryHandler.refreshTargetDirectory();
            return false;
        } catch (FileNotFoundException e) { // If the source does not exist.
            setCopyFilesFileNotFoundExceptionFlag(true);
            setFileThatCausedException(directoryHandler.getSourceFileNames().get(i));
            directoryHandler.refreshTargetDirectory();
            return false;
        } catch (IOException e) { // If an error occurs or setting the last-modified time didn't succeed.
            // Known causes:
            // - Target contains a file with the same name and extension as one of the files being copied from source and that file has had it's content modified and is open.
            // - Target does not have enough space for the requested file.
            // - Access to a source file is restricted. Access to target folder is restricted.
                // - User does not have 'write' permission for target directory.
            setCopyFilesIOExceptionFlag(true);
            setFileThatCausedException(directoryHandler.getSourceFileNames().get(i));
            directoryHandler.refreshTargetDirectory();
            return false;
        }
    }

    /**
     * Deletes all files contained in directoryHandler.sourceFiles.
     * Precondition: all requirements needed for deleteFromSourceActionPermitted() to evaluate to true are met.
     * Postcondition: directoryHandler.sourceDirectory is valid.
     * @return true if the operation was successful, false if failed or partially successful.
     */
    public boolean deleteFromSource(){
        boolean operationFailed = false;
        ArrayList<File> sourceFilesCopy = directoryHandler.sourceFilesCopy();
        for (int i = 0; i < directoryHandler.sourceFilesSize(); i++) {
            boolean deleteSuccessful = sourceFilesCopy.get(i).delete();
            if(deleteSuccessful){
                deleteFromSourceRecord.push("Deleted file '" + sourceFilesCopy.get(i).getName() + "' from source directory.");
            }
            else{
                deleteFromSourceRecord.push("Could not delete file '" + sourceFilesCopy.get(i).getName() + "' from source directory.");
                operationFailed = true;
            }
        }

        if(operationFailed){
            setDeleteFromSourceDidNotCompleteFlag(true);
            directoryHandler.refreshSourceDirectory();
            return false;
        }
        setDeleteFromSourceDidNotCompleteFlag(false);
        directoryHandler.refreshSourceDirectory();
        return true;
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Message functions --------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    /**
     * Creates a displayable error message to summarize all errors encountered after an execution of copyActionPermitted.
     * @return an error message if any errors were encountered, null otherwise.
     */
    public String generateCopyActionPermittedErrorMessage(){
        StringBuilder errorMessage = new StringBuilder();
        if(!directoryHandler.isSourceIsValidFlag()){
            errorMessage.append("invalid source directory, ");
        }
        else if(!directoryHandler.isTargetIsValidFlag()){
            errorMessage.append("invalid target directory, ");
        }
        else if(!directoryHandler.isSourceFilesIsValidSet()){
            errorMessage.append("invalid source files, ");
        }
        else if(!directoryHandler.isTargetFilesIsValidSet()){
            errorMessage.append("invalid target files, ");
        }
        else if(directoryHandler.isSourceEqualsTargetFlag()){
            errorMessage.append("source = target directory, ");
        }
        else if(isNotEnoughDiskSpaceFlag()){
            errorMessage.append("insufficient disk space, ");
        }
        else if(isNoSourceFilesFlag()){
            errorMessage.append("empty source directory, ");
        }
        else if(isSourceTargetDuplicatesFlag()){
            errorMessage.append("duplicate files between source and target directory, ");
        }

        if(errorMessage.isEmpty()){
            return null;
        }
        else{
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length()); // This is supposed to remove the last ', ' from the error message.
            return BackEndErrors.ERROR_1_0.toString() + " Errors: " + String.valueOf(errorMessage) + ".";
        }
    }

    /**
     * Creates a displayable error message to summarize all errors encountered after an execution of deleteFromSourceActionPermitted.
     * @return an error message if any errors were encountered, null otherwise.
     */
    public String generateDeleteFromSourceActionPermittedErrorMessage(){
        StringBuilder errorMessage = new StringBuilder();
        if(!directoryHandler.isSourceIsValidFlag()){
            errorMessage.append("invalid source directory, ");
        }
        else if(!directoryHandler.isSourceFilesIsValidSet()){
            errorMessage.append("invalid source files, ");
        }
        else if(directoryHandler.isSourceEqualsTargetFlag()){
            errorMessage.append("source = target directory, ");
        }
        else if(isNoSourceFilesFlag()){
            errorMessage.append("empty source directory, ");
        }

        if(errorMessage.isEmpty()){
            return null;
        }
        else{
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length()); // This is supposed to remove the last ', ' from the error message.
            return BackEndErrors.ERROR_3_0.toString() + " Errors: " + String.valueOf(errorMessage) + ".";
        }
    }

    /**
     * Creates a displayable error message to summarize all errors encountered after an execution of copyFiles.
     * @return a pair whose key is an error message and value is an error code message if any errors were encountered, null otherwise.
     */
    public Pair<String, String> generateCopyFileErrorMessage(){
        if(isCopyFilesNullPointerExceptionFlag()){
            return new Pair<>(BackEndErrors.ERROR_1_1.toString() + " Error caused by file: " + getFileThatCausedException() + ".", "Error (code 1.1)");
        }
        if(isCopyFilesIllegalArgumentExceptionFlag()){
            return new Pair<>(BackEndErrors.ERROR_1_2.toString() + " Error caused by file: " + getFileThatCausedException() + ".", "Error (code 1.2)");
        }
        if(isCopyFilesFileNotFoundExceptionFlag()){
            return new Pair<>(BackEndErrors.ERROR_1_3.toString() + " Error caused by file: " + getFileThatCausedException() + ".", "Error (code 1.3)");
        }
        if(isCopyFilesIOExceptionFlag()){
            return new Pair<>(BackEndErrors.ERROR_1_4.toString() + " Error caused by file: " + getFileThatCausedException() + ".", "Error (code 1.4)");
        }
        return null;
    }

    /**
     * Creates a displayable error message to summarize all errors encountered after an execution of deleteFromSource.
     * @return a pair whose key is an error message and value is an error code message if any errors were encountered, null otherwise.
     */
    public Pair<String, String> generateDeleteFromSourceErrorMessage(){
        if(deleteFromSourceDidNotCompleteFlag){
            return new Pair<>(BackEndErrors.ERROR_3_1.toString(), "Error (code 3.1)");
        }
        return null;
    }

    /**
     * Creates a displayable confirmation message that summarizes the current copy operation.
     * @return a message if the current copy request is permitted, null otherwise.
     */
    public String generateCopyConfirmationMessage(){
        if(copyActionPermitted()) {
            if(directoryHandler.sourceFilesSize() == 1){
                return "Copy " + directoryHandler.sourceFilesSize() + " file (" + FileUtils.byteCountToDisplaySize(directorySize(directoryHandler.sourceFilesCopy())) + ") to " + directoryHandler.getTargetDirectoryName() + "?";
            }
            return "Copy " + directoryHandler.sourceFilesSize() + " files (" + FileUtils.byteCountToDisplaySize(directorySize(directoryHandler.sourceFilesCopy())) + ") to " + directoryHandler.getTargetDirectoryName() + "?";
        }
        else{
            return null;
        }
    }

    /**
     * Creates a displayable confirmation message that summarizes the current delete operation.
     * @return a message if the current delete request is permitted, null otherwise.
     */
    public String generateDeleteConfirmationMessage(){
        if(deleteFromSourceActionPermitted()) {
            if(directoryHandler.sourceFilesSize() == 1){
                return "Delete " + directoryHandler.sourceFilesSize() + " file (" + FileUtils.byteCountToDisplaySize(directorySize(directoryHandler.sourceFilesCopy())) + ") from " + directoryHandler.getSourceDirectoryName() + "? This action cannot be undone.";
            }
            return "Delete " + directoryHandler.sourceFilesSize() + " files (" + FileUtils.byteCountToDisplaySize(directorySize(directoryHandler.sourceFilesCopy())) + ") from " + directoryHandler.getSourceDirectoryName() + "? This action cannot be undone.";
        }
        else{
            return null;
        }
    }

    // ------------------------------------------------------------------------------------------------------------
    // --------------------------------------------- Getters and setters ------------------------------------------
    // ------------------------------------------------------------------------------------------------------------

    // --------------------------------------------- Variables ----------------------------------------------------

    public Stack<String> getCopyRecord() {
        return copyRecord;
    }

    private void setCopyRecord(Stack<String> copyRecord) {
        this.copyRecord = copyRecord;
    }

    public String getFileThatCausedException() {
        return fileThatCausedException;
    }

    private void setFileThatCausedException(String fileThatCausedException) {
        this.fileThatCausedException = fileThatCausedException;
    }

    // --------------------------------------------- Flags --------------------------------------------------------

    public boolean isNotEnoughDiskSpaceFlag() {
        return notEnoughDiskSpaceFlag;
    }

    private void setNotEnoughDiskSpaceFlag(boolean notEnoughDiskSpaceFlag) {
        this.notEnoughDiskSpaceFlag = notEnoughDiskSpaceFlag;
    }

    public boolean isNoSourceFilesFlag() {
        return noSourceFilesFlag;
    }

    private void setNoSourceFilesFlag(boolean noSourceFilesFlag) {
        this.noSourceFilesFlag = noSourceFilesFlag;
    }

    public boolean isSourceTargetDuplicatesFlag() {
        return sourceTargetDuplicatesFlag;
    }

    private void setSourceTargetDuplicatesFlag(boolean sourceTargetDuplicatesFlag) {
        this.sourceTargetDuplicatesFlag = sourceTargetDuplicatesFlag;
    }

    public boolean isCopyFilesNullPointerExceptionFlag() {
        return copyFilesNullPointerExceptionFlag;
    }

    private void setCopyFilesNullPointerExceptionFlag(boolean copyFilesNullPointerExceptionFlag) {
        this.copyFilesNullPointerExceptionFlag = copyFilesNullPointerExceptionFlag;
    }

    public boolean isCopyFilesIllegalArgumentExceptionFlag() {
        return copyFilesIllegalArgumentExceptionFlag;
    }

    private void setCopyFilesIllegalArgumentExceptionFlag(boolean copyFilesIllegalArgumentExceptionFlag) {
        this.copyFilesIllegalArgumentExceptionFlag = copyFilesIllegalArgumentExceptionFlag;
    }

    public boolean isCopyFilesFileNotFoundExceptionFlag() {
        return copyFilesFileNotFoundExceptionFlag;
    }

    private void setCopyFilesFileNotFoundExceptionFlag(boolean copyFilesFileNotFoundExceptionFlag) {
        this.copyFilesFileNotFoundExceptionFlag = copyFilesFileNotFoundExceptionFlag;
    }

    public boolean isCopyFilesIOExceptionFlag() {
        return copyFilesIOExceptionFlag;
    }

    private void setCopyFilesIOExceptionFlag(boolean copyFilesIOExceptionFlag) {
        this.copyFilesIOExceptionFlag = copyFilesIOExceptionFlag;
    }

    public boolean isDeleteFromSourceDidNotCompleteFlag() {
        return deleteFromSourceDidNotCompleteFlag;
    }

    private void setDeleteFromSourceDidNotCompleteFlag(boolean deleteFromSourceDidNotCompleteFlag) {
        this.deleteFromSourceDidNotCompleteFlag = deleteFromSourceDidNotCompleteFlag;
    }
}
