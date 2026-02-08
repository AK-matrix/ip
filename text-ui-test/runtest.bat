@ECHO OFF

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist ERROR.TXT del ERROR.TXT
if exist data rmdir /s /q data
if exist ..\data\ak.txt del ..\data\ak.txt

REM build the code using Gradle
call "%~dp0..\gradlew.bat" shadowJar -p "%~dp0.."
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp "%~dp0..\build\libs\ak.jar" ak.AK < input.txt > ACTUAL.TXT 2> ERROR.TXT

REM compare the output to the expected output
FC /W ACTUAL.TXT EXPECTED.txt
