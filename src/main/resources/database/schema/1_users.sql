create table if not exists `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255),
    `last_name` varchar(255) ,
    `email` nvarchar(255),
    `mobile` nvarchar(255),

    primary key (`id`)
);