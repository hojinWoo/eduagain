-- 명시적 NULL 입력 
INSERT INTO departments 
                  (department_id,     --PK
                   department_name,   --NOT NULL 
                   manager_id,        --FK
                   location_id)       --FK
VALUES        (900, 
                   'KOSTA', 
                   NULL, 
                   NULL);
                   
                   
-- Data Dictionary 조회***********
-- 형식 : user_***
SELECT * 
FROM   user_constraints; --제약조건
-- 스키마, 제약조건 이름, 제약조건 type, table name, ...

-- 시퀀스를 이용한 부서번호 입력 
INSERT INTO departments (department_id,  department_name, manager_id, location_id) 
VALUES       (departments_seq.NEXTVAL, 'KOSTA', NULL, NULL); 

SELECT * 
FROM   user_sequences; 

-- 묵시적 NULL 입력 
INSERT INTO departments (department_id, department_name) 
VALUES     (510,  'KOSTA1'); 

-- 다른 테이블에 있는 것을 복사해서 사용할 때 사용
CREATE TABLE dept2
     AS SELECT * 
FROM dept
WHERE 0 = 1; -- 데이터구조만 복사(데이터 내용 필요 없이)

INSERT INTO DEPT2
SELECT * FROM  DEPT ;


-- UPDATE
UPDATE employees 
SET    salary = salary * 1.1 
WHERE  department_id = 30;

-- DELETE
DELETE FROM departments 
WHERE  department_name LIKE '%KOSTA%'; 

DELETE FROM departments 
WHERE  department_name LIKE '%KOSTA%'; 

DELETE FROM employees 
WHERE  salary > (SELECT AVG(salary) 
                       FROM   employees); 

desc employees;

select * from user_sequences;

select * from user_constraints;