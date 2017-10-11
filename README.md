# File-Mover
Polls a directory and moves files from it to another directory. The list of moved files can be viewed at http://localhost:8080

## How to build
```
$ cd file-mover
$ mvn package
```
## How to run
```
$ java -jar target/file-mover-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Running on different port
To start the server on port other than 8080, set the PORT environment variable
```
$ PORT=4848 java -jar target/file-mover-1.0-SNAPSHOT-jar-with-dependencies.jar
```
