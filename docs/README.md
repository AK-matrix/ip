# AK Chatbot

AK is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Features

### List all tasks: `list`
Shows a list of all tasks in the chatbot.

Format: `list`

### Add a Todo: `todo`
Adds a new todo task to the list.

Format: `todo <description>`

Example:
* `todo read book`

### Add a Deadline: `deadline`
Adds a task with a specific deadline.

Format: `deadline <description> /by <yyyy-MM-dd HHmm>`

Example:
* `deadline submit assignment /by 2024-12-01 2359`

### Add an Event: `event`
Adds a task that happens during a specific time period.

Format: `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`

Example:
* `event team meeting /from 2024-12-02 1400 /to 2024-12-02 1600`

### Mark a task as done: `mark`
Marks a specific task as done.

Format: `mark <index>`

* Marks the task at `index` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
* `mark 1` marks the first task in the list as done.

### Mark a task as not done: `unmark`
Marks a specific task as not done (incomplete).

Format: `unmark <index>`

* Marks the task at `index` as not done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
* `unmark 1` marks the first task in the list as not done.

### Delete a task: `delete`
Deletes the specified task from the list.

Format: `delete <index>`

* Deletes the task at the specified `index`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
* `delete 2` deletes the 2nd task in the list.

### Find tasks: `find`
Finds tasks whose names contain the given keyword.

Format: `find <keyword>`

* The search is case-sensitive.
* Displays a list of tasks that contain the keyword.

Example:
* `find book` returns paths containing "book".

### Exit the program: `bye`
Exits the application.

Format: `bye`

## Saving the data
AK data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
