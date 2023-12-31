CREATE TABLE IF NOT EXISTS `circles`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `member_id` BIGINT NOT NULL,
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY (`member_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);