/*
DRIVER:	org.sqlite.JDBC
URL: 	jdbc:sqlite:C:/Users/SOA_PC/Desktop/SOA_Labs/External_Services/DBs/StarkSystem.db

*/

CREATE TABLE PEDIDOS (
   CODIGO               INTEGER       PRIMARY KEY AUTOINCREMENT,
   CLIENTE              VARCHAR (40)  NOT NULL,
   ENDERECO             VARCHAR (100) NOT NULL,
   ESTADO               VARCHAR (20)  NOT NULL,
   QTD_REATOR_SOLAR     INTEGER       NOT NULL,
   QTD_REATOR_ARK       INTEGER       NOT NULL,
   TOTAL_REATOR_SOLAR   REAL          NOT NULL,
   TOTAL_REATOR_ARK     REAL          NOT NULL,
   TOTAL                REAL          NOT NULL
);