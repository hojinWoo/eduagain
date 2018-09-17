-- 접속 (by command)
--conn hr/hr

-- Table에 들어있는 각 Column 정보 표현
Describe Employees;

-- 명령어 및 출력 결과 저장
Spool C:\Kosta187\Spool.Sql
Select * From Employees;
Spool Off

-- ALIAS
Select Last_Name As "이름", Salary "월급",  From Employees;

-- DISTINCT 연산자
Select Distinct Department_Id From Employees;

-- 비교 연산자
Select Employee_Id, Last_Name, Salary From Employees Where Employee_Id = 177;
Select First_Name As Name, Salary From Employees Where Hire_Date < '2003/01/01';

-- **연산자** (리터럴 상수는 '' 줘야 한다)
Select Employee_Id, Last_Name, Job_Id From Employees Where Job_Id In('SA_MAN', 'IT_PROG');
Select Employee_Id, Last_Name, Hire_Date From Employees Where Hire_Date Between '2005/01/01' And '2005/12/31';
Select Employee_Id, Last_Name, Hire_Date From Employees Where Hire_Date Like '05%';

-- % : 0개 이상의 문자
Select Employee_Id, Last_Name, Job_Id From Employees Where Last_Name Like 'K%';
-- - : 임의의 한 문 자
Select Employee_Id, Last_Name, Job_Id From Employees Where Last_Name Like '_e%';
-- \_ ESCAPE '\' : 실제 _문자를 사용하기 위해 사용
Select Employee_Id, Last_Name, Job_Id From Employees Where Job_Id Like 'IT\_%'
-- ESCAPE '\';

-- SQL에서 NULL값은 JAVA와 달리 숫자가 큰 임의의 숫자로 인식
-- NULL 값을 가진 컬럼을 검색하는 경우 (is NULL, is not NULL)
Select * From Employees Where Commission_Pct Is Null;

-- 결합 연산자
Select First_Name || '' || Last_Name "FULL NAME" From Employees;

Select Employee_Id, Last_Name From Employees Where Hire_Date Like '05%';

