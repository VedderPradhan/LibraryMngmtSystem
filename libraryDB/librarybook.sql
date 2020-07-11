DROP TABLE IF EXISTS book_category;
DROP TABLE IF EXISTS book_master;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS lend_records;

CREATE TABLE lend_records
(
  record_id SERIAL PRIMARY KEY,
  member_id INTEGER,
  book_id INTEGER,
  lend_date DATE,
  due_date DATE,
  return_date DATE,
  remarks TEXT
);


CREATE TABLE book_category
(
  code INTEGER PRIMARY KEY,
  name TEXT
);

INSERT INTO book_category VALUES(0, '総記');
INSERT INTO book_category VALUES(1, '哲学');
INSERT INTO book_category VALUES(2, '歴史');
INSERT INTO book_category VALUES(3, '社会科学');
INSERT INTO book_category VALUES(4, '自然科学');
INSERT INTO book_category VALUES(5, '技術');
INSERT INTO book_category VALUES(6, '産業');
INSERT INTO book_category VALUES(8, '言語');
INSERT INTO book_category VALUES(7, '芸術');
INSERT INTO book_category VALUES(9, '文学');

CREATE TABLE book_master
(
  isbn TEXT PRIMARY KEY,
  title TEXT,
  category_code INTEGER,
  author TEXT,
  publisher TEXT,
  publish_date DATE,
  cover_url TEXT
);

INSERT INTO book_master VALUES('9784101373713', 'イチローの流儀', 0, '小西慶三／著', '新潮社', '2009-04-01', 'https://cover.openbd.jp/9784101373713.jpg');
INSERT INTO book_master VALUES('9784480081377', '存在と時間 上', 1, 'Heidegger,Martin／著 細谷貞雄／翻訳 ハイデッガーマルティン／著', '筑摩書房', '1994-06-01', '');
INSERT INTO book_master VALUES('9784532176204', '自壊の病理 日本陸軍の組織分析', 2, '戸部良一／著', '日本経済新聞出版社', '2017-04-27', 'https://cover.openbd.jp/9784532176204.jpg');
INSERT INTO book_master VALUES('9784106038280', '経済学者たちの日米開戦', 1, '牧野邦昭／著', '新潮社', '2018-05-25', 'https://cover.openbd.jp/9784106038280.jpg');
INSERT INTO book_master VALUES('9784844607656', '知るほど楽しい鎌倉時代', 2, '中西立太／著 多賀譲治／著', '理工図書', '2011-01-01', 'https://cover.openbd.jp/9784844607656.jpg');
INSERT INTO book_master VALUES('9784000613156', '社会科学と因果分析', 3, '佐藤俊樹／著', '岩波書店', '2019-01-30', 'https://cover.openbd.jp/9784000613156.jpg');
INSERT INTO book_master VALUES('9784766425659', '制度とは何か', 3, 'フランチェスコ・グァラ／著 瀧澤弘和／著 水野孝之／翻訳', '慶應義塾大学出版会', '2018-11-13', 'https://cover.openbd.jp/9784766425659.jpg');
INSERT INTO book_master VALUES('9784595315848', '自然科学はじめの一歩', 4, '大森聡一／著 岸根順一郎／著', '放送大学教育振興会 : NHK出版', '2015-03-01', 'https://cover.openbd.jp/9784595315848.jpg');
INSERT INTO book_master VALUES('9784130420679', '基礎統計学 3 (自然科学の統計学)', 4, '東京大学／著 東京大学教養学部統計学教室／著', '東京大学出版会', '1992-08-01', '');
INSERT INTO book_master VALUES('9784860645274', '「物理・化学」の単位・記号がまとめてわかる事典', 4, '齋藤勝裕／著', 'ベレ出版', '2017-10-23', 'https://cover.openbd.jp/9784860645274.jpg');
INSERT INTO book_master VALUES('9784822255923', '日経テクノロジー展望2019 世界をつなぐ 100の技術', 5, '日経BP社／著', '日経ＢＰ', '2018-10-26', 'https://cover.openbd.jp/9784822255923.jpg');
INSERT INTO book_master VALUES('9784774142043', 'Webを支える技術 : HTTP、URI、HTML、そしてREST', 5, '山本陽平／著', '技術評論社', '2010-05-01', 'https://cover.openbd.jp/9784774142043.jpg');
INSERT INTO book_master VALUES('9784806530008', '新産業構造ビジョン　一人ひとりの、世界の課題を解決する日本の未来', 6, '経済産業省経済産業政策局産業再生課／編集', '経済産業調査会', '2017-09-12', 'https://cover.openbd.jp/9784806530008.jpg');
INSERT INTO book_master VALUES('9784532321116', '第四次産業革命', 6, 'クラウス・シュワブ／著 世界経済フォーラム／翻訳', '日本経済新聞出版社', '2016-10-18', 'https://cover.openbd.jp/9784532321116.jpg');
INSERT INTO book_master VALUES('9784295402008', 'テクノロジーがすべてを塗り変える産業地図', 6, '泉田良輔／著', 'クロスメディア・パブリッシング;インプレス(発売)', '2018-07-01', '');
INSERT INTO book_master VALUES('9784772695336', '言語が違えば、世界も違って見えるわけ', 7, 'Deutscher,Guy／著 椋田直子／翻訳 ドイッチャーガイ／著', 'インターシフト : 合同出版', '2012-12-01', 'https://cover.openbd.jp/9784772695336.jpg');
INSERT INTO book_master VALUES('9784409100400', 'アートとは何か 芸術の存在論と目的論', 8, 'アーサー・Ｃ・ダントー／著 佐藤一進／著', '人文書院', '2018-11-28', 'https://cover.openbd.jp/9784409100400.jpg');
INSERT INTO book_master VALUES('9784623070435', '文学理論講義 新しいスタンダード', 9, 'ピーター・バリー／著 高橋和久／監修', 'ミネルヴァ書房', '2014-04-30', 'https://cover.openbd.jp/9784065176924.jpg');
INSERT INTO book_master VALUES('9784065176924', 'MOGUMOGU食べ歩きくま（2）', 9, 'ナガノ／著', '講談社', '2019-11-22', 'https://cover.openbd.jp/9784623070435.jpg');


CREATE TABLE book
(
  id SERIAL PRIMARY KEY,
  isbn TEXT,
  arrival_date DATE,
  disposal_date DATE,
  remarks TEXT
);

INSERT INTO book(isbn, arrival_date) VALUES('9784101373713', '2009-05-25');
INSERT INTO book(isbn, arrival_date) VALUES('9784101373713', '2009-05-25');
INSERT INTO book(isbn, arrival_date) VALUES('9784101373713', '2010-10-01');
INSERT INTO book(isbn, arrival_date) VALUES('9784480081377', '1994-06-10');
INSERT INTO book(isbn, arrival_date) VALUES('9784065176924', '2019-11-30');
INSERT INTO book(isbn, arrival_date) VALUES('9784065176924', '2019-11-30');
INSERT INTO book(isbn, arrival_date) VALUES('9784065176924', '2019-12-01');


--DROP TABLE IF EXISTS xxx;
--CREATE TABLE xxx
--(
--);

--INSERT INTO xxx VALUES();
