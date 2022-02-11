CURRENT_BRANCH=$(git branch --show-current)
echo "$CURRENT_BRANCH"

FLAVOUR_NAME="qa"

if [ "master" == "$CURRENT_BRANCH" ]; then
  echo "Current Branch is $CURRENT_BRANCH , proceeding with release build"
  ./gradlew assembleRelease
else
  echo "Current Branch is Not master, Proceeding with flavour build"
  ./gradlew `echo "assemble${FLAVOUR_NAME}Release"`
fi
