:: EXECUTE JFLEX

:: delete the last lexer generated
del generated/CLexer.java /F /Q

:: run jflex with some options, the .flex input file and dir name where the lexer will be generated
java -jar "C:\\jflex-1.8.2\\lib\\jflex-full-1.8.2.jar" -d lexer --encoding utf-8 --time lexer/CLexerSpec.flex 