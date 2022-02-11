echo "Current working directory $pwd"
currentVersionCode=$(awk '/versionCode/ {print $2}' app.gradle)
currentVersionName=$(awk '/versionName/ {print $2}' app.gradle | sed 's/\"//g')
status=$?

if [ "$status" = 0 ]; then
  echo "Current Version Code : $currentVersionCode"
else
  echo "Failed to get the current version code. Exiting..."
  exit 1
fi

echo "Updating CurrentVersionCode by 1"

let "currentVersionCode=currentVersionCode+1"

echo "New Version Code:$currentVersionCode"
echo "Version Name: $currentVersionName"
new_tag="$currentVersionName($currentVersionCode)"
echo "New Tag: $new_tag"
echo "NEW_TAG=$new_tag" >>$GITHUB_ENV
