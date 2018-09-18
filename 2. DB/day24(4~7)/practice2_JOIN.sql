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
-- 오라클 EQUI JOIN 구문
SELECT e.employee_id, 
       e.last_name, 
       d.department_name 
FROM   employees e, 
       departments d 
WHERE  e.department_id = d.department_id 
       AND e.salary >= 3000; 

-- ANSI 표준 EQUI JOIN 구문
SELECT e.employee_id, 
       e.last_name, 
       d.department_name 
FROM   employees e 
       JOIN departments d 
         ON e.department_id = d.department_id 
        -- using(department_id)  
WHERE  e.salary >= 3000; 

-- 3개이상 테이블 조인
-- Dictionary 테이블로부터 테이블 종류 조히
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
         ON e.department_id = d.department_id --부서
       JOIN locations l 
         ON d.location_id = l.location_id     --지역
       JOIN countries c 
         ON l.country_id = c.country_id;      --수도

-- NON-EQUI  JOIN(범위로 Join)
-- 공통 컬럼이 없는 테이블과 테이블간 조인
-- 조인 조건에서 ‘=’ 연산자를 이용한 동등비교가 아닌 다른 비교연산자를 사용하여 특정범위로 행과 행을 연결하여 조인
-- 사원 급여에 따른 급여등급 출력

-- 오라클 NON-EQUI JOIN(참고 : 연습용, 논리적으로는 맞지 않음)
-- 각 직업의 연봉 min, max로 연봉이 어떤 직업에 소속하는 지 돌려보기
SELECT e.employee_id, 
       e.last_name, 
       e.salary, 
       j.job_title 
FROM   employees e, 
       jobs j 
WHERE  e.salary BETWEEN j.min_salary AND j.max_salary 
ORDER  BY e.employee_id; 

-- Outer join
-- INNER JOIN 시 조인 조건을 만족하지 않는 행은 검색되지 않는다.
-- 조인 조건을 만족하지 않는 행들도 검색에 포함하고자 할 경우 사용한다.
-- employees 테이블에에서 부서번호가 NULL 인 Kimberely는 
-- EQUI Join만으로는 검색되지 않음
SELECT e.first_name, 
       d.department_name 
FROM   employees e, 
       departments d 
WHERE  e.department_id = d.department_id; 

-- 소속부서가 없는 사원도 출력!
-- 오라클 OUTER JOIN
SELECT e.employee_id, 
       e.first_name, 
       e.last_name, 
       d.department_name 
FROM   employees e, 
       departments d 
WHERE  e.department_id = d.department_id(+);  -- + : outer

-- ANSI 표준 OUTER JOIN(LEFT, RIGHT, FULL 예약어 사용)
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
--같은 테이블에 별칭을 부여하여 또 다른 테이블처럼 조인하는 것
--사원 별 상사(매니저) 이름 검색 시 SELF JOIN 필요

-- 사원별 상사 검색 
SELECT employee.first_name, 
       manager.first_name 
FROM   employees employee, 
       employees manager 
WHERE  employee.manager_id = manager.employee_id; 

-- 상사가 없는 사원도 검색 시 OUTER JOIN 필요 (oracle)  
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
                    
                    