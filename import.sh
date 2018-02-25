if [ -z $1 ]; then
	echo Usage: ./import.sh PROJECT_NAME
	exit 1
fi
cp ../$1/dist/$1.jar ./libs/
cd libs
jar xf $1.jar
if [ -f tictactoe.jar ]; then
	jar xf tictactoe.jaf
fi
rm *.jar
rm -r META-INF
rm -r ../bin/*
cp -r * ../bin/
jar cvf lib.jar *

