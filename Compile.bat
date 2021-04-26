SET "directory=clexergenerator/src/main/java/" 

javac --source-path .;%directory%;%directory%model/;%directory%validation/ -d bin %directory%model/*.java
javac --source-path .;%directory%;%directory%model/*.java -d bin %directory%validation/*.java

javac --source-path .;%directory%;%directory%model/*.java;%directory%validation/*.java -d bin %directory%lexer/*.java

javac --source-path .;%directory%;%directory%model/*.java;%directory%validation/*.java;%directory%lexer/*.java -d bin %directory%handler/*.java

javac --source-path .;%directory%;%directory%model/*.java;%directory%validation/*.java;%directory%lexer/*.java;%directory%handler/*.java -d bin %directory%Test.java


