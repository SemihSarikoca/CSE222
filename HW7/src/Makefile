JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		AVLTree.java \
		Stock.java \
		Main.java \
		StockDataManager.java \
		GUIVisualization.java \
		Generator.java \
		InputFileGenerator.java

all : Main.class InputFileGenerator.class

run :
	$(JR) -Xint Main

run-ifg:
	$(JR) InputFileGenerator arg1 arg2 arg3 arg4 arg5 arg6

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java

clean:
	$(RM) *.class

cleandoc:
	$(RM) -r ./javadoc