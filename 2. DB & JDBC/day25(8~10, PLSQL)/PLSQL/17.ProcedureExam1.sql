-- 자주 사용되는 PL/SQL 블럭을 재사용할 수 있도록 모듈화하여 DBMS에 저장하고, 사용(호출)
-- 부서번호 받아 사원리스트 처리
CREATE OR REPLACE PROCEDURE listByDeptno(p_deptno employees.department_id%TYPE)
IS
	employee_record employees%ROWTYPE ;
    CURSOR employee_cursor IS
    	SELECT * FROM employees
        WHERE department_id = p_deptno;
BEGIN
	DBMS_OUTPUT.put_line('--- 사원 리스트 ---');
    FOR employee_record IN employee_cursor LOOP
    	--EXIT WHEN employee_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(p_deptno || ',' || employee_record.employee_id || ', ' || employee_record.first_name || ', ' || employee_record.salary);
    END LOOP;
END;

-- 프로시저 실행
--EXECUTE listByDeptno(100);
