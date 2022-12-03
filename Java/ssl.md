## Step 1 -  Generate a private key in keystore file - password=develop

```
keytool -genkeypair -alias certificatekey -keyalg RSA -validity 7 -keystore keystore.jks
keytool -genkey -keyalg RSA -alias selfsigned -keystore mpbxkeystore.jks -storepass Na4ihatelno -validity 360 -keysize 2048
```

## Step 2 – Verifiy the newly created keystore file

```
keytool -list -v -keystore keystore.jks
```

## Step 3 – Export the certificate

```
keytool -export -alias certificatekey -keystore keystore.jks -rfc -file selfsignedcert.cer
keytool -export -alias selfsigned -keystore mpbxkeystore.jks -rfc -file mpbx_X509_certificate.cer
```

## Step 4 – Import the certificate in to the truststore file

```
keytool -import -alias certificatekey -file selfsignedcert.cer -keystore truststore.jks
keytool -import -alias selfsigned -file mpbx_X509_certificate.cer -keystore mpbxtruststore.jks
```

## Step 5 – Verify the newly created trust store file

```
keytool -list -v -keystore truststore.jks
```

## SSL Server Settings

```xml
    <plugin>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <configuration>
            <webApp>
                <resourceBases>
                    <resourceBase>src/main/webapp</resourceBase>
                    <resourceBase>${basedir}/target/generated-sources/webapp</resourceBase>
                    <resourceBase>${basedir}/../mpbx-lib/target/generated-sources/webapp</resourceBase>
                </resourceBases>
            </webApp>
            <systemProperties>
                <!--systemProperty>
                    <name>javax.net.debug</name>
                    <value>all</value>
                </systemProperty-->
                <systemProperty>
                    <name>javax.net.ssl.keyStore</name>
                    <value>keys/mpbxkeystore.jks</value>
                </systemProperty>
                <systemProperty>
                    <name>javax.net.ssl.keyStorePassword</name>
                    <value>Na4ihatelno</value>
                </systemProperty>
            </systemProperties>
        </configuration>
    </plugin>
```
## SSL Server Configuration

rmi.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans  xmlns           =   "http://www.springframework.org/schema/beans"
       
        xmlns:xsi       =   "http://www.w3.org/2001/XMLSchema-instance"
       
        xmlns:aop       =   "http://www.springframework.org/schema/aop"       
       
        xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-4.3.xsd

http://www.springframework.org/schema/aop 
http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
">
    

    <bean id="busService" class="ru.sifox.mpbx.bus.BusServiceImpl"/>

    <bean class="org.springframework.remoting.rmi.RmiServiceExporter">
        <property name="registryPort" value="1234"/>
        <property name="service" ref="busService"/>
        <property name="serviceInterface" value="ru.sifox.mpbx.bus.BusService"/>
        <property name="serviceName" value="BusService"/>
        
        <property name="clientSocketFactory">
            <bean class="javax.rmi.ssl.SslRMIClientSocketFactory"/>
        </property>
        <property name="serverSocketFactory">
            <bean class="javax.rmi.ssl.SslRMIServerSocketFactory"/>
        </property>        
    </bean>
</beans>
```

ru.sifox.mpbx.bus.BusService -- the remote interface acessable from client and server  
ru.sifox.mpbx.bus.BusServiceImpl -- the remote interface implementation on server

## SSL Client

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    
    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore", "../mpbx/keys/mpbxtruststore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "Na4ihatelno");
        System.setProperty("javax.net.ssl.trustStoreType", "jks");
        SpringApplication.run(Application.class, args);
    }
}
```

## SSL Client Configuration

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd 
    http://www.springframework.org/schema/context 
    http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="busService" class="org.springframework.remoting.rmi.RmiProxyFactoryBean">
        <property name="refreshStubOnConnectFailure" value="true"/>
        <property name="serviceInterface" value="ru.sifox.mpbx.bus.BusService"/>
        <property name="serviceUrl" value="rmi://localhost:1234/BusService"/>
    </bean>

</beans>
```

Import in configuration 

```java
@Configuration
@ImportResource({"classpath*:rmi-client.xml"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
```