SELECT * FROM banking_app.savings;

describe banking_app.savings;

insert into banking_app.savings value
(1, 200, "EUR", ('2022-09-02'), null, 40, 1, null, 1, 100, null, 1234, null, null),
(2, 400, "EUR", ('2019-1-5'), null, 40, 2, null, 2, 100, null, 1234, null, null),
(3, 600, "EUR", ('2020-6-10'), null, 40, 3, null, 3,  100, null, 1234, null, null),
(4, 1000, "EUR", ('2021-8-10'), null, 40, 4, null, 4, 100, null, 1234, null, null);