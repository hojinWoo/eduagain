-- ����� NULL �Է� 
INSERT INTO departments 
                  (department_id,     --PK
                   department_name,   --NOT NULL 
                   manager_id,        --FK
                   location_id)       --FK
VALUES        (900, 
                   'KOSTA', 
                   NULL, 
                   NULL);
                   
                   
-- Data Dictionary ��ȸ***********
-- ���� : user_***
SELECT * 
FROM   user_constraints; --��������
-- ��Ű��, �������� �̸�, �������� type, table name, ...

-- �������� �̿��� �μ���ȣ �Է� 
INSERT INTO departments (department_id,  department_name, manager_id, location_id) 
VALUES       (departments_seq.NEXTVAL, 'KOSTA', NULL, NULL); 

SELECT * 
FROM   user_sequences; 

-- ������ NULL �Է� 
INSERT INTO departments (department_id, department_name) 
VALUES     (510,  'KOSTA1'); 

-- �ٸ� ���̺� �ִ� ���� �����ؼ� ����� �� ���
CREATE TABLE dept2
     AS SELECT * 
FROM dept
WHERE 0 = 1; -- �����ͱ����� ����(������ ���� �ʿ� ����)

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