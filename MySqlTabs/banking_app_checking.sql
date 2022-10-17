SELECT * FROM banking_app.checking;

describe banking_app.checking;

insert into checking value
(2, 400, "EUR", ('2019-1-5'), null, 40, 2, null, 2, 250.00, 12, 1234, null),
(3, 600, "EUR", ('2020-6-10'), null,  40, 3, null, 3,  250.00, 12, 1234, null),
(4, 1000, "EUR",('2021-8-10'), null,  40, 4, null, 4, 250.00, 12, 1234, null);

select * from checking;
