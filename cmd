
CORBA

Terminal 1:

idlj -fall Prime.idl
javac Server.java
orbd -ORBInitialPort 1056&
java Server -ORBInitialPort 1056& 
Terminal 2:
javac Client.java
java Client -ORBInitialPort 1056 -ORBInitialHost localhost






========================================================

