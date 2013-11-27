CREATE TABLE `tweet` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `tweet_id` bigint(11) NOT NULL,
  `text` varchar(500) NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `score` int(2) NOT NULL,
  `latitude` decimal(9,6) DEFAULT NULL,
  `longitude` decimal(9,6) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `actor` varchar(100) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `raw_text` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

