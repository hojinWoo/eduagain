-- ���� (by command)
--conn hr/hr

-- Table�� ����ִ� �� Column ���� ǥ��
Describe Employees;

-- ��ɾ� �� ��� ��� ����
Spool C:\Kosta187\Spool.Sql
Select * From Employees;
Spool Off

-- ALIAS
Select Last_Name As "�̸�", Salary "����",  From Employees;

-- DISTINCT ������
Select Distinct Department_Id From Employees;

-- �� ������
Select Employee_Id, Last_Name, Salary From Employees Where Employee_Id = 177;
Select First_Name As Name, Salary From Employees Where Hire_Date < '2003/01/01';

-- **������** (���ͷ� ����� '' ��� �Ѵ�)
Select Employee_Id, Last_Name, Job_Id From Employees Where Job_Id In('SA_MAN', 'IT_PROG');
Select Employee_Id, Last_Name, Hire_Date From Employees Where Hire_Date Between '2005/01/01' And '2005/12/31';
Select Employee_Id, Last_Name, Hire_Date From Employees Where Hire_Date Like '05%';

-- % : 0�� �̻��� ����
Select Employee_Id, Last_Name, Job_Id From Employees Where Last_Name Like 'K%';
-- - : ������ �� �� ��
Select Employee_Id, Last_Name, Job_Id From Employees Where Last_Name Like '_e%';
-- \_ ESCAPE '\' : ���� _���ڸ� ����ϱ� ���� ���
Select Employee_Id, Last_Name, Job_Id From Employees Where Job_Id Like 'IT\_%'
-- ESCAPE '\';

-- SQL���� NULL���� JAVA�� �޸� ���ڰ� ū ������ ���ڷ� �ν�
-- NULL ���� ���� �÷��� �˻��ϴ� ��� (is NULL, is not NULL)
Select * From Employees Where Commission_Pct Is Null;

-- ���� ������
Select First_Name || '' || Last_Name "FULL NAME" From Employees;

Select Employee_Id, Last_Name From Employees Where Hire_Date Like '05%';

