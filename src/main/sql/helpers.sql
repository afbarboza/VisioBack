USE `visio_db`;

/* number of violations grouped by selection criteria and total number of occurences */
SELECT violation_type, conformance_level, developer_message, SUM(number_occurrences), COUNT(device_id)
FROM tb_violation
GROUP BY violation_type, conformance_level, developer_message;

/* number of distinct users */
SELECT device_id FROM tb_violation GROUP BY device_id;

/* maps the conformance level into an integer value */
SELECT conformance_level, char_length(conformance_level) FROM tb_violation GROUP BY conformance_level;