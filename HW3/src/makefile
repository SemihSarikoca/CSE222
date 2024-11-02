JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		mypackage/Inventory.java \
		mypackage/util/Device.java \
		Main.java \
		mypackage/util/SmartPhone.java \
		mypackage/util/Laptop.java \
		mypackage/util/ElectronicDevice.java \
		mypackage/util/Headphones.java \
		mypackage/util/SmartWatch.java \
		mypackage/util/TV.java \
		Menu.java


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


