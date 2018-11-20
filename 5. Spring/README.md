# Spring Framework



#### Make Ready

- Download Eclipse **STS** 

  Eclipse MarketPlace에서 STS 다운받기

- Perspective 설정 (STS 다운 후)
  Window >> Perspective >> Open Perspective >> Other >> **Spring** 설정
- File > new > **Spring Legacy Project** (스프링 프로젝트 생성)  > Templates에서 **Spring MVC Project** 사용(Maven 포함 및 기본 설정이 다 되어있는 것) 

- **porm.xml** 수정  (java version 및 Spring version은 원하는 버전에 맞게 수정)

  - (11 line)

    ```xml
    	<properties>
    		<java-version>1.8</java-version>
    		<org.springframework-version>5.0.10.RELEASE</org.springframework-version>
    		<org.aspectj-version>1.6.10</org.aspectj-version>
    		<org.slf4j-version>1.6.6</org.slf4j-version>
    	</properties>
    ```

  - (136 line)

    ```xml
    			<plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.5.1</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                        <compilerArgument>-Xlint:all</compilerArgument>
                        <showWarnings>true</showWarnings>
                        <showDeprecation>true</showDeprecation>
                    </configuration>
                </plugin>
    ```

    > cf) 2019년부터 JAVA 유료화 정책에 1.7부터 유료화....이므로 Spring은 default로 1.6을 잡고 있다.

  - jUnit 버전 변경 및 라이브러리 추가 (113 line)

    ```xml
    		<dependency>
    			<groupId>junit</groupId>
    			<artifactId>junit</artifactId>
    			<version>4.12</version><!-- 스프링 4.x이상 사용시 4.10 이상 설정 필요 -->
    			<scope>test</scope>
    		</dependency> 
    		
    		<!-- 라이브러리 추가하기 -->
    		
    		<!-- spring-Test 라이브러리-->
    		<dependency>
    		   <groupId>org.springframework</groupId>
    		   <artifactId>spring-test</artifactId>
    		   <version>${org.springframework-version}</version>
    		</dependency>
    		
    		<!-- constructor, setter, getter 자동 생성 lombok 라이브러리 -->
    		<dependency>
    		   <groupId>org.projectlombok</groupId>
    		   <artifactId>lombok</artifactId>
    		   <version>1.18.2</version>
    		   <scope>provided</scope>
    		</dependency>
    ```

  - 167 line

    ```xml
     			<plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>2.5.1</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                        <compilerArgument>-Xlint:all</compilerArgument>
                        <showWarnings>true</showWarnings>
                        <showDeprecation>true</showDeprecation>
                    </configuration>
                </plugin>
    ```

    > 마찬가지로 1.6으로 되어있다.

  > 파일 수정 후 >> Project 우클릭 > Maven > Update Project
  >
  > error가 나는 경우 .m2 폴더 삭제 후 다시 update하기 (sts bug)

