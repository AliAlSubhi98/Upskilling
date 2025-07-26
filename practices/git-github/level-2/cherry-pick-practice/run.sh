#!/bin/bash

echo "Cherry-Pick Practice Project"
echo "==========================="

# Navigate to source directory
cd src/main/java

echo "Compiling Java files..."
javac -d ../../../target com/upskilling/cherrypick/*.java

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running FeatureApp..."
    echo ""
    
    # Run the application
    cd ../../../target
    java com.upskilling.cherrypick.FeatureApp
    
    echo ""
    echo "Cleaning up compiled files..."
    cd ..
    rm -rf target/
else
    echo "Compilation failed!"
    cd ../../..
fi 