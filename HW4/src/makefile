JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		FileSystemElement.java \
		Directory.java \
		Main.java \
		File.java \
		FileSystem.java


all : Main.class

run :
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java

clean:
	$(RM) **/**/*.class **/*.class *.class

cleandoc:
	$(RM) -r ./javadoc