- [Lombok Library](https://projectlombok.org/all-versions) (plugin)설치, market place에 없어서 수동 설치 필요
  다운받은 알집파일이 있는 위치에서 dos창 열어서 명령어 입력

  ```bash
  java -jar lombok-1.18.2.jar
  ```

  > 이클립스가 있는 곳에 eclipse.exe파일 등록 필요!!
  >
  > 완료 후 이클립스 껐다가 다시 열기 (바탕화면에 이클립스 바로가기가 있는 경우도 삭제 후 다시 생성하기)
  >
  > 설치가 완료되어있다면 이클립스가 있는 곳에 lombok.jar파일이 들어있다
  >
  > > [Lombok 설명](https://projectlombok.org/features/all	)

- Oracle JDBC 등록

  1. 실제 WEB-INF/lib에 jar 파일 등록

  2. 사설파일 porm.xml등록 (잘 안되는 경우도 있어서 다른 코드를 넣는 것도 추천.)

     ```xml
     	<!-- Oracle Jdbc Driver maven Repository -->
     	<repositories>
     	     <repository>
     	          <id>codelds</id>
     	          <url>https://code.lds.org/nexus/content/groups/main-repo</url>
     	     </repository>
     	</repositories>
     
     		<!-- oracle jdbc driver -->
     		<dependency>
     		     <groupId>com.oracle</groupId>
     		     <artifactId>ojdbc6</artifactId>
     		     <version>11.2.0.3</version>
     		</dependency>
     ```

     > Oracle Driver Connection사용하려면 java 버전이 1.7이상이 되어야 한다.

- [Postman](https://chrome.google.com/webstore/search/postman) : 크롬에서 가상 데이터를 보내고 받을 때 사용하는 확장 플러그인




## 개념 정리

### POJO (Plain Old Java Object), 순수 Java Class

- **IoC / DI**
- **AOP**
- **PSA**



### IoC (Inversion Of Control)

객체 생성 책임을 컨테이너에 위임 : 객체 간 결합도를 낮춘다 (loose coupling)

**구현 방법**

- Dependency Lookup  (EJB에서 사용)
- Dependency Injection (Spring에서 사용)



#### DI (Dependency Injection)

객체 간 의존관계를 Bean 설정 정보를 바탕으로 자동적으로 설정, 외부에서 객체 생성 시점에 참조하는 객체에게 의존관계 제공(inversion of responsibility) > 객체는 인터페이스만 알고 있으므로, loose coupling이 가능. 

**대표적 3가지 유형**

- Constructor Injection (생성자를 이용한 의존성 삽입)
- Setter Injection (setter()를 이용한 의존성 삽입)
- Interface Injection (초기화 인터페이스를 이용한 의존성 삽입)



### Annotation 관리

- Class에 Annotation을 추가하는 방식 : 자동으로 Spring Bean 설정 가능

- Bean Scanner는 자동으로 Bean으로 등록 :  기본적으로 Bean의 아이디로 클래스  이름(클래스 이름의 첫 글자만  소문자로 바꾼 것)을 사용
  - Bean Scanner 설정 필요!! **context:component-scan**



### AOP(Aspect-Oriented Programming)

관심사의 분리를 통해서 모듈성을 향상시키고자  하는 프로그래밍 개발 방식(ex. 암호화처리, 인증, transaction, Log 기록 등) - Cross Cuting

##### 주요 용어

- ``Aspect``

  공통기능(횡단 관심사)에 대한 추상적인 명칭 (Advice + Pointcut)

- ``Joinpoint``

  공통기능을 적용할 대상(field, constructor, method)

- ``Advice``

  Joinpoint에서 실행되는 코드

- ``Pointcut``

  처리가 Joinpoing에 이르렀을 때 Advice를 호출할 지 선별



##### Spring은 <u>Proxy 기반 AOP</u> : business객체가 실행되기 전 Runtime에 Proxy가 중간에서 먼저 처리 후에 넘긴다. 



##### Advice 종류(pointcut)

- Before

- After

- AfterReturning

  Joinpoint가 정상 종료한 다음 실행되는 Advice

- Aroud

  Joinpoint 앞뒤에 실행되는 Advice

- AfterThrowing

  Joinpoint에서 예외가 발생했을 때 실행되는 Advice (ex. ControllerAdvice)



##### Pointcut 표현식

- ``execution``

  조건에 맞는 method 실행시 실행

- ``within``

  특정 타입내에 선언된 모든 method

- ``target``

  타깃 객체가 특정 인터페이스를 구현하는 조인포인트일 경우 실행

- ``this``

  프록시가 특정 인터페이스를 구현하는 조인포인트일 경우 실행

- ``args``

  ``args(java.io.Serialize)``아규먼트가 특정 타입을 갖는 경우



#### Transaction 관리

쪼개질 수 없는 하나의 단위 작업(ex. DB : commit, rollback 등)

**Service**에서 Transaction 처리 관리(DAO에서 하는 것 X, 게시판에서는 Mybatis가 commit 처리 했음)

##### ACID 원칙

- ``Atomicity``
- ``Consistency``
- ``Isolation``
- ``Durability``