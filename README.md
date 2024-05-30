Checksums

Design Patterns: Composite, Observer, Strategy Template method Builder Visitor Memento

Introduction
Create a solution which recursively scans a target directory and calculates the hashes of all of its files.

The results of each scan must be written to a text file. This text file consists of lines in the following format:

<hash> <relative-path>

Here <hash> is the calculated hash and <relative-path> is the path of the file relative to that of the target directory. For example, an imaginary output file may look like this:

836289C997463D0487C0CAA2A609991F file1.dat
189089AFB437AD7F6C4F38BBF51E5953 somedir/file2.txt
...

Your solution should support different checksum algorithms. The user should be able to specify an algorithm when running a scan. It should be easy to add or remove such algorithms to/from the solution at a later stage.

The solution should have two modes of operation, depending on how the user selects to treat symbolic links (in Unix-based systems) or shortcuts (.lnk files in Windows systems):
calculating the checksum of the actual symbolic link or shortcut (i.e., the contents of the file), or
traversing the target of the symbolic link or shortcut.

The solution should display a progress indicator when it is performing a scan.
