echo "Current working directory $pwd"
currentVersionCode=$(awk '/versionCode/ {print $2}' app.gradle)

status=$?

if [ "$status" = 0 ] ; then
  echo "Current Version Code : $currentVersionCode"
else
  echo "Failed to get the current version code. Exiting..."
  exit 1
fi

echo "Updating CurrentVersionCode by 1"

let "currentVersionCode=currentVersionCode+1"

echo "New Version Code:$currentVersionCode"
echo "::setOutput name=NEW_TAG::$currentVersionCode"
