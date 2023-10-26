create table if not exists `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255),
    `last_name` varchar(255) ,
    `email` varchar(255),
    `created_date` TIMESTAMP,
    `last_modified_date` TIMESTAMP,
    `password` char(255),

    primary key (`id`),
    unique(`email`)
);