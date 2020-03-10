# Database

## Setting up eclipse
* Make a new java projekt to /databaseprosjekt in eclipse
* Go to Properties -> Build Path -> Libraries -> Add external JARs -> add mysql-connector-java-8.0.19.jar
* Make sure that your database schema in MySQL Workbench is called "filmprosjekt" and that you have created tables and inserted data from the sql scripts in this repo.
* Also make sure the username and password in DBConn.java corresponds to your username and password of your running instance of MySQL.

## Running the executable jar we have compiled
Write the following command in the console:
`java -jar application.jar`
