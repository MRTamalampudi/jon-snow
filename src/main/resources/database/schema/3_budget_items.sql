CREATE TABLE IF NOT EXISTS `budget_items`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `item` VARCHAR(255) NOT NULL,
    `amount` DECIMAL(10,2) NOT NULL,
    `budget_id` BIGINT NOT NULL,
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`budget_id`) REFERENCES budget(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);