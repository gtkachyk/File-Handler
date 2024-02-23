# File Handler

A simple JavaFX program for performing niche file management operations.

## Action Descriptions

Copy to folder:
 - This action copies any files in the source directory that have not been filtered out to the target directory.
Delete from source:
 - Deletes all files in the source directory that have not been filtered out.

## Filter Descriptions

Include by name: 
 - Filters files whose name does not begin with the exact sequence of characters entered by the user.
Include by extension: 
 - Filters files whose extension does not match the extension entered by the user.
Exclude by name: 
 - Filters files whose name begins with the exact sequence of characters entered by the user.
Exclude by extension: 
 - Filters files whose extension matches the extension entered by the user.
Include by index: 
 - Filters files that are not included in the set of indices given by the user. 
     - How to enter a set of indices:
        - As a single integer: '9' would include only the file at index 9 for example.
        - As a range of integers: 9-12 would include only files at indices 9, 10, 11, and 12.
        - As a comma-separated integers and/or ranges: 3,5-9,13-15 would include only files at indices 3, 5, 6, 7, 8, 9, 13, 14, and 15.

## Basic Terminology

Action: Any process listed in the 'Select Action' dropdown menu.
Filter: Any process listed in the 'Select Filter' dropdown menu.
Source directory: The directory containing files that actions or filters will be applied to.
Target directory: The destination for files involved in copy actions.
Index: The number displayed next to a file.
Refresh: When a directory is refreshed, File Handler loads external changes made to the directory and re-applies all filters in order.

## Detailed Terminology

Valid source directory properties:
 - Not null.
 - Exists on or is accessible to the machine running the program.
 - Is a directory and nothing else (file).
 - Is readable (user must have read permission).
Valid target directory properties:
 - Not null.
 - Exists on or is accessible to the machine running the program.
 - Is a directory and nothing else (file).
 - Is readable (user must have read permission).
 - Is writable (user must have write permission).
Valid source directory file properties:
 - Not null.
 - Exists on or is accessible to the machine running the program.
 - Is a file and nothing else (directory).
 - Is not hidden.
 - Is readable.
 - Is stored in a valid source directory.
Valid target directory file properties:
 - Not null.
 - Exists on or is accessible to the machine running the program.
 - Is a file and nothing else (directory).
 - Is not hidden.
 - Is readable.
 - Is stored in a valid target directory.
Valid source file set properties:
 - Not null.
 - Contains no elements or all elements are valid source files.
Valid target file set properties:
 - Not null.
 - Contains no elements or all elements are valid target files.

## Program Behaviors and Limitations

 - External changes made to source or target directories will not be reflected in the File Handler GUI unless the
 - 'Refresh' option is selected from the 'File' menu.
 - After any copy or delete action, the source directory will be automatically refreshed. This can produce strange (but harmless) results
 - because it involves redoing all past filter requests, even ones that are now invalid. Simply press 'undo' until all filters are 
 - removed or error 2.1 is encountered to see the true state of the source directory.
 - Large copy operations may cause the program window to become unresponsive until it is finished. There is
 - currently no mechanism for communicating the progress of copy operations to the user.
 - Almost all nontrivial interactions with the program are displayed as a descriptive entry in the action log.
 - If new entries are no longer being added after important interactions, quit the program and submit a bug report.
 - Files may not be listed in the File Handler GUI in the same order they are listed in the operating system's file explorer.
 - The file explorer window that appears when choosing a source or target directory may change sizes each time it opens.
 - Subdirectories of the source and target directories are intentionally not handled by the program.
 - Hidden files contained in the source and target directories are intentionally not handled by the program.
 - Filters can only be applied to source files.

## Error Codes

1.0: A copy request was blocked because one of the following assertions was true:
 - The source directory was invalid.
 - The target directory was invalid.
 - The set of source files was invalid.
 - The set of target files was invalid.
 - The source directory and target directory were the same.
 - There was insufficient space available in the target directory.
 - The set of source files was empty.
 - There was some file in the set of source files with the same name as a file in the set of target files.
1.1: A NullPointerException was thrown during a copying operation. Pre-copy checks make this unlikely to be encountered.
1.2: An IllegalArgumentException was thrown during a copying operation. Pre-copy checks make this unlikely to be encountered.
1.3: A FileNotFoundException was thrown during a copying operation. Pre-copy checks make this unlikely to be encountered.
1.4: An IOException was thrown during a copying operation. May be thrown when working with folders/files with restricted permissions.
2.0: A filter operation was requested without any usable text in the text field above the 'Apply filter' button.
2.1: An 'undo filter' operation was requested when no filters had been applied. Commonly countered after applications of the 'Include by index' filter followed by 'delete from source' action.
3.0: A delete from source request was blocked because one of the following assertions was true:
 - The source directory was invalid.
 - The set of source files was invalid.
 - There was some file in the set of source files with the same name as a file in the set of target files.
 - The set of source files was empty.
3.1: A delete from source operation did not fully finish. Some or none of the requested file may have been deleted. When this error is encountered, read the action log to see exactly which files were deleted and which were not.

## Warning Codes

1.0: After refreshing the directories, the program detected that the source directory is invalid. This occurs when:
 - The source directory was invalid before refreshing.
 - The source directory was valid, but external changes loaded by refreshing caused it to become invalid.
1.1: After refreshing the directories, the program detected that the target directory is invalid. This occurs when:
 - The target directory was invalid before refreshing.
 - The target directory was valid, but external changes loaded by refreshing caused it to become invalid.
1.2: After refreshing the directories, the program detected that both directories are invalid. This occurs when warning 1.0 and 1.1 occur simultaneously.
2.0: The current source directory is the same as the current target directory. Currently, this prevents copying actions.
