java -jar lib/antlr-4.7-complete.jar src/MicroC_language/parsing/MicroC.g4 
javac -sourcepath src -cp lib/*.jar src/MicroC_language/MicroC.java src/MicroC_language/parsing/*.java -d bin
cd bin
java MicroC ../tests/test1.microC -cp ../lib/antlr-4.7-complete.jar 
cd ..
