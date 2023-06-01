SELECT project.id, SUM(worker.salary *
((DATEDIFF('YEAR', project.start_date, project.finish_date) * 12) +
EXTRACT(MONTH FROM project.finish_date) - EXTRACT(MONTH FROM project.start_date))) AS project_cost
 FROM project
 JOIN project_worker ON project.id = project_worker.project_id
 JOIN worker ON project_worker.worker_id = worker.id
 GROUP BY project.id
 ORDER BY project_cost DESC;