-- order by (ctrl + shift + ' : 자동으로 대소문자 변경 처리)
Select Employee_Id, Last_Name From Employees Order By Last_Name Asc, Salary Desc;

-- group by
Select Department_Id, Count(Department_Id) From Employees Group By Department_Id;

-- 서브쿼리(내부쿼리)를 이용한 Table 복사
Create Table Emp As
  Select *
  From Employees;
  
-- Union (합집합)
Select *
From Employees
Union
Select *
From Employees;

-- Union all 두가지를 모두 보여준다
Select *
From Employees
Union All
Select *
From Employees;

-- Intersect (교집합)
Select *
From Employees
intersect
Select *
From Employees;

--**** DUAL TABLE : 가상 테이블, X만 들어있기 때문에 결과 한 개만 원할 경우 자주 사용 ********
-- 문자열 관련 단일행 함수
select concat('Oracle', 'Java Programming') as JAVA From dual;

--주어진 문자열의 첫 번째 문자를 대문자로 변환하여 반환
SELECT INITCAP('kim ki jung')
FROM dual;

-- 주어진 문자열을 소문자로 변환하여 반환
SELECT first_name, last_name
FROM employees
WHERE LOWER(first_name) = 'james';

SELECT UPPER('bangry')
FROM dual;

-- 주어진 문자열에 대해 n자리 확보 후 오른쪽으로 정렬 후 왼쪽에 생긴 빈 공백에 특정 문자를 채워 반환
SELECT LPAD('DataBase', 10, '*')
FROM dual;

SELECT RPAD('DataBase', 10, '*')
FROM dual;

-- 주어진 문자열에서 n번째 자리부터 length개의 문자열을 추출하여 반환(1부터 시작!!) **
SELECT SUBSTR('Java Developer', 6, 9)
FROM dual;

SELECT SUBSTR('서울시가산동', 4)
FROM dual;

SELECT first_name, LENGTH(first_name)
FROM employees;

-- REPLACE(column | expression, char1, char2)
-- 주어진 문자열의 특정 문자를 다른 문자로 변환하여 반환
SELECT REPLACE('기정바보', '바보', '최고')
FROM dual;

SELECT REPLACE('서울 시', ' ', '')
FROM dual;

--INSTR(column | expression, char, n, index)
--주어진 문자열에서 char문자가 n 시작위치에서 index번째 출현하는 위치 반환ㄴ
SELECT INSTR('DataBase', 'B')
FROM dual;

-- 2번째 a위치
SELECT INSTR('DataBase', 'a', 1, 2)
FROM dual;

-- 주어진 문자열의 왼쪽에서 공백이나 특정문자 제거 후 반환
--SELECT LTRIM('    JavaDeveloper')
--SELECT LTRIM('    JavaDeveloper', ' ')
SELECT LTRIM('JavaDeveloper', 'Java')
FROM dual;

--SELECT RTRIM('JavaDeveloper      ')
--SELECT RTRIM('JavaDeveloper      ', ' ')
SELECT RTRIM('JavaDeveloper', 'Developer')
FROM dual;

-- 주어진 문자열의 왼쪽과 오른쪽으로부터 공백 제거 후 반환
SELECT TRIM('      Java  Developer      ')
FROM dual;

SELECT  ROUND(45.923), ROUND(45.923, 0), ROUND(45.923, 2), ROUND(45.923, -1)
FROM dual;

SELECT  TRUNC(45.923), TRUNC(45.923, 0), TRUNC(45.923, 2), TRUNC(45.923, -1) 
FROM dual;

SELECT  MOD(123456, 2)
FROM dual;

SELECT  CEIL(123.123)
FROM dual;

SELECT  FLOOR(123.123)
FROM dual;

SELECT  ABS(-500)
FROM dual;

-- LOG N (자연로그)
SELECT  LN(10)
FROM dual;

SELECT  POWER(5, 2), SQRT(5), SIN(30), COS(30), TAN(30)
FROM dual;

-- 전달인자중 최소값 반환
SELECT  LEAST(10, 20, 30, 40)
FROM dual;

-- 전달인자중 최대값 반환
SELECT  GREATEST(10, 20, 30, 40)
FROM dual;

SELECT SYSDATE
FROM dual;

-- DATE 타입에 연산 가능
SELECT SYSDATE - 1 "어제" , SYSDATE "오늘", SYSDATE + 1 "내일"
FROM dual;

-- 사원별 근무 일수 검색
SELECT first_name,  hire_date, SYSDATE, CEIL(SYSDATE - hire_date) "근무일수"
FROM employees;

-- 사원별 근무 개월수 검색
SELECT first_name, TRUNC(MONTHS_BETWEEN(SYSDATE, hire_date))  "근무개월수"
FROM employees;

-- 특정개월수를 더한 날짜 반환
SELECT SYSDATE, ADD_MONTHS(SYSDATE, 2) "오늘부터 2개월 후"
FROM dual;

-- 이번주 토요일 날짜
SELECT SYSDATE "오늘", NEXT_DAY(SYSDATE, 7) "이번주 토요일"
FROM dual;

-- 이번달 마지막 날짜
SELECT SYSDATE, LAST_DAY(SYSDATE) "이번달 마지막날"
FROM dual;

-- TO_DATE(colum | expression [, ‘날짜포맷형식’])
-- 날짜로 포맷
SELECT TO_DATE('2011/12/31 18:45:23', 'YYYY/MM/DD HH24:MI:SS')
FROM dual;

SELECT first_name, hire_date
FROM employees
WHERE hire_date = TO_DATE('2003-06-17', 'YYYY-MM-DD');

SELECT first_name, hire_date
FROM employees
WHERE hire_date = TO_DATE(20030617, 'YYYY-MM-DD');

SELECT first_name, hire_date
FROM employees
WHERE hire_date = TO_DATE('2003/06/17', 'YYYY-MM-DD');

-- TO_NUMBER(colum | expression [,숫자포맷형식])
-- 전달인자로 숫자형식의 문자열을 입력 받아 숫자 형으로 변환하여 반환
SELECT TO_NUMBER('12345') + 1
FROM dual;

SELECT TO_NUMBER('12,345', '00,000') + 1
FROM dual;

SELECT TO_NUMBER('1000') + TO_NUMBER('2,000', '0,000') + 1
FROM dual;

-- TO_CHAR(colum | expression, ‘문자포맷형식’ [, nls_parameter])
-- 전달인자로 숫자나 날짜를  입력 받아 문자열 형으로 변환하여 반환


SELECT TO_CHAR(12345), TO_CHAR(12345.67)
FROM dual;

SELECT TO_CHAR(12345, '999,999'), TO_CHAR(12345.677, '999,999.99')
FROM dual;

SELECT TO_CHAR(12345, '000,000'), TO_CHAR(12345.677, '000,000.00')
FROM dual;

SELECT TO_CHAR(150, '$9999'), TO_CHAR(150, '$0000')
FROM dual;

SELECT TO_CHAR(150, 'S9999'), TO_CHAR(150, 'S0000')
FROM dual;

SELECT TO_CHAR(150, '9999MI'), TO_CHAR(-150, '9999MI')
FROM dual;

SELECT TO_CHAR(150, '9999EEEE'), TO_CHAR(150, '99999B')
FROM dual;

-- 로마자 대(소)문자로 표시
SELECT TO_CHAR(150, 'RN'), TO_CHAR(150, 'rn')
FROM dual;

-- 16진수 알파벳 대(소)문자로 표시
SELECT TO_CHAR(10, 'X'), TO_CHAR(10, 'x'), TO_CHAR(15, 'X')
FROM dual;

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS DAY')
FROM dual;

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS DAY', 'NLS_DATE_LANGUAGE=KOREAN')
--SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS DAY', 'NLS_DATE_LANGUAGE=ENGLISH')
FROM dual;

-- 초기 파라메터(환경설정) 목록 검색, 프로그램에서 환경 변수 조회 *********
SELECT * FROM  NLS_SESSION_PARAMETERS;

SELECT first_name, hire_date, TO_CHAR(hire_date, 'YYYY-MM-DD HH24:MI')
FROM employees;

-- 입사년도가 2002년도인 사원들
SELECT first_name, hire_date
FROM employees
WHERE TO_CHAR(hire_date, 'YYYY') = '2002';

SELECT 10 * NULL, 10 * NVL(NULL, 1)
FROM dual;

-- NVL
-- test NULL인 경우 모두 NULL로 나오게 됨
SELECT first_name, 
          salary, 
          commission_pct, 
          ( salary + ( salary * commission_pct ) ) * 12 "연봉"
FROM   employees;

-- NULL인 경우 모두 0으로 변환해서 나오게 됨
SELECT first_name, 
          salary, 
          commission_pct, 
          ( salary + ( salary * NVL(commission_pct, 0) ) ) * 12 "연봉" 
FROM   employees;


-- DECODE (if, else가 없기 때문에 함수로 제어문)
SELECT first_name, 
          job_id, 
          salary, 
          DECODE(job_id, 'IT_PROG', salary * 1.5, 
                              'AC_MRG', salary * 1.3, 
                              'AC_ASST', salary * 1.1, 
                              salary) "인상된급여" 
FROM   employees;
