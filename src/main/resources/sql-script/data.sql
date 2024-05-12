CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `username_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into users values('mike','{bcrypt}$2a$12$EC487ovcs8YkXi1BE4fF3eOk06JiTTY.r6GPQ/PodEnYajiLADpMO',1),
('jane','{bcrypt}$2a$12$EC487ovcs8YkXi1BE4fF3eOk06JiTTY.r6GPQ/PodEnYajiLADpMO',1),
('john','{bcrypt}$2a$12$EC487ovcs8YkXi1BE4fF3eOk06JiTTY.r6GPQ/PodEnYajiLADpMO',1);


insert into
authorities values('mike','ROLE_ADMIN'),('mike','ROLE_EMPLOYEE'),('jane','ROLE_MANAGER'),('jane','ROLE_EMPLOYEE'),('john','ROLE_EMPLOYEE');