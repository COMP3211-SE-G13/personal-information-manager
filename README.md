# Personal Information Manager (Command Line Interface)

## Description

A personal information manager that can write notes & add contacts & create some tasks and events that will notify the user.

## Running Platform & Programming Language & File Format

- MacOS & Unix & Linux
- Java (JDK Version 17)
- Data File - CSV

## Code Usage

- Building

  - IntelliJ IDEA

    - Just run the code
  - Command Line
- Testing

  - Download the JUnit 4.9 Jar Library
    - Link: http://www.java2s.com/Code/JarDownload/junit/junit-4.9.jar.zip
  - IntelliJ IDEA
    - Add dependency
      - Add JSON Jar to the project dependency (in Project Structure)

## Content

| view                      | Model                | controller    |
| ------------------------- | -------------------- | ------------- |
| Login Page                | SimpleDatabase Class | Auth Class    |
| Signup Page               |                      | Note Class    |
| Home Page                 |                      | Contact Class |
| Note Page                 |                      | Task Class    |
| Searching Page            |                      | Event Class   |
| Contact Page              |                      | Export Class  |
| Tasks Pages (To-do Pages) |                      |               |
| Events Pages              |                      |               |
| Load .pim file Page       |                      |               |
| Export .pim file Page     |                      |               |

## File Database Design


| userID | userName | password |
| ------ | -------- | -------- |
|        |          |          |

| contactID | userID | firstName | lastName | phoneNumber | address |
| --------- | ------ | --------- | -------- | ----------- | ------- |
|           |        |           |          |             |         |

| noteID | userID | noteTitle | noteContent | createTime | lastModifyTime |
| ------ | ------ | --------- | ----------- | ---------- | -------------- |
|        |        |           |             |            |                |

| taskID | userID | taskTitle | taskDescription | taskDDL | isTaskComplete |
| ------ | ------ | --------- | --------------- | ------- | -------------- |
|        |        |           |                 |         |                |

| eventID | userID | eventTitle | eventDescription | eventStartTime | eventAlarm |
| ------- | ------ | ---------- | ---------------- | -------------- | ---------- |
|         |        |            |                  |                |            |

## Contributors

- HU Yuhang
- MENG Guanlin
- JIANG Guanlin
- YE Feng
