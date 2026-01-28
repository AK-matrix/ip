@ECHO OFF

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist data rmdir /s /q data
if exist ..\data\ak.txt del ..\data\ak.txt

REM build the code using Gradle
call ..\gradlew shadowJar
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar ..\build\libs\ak.jar < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT