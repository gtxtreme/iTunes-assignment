echo "Current working directory $pwd"
currentVersionCode=$(awk '/versionCode/ {print $2}' app.gradle) # Regex filter to grab 2 from "versionCode 2"
currentVersionName=$(awk '/versionName/ {print $2}' app.gradle | sed 's/\"//g') #Regex filter to grab 2.0 from "versionName 2.0"
status=$?

if [ "$status" = 0 ]; then
  echo "Current Version Code : $currentVersionCode"
else
  echo "Failed to get the current version code. Exiting..."
  exit 1
fi

echo "Updating CurrentVersionCode by 1"

let "currentVersionCode=currentVersionCode+1" # Bumping versionCode By one

echo "New Version Code:$currentVersionCode"
echo "Version Name: $currentVersionName"
new_tag="v$currentVersionName($currentVersionCode)" # New tag becomes v1.0(3)
echo "New Tag: $new_tag"
echo "NEW_TAG=$new_tag" >>$GITHUB_ENV # Setting this for use later

sed -i 's/versionCode [0-9a-zA-Z -_]*/versionCode '$currentVersionCode'/' app.gradle # Making changes to files permanent using sed