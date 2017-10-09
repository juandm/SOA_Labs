/*
DRIVER:	org.sqlite.JDBC
URL: 	jdbc:sqlite:C:/Users/SOA_PC/Desktop/SOA_Labs/External_Services/DBs/BrRobotics.db

*/

CREATE TABLE PEDIDOS (
    CODIGO               INTEGER       PRIMARY KEY AUTOINCREMENT,
    CLIENTE              VARCHAR (40)  NOT NULL,
    ENDERECO             VARCHAR (100) NOT NULL,
    ESTADO               VARCHAR (20)  NOT NULL,
    QTD_ROBO_SEGURANCA   INTEGER       NOT NULL,
    QTD_ROBO_DOMESTICO   INTEGER       NOT NULL,
    QTD_ROBO_MEDICO      INTEGER       NOT NULL,
    TOTAL_ROBO_SEGURANCA REAL          NOT NULL,
    TOTAL_ROBO_DOMESTICO REAL          NOT NULL,
    TOTAL_ROBO_MEDICO    REAL          NOT NULL,
    TOTAL                REAL          NOT NULL
);