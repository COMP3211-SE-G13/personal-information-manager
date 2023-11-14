# Personal Information Manager (Command Line Interface)

## Description

A personal information manager that can write notes & add contacts & create some tasks and events that will notify the user.

## Running Platform & Programming Language & File Format

- MacOS & Linux & Unix
- Java (JDK Version 17)
- Data File - CSV

## Code Structure

```bash
├── PIM.iml
├── lib
│   ├── junit-jupiter-5.8.1.jar
│   ├── junit-jupiter-api-5.8.1.jar
│   ├── junit-jupiter-engine-5.8.1.jar
│   └── junit-jupiter-params-5.8.1.jar
├── src
│   ├── controller
│   │   ├── Auth.java
│   │   ├── Contact.java
│   │   ├── Event.java
│   │   ├── Export.java
│   │   ├── Import.java
│   │   ├── Input.java
│   │   ├── Note.java
│   │   ├── PIM.java
│   │   ├── Search.java
│   │   └── Todo.java
│   ├── input
│   ├── model
│   │   └── SimpleDatabase.java
│   ├── output
│   └── view
│       └── Pages.java
└── test
    └── ProjectTest.java
```

## Code Usage

- Terminal

  - PIM Compile

    ```bash
    ./PIM.sh -c
    ## OR
    ./PIM.sh --compile
    ```

  - PIM Help

    ```bash
    ./PIM.sh -h
    ## OR
    ./PIM.sh --help
    ## OR
    ./PIM.sh
    ```

    <img src="/Users/davidjiang/Library/CloudStorage/OneDrive-Personal/HKPolyU/3 Strengthening Year/Sem 1/COMP3211 - Software Engineering/Project/img/1.png" alt="1" style="zoom:60%;" />

- IntelliJ IDEA

  - PIM Compile & Running

    <img src="/Users/davidjiang/Library/CloudStorage/OneDrive-Personal/HKPolyU/3 Strengthening Year/Sem 1/COMP3211 - Software Engineering/Project/img/2.png" alt="2" style="zoom:55%;" />

    <img src="/Users/davidjiang/Library/CloudStorage/OneDrive-Personal/HKPolyU/3 Strengthening Year/Sem 1/COMP3211 - Software Engineering/Project/img/3.png" alt="3" style="zoom:50%;" />

  - Unit Test

    <img src="/Users/davidjiang/Library/CloudStorage/OneDrive-Personal/HKPolyU/3 Strengthening Year/Sem 1/COMP3211 - Software Engineering/Project/img/4.png" alt="4" style="zoom:48%;" />

    <img src="/Users/davidjiang/Library/CloudStorage/OneDrive-Personal/HKPolyU/3 Strengthening Year/Sem 1/COMP3211 - Software Engineering/Project/img/5.png" alt="5" style="zoom:41%;" />

## File Database Design


| userID | userName | password |
| ------ | -------- | -------- |
|        |          |          |

| contactID | userID | firstName | lastName | phoneNumber | address |
| --------- | ------ | --------- | -------- | ----------- | ------- |
|           |        |           |          |             |         |

| noteID | userID | noteTitle | noteContent | lastModifyTime |
| ------ | ------ | --------- | ----------- | -------------- |
|        |        |           |             |                |

| taskID | userID | taskTitle | taskDescription | taskDDL |
| ------ | ------ | --------- | --------------- | ------- |
|        |        |           |                 |         |

| eventID | userID | eventTitle | eventDescription | eventStartTime | eventAlarm |
| ------- | ------ | ---------- | ---------------- | -------------- | ---------- |
|         |        |            |                  |                |            |

## Contributors

- HU Yuhang
- MENG Guanlin
- JIANG Guanlin
- YE Feng
