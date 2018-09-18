-- cross join (Oracle join)
select *
from employees e, departments d;

-- cross join (by ANSI)
SELECT e.last_name, 
       d.department_id 
FROM   employees e 
       CROSS JOIN departments d; 
    
SELECT e.employee_id, 
       e.last_name, 
       d.department_name 
FROM   employees e 
       CROSS JOIN departments d; 
  
-- INNER JOIN *********  
-- ����Ŭ EQUI JOIN ����
SELECT e.employee_id, 
       e.last_name, 
       d.department_name 
FROM   employees e, 
       departments d 
WHERE  e.department_id = d.department_id 
       AND e.salary >= 3000; 

-- ANSI ǥ�� EQUI JOIN ����
SELECT e.employee_id, 
       e.last_name, 
       d.department_name 
FROM   employees e 
       JOIN departments d 
         ON e.department_id = d.department_id 
        -- using(department_id)  
WHERE  e.salary >= 3000; 

-- 3���̻� ���̺� ����
-- Dictionary ���̺�κ��� ���̺� ���� ����
SELECT *
FROM user_tables;

SELECT e.employee_id, 
       e.last_name, 
       d.department_name, 
       l.city, 
       l.state_province, 
       c.country_name 
FROM   employees e 
       JOIN departments d 
         ON e.department_id = d.department_id --�μ�
       JOIN locations l 
         ON d.location_id = l.location_id     --����
       JOIN countries c 
         ON l.country_id = c.country_id;      --����

-- NON-EQUI  JOIN(������ Join)
-- ���� �÷��� ���� ���̺�� ���̺� ����
-- ���� ���ǿ��� ��=�� �����ڸ� �̿��� ����񱳰� �ƴ� �ٸ� �񱳿����ڸ� ����Ͽ� Ư�������� ��� ���� �����Ͽ� ����
-- ��� �޿��� ���� �޿���� ���

-- ����Ŭ NON-EQUI JOIN(���� : ������, �������δ� ���� ����)
-- �� ������ ���� min, max�� ������ � ������ �Ҽ��ϴ� �� ��������
SELECT e.employee_id, 
       e.last_name, 
       e.salary, 
       j.job_title 
FROM   employees e, 
       jobs j 
WHERE  e.salary BETWEEN j.min_salary AND j.max_salary 
ORDER  BY e.employee_id; 

-- Outer join
-- INNER JOIN �� ���� ������ �������� �ʴ� ���� �˻����� �ʴ´�.
-- ���� ������ �������� �ʴ� ��鵵 �˻��� �����ϰ��� �� ��� ����Ѵ�.
-- employees ���̺����� �μ���ȣ�� NULL �� Kimberely�� 
-- EQUI Join�����δ� �˻����� ����
SELECT e.first_name, 
       d.department_name 
FROM   employees e, 
       departments d 
WHERE  e.department_id = d.department_id; 

-- �ҼӺμ��� ���� ����� ���!
-- ����Ŭ OUTER JOIN
SELECT e.employee_id, 
       e.first_name, 
       e.last_name, 
       d.department_name 
FROM   employees e, 
       departments d 
WHERE  e.department_id = d.department_id(+);  -- + : outer

-- ANSI ǥ�� OUTER JOIN(LEFT, RIGHT, FULL ����� ���)
--  LEFT OUTER JOIN
SELECT e.first_name, 
       d.department_name 
FROM   employees e 
       LEFT OUTER JOIN departments d 
                    ON e.department_id = d.department_id; 

-- RIGHT OUTER JOIN
SELECT e.first_name, 
       d.department_name 
FROM   employees e 
       RIGHT OUTER JOIN departments d 
                     ON e.department_id = d.department_id;  

-- FULL OUTER JOIN
SELECT e.first_name, 
       d.department_name 
FROM   employees e 
       FULL OUTER JOIN departments d 
                    ON e.department_id = d.department_id; 

-- SELF JOIN ***************
--���� ���̺� ��Ī�� �ο��Ͽ� �� �ٸ� ���̺�ó�� �����ϴ� ��
--��� �� ���(�Ŵ���) �̸� �˻� �� SELF JOIN �ʿ�

-- ����� ��� �˻� 
SELECT employee.first_name, 
       manager.first_name 
FROM   employees employee, 
       employees manager 
WHERE  employee.manager_id = manager.employee_id; 

-- ��簡 ���� ����� �˻� �� OUTER JOIN �ʿ� (oracle)  
SELECT employee.first_name, 
       manager.first_name 
FROM   employees employee, 
       employees manager 
WHERE  employee.manager_id = manager.employee_id(+); 

-- ANSI  
SELECT employee.first_name, 
       manager.first_name 
FROM   employees employee 
       left outer join employees manager 
                    ON employee.manager_id = manager.employee_id; 
                    
                    