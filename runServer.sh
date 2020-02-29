set +e
clear
javac $(find server/* |grep .java)
java server/ChatServer $1 $2