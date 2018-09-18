-- 서브(내부) 쿼리 *********************************
-- 동적인 값으로 하기 위해서
-- 서브쿼리를 사용하지 않을 경우...
SELECT salary 
FROM   employees 
WHERE  Lower(last_name) = 'seo'; 

SELECT * 
FROM   employees 
WHERE  salary = 2700; 

-- 단일행 서브쿼리 (결과값이 한 개만 있기 때문) (=, <, >, <=, >=, <>(!=)) **
-- 서브쿼리만 실행하고 싶은 경우 블록을 잡고 F5 누르면 미리 알 수 있다.
SELECT * 
FROM   employees 
WHERE  salary = (SELECT salary 
                 FROM   employees 
                 WHERE  Lower(last_name) = 'seo') 
       AND Lower(last_name) != 'seo'; 

-- 전체사원 평균급여보다 더 많은 급여를 받는 사원 목록 조회
SELECT * 
FROM   employees 
WHERE  salary > (SELECT AVG(salary) 
                      FROM    employees);
       
-- 복수행 서브쿼리 (IN, ANY, ALL) ** 

-- IN 연산자 활용
-- 30번 부서에 소속된 사원들과 동일한 업무를 가지는 전체 사원목록 조회
SELECT last_name, 
          job_id, 
          department_id 
FROM   employees 
WHERE  job_id IN (SELECT job_id 
                       FROM   employees 
                       WHERE  department_id = 30);

-- ANY 연산자 활용
-- 30번 부서의 최소급여자 보다  더 많은 급여를 받는 전체 사원목록 조회
SELECT * 
FROM   employees 
WHERE  salary > ANY (SELECT salary 
                             FROM   employees 
                             WHERE  department_id = 30);

-- ALL 연산자 활용 
-- 30번 부서의 최대급여자 보다 더 많은 급여를 받는 전체 사원목록 조회
SELECT * 
FROM   employees 
WHERE  salary < ALL (SELECT salary 
                            FROM   employees 
                            WHERE  department_id = 30);
                            
-- 다중컬럼 서브쿼리 ** 
-- 부서별 최소급여자 정보 
SELECT * 
FROM   employees 
WHERE  ( department_id, salary ) IN(SELECT department_id, 
                                           Min(salary) 
                                    FROM   employees 
                                    GROUP  BY department_id) 
ORDER  BY department_id;

-- 서브쿼리와 가상(Pseudo)컬럼 활용 *****
--    ROWID, ROWNUM (SQL은 LIMIT 가능하지만 Oracle은 없다.)
--      테이블의 물리적 컬럼이 아닌 SQL문에서 사용할 수 있는 가상컬럼이다.
--      ROWNUM은 SELECT문이 실행되는 과정에서 행이  인출(Fetch)된 후에 순차적으로 부여되는 동적 일련번호(1, 2, 3..)이다.
--      ROWNUM은 실행 시마다 동적 생성!!되기 때문에 테이블의 같은 행이라도 서로 다른 값을 가질 수 있다.
--      ROWID는 index에서 사용

-- 가상컬럼(ROWID, ROWNUM) 
SELECT rowid, 
       rownum, 
       employee_id, 
       last_name 
FROM   employees; 

-- 테이블의 같은 행이라도 서로 다른 ROWNUM을 가질 수 있다 
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

-- 첫번째 행의 rownum이 1이므로 
-- 1 > 1은 false가 되어 rownum은 더이상 증가하지 않으며, 하나의 행도 반환되지 않음 
SELECT * 
FROM   employees 
WHERE  rownum > 1; 

-- 첫번째 10개행(범위)만을 조회할 경우
-- 첫번째 행의 rownum이 1이므로
-- 1 <= 10은 true가 되어 첫번째 행에 1이 할당되고 rownum은 2로 증가
SELECT *
FROM   employees
WHERE  ROWNUM <= 10;
 
/* 특정 컬럼을 기준으로 정렬하여 상위 5개(범위)를 조회하고자 한다면 */ 
-- 예)전체 사원의 급여순으로 5명 가져오기 
-- 전체 급여 순위가 아닌 처음 5명안에서의 급여순위가 됨 
SELECT first_name, 
       salary 
FROM   employees 
WHERE  rownum <= 5 
ORDER  BY salary DESC; 

-- FROM절에서 서브쿼리(Inline View)를 사용해야 한다 (ORDER BY는 무조건 마지막에 써야 함) > 상위5명 가져오기
SELECT * 
FROM   (SELECT * 
        FROM   employees 
        ORDER  BY salary DESC) 
WHERE  rownum <= 5; 

-- 급여순으로 10 ~ 20 사이 (이 방법 말고도 HINT 사용 가능)
SELECT page, 
       employee_id, 
       first_name, 
       salary 
-- rownum을 통해 10단위로 자름
FROM   (SELECT Ceil(rownum / 10) page, 
               employee_id, 
               first_name, 
               salary 
       -- 상위레벨로 order      
        FROM   (SELECT rownum,  
                       employee_id, 
                       first_name, 
                       salary 
                FROM   employees 
                ORDER  BY salary DESC)) 
WHERE  page = 2; 

