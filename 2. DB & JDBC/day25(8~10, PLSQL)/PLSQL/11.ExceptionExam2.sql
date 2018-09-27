-- 사용자 정의 오라클 서버 오류 처리
DECLARE
	-- 예외선언 및 오라클 서버 오류 번호와 결합
    e_notnull EXCEPTION;
    PRAGMA EXCEPTION_INIT(e_notnull, -1400);

BEGIN
	INSERT INTO employees(first_name, department_id)
  VALUES('James', 30);

	EXCEPTION
		WHEN e_notnull THEN
			DBMS_OUTPUT.PUT_LINE('이름을 반드시 입력하여 주세요');
END;


