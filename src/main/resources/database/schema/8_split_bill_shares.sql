CREATE TABLE IF NOT EXISTS `split_bill_shares`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `transaction_type` VARCHAR(20) NOT NULL,
    `amount` DECIMAL(10,2),
    `status` VARCHAR(10),
    `user_id` BIGINT NOT NULL,
    `split_bill_id` BIGINT NOT NULL,
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,

    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(`split_bill_id`) REFERENCES split_bills(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);