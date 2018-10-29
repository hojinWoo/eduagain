## JUnit 

[JUnit](https://junit.org/junit4/) : 단위 테스트 프레임워크(Container 구조 = Factory를 생성하고 life cyle도 관리), 나중에는 Maven으로 동적 생성 가능

- TestCase 클래스를 상속받아 Test 클래스를 정의하는 방법(JUnit 3)
- Annotation을 사용하여 클래스를 정의하는 방법(JUnit 4) 

> 3버전과 4버전이 같이 있으면 충돌 생김



**현재 [JUnit5](https://junit.org/junit5/)도 있음**



## Log4J

- Log4J는 Multi-Thread 환경에서도 성능에 전혀 영향을 주지 않으면서, 
  안전하게 로그를 기록할 수 있는 환경을 제공한다

- 다양한 출력 대상 지정 가능

  >Console, **File**, Database(?), TCP 서버 등

- 낮은 등급에서 높은 등급으로의 6단계 로그 레벨 별 출력 가능

  > TRACE(test)< DEBUG(개발) < INFO < WARN < ERROR < FATAL

- 환경설정파일(log4j.xml)에  Java package 별로 로그 레벨 설정이 가능하고, 
  설정한 레벨 이상의 로그만 출력 가능하도록 설정 가능



#### Log4J 설정 방법

- 자바 클래스를 이용한 설정
- properties  파일을 이용한 설정
- **XML 파일을 이용한 설정**