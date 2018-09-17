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

