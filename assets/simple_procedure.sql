/* @file: simple_procedure.sql
 * @desc: Do something common. (Not part of the assignment but should totally be part of curriculum!)
 */

USE `coding_events`;
DROP procedure IF EXISTS `drop_coding_events`;

DELIMITER $$
USE `coding_events`$$
CREATE PROCEDURE `drop_coding_events` ()
BEGIN
DROP TABLE IF EXISTS coding_events.hibernate_sequence;
DROP TABLE IF EXISTS coding_events.event;
DROP TABLE IF EXISTS coding_events.event_category;
END$$

DELIMITER ;

/* Later you can use this call statement */
/* CALL coding_events.drop_coding_events(); */