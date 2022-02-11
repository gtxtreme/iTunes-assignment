CURRENT_BRANCH=$(git branch --show-current)
echo "$CURRENT_BRANCH"

FLAVOUR_NAME="qa"
GRADLE_PATH=$GITHUB_WORKSPACE/gradlew

if [ "master" == "$CURRENT_BRANCH" ]; then
  echo "Current Branch is $CURRENT_BRANCH , proceeding with release build"
  bash $GRADLE_PATH assembleRelease
else
  echo "Current Branch is Not master, Proceeding with flavour build"
  bash $GRADLE_PATH `echo "assemble${FLAVOUR_NAME}Release"`
fi

echo "RELEASE_OUTPUT_FILE=app/build/outputs/apk/$FLAVOUR_NAME/release/">>$GITHUB_ENV
