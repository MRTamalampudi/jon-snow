CREATE TABLE IF NOT EXISTS `split_bills`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `bill` VARCHAR(255),
    `amount` DECIMAL(10,2) NOT NULL,
    `split_algo` VARCHAR(255) NOT NULL,
    `date` TIMESTAMP NOT NULL,
    `split_bill_group_id` BIGINT NOT NULL,
    `paid_by` BIGINT NOT NULL,
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,

    PRIMARY KEY(`id`),
    FOREIGN KEY(`split_bill_group_id`) REFERENCES split_bill_groups(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(`paid_by`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);