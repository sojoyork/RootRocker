echo "cloning..." `git clone https://github.com/sojoyork/RootRocker.git`
echo "going in..." `cd RootRocker`
echo "compiling and running..." `kotlinc rootrocker.kt -d rootrocker.jar && java -jar rootrocker.jar`
