set +e
clear
javac $(find client/* |grep .java)
java client/ChatClient $1 $2 $3 $4