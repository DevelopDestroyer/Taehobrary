import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//class 내에 선언
private static final Logger logger = LoggerFactory.getLogger(myClass.class);	//본 클래스의 클래스명 적어줌

//로그 사용
logger.info("테스트 로그 입니다");

//application.properties에 설정 추가
logging.config=classpath:logback-spring.xml

/*
	- src/main/resource에 xml파일 생성한다 : logback-spring.xml
	
	- 해당 파일 내용은 다음과 같이 설정한다
	--------------------------------------------------------------------
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>

    <appender name="dailyRollingFileAppender"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <prudent>true</prudent>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>D://mylog//application.%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level>
        </filter>

        <encoder>
            <pattern>%d{yyyy:MM:dd HH:mm:ss.SSS} %-5level --- [%thread] %logger{35} : %msg %n</pattern>
        </encoder>
    </appender>

    <logger name="com.exlogger" level="INFO"></logger>

    <logger name="org.springframework.web" level="DEBUG"/>
    <!--
    <logger name="org.thymeleaf" level="DEBUG"/>
    <logger name="org.hibernate.SQL" level="DEBUG"/>
    <logger name="org.quartz.core" level="DEBUG"/>
    <logger name="org.h2.server.web" level="DEBUG"/>
    -->

    <root level="INFO">
        <appender-ref ref="dailyRollingFileAppender" />
    </root>
</configuration>
	--------------------------------------------------------------------


*/