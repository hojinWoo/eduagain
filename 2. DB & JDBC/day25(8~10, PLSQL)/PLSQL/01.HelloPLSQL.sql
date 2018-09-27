-- Client에서 server에 보내는 명령어

-- 서버에서 받은 데이터를 출력하기 위해 사용
-- (SQL PLUS가 OFF되어 있기 때문에 set serveroutput on; 설정 필요...) 
-- (SQL DEVELOPER는 DEFAULT가 ON이 되어있음)
SET SERVEROUTPUT ON;

-- 선언부
DECLARE
	-- 변수선언 (대소문자 구분 X)
  -- ':=' : assign
  v_no NUMBER(3) := 10;
  -- 상수처리
  c_message CONSTANT VARCHAR2(50) := '안녕하세요 PL/SQL';
  v_hireDate VARCHAR2(30) := TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS');
-- 실행부
BEGIN --중괄호 역할
	-- DBMS_OUTPUT 패키지의 put_line 프로시저(함수)를 이용한 결과 출력
  dbms_output.put_line('--- 오늘부터 PL/SQL입니다 ---');
	dbms_output.put_line(v_no);
  dbms_output.put_line(c_message);
  dbms_output.put_line(v_hiredate);
END;

-- 컴파일 과정 필요 (실행)

-- 실행방법 (F5) 
