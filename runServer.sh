set +e
clear
javac $(find server/* |grep .java)
java ChatServer