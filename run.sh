set +e
clear
javac $(find * |grep .java)
java ChatServer