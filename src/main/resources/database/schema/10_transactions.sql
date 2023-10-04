CREATE TABLE IF NOT EXISTS `transactions`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `note` VARCHAR(255),
    `amount` DECIMAL(10,2) NOT NULL,
    `transaction_type`  VARCHAR(255),
    `date` TIMESTAMP NOT NULL,
    `payment_mode` VARCHAR(255),
    `user_id` BIGINT NOT NULL,
    `split_bill_id` BIGINT,
    `budget_id` BIGINT,
    `transactee_id` BIGINT,
    `budget_item_id` BIGINT,

    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(`split_bill_id`) REFERENCES split_bills(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(`budget_id`) REFERENCES budget(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(`budget_item_id`) REFERENCES budget_items(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(`transactee_id`) REFERENCES transactees(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);