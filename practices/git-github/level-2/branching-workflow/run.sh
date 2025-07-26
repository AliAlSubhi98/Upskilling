#!/bin/bash
echo "Compiling BranchingApp.java..."
javac BranchingApp.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running the app..."
    java BranchingApp
    
    echo ""
    echo "Cleaning up..."
    rm -f *.class
else
    echo "Compilation failed!"
fi 