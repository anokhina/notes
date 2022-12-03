## Spring configuration

Xml configuration with constructor arguments

```xml
<bean id="DiameterClient" class="com.rory.ptspsim.diameterclient.DiameterClient"       scope="singleton" init-method="start">
    <constructor-arg type="java.lang.String" index="0"><value>${pcca.host}</value></constructor-arg>
    <constructor-arg index="1">
       <list>
           <ref bean="aarHandler" />
           <ref bean="cerHandler" />     
           <ref bean="ppaHandler" />
           <ref bean="strHandler" />
           <ref bean="dwrHandler" />
       </list>       
    </constructor>
</bean>
```

```xml
<bean id="DiameterClient" class="com.rory.ptspsim.diameterclient.DiameterClient" init-method="start">
    <constructor-arg value="${pcca.host}" />
    <constructor-arg>
        <list>
            <ref bean="aarHandler" />
            ...
        </list>
    </constructor-arg>      
</bean>

<bean id="aarHandler" class="com.rory.ptspsim.diameterclient.AARHandler" />
```

```xml
<bean class="ru.example.ExampleControllerComponent">    
    <constructor-arg value="str0" />    
    <constructor-arg>
        <array>
            <value>str1</value>
            <value>str2</value>
        </array>
    </constructor-arg>
</bean>
```