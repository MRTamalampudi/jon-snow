CREATE TABLE IF NOT EXISTS `group_members`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `member_id` BIGINT NOT NULL,
    `owe_share` DECIMAL(10,2),
    `lent_share` DECIMAL(10,2),
    `group_id` BIGINT NOT NULL,

    PRIMARY KEY(`id`),
    FOREIGN KEY(`member_id`) REFERENCES users(`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(`group_id`) REFERENCES split_bill_groups(`id`) ON DELETE CASCADE ON UPDATE NO ACTION
);