SELECT ID,name, birthday, levels, salary FROM worker
 WHERE salary = (SELECT MAX(salary) FROM worker);