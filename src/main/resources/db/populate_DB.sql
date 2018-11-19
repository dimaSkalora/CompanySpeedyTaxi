DELETE FROM cst.users;
DELETE FROM cst.user_roles;
DELETE FROM cst.vehicles;
DELETE FROM cst.user_vehicles;
DELETE FROM cst.user_state;
DELETE FROM cst.vehicle_state;
DELETE FROM cst.access_to_route;
DELETE FROM cst.taxi_dispatchers;
DELETE FROM cst.taxi_dispatcher_orders;
DELETE FROM cst.taxi_user_orders;
DELETE FROM cst.type_payment;
DELETE FROM cst.type_bank_card;
DELETE FROM cst.bank_card;
DELETE FROM cst.user_bank_card;
DELETE FROM cst.refilling_car;
DELETE FROM cst.taxi_order_acceptance;
DELETE FROM cst.taxi_route_on_orders;
DELETE FROM cst.taxi_route;
DELETE FROM cst.coming_consuption_fuel;
DELETE FROM cst.bank_card_operations;
DELETE FROM cst.taxi_user_vehicle_info;
DELETE FROM cst.taxi_job_status;
DELETE FROM cst.departments_company;
DELETE FROM cst.payroll_accounting;
DELETE FROM cst.pay_sheet;

ALTER SEQUENCE cst.global_seq RESTART WITH 100000;

---------------users---------------1
INSERT INTO cst.users (name, email, password,adress) VALUES
 ('Admin', 'admin@gmail.com', 'admin', 'address1'),
 ('Company', 'company@gmail.com', 'company', 'address2'),
 ('User1', 'user1@gmail.com', 'user1', 'address3'),
 ('User2', 'user2@gmail.com', 'user2', 'address4'),
 ('User3', 'user3@gmail.com', 'user3', 'address5'),
 ('Driver1', 'driver1@gmail.com', 'driver1', 'address6'),
 ('Driver2', 'driver2@gmail.com', 'driver2', 'address7'),
 ('Driver3', 'driver3@gmail.com', 'driver3', 'address8'),
 ('Driver4', 'driver4@gmail.com', 'driver4', 'address9'),
 ('Accountant', 'accountant@gmail.com', 'accountant', 'address10'),
 ('Dispatcher1', 'dispatcher1@gmail.com', 'dispatcher1', 'address11'),
 ('Dispatcher2', 'dispatcher2@gmail.com', 'dispatcher2', 'address12'),
 ('Refuelling_Mustang', 'refuelling_mustang@gmail.com', 'refuelling_mustang', 'address13');

---------------user_roles---------------2
INSERT INTO cst.user_roles (user_id, role) VALUES
 (100000,'ROLE_ADMIN'),
 (100000,'ROLE_USER'),
 (100000,'ROLE_DRIVER'),
 (100000,'ROLE_ACCOUNTANT'),
 (100000,'ROLE_COMPANY'),
 (100000,'ROLE_DISPATCHER'),
 (100001,'ROLE_COMPANY'),
 (100001,'ROLE_DRIVER'),
 (100002,'ROLE_USER'),
 (100003,'ROLE_USER'),
 (100004,'ROLE_USER'),
 (100005,'ROLE_DRIVER'),
 (100005,'ROLE_USER'),
 (100006,'ROLE_DRIVER'),
 (100006,'ROLE_USER'),
 (100007,'ROLE_DRIVER'),
 (100007,'ROLE_USER'),
 (100008,'ROLE_DRIVER'),
 (100008,'ROLE_USER'),
 (100009,'ROLE_ACCOUNTANT'),
 (100009,'ROLE_USER'),
 (100010,'ROLE_DISPATCHER'),
 (100010,'ROLE_USER'),
 (100011,'ROLE_DISPATCHER'),
 (100011,'ROLE_USER'),
 (100012,'ROLE_REFUELLING_MUSTANG'),
 (1000012,'ROLE_USER');

