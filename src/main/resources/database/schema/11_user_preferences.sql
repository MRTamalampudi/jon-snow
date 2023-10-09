CREATE TABLE if NOT EXISTS `user_preferences` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `dark_mode` BIT(1) DEFAULT 0,
    `currency` VARCHAR(255) DEFAULT "INR",
    `user_id` BIGINT,
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (`user_id`) REFERENCES users(id) ON DELETE CASCADE ON UPDATE NO ACTION
);