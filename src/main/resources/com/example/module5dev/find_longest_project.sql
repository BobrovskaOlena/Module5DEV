SELECT id,
       (EXTRACT(YEAR FROM p.finish_date) - EXTRACT(YEAR FROM p.start_date)) * 12
       + (EXTRACT(MONTH FROM p.finish_date) - EXTRACT(MONTH FROM p.start_date)) AS months
       FROM project p WHERE (EXTRACT(YEAR FROM p.finish_date) - EXTRACT(YEAR FROM p.start_date)) * 12
      + (EXTRACT(MONTH FROM p.finish_date) - EXTRACT(MONTH FROM p.start_date)) IN (
          SELECT (EXTRACT(YEAR FROM p2.finish_date) - EXTRACT(YEAR FROM p2.start_date)) * 12
                 + (EXTRACT(MONTH FROM p2.finish_date) - EXTRACT(MONTH FROM p2.start_date)) AS months
          FROM project p2
                   ORDER BY months DESC
          LIMIT 1
      );