-- order by (ctrl + shift + ' : �ڵ����� ��ҹ��� ���� ó��)
Select Employee_Id, Last_Name From Employees Order By Last_Name Asc, Salary Desc;

-- group by
Select Department_Id, Count(Department_Id) From Employees Group By Department_Id;

-- ��������(��������)�� �̿��� Table ����
Create Table Emp As
  Select *
  From Employees;
  
-- Union (������)
Select *
From Employees
Union
Select *
From Employees;

-- Union all �ΰ����� ��� �����ش�
Select *
From Employees
Union All
Select *
From Employees;

-- Intersect (������)
Select *
From Employees
intersect
Select *
From Employees;

--**** DUAL TABLE : ���� ���̺�, X�� ����ֱ� ������ ��� �� ���� ���� ��� ���� ��� ********
-- ���ڿ� ���� ������ �Լ�
select concat('Oracle', 'Java Programming') as JAVA From dual;

--�־��� ���ڿ��� ù ��° ���ڸ� �빮�ڷ� ��ȯ�Ͽ� ��ȯ
SELECT INITCAP('kim ki jung')
FROM dual;

-- �־��� ���ڿ��� �ҹ��ڷ� ��ȯ�Ͽ� ��ȯ
SELECT first_name, last_name
FROM employees
WHERE LOWER(first_name) = 'james';

SELECT UPPER('bangry')
FROM dual;

-- �־��� ���ڿ��� ���� n�ڸ� Ȯ�� �� ���������� ���� �� ���ʿ� ���� �� ���鿡 Ư�� ���ڸ� ä�� ��ȯ
SELECT LPAD('DataBase', 10, '*')
FROM dual;

SELECT RPAD('DataBase', 10, '*')
FROM dual;

-- �־��� ���ڿ����� n��° �ڸ����� length���� ���ڿ��� �����Ͽ� ��ȯ(1���� ����!!) **
SELECT SUBSTR('Java Developer', 6, 9)
FROM dual;

SELECT SUBSTR('����ð��굿', 4)
FROM dual;

SELECT first_name, LENGTH(first_name)
FROM employees;

-- REPLACE(column | expression, char1, char2)
-- �־��� ���ڿ��� Ư�� ���ڸ� �ٸ� ���ڷ� ��ȯ�Ͽ� ��ȯ
SELECT REPLACE('�����ٺ�', '�ٺ�', '�ְ�')
FROM dual;

SELECT REPLACE('���� ��', ' ', '')
FROM dual;

--INSTR(column | expression, char, n, index)
--�־��� ���ڿ����� char���ڰ� n ������ġ���� index��° �����ϴ� ��ġ ��ȯ��
SELECT INSTR('DataBase', 'B')
FROM dual;

-- 2��° a��ġ
SELECT INSTR('DataBase', 'a', 1, 2)
FROM dual;

-- �־��� ���ڿ��� ���ʿ��� �����̳� Ư������ ���� �� ��ȯ
--SELECT LTRIM('    JavaDeveloper')
--SELECT LTRIM('    JavaDeveloper', ' ')
SELECT LTRIM('JavaDeveloper', 'Java')
FROM dual;

--SELECT RTRIM('JavaDeveloper      ')
--SELECT RTRIM('JavaDeveloper      ', ' ')
SELECT RTRIM('JavaDeveloper', 'Developer')
FROM dual;

-- �־��� ���ڿ��� ���ʰ� ���������κ��� ���� ���� �� ��ȯ
SELECT TRIM('      Java  Developer      ')
FROM dual;

SELECT  ROUND(45.923), ROUND(45.923, 0), ROUND(45.923, 2), ROUND(45.923, -1)
FROM dual;

SELECT  TRUNC(45.923), TRUNC(45.923, 0), TRUNC(45.923, 2), TRUNC(45.923, -1) 
FROM dual;

SELECT  MOD(123456, 2)
FROM dual;

SELECT  CEIL(123.123)
FROM dual;

SELECT  FLOOR(123.123)
FROM dual;

SELECT  ABS(-500)
FROM dual;

-- LOG N (�ڿ��α�)
SELECT  LN(10)
FROM dual;

SELECT  POWER(5, 2), SQRT(5), SIN(30), COS(30), TAN(30)
FROM dual;

-- ���������� �ּҰ� ��ȯ
SELECT  LEAST(10, 20, 30, 40)
FROM dual;

-- ���������� �ִ밪 ��ȯ
SELECT  GREATEST(10, 20, 30, 40)
FROM dual;

SELECT SYSDATE
FROM dual;

-- DATE Ÿ�Կ� ���� ����
SELECT SYSDATE - 1 "����" , SYSDATE "����", SYSDATE + 1 "����"
FROM dual;

-- ����� �ٹ� �ϼ� �˻�
SELECT first_name,  hire_date, SYSDATE, CEIL(SYSDATE - hire_date) "�ٹ��ϼ�"
FROM employees;

-- ����� �ٹ� ������ �˻�
SELECT first_name, TRUNC(MONTHS_BETWEEN(SYSDATE, hire_date))  "�ٹ�������"
FROM employees;

-- Ư���������� ���� ��¥ ��ȯ
SELECT SYSDATE, ADD_MONTHS(SYSDATE, 2) "���ú��� 2���� ��"
FROM dual;

-- �̹��� ����� ��¥
SELECT SYSDATE "����", NEXT_DAY(SYSDATE, 7) "�̹��� �����"
FROM dual;

-- �̹��� ������ ��¥
SELECT SYSDATE, LAST_DAY(SYSDATE) "�̹��� ��������"
FROM dual;

-- TO_DATE(colum | expression [, ����¥�������ġ�])
-- ��¥�� ����
SELECT TO_DATE('2011/12/31 18:45:23', 'YYYY/MM/DD HH24:MI:SS')
FROM dual;

SELECT first_name, hire_date
FROM employees
WHERE hire_date = TO_DATE('2003-06-17', 'YYYY-MM-DD');

SELECT first_name, hire_date
FROM employees
WHERE hire_date = TO_DATE(20030617, 'YYYY-MM-DD');

SELECT first_name, hire_date
FROM employees
WHERE hire_date = TO_DATE('2003/06/17', 'YYYY-MM-DD');

-- TO_NUMBER(colum | expression [,������������])
-- �������ڷ� ���������� ���ڿ��� �Է� �޾� ���� ������ ��ȯ�Ͽ� ��ȯ
SELECT TO_NUMBER('12345') + 1
FROM dual;

SELECT TO_NUMBER('12,345', '00,000') + 1
FROM dual;

SELECT TO_NUMBER('1000') + TO_NUMBER('2,000', '0,000') + 1
FROM dual;

-- TO_CHAR(colum | expression, �������������ġ� [, nls_parameter])
-- �������ڷ� ���ڳ� ��¥��  �Է� �޾� ���ڿ� ������ ��ȯ�Ͽ� ��ȯ


SELECT TO_CHAR(12345), TO_CHAR(12345.67)
FROM dual;

SELECT TO_CHAR(12345, '999,999'), TO_CHAR(12345.677, '999,999.99')
FROM dual;

SELECT TO_CHAR(12345, '000,000'), TO_CHAR(12345.677, '000,000.00')
FROM dual;

SELECT TO_CHAR(150, '$9999'), TO_CHAR(150, '$0000')
FROM dual;

SELECT TO_CHAR(150, 'S9999'), TO_CHAR(150, 'S0000')
FROM dual;

SELECT TO_CHAR(150, '9999MI'), TO_CHAR(-150, '9999MI')
FROM dual;

SELECT TO_CHAR(150, '9999EEEE'), TO_CHAR(150, '99999B')
FROM dual;

-- �θ��� ��(��)���ڷ� ǥ��
SELECT TO_CHAR(150, 'RN'), TO_CHAR(150, 'rn')
FROM dual;

-- 16���� ���ĺ� ��(��)���ڷ� ǥ��
SELECT TO_CHAR(10, 'X'), TO_CHAR(10, 'x'), TO_CHAR(15, 'X')
FROM dual;

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS DAY')
FROM dual;

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS DAY', 'NLS_DATE_LANGUAGE=KOREAN')
--SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS DAY', 'NLS_DATE_LANGUAGE=ENGLISH')
FROM dual;

-- �ʱ� �Ķ����(ȯ�漳��) ��� �˻�, ���α׷����� ȯ�� ���� ��ȸ *********
SELECT * FROM  NLS_SESSION_PARAMETERS;

SELECT first_name, hire_date, TO_CHAR(hire_date, 'YYYY-MM-DD HH24:MI')
FROM employees;

-- �Ի�⵵�� 2002�⵵�� �����
SELECT first_name, hire_date
FROM employees
WHERE TO_CHAR(hire_date, 'YYYY') = '2002';

SELECT 10 * NULL, 10 * NVL(NULL, 1)
FROM dual;

-- NVL
-- test NULL�� ��� ��� NULL�� ������ ��
SELECT first_name, 
          salary, 
          commission_pct, 
          ( salary + ( salary * commission_pct ) ) * 12 "����"
FROM   employees;

-- NULL�� ��� ��� 0���� ��ȯ�ؼ� ������ ��
SELECT first_name, 
          salary, 
          commission_pct, 
          ( salary + ( salary * NVL(commission_pct, 0) ) ) * 12 "����" 
FROM   employees;


-- DECODE (if, else�� ���� ������ �Լ��� ���)
SELECT first_name, 
          job_id, 
          salary, 
          DECODE(job_id, 'IT_PROG', salary * 1.5, 
                              'AC_MRG', salary * 1.3, 
                              'AC_ASST', salary * 1.1, 
                              salary) "�λ�ȱ޿�" 
FROM   employees;
