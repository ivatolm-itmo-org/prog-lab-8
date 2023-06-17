#!/bin/bash

./scripts/build.sh

cd client
    mvn exec:java -Dexec.args=""
cd ..
