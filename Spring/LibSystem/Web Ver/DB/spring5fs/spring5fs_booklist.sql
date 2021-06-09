-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: spring5fs
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booklist`
--

DROP TABLE IF EXISTS `booklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `booklist` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_bin NOT NULL,
  `author` varchar(45) COLLATE utf8_bin NOT NULL,
  `publisher` varchar(45) COLLATE utf8_bin NOT NULL,
  `rent` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booklist`
--

LOCK TABLES `booklist` WRITE;
/*!40000 ALTER TABLE `booklist` DISABLE KEYS */;
INSERT INTO `booklist` VALUES (1,'아몬드','손원평','창비','ay'),(2,'방구석 미술관','조원재','블랙피쉬','ay'),(3,'푸른 사자 와니니','이현','창비',NULL),(4,'꽃을 보듯 너를 본다','나태주','지혜',NULL),(5,'지리의 힘','팀 마샬','사이',NULL),(6,'페인트','이희영','창비',NULL),(7,'부의 인문학','브라운스톤','오픈마인드',NULL),(8,'체리새우','황영미','문학동네',NULL),(9,'나미야 잡화점의 기적','히가시노 게이고','현대문학',NULL),(10,'아홉 살 마음 사전','박성우','창비',NULL),(11,'총, 균, 쇠','재레드 다이아몬드','문학사상',NULL),(12,'불량한 자전거 여행','김남중','창비',NULL),(13,'시간을 파는 상점','김선영','자음과 모음',NULL),(14,'우리는 언제나 다시 만나','윤여림','위즈덤하우스',NULL),(15,'페스트','알베르 까뮈','민음사',NULL),(16,'선량한 차별주의자','김지혜','창비',NULL),(17,'정의란 무엇인가','마이클 샌델','와이즈베리',NULL),(18,'기억 전달자','로이스 로리','비룡소',NULL),(19,'한밤중 달빛 식당','이분희','비룡소',NULL),(20,'아주 작은 습관의 힘','제임스 클리어','비즈니스북스',NULL),(21,'설민석의 조선왕조실록','설민석','세계사',NULL),(22,'미라클 모닝','할 엘로드','한빛비즈',NULL),(23,'수박 수영장','안녕달','창비',NULL),(24,'오은영의 화해','오은영','코리아닷컴',NULL),(25,'데미안','헤르만 헤세','민음사',NULL),(26,'달 샤베트','백희나','책읽는곰',NULL),(27,'여행의 이유','김영하','문학동네',NULL),(28,'잘못 뽑은 반장','이은재','주니어김영사',NULL),(29,'부의 본능','브라운스톤','토트출판사',NULL),(30,'동물농장','조지오웰','민음사',NULL);
/*!40000 ALTER TABLE `booklist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-07 12:16:31
