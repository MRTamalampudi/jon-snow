CREATE TABLE if NOT EXISTS `budget` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(255),
    `name` VARCHAR(255),
    `date` TIMESTAMP,
    `user_id` BIGINT,
    PRIMARY KEY (`id`),

    CONSTRAINT `fk_user_id_in_budget` FOREIGN KEY (`user_id`) REFERENCES users(id) ON DELETE CASCADE ON UPDATE NO ACTION
);