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

  > 파일 수정 후 >> Project 우클릭 > Maven > Update Project
  >
  > error가 나는 경우 .m2 폴더 삭제 후 다시 update하기 (sts bug)

- [Lombok Library](https://projectlombok.org/all-versions) (plugin)설치, market place에 없어서 수동 설치 필요
  알집파일 위치에서 Dos창 열어서 명령어 입력

  ```bash
  java -jar lombok-1.18.2.jar
  ```

  > 이클립스가 있는 곳에 eclipse.exe파일 등록 필요!!
  >
  > 완료 후 이클립스 껐다가 다시 열기 (바탕화면에 이클립스 바로가기가 있는 경우도 삭제 후 다시 생성하기)
  >
  > 설치가 완료되어있다면 이클립스가 있는 곳에 lombok.jar파일이 들어있다





## 개념 정리

### POJO (Plain Old Java Object)

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
- 