/* @file types_to_categories.sql
 * @desc These are the queries we can use in 18.2.3 when we migrate from types to categories.
 * NOTE: Hibernate will generate tables, but we will still need to manually edit them (for now) to update tables.
 */

/* See what is each of our tables */
SELECT * FROM coding_events.event;
SELECT * FROM coding_events.event_category;

/* See the columns in the coding_events.event table. */
SHOW COLUMNS FROM coding_events.event;
/* DESCRIBE is the same as SHOW COLUMNS FROM */
DESCRIBE coding_events.event;
/* PRI indicates a primary key
 * UNI indicates a unique key
 * MUL indicates a non-unique key. Foreign keys are represented with this.
 */

/* TODO: It might have been better to assign the value from type to category_id first. */
UPDATE coding_events.event SET category_id = type WHERE category_id = 0;
/* Remove the type column from the coding events table. */
ALTER TABLE coding_events.event DROP COLUMN type;

/* Alternatively, we could just blank out both tables and start from scratch.
 * This will allow us to blank out hte rows that are considered constrain violations.
 * We still need to run that ALTER command though.
 */
TRUNCATE coding_events.event;
TRUNCATE coding_events.event_category;

/* After going through hell...Just drop the tables! */
DROP TABLE IF EXISTS coding_events.hibernate_sequence;
DROP TABLE IF EXISTS coding_events.event;
DROP TABLE IF EXISTS coding_events.event_category;