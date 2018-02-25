rm -r bin/ dist/ 2>/dev/null
mkdir bin/ dist/
cd src
if [ -f ../libs/lib.jar ]; then
	javac $@ -cp ../libs/lib.jar -d ../bin/ org/tictactoe/*.java || exit 1
else
	javac $@ -d ../bin/ org/tictactoe/*.java || exit 1
fi
cd ../bin/
jar cvmf ../META-INF/MANIFEST.MF ../dist/tictactoe.jar * ../resources

