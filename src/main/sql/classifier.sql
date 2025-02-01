USE `visio_db`;

DROP FUNCTION IF EXISTS priority_violation;
DELIMITER $$
CREATE FUNCTION priority_violation(
	total_occurrences DECIMAL,
    number_users DECIMAL,
	conformance_level VARCHAR(100)
)
RETURNS FLOAT(2)
DETERMINISTIC
BEGIN
	# DECLARE USED VARIABLES
	DECLARE var_conformance_level DECIMAL DEFAULT 0;
    DECLARE var_numerator DECIMAL DEFAULT 0;

	SELECT char_length(conformance_level) INTO var_conformance_level;
	SELECT (total_occurrences * number_users) INTO var_numerator;

	RETURN (var_numerator / var_conformance_level);
    RETURN 0.0;
END$$
DELIMITER ;

SELECT violation_type, conformance_level, developer_message, SUM(number_occurrences) AS total_occurrences, COUNT(device_id) AS users, priority_violation(SUM(number_occurrences), COUNT(device_id), conformance_level) AS final_priority
FROM tb_violation
GROUP BY violation_type, conformance_level, developer_message
ORDER BY final_priority DESC;