---------------vehicles---------------3
INSERT INTO cst.vehicles (id,name_car, vehicle_number, year_issue, category, color, fuel_consumption) VALUES
 (1,'Toyota Camry','AA2015BK', 2015, 'легковая', 'желтый',7),
 (2,'Toyota Camry','AA2222BK', 2015, 'легковая', 'желтый',7),
 (3,'Toyota Camry','AA2111BK', 2016, 'легковая', 'желтый',6),
 (4,'Toyota Camry','AA2225BW', 2016, 'легковая', 'желтый',6),
 (5,'Toyota Colora','AA3333BQ', 2015, 'легковая', 'желтый',7),
 (6,'Toyota Colora','AA2512BS', 2015, 'легковая', 'желтый',7),
 (7,'Toyota Colora','AA3334BQ', 2016, 'легковая', 'желтый',6),
 (8,'Toyota Colora','AA2412BS', 2016, 'легковая', 'желтый',6);

---------------user_vehicles---------------4
INSERT INTO cst.user_vehicles (id, id_user, id_vehicle, is_current_user_machine) VALUES
 (1, 100005, 2, 1),
 (2, 100006, 4, 1),
 (3, 100007, 6, 1),
 (4, 100008, 8, 1);

---------------user_state---------------5
INSERT INTO cst.user_state (id, name_us) VALUES
 (1,'Здоровый'),
 (2,'Больной'),
 (3,'Не трезвый');

---------------vehicle_state---------------6
INSERT INTO cst.vehicle_state (id, name_vs) VALUES
 (1,'Рабочий'),
 (2,'Сломан'),
 (3,'В ремонте');

---------------access_to_route---------------7
INSERT INTO cst.access_to_route (id, id_user_vehicles, id_user_state, id_vehicle_state, is_access) VALUES
 (1,1,1,1,1),
 (2,2,1,1,1),
 (3,3,1,1,1),
 (4,4,1,1,1),
 (5,1,1,2,0),
 (6,2,2,1,0);

---------------taxi_dispatchers---------------8
INSERT INTO cst.taxi_dispatchers (id, id_user) VALUES
 (1,100010),
 (2,100011);

---------------taxi_dispatcher_orders---------------9
INSERT INTO cst.taxi_dispatcher_orders (id, id_taxi_dispatcher, user_name, user_phone, address_departure, address_arrival, start_date, end_date, fulfilled) VALUES
 (1, 1, 'user1', '8011-11-11-111', 'City, address1', 'City, address2', '2018-11-12 13:00:00',    '2018-11-12 14:00:00', 1),
 (2, 2, 'user2', '8011-00-11-111', 'City, address3', 'City, address4', '2018-11-13 13:00:00',    '2018-11-13 14:00:00', 0),
 (3, 1, 'user3', '8011-22-11-111', 'City, address5', 'City, address6', '2018-11-14 13:00:00',    '2018-11-14 14:00:00', 0),
 (4, 2, 'user4', '8011-33-11-111', 'City, address7', 'City, address8', '2018-11-15 13:00:00',    '2018-11-15 14:00:00', 1);

---------------taxi_user_orders---------------10
INSERT INTO cst.taxi_user_orders (id, id_user, address_departure, address_arrival, start_date, end_date, fulfilled) VALUES
 (1, 100002, 'City, address1', 'City, address2', '2018-11-12 13:00:00',    '2018-11-12 14:00:00', 1),
 (2, 100003, 'City, address3', 'City, address4', '2018-11-13 13:00:00',    '2018-11-13 14:00:00', 0),
 (3, 100004, 'City, address5', 'City, address6', '2018-11-14 13:00:00',    '2018-11-14 14:00:00', 0);

---------------type_payment---------------11
INSERT INTO cst.type_payment (id, name_tp) VALUES
 (1, 'Зарплата'),
 (2, 'Аванс'),
 (3, 'Премия'),
 (4, 'Больничные'),
 (5, 'Отпускные');

 ---------------type_bank_card---------------12
INSERT INTO cst.type_bank_card (id, type_name) VALUES
 (1, 'Зарплатная'),
 (2, 'Компания'),
 (3, 'Водительская'),
 (4, 'Авто заправная'),
 (5, 'Разное');

