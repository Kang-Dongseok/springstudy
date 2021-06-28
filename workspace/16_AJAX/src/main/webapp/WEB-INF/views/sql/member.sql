DROP TABLE MEMBER;
CREATE TABLE MEMBER
(
	NO NUMBER PRIMARY KEY,
	ID VARCHAR2(32) NOT NULL UNIQUE,
	NAME VARCHAR2(64),
	ADDRESS VARCHAR2(100),
	GENDER VARCHAR2(10)
);

DROP SEQUENCE MEMBER_SEQ;
CREATE SEQUENCE MEMBER_SEQ INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;
select * from member;