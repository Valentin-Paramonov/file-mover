# File-Mover
Starts a server on port 8080, polls a directory (/tmp by default) periodically (10 seconds by default) and moves files from it to another directory (/tmp by default). If the source and destination directories are the same, the files are left untouched. The list of moved files can be viewed at http://localhost:8080.

## How to build
```
$ cd file-mover
$ mvn package
```
## How to run
```
$ java -jar target/file-mover-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Changing source, destination directories and polling interval
To change these parameters, edit the web.xml inside src/main/webapp/WEB-INF

## Running on different port
To start the server on port other than 8080, set the PORT environment variable
```
$ PORT=4848 java -jar target/file-mover-1.0-SNAPSHOT-jar-with-dependencies.jar
```
