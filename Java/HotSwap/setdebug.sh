export MAVEN_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787 -XXaltjvm=dcevm -javaagent:/home/$USER/bin/java/hotswap-agent-1.3.1-SNAPSHOT.jar"
