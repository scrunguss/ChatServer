set +e
clear
javac $(find server/* |grep .java)
java server/ChatServer