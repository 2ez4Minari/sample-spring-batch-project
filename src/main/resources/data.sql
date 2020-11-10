INSERT INTO CUSTOMER (NAME, AGE, ADDRESS, ACCOUNT_OPENING_DATE) values ('Lep', 23, 'Manda', '2020-11-05');
INSERT INTO CUSTOMER (NAME, AGE, ADDRESS, ACCOUNT_OPENING_DATE) values ('Hi', 15, 'Manila', '2020-11-04');
INSERT INTO CUSTOMER (NAME, AGE, ADDRESS, ACCOUNT_OPENING_DATE) values ('Hello', 16, 'HK', '2020-11-01');
INSERT INTO CUSTOMER (NAME, AGE, ADDRESS, ACCOUNT_OPENING_DATE) values ('Rold', 17, 'Cebu', '2020-11-02');
INSERT INTO CUSTOMER (NAME, AGE, ADDRESS, ACCOUNT_OPENING_DATE) values ('Nathan', 23, 'Dyan', '2020-11-05');
INSERT INTO CUSTOMER (NAME, AGE, ADDRESS, ACCOUNT_OPENING_DATE) values ('Tapos', 14, 'Doon', '2020-11-05');


insert into rwd.BALANCE_AND_INDEX (account_number, profile_account_number, cif, MOBILE_NUMBER, involved_party_uuid, product_agreements_uuid, account_opening_date, product_type, created_datetime, created_by)
values ('787899', '123', '1234567','639166080637', '36bcb3d0-1f36-11eb-adc1-0242ac120002', '1f57cd59-7951-4c96-9f65-ab2177aabd14', '2020-11-04', 'savings', CURDATE(), CURRENT_USER());
insert into rwd.BALANCE_AND_INDEX (account_number, profile_account_number, cif, MOBILE_NUMBER, involved_party_uuid, product_agreements_uuid, account_opening_date, product_type, created_datetime, created_by)
values ('12344', '123', '1234567','639166080637', '36bcb3d0-1f36-11eb-adc1-0242ac120002', '1f57cd59-7951-4c96-9f65-ab2177aabd14', CURDATE(), 'savings', CURDATE(), CURRENT_USER());
insert into rwd.BALANCE_AND_INDEX (account_number, profile_account_number, cif, MOBILE_NUMBER, involved_party_uuid, product_agreements_uuid, account_opening_date, product_type, created_datetime, created_by)
values ('11111', '123', '1234567','639166080637', '36bcb3d0-1f36-11eb-adc1-0242ac120002', '1f57cd59-7951-4c96-9f65-ab2177aabd14', CURDATE(), 'savings', CURDATE(), CURRENT_USER());
insert into rwd.BALANCE_AND_INDEX (account_number, profile_account_number, cif, MOBILE_NUMBER, involved_party_uuid, product_agreements_uuid, account_opening_date, product_type, created_datetime, created_by)
values ('22222', '123', '1234567','639166080637', '36bcb3d0-1f36-11eb-adc1-0242ac120002', '1f57cd59-7951-4c96-9f65-ab2177aabd14', CURDATE(), 'savings', CURDATE(), CURRENT_USER());
insert into rwd.BALANCE_AND_INDEX (account_number, profile_account_number, cif, MOBILE_NUMBER, involved_party_uuid, product_agreements_uuid, account_opening_date, product_type, created_datetime, created_by)
values ('33333', '123', '1234567','639166080637', '36bcb3d0-1f36-11eb-adc1-0242ac120002', '1f57cd59-7951-4c96-9f65-ab2177aabd14', CURDATE(), 'savings', CURDATE(), CURRENT_USER());