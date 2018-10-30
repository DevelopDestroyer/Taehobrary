/*
	- 작성자 : 이태호 (Taeho Lee)
	- 이메일 : developdestroyer@gmail.com
	- 작성일 : 2018-10-30

	 웹 서비스 개발에서의 한글깨짐 현상의 원인은 다양한 환경 요인에서 올 수 있다.
	 이에 따라 "톰캣, 프론트-엔드, 백엔드, DB"까지 모든 과정에서의 인코딩 세팅을 일치화 시켜주는 것이 중요하다
	 본 파일에서는 이를 보다 빠르고 편리하게 구축할 수 있도록 가이드를 제시 한다.
	 예시에서는 모두 UTF-8로 통일하였음
*/

/*---------------------------------------------------------------
	- JSP 설정
----------------------------------------------------------------*/
/*
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
*/




/*---------------------------------------------------------------
	- 백엔드 설정 => Application.java
----------------------------------------------------------------*/
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
}



/*---------------------------------------------------------------
	- DB 설정
----------------------------------------------------------------*/
/*

<	1. DB서버에 접속하여 다음과 같이 vi etc/my.cnf 수정 >
--------------------------------------------------------
[client]
default-character-set = utf8

[mysqld]
init_connect = SET collation_connection = utf8_general_ci
init_connect = SET NAMES utf8
character-set-server = utf8
collation-server = utf8_general_ci

[mysqldump]
default-character-set = utf8

[mysql]
default-character-set = utf8
--------------------------------------------------------


<	2. 서비스 재시작하고 다음과 같은 쿼리를 날려본다>
--------------------------------------------------------
(1)
# service mysqld restart

(2)
# mysql -u root -p
...

(3)
 > use database_name;
 > show variables like 'c%';
--------------------------------------------------------

<	3. Output이 이렇게 나온다면 성공! >
MariaDB [OPENHOLO]> show variables like 'c%';
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | utf8                       |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | utf8                       |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
| collation_connection     | utf8_general_ci            |
| collation_database       | utf8_general_ci            |
| collation_server         | utf8_general_ci            |
| completion_type          | NO_CHAIN                   |
| concurrent_insert        | AUTO                       |
| connect_timeout          | 10                         |
| core_file                | OFF                        |
+--------------------------+----------------------------+


*/
