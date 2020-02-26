set +e
clear
javac $(find client/* |grep .java)
java client/ChatClient