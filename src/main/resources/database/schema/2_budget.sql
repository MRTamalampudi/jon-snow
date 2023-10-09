CREATE TABLE if NOT EXISTS `budget` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255),
    `name` VARCHAR(255),
    `date` TIMESTAMP,
    `user_id` BIGINT NOT NULL,
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);