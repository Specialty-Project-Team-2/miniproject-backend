-- password : 1234
INSERT INTO `member` (email, nickname, password)
VALUES ('rladydqls99@naver.com', 'nick', '$2a$10$sw8G4sVnrHzd2rpGQ1GeoulDn3TvSA2N0v6LGGxwc/nGgMxMHZ.QW')
     ,('rlaydld@asd.com', 'rasd', '$2a$10$sw8G4sVnrHzd2rpGQ1GeoulDn3TvSA2N0v6LGGxwc/nGgMxMHZ.QW');

INSERT INTO `company` (id, company_name, location)
VALUES ('1233', 'samsung', 'seoul')
     ,('1234', 'toss', 'daejeon')
     ,('1235', 'samsung', 'daegu')
     ,('1236', 'samsung', 'seoul')
     ,('1237', 'toss', 'daejeon');

INSERT INTO `comment` (comment, detail_id, member_id)
VALUES ('복지 좋음', '1234', '1')
     ,('사장님 착함', '1234', '1')
     ,('야근 많음', '1235', '2')
     ,('밥 맛있음', '1235', '2');