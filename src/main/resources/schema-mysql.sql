# Role table creation
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `role_id` int(11) NOT NULL AUTO_INCREMENT,
                        `role` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `role` (`role_id`, `role`) VALUES (1,'ADMIN');
INSERT INTO `role` (`role_id`, `role`) VALUES (2,'USER');

UNLOCK TABLES;

DROP TABLE IF EXISTS `usersEntity`;

CREATE TABLE `usersEntity` (
                        `user_id` int(11) NOT NULL AUTO_INCREMENT,
                        `active` int(11) DEFAULT NULL,
                        `email` varchar(255) NOT NULL,
                        `last_name` varchar(255) NOT NULL,
                        `name` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `usersEntity` WRITE;

INSERT INTO `usersEntity` (`user_id`, `active`, `email`, `last_name`, `name`, `password`)
VALUES
(1,1,'admin@gmail.com','lastAdmin','admin','admin'),
(2,1,'user@gmail.com','lastUser','user','user');


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `user_id` int(11) NOT NULL,
                             `role_id` int(11) NOT NULL,
                             PRIMARY KEY (`user_id`,`role_id`),
                             CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `usersEntity` (`user_id`),
                             CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
LOCK TABLES `user_role` WRITE;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1,1);