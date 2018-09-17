#### oracle 최상위 접속

DB의 서버는 OS부팅 시 자동으로 부팅된다



##### SQL PLUS를 이용한 Oracle 접속 (SYS계정)

```sql
SQL> connect sys/oracle as sysdba;
```

> HR 계정을 풀어주기 위해서는 SYS로 접근 필요



##### HR(Sample) 계정 비밀번호 설정 및 잠금 해제

```sql
SQL> alter user hr identified by hr account unlock;
```

> 비밀번호 'hr' 



##### HR 계정 접근 (SYS계정에서는 나가야 함)

```sql
SQL> conn hr/hr

/*desc*/
SQL> describe employees;
/*Table에 들어있는 각 Column 정보 표현
Name			Null?	Type*/
```



##### Sample Table

```sql
SELECT * from employees;
/*	employees 	(mep)
	departments	(dept)
	locations
	countries
	regions
	jobs
	jobs_history
*/
```



### 명령어

1. SQL
2. SQL Plus (console에서 입력하는 명령어들)



##### SQL PLUS 명령어 (파일, 실행 명령어)

```sql
SQL> edit;
/* (메모리 버퍼에 저장되어 있는) 마지막으로 입력한 sql 명령어 메모장으로 편집 가능 */

SQL> run;
/* (실행하는 명령어가 먼저 찍힌 후에) 메모리 버퍼에 있는 수정된 명령어 실행 결과 */

SQL> /
/* 메모리 버퍼에 있는 수정된 명령어 실행 결과 출력 */

SQL> host
/* Shell로 빠져나가기 */

SQL> cls
/* clear */

SQL> exit
-- o/s prompt로 빠져나간다

SQL> save {파일명}
-- 버퍼의 내용 파일에 저장

SQL> @ {파일명}
-- GET과 RUN을 동시에 실행

SQL> spool {파일명}
/* 화면에 저장 시작*/
/* 저장하고 싶은 내용 입력 (명령어 및 출력 결과 저장됨)*/
SQL> spool off
```



### 형 변환 함수

#### TO_DATE(colum | expression [, ‘날짜포맷형식’])

- 전달인자로 날짜 형식의 문자열이나 숫자 받아 날짜 형으로 변환하여 반환
- 날짜 포맷 형식

| 포맷문자 | 설    명   | 예        |
| -------- | ---------- | --------- |
| YYYY     | 4자리 년도 | 2002      |
| YY       | 2자리 년도 | 02        |
| MM       | 2자리 월   | 10        |
| MONTH    | 알파벳 월  | JANUARY   |
| MON      | 알파벳 월  | JAN       |
| DD       | 날짜       | 15        |
| DAY      | 요일       | SUNDAY    |
| DY       | 요일       | SUN       |
| D        | 주의 일수  | 일요일: 1 |
| Q        | 1, 2, 3, 4 | 분기      |
| AMPM     | 오전/오후  | AM/PM     |
| HH/HH12  | 12시간     | 03        |
| MI       | 분         | 45        |
| SS       | 초         | 26        |



#### To_CHAR(column | expression (, 문자포맷형식’)(, nls_parameter))

- 전달인자로 숫자나 날짜를  입력 받아 문자열 형으로 변환하여 반환

-  숫자 포맷 형식

| 포맷문자  | 설    명                                                     |
| --------- | ------------------------------------------------------------ |
| 9         | 출력할 자릿수를 지정하고, 값이 지정한 자릿수보다 작으면 공백으로 표시 |
| 0         | 출력할 자릿수를 지정하고, 값이 지정한 자릿수보다 작으면 앞을 0으로 표시 |
| $         | 달러 기호를 숫자 앞에 표시                                   |
| ,(콤마)   | 명시한 위치에 콤마(,) 표시                                   |
| .(소수점) | 명시한 위치에 소수점(.) 표시                                 |
| S         | 숫자 앞에 부호(+,   -) 표시                                  |
| MI        | 오른쪽에 음수 “-” 기호로 표시                                |
| PR        | 음수값을 “<>” 기호로   표시                                  |
| EEEE      | 지수(과학적 표기)로 표시                                     |
| B         | 출력할 자릿수를 지정하고, 값이 지정한 자릿수보다 작으면 공백으로 표시 |
| L         | 지역 통화로 표시                                             |
| RN(rm)    | 로마자 대(소)문자로 표시                                     |
| X(x)      | 16진수   알파벳 대(소)문자로   표시                          |



### 일반 함수 (모든 데이터타입에 사용 가능)

#### NVL(column | expression, value)

- NULL을 다른 값으로 변환

- 주어진 숫자, 문자, 날짜가 NULL 인 경우 value로 변환하여 반환 



### 조건[분기] 함수

#### DECODE(colum | expression, search1, result1 

#### 							[, search2, result2, …]

#### 							[, default]))

- SWITCH문과 유사한 함수 (조건에 맞는 문장을 수행)