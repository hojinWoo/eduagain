### 참고 사이트

SQL 예쁘게 나올 수 있게 formatting 해주는 사이트

[SQL Format](http://www.dpriver.com/pp/sqlformat.htm)



### Error 해결(1)

[참고 사이트](https://community.cisco.com/t5/collaboration-voice-and-video/the-user-receives-the-quot-ora-12560-tns-protocol-adapter-quot/ta-p/3124415)

```sql
SQL> conn hr/hr
ERROR:
ORA-12560: TNS:protocol adapter error
```

> 실제 DB에 연결이 되지 않을 때
>
> 시작> 설정> 제어판> 서비스 > 데이터베이스 상태 확인 

![error](./image/error.png)

**oracleServiceXE** 와 **OracleXETNSLitener**를 시작 시켜줘야 한다.

(OracleXETNSLitener는 SQL DEVELOPER를 사용 시에 연결시켜주는 Listener)

![error complete](./image/error_complete.png)

> 해결 완료



