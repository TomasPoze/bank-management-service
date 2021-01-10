INSERT INTO Accounts (account_number,total_balance,user,currency) VALUES ('LT475896582054871698',687.64,'Jonas Jonaitis','EURO');
INSERT INTO Accounts (account_number,total_balance,user,currency) VALUES ('LT456468846868813288',368.99,'Virginijus Virginaitis','EURO');
INSERT INTO Accounts (account_number,total_balance,user,currency) VALUES ('LT754258965742358417',6107,'Ona Onaitiene','US_DOLLAR');


INSERT INTO Account_Transactions (account_number,amount,beneficiary,comment,currency,operation_date, account_id) VALUES ('LT475896582054871698',11.99,'Netflix','Monthly subscription','EURO','2021-01-09 16:32',1);
INSERT INTO Account_Transactions (account_number,amount,beneficiary,comment,currency,operation_date, account_id) VALUES ('LT475896582054871698',6,'Benas Benavicius','Transfer','EURO','2021-01-10 12:02',1);
INSERT INTO Account_Transactions (account_number,amount,beneficiary,comment,currency,operation_date, account_id) VALUES ('LT754258965742358417',20,'Maxima','Transfer','US_DOLLAR','2021-01-10 18:11',3);