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
 (100002,'ROLE_USER'),
 (100003,'ROLE_USER'),
 (100004,'ROLE_USER'),
 (100005,'ROLE_DRIVER'),
 (100006,'ROLE_DRIVER'),
 (100007,'ROLE_DRIVER'),
 (100008,'ROLE_DRIVER'),
 (100009,'ROLE_ACCOUNTANT'),
 (100010,'ROLE_DISPATCHER'),
 (100011,'ROLE_DISPATCHER'),
 (100012,'ROLE_REFUELLING_MUSTANG');

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



