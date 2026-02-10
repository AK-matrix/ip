# AK
## The Game of Thrones Themed Chatbot

AK is a powerful **Graphical User Interface (GUI)** desktop application for managing tasks, designed with a classy **Game of Thrones (Ice and Fire)** aesthetic. While it is optimized for fast input, it provides a stunning visual experience reminiscent of Westeros.

## 📖 Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
    * [Adding Tasks](#adding-tasks)
    * [Managing Tasks](#managing-tasks)
    * [Managing Contacts](#managing-contacts)
    * [Finding Tasks](#finding-tasks)
* [Command Summary](#command-summary)

## ⚡ Quick Start

1.  Ensure you have Java `17` or above installed in your Computer.
2.  Download the latest `ak.jar` from [here](https://github.com/AK-matrix/ip/releases/download/Level-10/ak.jar).
3.  Double-click the file to start the application.
4.  The GUI should appear in a few seconds, displaying the "Game of Thrones" theme.
5.  Type the command in the command box and press Enter to execute it.
6.  Refer to the [Features](#features) section below for details of each command.

## ⚔️ Features

> ℹ️ **Notes about the command format:**
> * Words in `<angle_brackets>` are the parameters to be supplied by the user e.g. in `todo <description>`, `<description>` is a parameter which can be used as `todo read book`.
> * Items in `[square_brackets]` are optional.
> * Parameters must be distinct and specific.

### Adding Tasks

#### 1. Add a Todo: `todo`
Adds a standard todo task to your list.

* **Format:** `todo <description>`
* **Example:** `todo Watch House of the Dragon`

#### 2. Add a Deadline: `deadline`
Adds a task that needs to be done before a specific date/time.

* **Format:** `deadline <description> /by <yyyy-MM-dd HHmm>`
* **Example:** `deadline Pay Iron Bank debts /by 2024-12-01 2359`

#### 3. Add an Event: `event`
Adds a task that takes place during a specific time period.

* **Format:** `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
* **Example:** `event Small Council Meeting /from 2024-12-02 1400 /to 2024-12-02 1600`

### Managing Tasks

#### 4. List all tasks: `list`
Displays a scrollable list of all pending and completed tasks.

* **Format:** `list`

#### 5. Mark task as done: `mark`
Marks a task as completed. The task background may change to indicate completion.

* **Format:** `mark <index>`
* **Example:** `mark 1`

#### 6. Mark task as not done: `unmark`
Restores a completed task to the pending state.

* **Format:** `unmark <index>`
* **Example:** `unmark 1`

#### 7. Delete a task: `delete`
Permanently removes a task from the list.

* **Format:** `delete <index>`
* **Example:** `delete 3`

### Managing Contacts

#### 8. Add a Contact: `add contact`
Adds a new contact to your address book.

* **Format:** `add contact n/<name> [p/<phone>] [e/<email>] [i/<info>]`
* **Example:** `add contact n/Jon Snow p/12345678 e/jon@winterfell.com i/Knows nothing`

#### 9. List Contacts: `contact list`
Displays all stored contacts.

* **Format:** `contact list`

#### 10. Edit a Contact: `edit contact`
Edits an existing contact at the specified index. Only the specified fields will be updated.

* **Format:** `edit contact <index> [n/<name>] [p/<phone>] [e/<email>] [i/<info>]`
* **Example:** `edit contact 1 p/87654321`

#### 11. Delete a Contact: `delete contact`
Removes a contact from the list.

* **Format:** `delete contact <index>`
* **Example:** `delete contact 2`

### Finding Tasks

#### 8. Find tasks: `find`
Locates tasks containing a specific keyword (case-sensitive).

* **Format:** `find <keyword>`
* **Example:** `find Winter`

### Exiting

#### 9. Exit: `bye`
Closes the application window.

* **Format:** `bye`

---

## 📜 Command Summary

| Action | Format, Examples |
| :--- | :--- |
| **Add Todo** | `todo <description>` <br> e.g., `todo Join the Night's Watch` |
| **Add Deadline** | `deadline <description> /by <date>` <br> e.g., `deadline Defend the Wall /by 2024-12-31 2359` |
| **Add Event** | `event <description> /from <date> /to <date>` <br> e.g., `event Tourney /from 2024-01-01 1000 /to 2024-01-01 1800` |
| **List** | `list` |
| **Mark** | `mark <index>` <br> e.g., `mark 1` |
| **Unmark** | `unmark <index>` <br> e.g., `unmark 1` |
| **Delete** | `delete <index>` <br> e.g., `delete 2` |
| **Find** | `find <keyword>` <br> e.g., `find Dragon` |
| **Add Contact** | `add contact n/<name> ...` <br> e.g., `add contact n/Tyrion` |
| **List Contacts** | `contact list` |
| **Delete Contact** | `delete contact <index>` <br> e.g., `delete contact 1` |
| **Edit Contact** | `edit contact <index> ...` <br> e.g., `edit contact 1 n/Jaime` |
| **Bye** | `bye` |

---

*Valar Dohaeris.*
