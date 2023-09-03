package BackEnd;

public enum BackEndErrors {
    WARNING_1_0("Refresh resulted in an invalid source directory."),
    WARNING_1_1("Refresh resulted in an invalid target directory."),
    WARNING_1_2("Refresh resulted in an invalid source and target directory."),
    WARNING_2_0("The selected source directory and target directory are the same."),

    // Copy action errors.
    ERROR_1_0("The requested copy operation was not permitted because one or more safety checks failed."),
    ERROR_1_1("The copy operation was halted because a NullPointerException was thrown."),
    ERROR_1_2("The copy operation was halted because an IllegalArgumentException was thrown."),
    ERROR_1_3("The copy operation was halted because a FileNotFoundException was thrown."),
    ERROR_1_4("The copy operation was halted because an IOException was thrown."),

    // Filter errors.
    ERROR_2_0("Invalid filter request."),
    ERROR_2_1("There are no filter applications to undo.");

    String message;

    BackEndErrors(String error) {
        this.message = error;
    }

    @Override
    public String toString() {
        return message;
    }

}
