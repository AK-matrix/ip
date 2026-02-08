#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi
if [ -e "./EXPECTED-UNIX.txt" ]
then
    rm EXPECTED-UNIX.txt
fi
if [ -d "./data" ]
then
    rm -rf ./data
fi

# build the code using Gradle
if ! ../gradlew shadowJar -p ..
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp ../build/libs/ak.jar ak.AK < input.txt > ACTUAL.TXT 2> ERROR.TXT

# convert to UNIX format
cp EXPECTED.txt EXPECTED-UNIX.txt
dos2unix ACTUAL.TXT EXPECTED-UNIX.txt

# compare the output to the expected output
diff -w ACTUAL.TXT EXPECTED-UNIX.txt
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
