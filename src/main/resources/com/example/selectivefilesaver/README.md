Version 1.0
Developed by Grant Tkachyk

Program behaviours and limitations:
    - External changes made to source or target directories will not be reflected in the File Handler GUI unless the
      'Refresh' option is selected from the 'File' menu.
    - Large copy operations may cause the program window to become unresponsive until it is finished. There is
      currently no mechanism for communicating the progress of copy operations to the user.
    - Almost all nontrivial interactions with the program are displayed as a descriptive entry in the action log.
      If new entries are no longer being added after important interactions, quit the program and submit a bug report.
    - Files may not be listed in the File Handler GUI in the same order they are listed in the operating system's file explorer.
    - The file explorer window that appears when choosing a source or target directory may change sizes each time it opens.
    - Subdirectories of the source and target directories are intentionally not handled by the program.
    - Hidden files contained in the source and target directories are intentionally not handled by the program.

Terminology:
    Action: Any process listed in the 'Select Action' dropdown menu.
    Filter: Any process listed in the 'Select Filter' dropdown menu.
    Source directory: The directory containing files that actions or filters will be applied to.
    Target directory: The destination for files involved in copy actions.
    Valid source directory properties:
        1) Not null.
        2) Exists on or is accessible to the machine running the program.
        3) Is a directory and nothing else (file).
        4) Is readable (user must have read permission).
    Valid target directory properties:
        1) Not null.
        2) Exists on or is accessible to the machine running the program.
        3) Is a directory and nothing else (file).
        4) Is readable (user must have read permission).
        5) Is writable (user must have write permission).
    Valid source directory file properties:
        1) Not null.
        2) Exists on or is accessible to the machine running the program.
        3) Is a file and nothing else (directory).
        4) Is not hidden.
        5) Is readable.
        6) Is stored in a valid source directory.
    Valid target directory file properties:
        1) Not null.
        2) Exists on or is accessible to the machine running the program.
        3) Is a file and nothing else (directory).
        4) Is not hidden.
        5) Is readable.
        6) Is stored in a valid target directory.
    Valid source file set properties:
        1) Not null.
        2) Contains no elements or all elements are valid source files.
    Valid target file set properties:
        1) Not null.
        2) Contains no elements or all elements are valid target files.

Action descriptions:
    Copy: This action copies any files in the source directory that have not been filtered out to the target directory.

Filter descriptions:
    Include by name: Filters files whose name does not begin with the exact sequence of characters entered by the user.
    Include by extension: Filters files whose extension does not match the extension entered by the user.
    Exclude by name: Filters files whose name begins with the exact sequence of characters entered by the user.
    Exclude by extension: Filters files whose extension matches the extension entered by the user.

Error codes:
    1.0: A copy request was blocked because one of the following assertion was true:
        1) The source directory was null.
        2) The target directory was null.
        3) The list of source files was null.
        4) The list of target files was null.
        5) The source files contained an invalid entry.
        6) The target files contained an invalid entry.
        7) The source directory and target directory were the same.
        8) There was insufficient space available in the target directory.
        9) The set of source files was empty.
        10) There was some file in the set of source files with the same name as a file in the set of target files.
    1.1: A NullPointerException was thrown during a copying operation. Pre-copy checks make this unlikely to be encountered.
    1.2: An IllegalArgumentException was thrown during a copying operation. Pre-copy checks make this unlikely to be encountered.
    1.3: A FileNotFoundException was thrown during a copying operation. Pre-copy checks make this unlikely to be encountered.
    1.4: An IOException was thrown during a copying operation. May be thrown when working with folders/files with restricted permissions.
    2.0: A filter operation was requested without any usable text in the text field above the 'Apply filter' button.
    2.1: An 'undo filter' operation was requested when no filters had been applied. Unlikely to be encountered.

Warning codes:
    1.0: After refreshing the directories, the program detected that the source directory is invalid. This occurs when:
         1) The source directory was invalid before refreshing.
         2) The source directory was valid, but external changes loaded by refreshing caused it to become invalid.
    1.1: After refreshing the directories, the program detected that the target directory is invalid. This occurs when:
         1) The target directory was invalid before refreshing.
         2) The target directory was valid, but external changes loaded by refreshing caused it to become invalid.
    1.2: After refreshing the directories, the program detected that both directories are invalid. This occurs when
         warning 1.0 and 1.1 occur simultaneously.
    2.0: The current source directory is the same as the current target directory. Currently, this prevents copying actions.

