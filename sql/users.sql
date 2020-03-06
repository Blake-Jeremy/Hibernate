-- users sql database import
CREATE DATABASE IF NOT EXISTS `users`;
USE `users`;

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE IF NOT EXISTS `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` char(20) NOT NULL,
  `pword` char(20) NOT NULL,
  `manager_level` int(11) NOT NULL DEFAULT 0,
  `active` int(11) NOT NULL DEFAULT 1,
  `session` char(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

INSERT INTO `app_user` (`id`, `uname`, `pword`, `manager_level`, `active`, `session`) VALUES
	(37, 'sblake', 'pass123', 1, 1, ''),
	(38, 'eblake', 'pass234', 0, 1, ''),
	(39, 'bblake', 'pass345', 0, 1, ''),
	(40, 'ablake', 'pass456', 0, 1, ''),
	(41, 'jblake', 'pass567', 1, 1, '');

DROP TABLE IF EXISTS `phone_number`;
CREATE TABLE IF NOT EXISTS `phone_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO `phone_number` (`id`, `phone`) VALUES
	(1, '(120)345-6789'),
	(2, '(208)321-4567'),
	(3, '(201)456-0987');

DROP TABLE IF EXISTS `user_number`;
CREATE TABLE IF NOT EXISTS `user_number` (
  `user_id` int(11) NOT NULL,
  `phone_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

INSERT INTO `user_number` (`user_id`, `phone_id`, `id`) VALUES
	(37, 1, 1),
	(38, 3, 7),
	(38, 2, 8),
	(39, 1, 9),
	(40, 3, 10),
	(41, 2, 11);

