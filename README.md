# 📝 To-Do List App

A simple and elegant Android To-Do List application built using Java and XML. This app helps users manage daily tasks with ease by allowing them to add, update, delete, and check off completed tasks.

## ✅ Features

- ➕ Add new tasks
- 📝 Edit existing tasks
- ❌ Delete tasks
- ✅ Mark tasks as completed
- 📋 Task list persists across sessions using SQLite
- 💡 Clean and user-friendly UI

## 🛠️ Built With

- **Language:** Java
- **UI:** XML (ConstraintLayout, RecyclerView)
- **Database:** SQLite (via `SQLiteOpenHelper`)
- **Architecture:** MVC-like structure with helper classes
- **Components:**
  - RecyclerView
  - AlertDialogs
  - SQLite for local data persistence

## 📷 Screenshots

| Task List | Add Task Dialog |
|-----------|-----------------|
| ![Task List](screenshots/task_list.png) | ![Add Task](screenshots/add_task.png) |

> _Make sure to create a `/screenshots` folder in your repo and place the actual images there._

## 🚀 Getting Started

### Prerequisites

- Android Studio (Giraffe or newer)
- Minimum SDK: 21 (Lollipop)
- Java 8 or higher

### Installation

1. Clone the repository:

```bash
git clone https://github.com/renu-123-pixel/ToDoList.git
Open in Android Studio and sync Gradle.

Run the app on an emulator or Android device.

📁 Project Structure
pgsql
Copy
Edit
ToDoList/
├── app/
│   ├── java/com/example/todolist/
│   │   ├── MainActivity.java
│   │   ├── TaskAdapter.java
│   │   ├── TaskModel.java
│   │   └── DatabaseHelper.java
│   └── res/
│       ├── layout/
│       │   ├── activity_main.xml
│       │   └── task_item.xml
│       └── values/
├── AndroidManifest.xml
└── build.gradle
🔄 Future Improvements
 Add task priority levels

 Add due dates and reminders

 Dark mode support

 Task categories or labels

🙋‍♀️ Author
Renu – GitHub Profile

🤝 Contributing
Contributions are welcome! Feel free to fork the project and submit a pull request.

📄 License
This project is licensed under the MIT License. See the LICENSE file for details.

