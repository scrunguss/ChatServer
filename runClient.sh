set +e
clear
javac $(find client/* |grep .java)
java client/ChatClient  -cca 192.168.1.1