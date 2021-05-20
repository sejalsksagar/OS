OS Lab #1 20/05/21
# Title: Exploration of Unix/Linux Commands (File, Directory and Process commands)

## 1. File Commands
| Sr no. |    Command     |                 Use and Syntax                               |
| ------ | -------------- | ------------------------------------------------------------ |
| 1.     | ls OR ls -a    | listing of all files and directories                         |
| 2.     | ls -l          | long listing of files and directories                        |
| 3.     | ls -al         | long listing of all files and directories                    |
| 4.     | cat            | to open a file (syntax: cat file_name)                       |
| 5.     | cat            | to create a file (syntax: cat > file_name) <br>EOF command `crtl+d` |
| 6.     | cat            | to concatenate two files (syntax: cat file1 >> file2)  <br>o/p in file2   |
| 7.     | cp             | to copy contents of one file in another (syntax: cp file1 file2) <br>creates file2 if not present    |
| 8.     | mv             | to rename/move file1 to file2 (syntax: mv file1 file2) <br>creates file2 if not present       |
| 9.     | rm             | to remove/delete a file (syntax: mv file1 file2)             |
| 10.    | touch          | to update timestamp of file to current time (syntax: touch file_name) <br>creates new file if not present |
| 11.    | chmod <br>(CHange MODe)| Used to change access modes of file (syntax: chmod permission-bits file_name) <br>for this command, you need to become super-user  |


## 2. Directory Commands
| Sr no. |    Command     |                 Use and Syntax                               |
| ------ | -------------- | ------------------------------------------------------------ |
| 1.     | cd             | change directory (syntax: cd directory_name)                 |
| 2.     | cd \           | change to root directory                                     |
| 3.     | cd ..          | come out of current directory or come out of parent directory     |
| 4.     | pwd            | present or parent working directory                          |
| 5.     | mkdir          | create a directory (syntax: mkdir directory_name)            |
| 6.     | rmdir          | to delete an empty directory (syntax: rmdir directory_name)  |
| 7.     | rm -r          | to delete a filled directory (syntax: rm -r directory_name) <br>recursively deletes files & subdirectories within that directory & lastly that directory is also deleted |
| 8.     | uname          | name of OS will get displayed (o/p: Linux)                   |


## 3. Process Commands
| Sr no. |    Command     |                 Use and Syntax                               |
| ------ | -------------- | ------------------------------------------------------------ |
| 1.     | ps <br>(Process State)| displays the currently-running processes & their: <br>`PID` Process ID<br>`TTY` Terminal Type<br>`TIME` total time the process has been running<br>`CMD` name of the command that launches the process|
| 2.     | ps -A OR ps -e | lists even those processes that are currently not running        |
| 3.     | top            | displays a list of processes that are running in real-time along with their memory & CPU usage &: <br>`PID` Unique Process ID given to each process <br>`PPID` Parent Process ID <br>`User` Username of the process owner <br>`STAT` represents process state:<br> - `D`: uninterruptible sleep <br> - `R`: running<br> - `S`: sleeping <br> - `T`: traced or stopped <br> - `Z`: zombie <br>`VSZ` size of virtual memory used by a process <br>`%VSZ` amount of physical memory used by a process <br>`CPU` CPU utilization by that process. <br>`%CPU` Percentage of CPU used by the process <br>`Command` Command used to activate the process      |