---------------bank_card---------------13
INSERT INTO cst.bank_card (id, bank_card_number, id_type_bank_card, money_balance, is_active) VALUES
 (1, '0002000200020001', 2, 50000.0, 1),
 (2, '0002000200020002', 2, 50000.0, 1),
 (3, '0001000100010001', 1, 0.0, 1),
 (4, '0001000100010002', 1, 0.0, 1),
 (5, '0001000100010003', 1, 0.0, 1),
 (6, '0001000100010004', 1, 0.0, 1),
 (7, '0001000100010005', 1, 0.0, 1),
 (8, '0001000100010006', 1, 0.0, 1),
 (9, '0001000100010007', 1, 0.0, 1),
 (10, '0001000100010008', 1, 0.0, 1),
 (11, '0001000100010009', 1, 0.0, 1),
 (12, '0003000300030001', 3, 0.0, 1),
 (13, '0003000300030002', 3, 0.0, 1),
 (14, '0003000300030003', 3, 0.0, 1),
 (15, '0003000300030004', 3, 0.0, 1),
 (16, '0004000400040001', 4, 500.0, 1),
 (17, '0004000400040002', 4, 500.0, 1),
 (18, '0004000400040003', 4, 500.0, 1),
 (19, '0004000400040004', 4, 500.0, 1),
 (20, '0005000500050001', 5, 0.0, 1);

---------------user_bank_card---------------14
INSERT INTO cst.user_bank_card (id, id_user, id_bank_card) VALUES
 (1, 100000, 1),
 (2, 100000, 3),
 (3, 100001, 2),
 (4, 100005, 4),
 (5, 100005, 12),
 (6, 100005, 16),
 (7, 100006, 5),
 (8, 100006, 13),
 (9, 100006, 17),
 (10, 100007, 6),
 (11, 100007, 14),
 (12, 100007, 18),
 (13, 100008, 7),
 (14, 100008, 15),
 (15, 100008, 19),
 (16, 100009, 8),
 (17, 100010, 9),
 (18, 100011, 10),
 (19, 100012, 20);

---------------refilling_car---------------15
INSERT INTO cst.refilling_car (id, id_user_vehicle, id_user_bank_card, liter, price_per_liter, payment_of_refueling) VALUES
 (1, 1, 6, 20.0, 5.5, 110.0),
 (2, 2, 9, 15.0, 5.5, 82.5),
 (3, 3, 12, 20.0, 5.5, 110.0),
 (4, 4, 15, 18.0, 5.5, 99.0);

---------------taxi_order_acceptance---------------16
INSERT INTO cst.taxi_order_acceptance (id, id_user_vehicle, id_taxi_dispatcher_order, id_taxi_user_order, execution_status, adoption_status) VALUES
 (1,1,1,null,0,1),
 (2,2,2,null,0,0),
 (3,3,null,1,0,1),
 (4,1,1,null,1,1),
 (5,2,null,2,1,1);

---------------taxi_route_on_orders---------------17
INSERT INTO cst.taxi_route_on_orders (id, id_user_vehicle, id_user_bank_card, id_taxi_order_acceptance, landing, tariff_per_kilometer, distance, fare_payment, fuel_consuption) VALUES
 (1, 1, 5, 4, 30.5, 10.0, 8.3, 113.5, 1.3),
 (2, 2, 8, 5, 30.5, 10.0, 15.4, 184.5, 2.2);

---------------taxi_route---------------18
INSERT INTO cst.taxi_route (id, id_user_vehicle, id_user_bank_card, departure_point, arrival_point, start_date, end_date, landing, tariff_per_kilometer, distance, fare_payment, fuel_consuption) VALUES
 (1, 1, 5, 'City address1', 'City address2', '2018-11-12 13:00:00', '2018-11-12 14:00:00', 30.5, 10.0, 9.4, 124.5, 1.4),
 (2, 2, 8, 'City address3', 'City address4', '2018-11-14 10:00:00', '2018-11-14 11:00:00', 30.5, 10.0, 13.7,167.5, 2.0);

