compile-all:
	javac *.java

rmic:
	rmic ExampleServer

registry:
	rmiregistry

