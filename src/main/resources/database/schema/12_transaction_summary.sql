CREATE TABLE `transaction_summary` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `cash_in` decimal(10,2) DEFAULT NULL,
    `cash_out` decimal(10,2) NOT NULL,
    `owe` decimal(10,2) NOT NULL,
    `lent` decimal(10,2) DEFAULT NULL,
    `user_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);