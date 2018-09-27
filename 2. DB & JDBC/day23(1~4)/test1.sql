desc employees;

--1. employees ���̺��� �޿��� 5000�̻� 15000���� ���̿� ���Ե��� �ʴ� ����� �����ȣ(employee_id), �̸�(last_name), �޿�(salary), �Ի�����(hire_date)�� ��ȸ�Ͻÿ�.
SELECT employee_id AS �����ȣ, 
       last_name   AS �̸�, 
       salary      "�޿�", 
       hire_date   "�Ի�����" 
FROM   employees 
WHERE  salary NOT BETWEEN 5000 AND 15000;  

--2. �μ���ȣ(department_id) 50, ����(job_id) 'ST_MAN', �Ի��� 2004-7-18���� ����� �����ȣ, �̸�, ����, �Ի����� ��ȸ�Ͻÿ�.
SELECT employee_id "�����ȣ", 
       last_name   "�̸�", 
       job_id      "����", 
       hire_date   "�Ի���" 
FROM   employees 
WHERE  department_id = 50 
       AND job_id = 'ST_MAN' 
       AND hire_date = '2004-7-18'; 

--3. 2002�� ���� �Ի��� ����߿� ���('ST_MAN', 'ST_CLERK')������ ����ϴ� ������� ��� �÷��� ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  job_id IN( 'ST_MAN', 'ST_CLERK' ) 
       AND hire_date >= '2002/01/01'; 
       
--4. ���(manager_id)�� ���� ����� ��� �÷��� ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  manager_id IS NULL; 

--5. �޿��� 10000 �̸��� ����߿��� ���('SH_CLERK')�̳� ����('PU_MAN', 'PU_CLERK')������ ����ϴ� ������� ��� �÷��� ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  salary < 10000 
       AND job_id IN( 'SH_CLERK', 'PU_MAN', 'PU_CLERK' ); 
       
--6. ����̸�(last_name)�� J, A �Ǵ� M���� �����ϴ� ��� ����� �̸�(ù ���ڴ� �빮�ڷ�, ������ ���ڴ� �ҹ��ڷ� ǥ��) �� �̸� ���̸� ��ȸ�Ͻÿ�(��, last_name ���������� ���� ����).
SELECT Initcap(last_name) "����̸�", 
       Length(last_name)  "�̸� ����" 
FROM   employees 
WHERE  last_name LIKE 'J%' 
        OR last_name LIKE 'A%' 
        OR last_name LIKE 'M%' 
ORDER  BY last_name ASC; 

--7. �⵵�� �Ի��ο����� ��ȸ�Ͻÿ�.
SELECT To_char(hire_date, 'YYYY'), 
       Count(employee_id) 
FROM   employees 
GROUP  BY To_char(hire_date, 'YYYY') 
ORDER  BY To_char(hire_date, 'YYYY'); 

--8. �����ȣ(employee_id)�� Ȧ���� ����� ��� ������ ��ȸ�Ͻÿ�.
SELECT * 
FROM   employees 
WHERE  MOD(employee_id, 2) = 1; 

--9.���ú��� 6���� �� ���ƿ��� ù��° �ݿ����� ��¥�� ����Ͻÿ�.
--   (��¥ ���� ��: 2002-06-05 15:23:10 �ݿ���)
SELECT To_char(Next_day(Add_months(SYSDATE, 6), 6), 'YYYY-MM-DD HH24:MI:SS DAY') 
       "6���� �� �ݿ���" 
FROM   dual; 

--10.�����ȣ(employee_id), �����(first_name), ����ȣ(manager_id)�� ��ȸ�ϵ�
--   ��簡 ����(NULL) ��� ����ȣ�� '�뻧'�̶� ����Ͻÿ�. 
SELECT employee_id                                "�����ȣ", 
       first_name                                 "�����", 
       Decode(manager_id, NULL, '�뻧', 
                          manager_id, manager_id) "����ȣ" 
FROM   employees; 

--11.������� 3���౸������ �з��ϱ� ���� ����� 3���� ������
--   �������� 0�̸� "��ȭ�����"
--   �������� 1�̸� "���׸���"
--   �������� 2�̸� "������"���� �з��Ͽ� ���̸�, �����ȣ, ������� ����Ͻÿ�.
SELECT Decode(MOD(employee_id, 3), 0, '��ȭ�����', 
                                   1, '���׸���', 
                                   2, '������') "���̸�", 
       employee_id                                 "�����ȣ", 
       last_name                                   "�����" 
FROM   employees; 
      

--12.����� �޿��׷����� �Ʒ��� ���� ����Ͻÿ�.
--   Steven King     *****($5,000) // $1000�޷��� �� 1���߰�.
--   Neena Kochhar   ***($3,000)--    .........
--   XXXX XXXXX      *****************($17,000)
SELECT first_name || ' ' ||last_name "�̸�", 
   LPAD('(' || TRIM(to_char(salary,'$99,999B')) ||')', salary/1000 + 8, '*')
FROM  employees; 
-- LENGTH('(' || TRIM(to_char(salary,'$99,999B')) ||')') = 8

--13.2002�� 3������ 2003�� 2�� �Ⱓ ���� �Ի��� ����� ������� �μ��� �ο����� ��ȸ�Ͻÿ�.
--   (����� �ο����� ���� ������� �����Ͽ� ���)
SELECT department_id        "�μ�", 
       Count(department_id) "�μ��� �ο���" 
FROM   employees 
WHERE  To_char(hire_date) BETWEEN '02/01/01' AND '03/02/29' 
GROUP  BY department_id 
ORDER  BY Count(department_id) DESC; 

--14.������ ��� �޿��� ����϶�. ��, ��ձ޿��� 10000�� �ʰ��ϴ� ���� �����ϰ� ��� �޿��� ���� ������������ �����Ͻÿ�.
SELECT job_id      "����", 
       Avg(salary) "��ձ޿�" 
FROM   employees 
GROUP  BY job_id 
HAVING Avg(salary) <= 10000 
ORDER  BY Avg(salary) DESC; 

--15.2004�⿡ �Ի��� ������� ������κ��� �б⺰ �Ի����� ���� �Ʒ��� ���� ����Ͻÿ�.(��Ʈ: ���˹��� Q Ȱ��)
--�б�   �����
-----------------
--1      3
--2      1
--3      2
SELECT To_char(hire_date, 'Q') "�б�", 
       Count(*)                "�����" 
FROM   employees 
WHERE  To_char(hire_date, 'yy') = 04 
GROUP  BY To_char(hire_date, 'Q'); 

select To_date('02-01-02', 'Q')
from dual;