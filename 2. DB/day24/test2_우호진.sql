--�� ���º� - ���ѽð� 60��(bangry313@naver.com �̸��� ����) 
--1. 'London'���� �ٹ��ϴ� ��� ����� �����ȣ(employee_id), ����̸�(last_name), �����̸�(job_title), �μ��̸�(department_name), �μ���ġ(city)�� ��ȸ�Ͻÿ�. 
--  -���� ���̺� : employees, jobs, departments, locations 
SELECT employee_id     "�����ȣ", 
       last_name       "����̸�", 
       job_title       "�����̸�", 
       department_name "�μ��̸�", 
       city            "�μ���ġ" 
FROM   employees 
       JOIN departments using(department_id) 
       JOIN jobs using(job_id) 
       JOIN locations using(location_id) 
WHERE  city = 'London'; 

--2. ����̸�(last_name)�� 'A'�� ���Ե� ����� �̸�(last_name)�� �μ���(department_name)�� ��ȸ�Ͻÿ�. 
SELECT e.last_name       "����̸�", 
       d.department_name "�μ���" 
FROM   employees e 
       JOIN departments d 
         ON e.department_id = d.department_id 
WHERE  e.last_name LIKE '%A%'; 

--3. �޿�(salary)�� 3000���� 5000 ������ ����� ��ȣ, �̸�, �޿�, �μ����� ��ȸ�϶�. 
SELECT employee_id       "�����ȣ", 
       last_name         "����̸�", 
       salary            "�޿�", 
       d.department_name "�μ���" 
FROM   employees e 
       JOIN departments d 
         ON e.department_id = d.department_id 
WHERE  e.salary >= 3000 
       AND e.salary <= 5000; 

--4. 'Accounting' �μ��� �ٹ��ϴ� ����� �̸��� �Ի����� ��ȸ�϶�. 
--   - �Ի��� ��� ���� - 2007�� 05�� 21��(������) 
SELECT last_name "����̸�", 
--      To_char(hire_date, 'dl')
       To_char(hire_date, 'yyyy')||'�� '||  To_char(hire_date, 'mm')||'�� '|| To_char(hire_date, 'dd')||'��('||  To_char(hire_date, 'day')||')' 
FROM   employees e 
       JOIN departments d 
         ON e.department_id = d.department_id 
WHERE  department_name = 'Accounting'; 

--5. 'Landry(last_name)'�� ���� �μ��� �ٹ��ϴ� ����� ��� ��� ������ ��ȸ�϶�. 
--    (��. Landry�� ����) 
SELECT * 
FROM   employees 
WHERE  department_id = (SELECT department_id 
                        FROM   employees 
                        WHERE  last_name = 'Landry') 
       AND last_name != 'Landry'; 

--6. 'Lee(last_name)'���� �ʰ� �Ի��� ����� �̸��� �Ի��� ��ȸ�϶�. 
SELECT last_name "����̸�", 
       hire_date "�Ի���" 
FROM   employees 
WHERE  hire_date > (SELECT hire_date 
                    FROM   employees 
                    WHERE  last_name = 'Lee'); 

--7. 'Lee(last_name)'���� ���� �޿��� �޴� ����� �̸��� �޿��� ��ȸ�϶�. 
SELECT last_name "����̸�", 
       salary    "�޿�" 
FROM   employees 
WHERE  salary > (SELECT salary 
                 FROM   employees 
                 WHERE  last_name = 'Lee'); 

--8. 50�� �μ��� ������ ���� �޿��� �޴� ����� �̸��� �޿��� ��ȸ�϶�. 
SELECT last_name "����̸�", 
       salary    "�޿�" 
FROM   employees 
WHERE  salary = ANY (SELECT salary 
                     FROM   employees 
                     WHERE  department_id = 50); 

--9. ��� ����� ��ձ޿����� ���� �޿��� �޴� ������� ���, �̸�, �޿��� ��ȸ�϶�. 
SELECT employee_id "���", 
       last_name   "�̸�", 
       salary      "�޿�" 
FROM   employees 
WHERE  salary > (SELECT Avg(salary) 
                 FROM   employees); 

--10.�̸�(last_name)��  'T'�� ���ԵǾ� �ִ� ����� ������ �μ��� �ٹ��ϴ� ����� ��ȣ, �̸��� ��ȸ�϶�. 
SELECT employee_id "���", 
       last_name   "�̸�" 
FROM   employees 
WHERE  department_id IN (SELECT department_id 
                         FROM   employees 
                         WHERE  last_name LIKE '%T%'); 

--11.10�� �μ� �ִ�޿��ڿ� ������ �޿��� �޴� ����� ��ȣ, �̸�, �޿��� ��ȸ�϶�. 
SELECT employee_id "���", 
       last_name   "�̸�", 
       salary      "�޿�" 
FROM   employees 
WHERE  salary = (SELECT Max(salary) 
                 FROM   employees 
                 WHERE  department_id = 10); 

--12. 10���μ��� �ٹ��ϴ� ����� �̸��� �μ��� ��ȸ�϶�. 
SELECT last_name       "����̸�", 
       department_name "�μ���" 
FROM   employees 
       JOIN departments using(department_id) 
WHERE  department_id = 10; 

--13. 'IT_PROG' ������ �ִ� �޿��ں��� ���� �޿��� �޴� ��� ����(�μ���ȣ, �����ȣ, �̸�, �޿�)�� ����϶�. 
SELECT department_id "�μ���ȣ", 
       employee_id   "���", 
       last_name     "�̸�", 
       salary        "�޿�" 
FROM   employees 
WHERE  salary > ALL (SELECT salary 
                     FROM   employees 
                     WHERE  job_id = 'IT_PROG'); 

--14. ��� �޿����� ���� �޿��� �ް� �̸��� u�� ���Ե� ����� ���� �μ��� �ٹ��ϴ� ��� ����� ��� ����(�����ȣ, �̸�, �޿�)�� ��ȸ�϶�. 
SELECT employee_id "���", 
       last_name   "�̸�", 
       salary      "�޿�" 
FROM   employees 
WHERE  salary > (SELECT Avg(salary) 
                 FROM   employees) 
       AND department_id IN (SELECT department_id 
                             FROM   employees 
                             WHERE  last_name LIKE '%u%'); 

--15. ��ձ޿��� ���� ���� ������ȣ(job_id)�� ��ձ޿��� ����϶� 
--    ��) ������ȣ  ��ձ޿� 
--       ------------------- 
--        CLERK      2300     
SELECT job_id      "������ȣ", 
       Avg(salary) "��ձ޿�" 
FROM   employees 
GROUP  BY job_id 
HAVING Avg(salary) <= ALL (SELECT Avg(salary) 
                           FROM   employees 
                           GROUP  BY job_id); 