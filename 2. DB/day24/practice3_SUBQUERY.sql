-- ����(����) ���� *********************************
-- ������ ������ �ϱ� ���ؼ�
-- ���������� ������� ���� ���...
SELECT salary 
FROM   employees 
WHERE  Lower(last_name) = 'seo'; 

SELECT * 
FROM   employees 
WHERE  salary = 2700; 

-- ������ �������� (������� �� ���� �ֱ� ����) (=, <, >, <=, >=, <>(!=)) **
-- ���������� �����ϰ� ���� ��� ����� ��� F5 ������ �̸� �� �� �ִ�.
SELECT * 
FROM   employees 
WHERE  salary = (SELECT salary 
                 FROM   employees 
                 WHERE  Lower(last_name) = 'seo') 
       AND Lower(last_name) != 'seo'; 

-- ��ü��� ��ձ޿����� �� ���� �޿��� �޴� ��� ��� ��ȸ
SELECT * 
FROM   employees 
WHERE  salary > (SELECT AVG(salary) 
                      FROM    employees);
       
-- ������ �������� (IN, ANY, ALL) ** 

-- IN ������ Ȱ��
-- 30�� �μ��� �Ҽӵ� ������ ������ ������ ������ ��ü ������ ��ȸ
SELECT last_name, 
          job_id, 
          department_id 
FROM   employees 
WHERE  job_id IN (SELECT job_id 
                       FROM   employees 
                       WHERE  department_id = 30);

-- ANY ������ Ȱ��
-- 30�� �μ��� �ּұ޿��� ����  �� ���� �޿��� �޴� ��ü ������ ��ȸ
SELECT * 
FROM   employees 
WHERE  salary > ANY (SELECT salary 
                             FROM   employees 
                             WHERE  department_id = 30);

-- ALL ������ Ȱ�� 
-- 30�� �μ��� �ִ�޿��� ���� �� ���� �޿��� �޴� ��ü ������ ��ȸ
SELECT * 
FROM   employees 
WHERE  salary < ALL (SELECT salary 
                            FROM   employees 
                            WHERE  department_id = 30);
                            
-- �����÷� �������� ** 
-- �μ��� �ּұ޿��� ���� 
SELECT * 
FROM   employees 
WHERE  ( department_id, salary ) IN(SELECT department_id, 
                                           Min(salary) 
                                    FROM   employees 
                                    GROUP  BY department_id) 
ORDER  BY department_id;

-- ���������� ����(Pseudo)�÷� Ȱ�� *****
--    ROWID, ROWNUM (SQL�� LIMIT ���������� Oracle�� ����.)
--      ���̺��� ������ �÷��� �ƴ� SQL������ ����� �� �ִ� �����÷��̴�.
--      ROWNUM�� SELECT���� ����Ǵ� �������� ����  ����(Fetch)�� �Ŀ� ���������� �ο��Ǵ� ���� �Ϸù�ȣ(1, 2, 3..)�̴�.
--      ROWNUM�� ���� �ø��� ���� ����!!�Ǳ� ������ ���̺��� ���� ���̶� ���� �ٸ� ���� ���� �� �ִ�.
--      ROWID�� index���� ���

-- �����÷�(ROWID, ROWNUM) 
SELECT rowid, 
       rownum, 
       employee_id, 
       last_name 
FROM   employees; 

-- ���̺��� ���� ���̶� ���� �ٸ� ROWNUM�� ���� �� �ִ� 
SELECT rownum, 
       employee_id 
FROM   employees; 

SELECT rownum, 
       employee_id 
FROM   employees 
ORDER  BY employee_id DESC; 

SELECT * 
FROM   employees 
WHERE  rownum > 0; 

-- ù��° ���� rownum�� 1�̹Ƿ� 
-- 1 > 1�� false�� �Ǿ� rownum�� ���̻� �������� ������, �ϳ��� �൵ ��ȯ���� ���� 
SELECT * 
FROM   employees 
WHERE  rownum > 1; 

-- ù��° 10����(����)���� ��ȸ�� ���
-- ù��° ���� rownum�� 1�̹Ƿ�
-- 1 <= 10�� true�� �Ǿ� ù��° �࿡ 1�� �Ҵ�ǰ� rownum�� 2�� ����
SELECT *
FROM   employees
WHERE  ROWNUM <= 10;
 
/* Ư�� �÷��� �������� �����Ͽ� ���� 5��(����)�� ��ȸ�ϰ��� �Ѵٸ� */ 
-- ��)��ü ����� �޿������� 5�� �������� 
-- ��ü �޿� ������ �ƴ� ó�� 5��ȿ����� �޿������� �� 
SELECT first_name, 
       salary 
FROM   employees 
WHERE  rownum <= 5 
ORDER  BY salary DESC; 

-- FROM������ ��������(Inline View)�� ����ؾ� �Ѵ� (ORDER BY�� ������ �������� ��� ��) > ����5�� ��������
SELECT * 
FROM   (SELECT * 
        FROM   employees 
        ORDER  BY salary DESC) 
WHERE  rownum <= 5; 

-- �޿������� 10 ~ 20 ���� (�� ��� ���� HINT ��� ����)
SELECT page, 
       employee_id, 
       first_name, 
       salary 
-- rownum�� ���� 10������ �ڸ�
FROM   (SELECT Ceil(rownum / 10) page, 
               employee_id, 
               first_name, 
               salary 
       -- ���������� order      
        FROM   (SELECT rownum,  
                       employee_id, 
                       first_name, 
                       salary 
                FROM   employees 
                ORDER  BY salary DESC)) 
WHERE  page = 2; 

