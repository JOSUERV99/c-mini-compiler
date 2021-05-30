:: generate symbol definition for lexical scanner ::
:: java -jar "C:\Program Files\Java\jdk-14.0.2\lib\java-cup-11b.jar" -interface -parser Parser parser/CParser.cup 
cd "C:\Users\Josue\Documents\git\CLexerGenerator\cminicompiler\src\main\java\parser"
java -cp "C:\Program Files\Java\jdk-14.0.2\lib\java-cup-11b.jar" java_cup.Main -interface -parser CParser -package parser  < "C:\Users\Josue\Documents\git\CLexerGenerator\cminicompiler\src\main\java\parser\CParser.cup"
cd ..