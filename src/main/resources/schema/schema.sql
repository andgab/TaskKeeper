CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `work_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `assigned_to_user_id` bigint(20) DEFAULT NULL,
  `do_date` date DEFAULT NULL,
  `done_date` date DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`assigned_to_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

