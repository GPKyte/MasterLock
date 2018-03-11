#!/bin/bash
# This will remove any previous versions and build the current package
# Note the && and || following most lines, these stop the program early
# in case of failure
DIR="$(cd "$(dirname "$0")" ; pwd -P)"

cd ${DIR}/MasterLockBuild/ &&
gradle clean &&
gradle test &&
gradle jar &&
mv -v ${DIR}/MasterLockBuild/build/libs/*.jar ${DIR}/lock.jar &&
echo "Build Finished. See README for usage" &&
cd - >> /dev/null
