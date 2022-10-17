SELECT * FROM banking_app.credit_card;

describe banking_app.credit_card;

insert into banking_app.credit_card value
(1, 200, "EUR", ('2022-09-02'), null, 40, 1, null, 1, null, null),
(2, 400, "EUR", ('2019-1-5'), null, 40, 2, null, 2, null, null),
(3, 600, "EUR", ('2020-6-10'), null, 40, 3, null, 3,  null, null),
(4, 1000, "EUR", ('2021-8-10'), null, 40, 4, null, 4, null, null);

DELETE FROM checking where account_id = 13;