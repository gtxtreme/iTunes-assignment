currentVersionCode=$(awk '/versionCode/ {print $2}' app.gradle)

sed -i 's/versionCode [0-9a-zA-Z -_]*/versionCode '$currentVersionCode'/' app.gradle # Making changes to files permanent using sed

# Adding those permanent changes back to version control
git add app/app.gradle
git commit -m "Bumped versionCode by 1"
#!/bin/sh
set -e

# Commit and tag this version.
version=`grep 'version: ' pubspec.yaml | sed 's/version: //'`

git commit -m "Bump version to $version" pubspec.yaml --no-verify

CURRENT_BRANCH=$(git branch --show-current)
git push origin $CURRENT_BRANCH
