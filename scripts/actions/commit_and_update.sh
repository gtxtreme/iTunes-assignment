currentVersionCode=$(awk '/versionCode/ {print $2}' $GITHUB_WORKSPACE/app/app.gradle)

# Making changes to files permanent using sed
let "currentVersionCode=currentVersionCode+1"
sed -i 's/versionCode [0-9a-zA-Z -_]*/versionCode '$currentVersionCode'/' $GITHUB_WORKSPACE/app/app.gradle

# Adding those permanent changes back to version control
git add $GITHUB_WORKSPACE/app/app.gradle
git commit -m "Bumped versionCode by 1"
#!/bin/sh
set -e

# Commit and tag this version.
version=$(grep 'version: ' pubspec.yaml | sed 's/version: //')

git config --global user.email "william-the-bot@wednesday.is"
git config --global user.name "William The Bot"
git commit -m "Bump version to $version" pubspec.yaml --no-verify

CURRENT_BRANCH=$(git branch --show-current)
git push origin $CURRENT_BRANCH
