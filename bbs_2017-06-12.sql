# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.16)
# Database: bbs
# Generation Time: 2017-06-12 12:43:53 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table class
# ------------------------------------------------------------

DROP TABLE IF EXISTS `class`;

CREATE TABLE `class` (
  `classID` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `classTeacherID` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `classStudentID` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `classTime` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `className` varchar(11) NOT NULL DEFAULT '',
  PRIMARY KEY (`classID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;

INSERT INTO `class` (`classID`, `classTeacherID`, `classStudentID`, `classTime`, `className`)
VALUES
	('c001','201','101','Mon12','JavaWEB'),
	('c002','202','101','Fri34','C++'),
	('c003','202','101','Tue78','C'),
	('c004','201','102','Wed12','Java');

/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `replyID` varchar(11) NOT NULL,
  `postID` varchar(11) NOT NULL,
  `floor` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `content` varchar(300) NOT NULL DEFAULT '',
  `replyUserID` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `replyDate` varchar(50) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;

INSERT INTO `post` (`replyID`, `postID`, `floor`, `content`, `replyUserID`, `replyDate`)
VALUES
	('p001f1','p001','1','哇JavaWEB这么难怎么办啊。。。','101','2017-06-11 20:21:28'),
	('p001f2','p001','2','兰州烧饼,这么简单，好好看书。','201','2017-06-11 22:08:47'),
	('p002f1','p002','1','123412','101','2017-06-12 09:22:20');

/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table postInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `postInfo`;

CREATE TABLE `postInfo` (
  `postID` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `classID` varchar(11) NOT NULL,
  `userID` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `sendDate` varchar(50) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `updateDate` varchar(50) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `isTop` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `title` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`postID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `postInfo` WRITE;
/*!40000 ALTER TABLE `postInfo` DISABLE KEYS */;

INSERT INTO `postInfo` (`postID`, `classID`, `userID`, `sendDate`, `updateDate`, `isTop`, `title`)
VALUES
	('p001','c001','101','2017-06-11 20:21:28','2017-06-11 22:08:47','no','JavaWEB好难啊'),
	('p002','c001','101','2017-06-12 09:22:20','2017-06-12 09:22:20','no','123');

/*!40000 ALTER TABLE `postInfo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `account` varchar(11) CHARACTER SET latin1 NOT NULL,
  `password` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `nickName` varchar(11) NOT NULL DEFAULT '',
  `question` varchar(11) NOT NULL DEFAULT '',
  `answer` varchar(11) NOT NULL DEFAULT '',
  `type` varchar(11) CHARACTER SET latin1 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`userID`, `account`, `password`, `nickName`, `question`, `answer`, `type`)
VALUES
	('101','wgg','wgg','白银V强者','333','444','student'),
	('201','lqy','lqy','世外高人','111','222','teacher'),
	('301','hwd','hwd','少年游','hehe','haha','admin'),
	('102','MYZ','myz','阿毛','阿毛多少重？','200斤','student');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table userInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `userInfo`;

CREATE TABLE `userInfo` (
  `userID` varchar(11) NOT NULL DEFAULT '',
  `userClass` varchar(11) NOT NULL DEFAULT '',
  `userName` varchar(11) NOT NULL DEFAULT '',
  `userPhone` varchar(11) NOT NULL DEFAULT '',
  `userEmail` varchar(11) NOT NULL DEFAULT '',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

LOCK TABLES `userInfo` WRITE;
/*!40000 ALTER TABLE `userInfo` DISABLE KEYS */;

INSERT INTO `userInfo` (`userID`, `userClass`, `userName`, `userPhone`, `userEmail`)
VALUES
	('101','XinJi142','王冠冠','421','1234'),
	('102','XinJi142','毛宇泽','21512','214215'),
	('201','XinJi141','刘启玉','555','4567'),
	('301','XinJi142','胡文笛','138','1023');

/*!40000 ALTER TABLE `userInfo` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
