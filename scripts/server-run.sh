#!/bin/bash

./scripts/build.sh

# . .env

cd server
    mvn exec:java -Dexec.args="127.0.0.1 24000"
cd ..
