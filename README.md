##Webing Pilot
=========
Webing 팀의 파일럿 프로젝트 운영툴

Features:
-----------
- 후보별 공약 편집 작업
- 후보별 뉴스 키워드 편집 작업
- 19대 의원별 의안 키워드 편집 작업
- ...etc


Set up Webing environment
-----------
firstly, you need to install mysql, tomcat and maven.

after dependencies is installed , you can follow below commands.

To deploy a war file on tomcat, you have to config the authentication about tomcat and maven.

```
# %TOMCAT_PATH%/conf/tomcat-user.xml
<?xml version='1.0' encoding='utf-8'?>
<tomcat-users>

	<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<user username="admin" password="password" roles="manager-gui,manager-script" />

</tomcat-users>
```
------------------------------------------------
```
# %MAVEN_PATH%/conf/settings.xml
<?xml version="1.0" encoding="UTF-8"?>
<settings ...>
	<servers>
	   
		<server>
			<id>TomcatServer</id>
			<username>admin</username>
			<password>password</password>
		</server>

	</servers>
</settings>
```
-------------------------------------------------


How to Implement
--------

```
$ git clone https://github.com/we-bing/pilot-editor.git
$ cd pilot-editor
$ mysql> create database webing-pilot
$ mysql -u {mysql.user} -p {mysql.password} lionboard < database/WEBING-PILOT_20xx_xx_xx.sql
$ vi /src/main/resources/custom.properties # Fill up the blank.
```

Run with tomcat
-------

```
$ cd ../pilot-editor # the root of project.
$ mvn clean install tomcat7:run-war -Dwarfile=src/target/pilot-editor.war -Dmaven.test.skip=true
```



