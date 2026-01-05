Java File-Based App with SQLite Integration
Overview

This Java application was originally built as a file-based storage system and has now been upgraded to use SQLite for persistent data storage. It demonstrates basic CRUD (Create, Read, Update, Delete) operations while maintaining a lightweight and easy-to-use architecture.

Features

SQLite database integration for reliable data storage

CRUD operations (Add, View, Update, Delete records)

Easy-to-extend structure for adding new features

Lightweight and self-contained (no external database server needed)

Technologies Used

Java 8+

SQLite (via JDBC)

Maven/Gradle (optional, if you’re using a build tool)

Getting Started
Prerequisites

Java Development Kit (JDK) 8 or higher

SQLite (embedded in the app via JDBC, no separate installation required)

Optional: Maven or Gradle if you want to manage dependencies

Installation

Clone the repository:

git clone https://github.com/yourusername/your-app.git


Navigate to the project folder:

cd your-app


Compile the Java files:

javac -d bin src/dbConnect/DatabaseConnection.java src/Main.java


(Adjust paths according to your project structure)

Run the application:

java -cp bin Main

Database

The SQLite database file is stored as bank_accounts.db (or your preferred name).

The connection is handled via DatabaseConnection.java.

The app automatically creates the database file if it doesn’t exist.

Usage

Launch the app and follow the console prompts to perform operations on your data.

Data is now persisted between sessions thanks to SQLite integration.

Contributing

Contributions are welcome! You can:

Add new features

Improve database handling

Refactor code for better maintainability

License

This project is licensed under the MIT License. See the LICENSE
 file for details.
