SELECT client.id, client.name, COUNT(project.client_id) AS project_count
 FROM client
 LEFT JOIN project ON client.id = project.client_id
 GROUP BY client.id
 HAVING COUNT(project.client_id) = (
    SELECT COUNT(client_id)
    FROM project
    GROUP BY client_id
    ORDER BY COUNT(client_id) DESC
    LIMIT 1
);