insert into contact (first_name, last_name, birth_date) values ('Chris', 'Schaefer', '1981-05-03');
--insert into contact (first_name, last_name, birth_date) values ('Scott', 'Tiger', '1990-11-02');
--insert into contact (first_name, last_name, birth_date) values ('John', 'Smith', '1964-02-28');

--insert into contact_tel_detail (contact_id, tel_type, tel_number) values (1, 'Mobile', '1234567890');
--insert into contact_tel_detail (contact_id, tel_type, tel_number) values (1, 'Home', '1234567890');
--insert into contact_tel_detail (contact_id, tel_type, tel_number) values (2, 'Home', '1234567890');

--insert into hobby (hobby_id) values ('Swimming');
--insert into hobby (hobby_id) values ('Jogging');
--insert into hobby (hobby_id) values ('Programming');
--insert into hobby (hobby_id) values ('Movies');
--insert into hobby (hobby_id) values ('Reading');

insert into contact_hobby_detail(contact_id, hobby_id) values (1, 'Swimming'); 
--insert into contact_hobby_detail(contact_id, hobby_id) values (1, 'Movies'); 
--insert into contact_hobby_detail(contact_id, hobby_id) values (2, 'Swimming');

--Hibernate: select distinct contact0_.ID as ID1_0_0_, 
--contacttel1_.ID as ID1_2_1_, hobby3_.HOBBY_ID as HOBBY_ID1_3_2_, 
--contact0_.BIRTH_DATE as BIRTH_DA2_0_0_, contact0_.FIRST_NAME as FIRST_NA3_0_0_,
--contact0_.LAST_NAME as LAST_NAM4_0_0_, contact0_.VERSION as VERSION5_0_0_, 
--contacttel1_.CONTACT_ID as CONTACT_5_2_1_, contacttel1_.TEL_NUMBER as TEL_NUMB2_2_1_,
--contacttel1_.TEL_TYPE as TEL_TYPE3_2_1_, contacttel1_.VERSION as VERSION4_2_1_, 
--contacttel1_.CONTACT_ID as CONTACT_5_0_0__, contacttel1_.ID as ID1_2_0__,
--hobbies2_.CONTACT_ID as CONTACT_2_0_1__, 
--hobbies2_.HOBBY_ID as HOBBY_ID1_1_1__ from contact contact0_ 
--left outer join contact_tel_detail contacttel1_ on contact0_.ID=contacttel1_.CONTACT_ID left outer join contact_hobby_detail hobbies2_ on contact0_.ID=hobbies2_.CONTACT_ID left outer join hobby hobby3_ on hobbies2_.HOBBY_ID=hobby3_.HOBBY_ID

