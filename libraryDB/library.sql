DROP TABLE IF EXISTS member_list;

------------------------------------------------------------
----------------------------member_list
------------------------------------------------------------
CREATE TABLE member_list
(
  member_id SERIAL PRIMARY KEY,
  name TEXT,
  address TEXT,
  tel TEXT,
  email TEXT,
  birthday DATE,
  joined_date DATE,
  withdraw_date DATE
);



-------------------------------------------------------------
----------------------member_listのデータ
-------------------------------------------------------------
INSERT INTO member_list(name, address, tel, email, birthday, joined_date, withdraw_date)
VALUES('新宿太郎','〒160ー0000 新宿区', '03-0000-0000', 'a@example.com', '1980-01-01', '2010-01-01', '2010-02-01');

INSERT INTO member_list(name, address, tel, email, birthday, joined_date, withdraw_date)
VALUES('渋谷地下','〒160ー0000 新宿区', '03-0000-1111', 'b@example.com', '1983-01-01', '2010-01-01', NULL);

INSERT INTO member_list(name, address, tel, email, birthday, joined_date, withdraw_date)
VALUES ('山田花子','〒160ー0000 新宿区', '03-0000-2222', 'c@example.com', '1984-01-01', '2010-03-01', NULL);
