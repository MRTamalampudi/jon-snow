CREATE TABLE IF NOT EXISTS `split_bill_groups`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `avatar` TEXT,
    `date` TIMESTAMP,
    `created_by` BIGINT NOT NULL,
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,

    PRIMARY KEY(`id`),
    FOREIGN KEY(`created_by`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);