---------------coming_consuption_fuel---------------19
INSERT INTO cst.coming_consuption_fuel (id, date_ccf, id_user_vehicle, consuption, coming, remainder, kilometer, total_kilometer) VALUES
 (1, '2018-11-01 10:00:00', 1, 0.0, 20.0, 20.0, 0.0, 0.0),
 (2, '2018-11-02 10:00:00', 2, 0.0, 15.0, 15.0, 0.0, 0.0),
 (3, '2018-11-03 10:00:00', 3, 0.0, 20.0, 20.0, 0.0, 0.0),
 (4, '2018-11-04 10:00:00', 4, 0.0, 18.0, 18.0, 0.0, 0.0),
 (5, '2018-11-05 10:00:00', 1, 1.3, 0.0, 18.7, 8.3, 8.3),
 (6, '2018-11-06 10:00:00', 2, 2.2, 0.0, 17.8, 15.4, 15.4),
 (7, '2018-11-07 10:00:00', 1, 1.4, 0.0, 17.3, 9.4, 17.7),
 (8, '2018-11-08 10:00:00', 2, 2.0, 0.0, 15.8, 13.7, 29.1);

---------------bank_card_operations---------------20
INSERT INTO cst.bank_card_operations (id, id_user_bank_card, date_time_arrivel_money, id_bank_card_arrivel, arrival_money, date_time_spending_money, id_bank_card_spending, spending_money, money_balance) VALUES
 (1, 6, null, null, null, '2018-11-10 10:00:00', 20, 110.0, 390.0),
 (2, 19, '2018-11-10 10:01:00', 16, 110.0, null, null, null, 110.0),
 (3, 9, null, null, null, '2018-11-10 10:10:00', 20, 82.5, 417.5),
 (4, 19, '2018-11-10 10:11:00', 17, 82.5, null, null, null, 192.5),
 (5, 12, null, null, null, '2018-11-10 10:20:00', 20, 110.0, 390.0),
 (6, 19, '2018-11-10 10:21:00', 18, 110.0, null, null, null, 302.5),
 (7, 15, null, null, null, '2018-11-10 10:30:00', 20, 99.0, 410.0),
 (8, 19, '2018-11-10 10:31:00', 19, 99.0, null, null, null, 401.5);

---------------taxi_job_status---------------21
INSERT INTO cst.taxi_job_status (id, name_tjs) VALUES
 (1, 'Свободный'),
 (2, 'Занятый'),
 (3, 'Обед'),
 (4, 'Не рабатаю');

---------------taxi_user_vehicle_info---------------22
INSERT INTO cst.taxi_user_vehicle_info (id, id_user_vehicle, id_taxi_job_status) VALUES
 (1, 1, 2),
 (2, 2, 3),
 (3, 3, 1),
 (4, 4, 4);

---------------departments_company---------------23
INSERT INTO cst.departments_company (id, name_dc) VALUES
 (1, 'Бухгалтерия'),
 (2, 'Финансы'),
 (3, 'Администрация'),
 (4, 'Автопарк');

---------------payroll_accounting---------------24
INSERT INTO cst.payroll_accounting (id, id_department_company, id_user_bank_card, month_year, norm_number_days_worked_month, number_days_worked_month, stake_days, norm_hours_worked_month, hours_worked_month, stake_hour, payment_date_time, id_type_payment, payout, sum_payout_month) VALUES
 (1, 4, 4, '10.2018', 21, 21, 120.0, 168, 168, 15.0, '2018-11-07 10:00:00', 1, 2520.0, 2520.0),
 (2, 4, 7, '10.2018', 21, 20, 120.0, 168, 160, 15.0, '2018-11-07 10:00:00', 1, 2400.0, 2400.0);

---------------pay_sheet---------------25
INSERT INTO cst.pay_sheet (id, id_user, id_bank_card, id_department_company, month_year, prepayment, salary, premium, hospitals, holiday, amount_without_taxes, ndfl, esv, vs, amount_with_texas, norm_hours_worked, hours_worked, hours_holiday) VALUES
 (1, 100005, 4, 4, '10.2018', 800.0, 1720.0, 500.0, 0.0, 0.0, 2220.0, 18.0, 22.0, 1.5, 1787.1, 168, 168, 0),
 (2, 100006, 5, 4, '10.2018', 800.0, 1600.0, 400.0, 0.0, 0.0, 2000.0, 18.0, 22.0, 1.5, 1610.0, 168, 160, 0);
