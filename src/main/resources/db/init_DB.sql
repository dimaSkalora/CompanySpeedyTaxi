DROP TABLE IF EXISTS cst.users CASCADE;
DROP TABLE IF EXISTS cst.user_roles CASCADE;
DROP TABLE IF EXISTS cst.vehicles CASCADE;
DROP TABLE IF EXISTS cst.user_vehicles CASCADE;
DROP TABLE IF EXISTS cst.user_state CASCADE;
DROP TABLE IF EXISTS cst.vehicle_state CASCADE;
DROP TABLE IF EXISTS cst.access_to_route;
DROP TABLE IF EXISTS cst.taxi_dispatchers CASCADE;
DROP TABLE IF EXISTS cst.taxi_dispatcher_orders CASCADE;
DROP TABLE IF EXISTS cst.taxi_user_orders CASCADE;
DROP TABLE IF EXISTS cst.taxi_order_acceptance CASCADE;
DROP TABLE IF EXISTS cst.coming_consuption_fuel;
DROP TABLE IF EXISTS cst.taxi_route_on_orders;
DROP TABLE IF EXISTS cst.taxi_route;
DROP TABLE IF EXISTS cst.refilling_car;
DROP TABLE IF EXISTS cst.type_payment CASCADE;
DROP TABLE IF EXISTS cst.type_bank_card CASCADE;
DROP TABLE IF EXISTS cst.bank_card CASCADE;
DROP TABLE IF EXISTS cst.user_bank_card;
DROP TABLE IF EXISTS cst.bank_card_operations;
DROP TABLE IF EXISTS cst.taxi_user_vehicle_info;
DROP TABLE IF EXISTS cst.taxi_job_status;
DROP TABLE IF EXISTS cst.departments_company CASCADE;
DROP TABLE IF EXISTS cst.payroll_accounting;
DROP TABLE IF EXISTS cst.pay_sheet;

DROP SEQUENCE cst.global_seq;

CREATE SEQUENCE cst.global_seq START 100000;

---------------users---------------1
CREATE TABLE cst.users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  adress           VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON cst.users (email); --email каждого пользователя должен быть уникальным
COMMENT ON COLUMN cst.users.registered
    IS 'Дата регистраиции пользователя';
COMMENT ON COLUMN cst.users.enabled
    IS 'true - активный, false - не активный';

---------------user_roles---------------2
CREATE TABLE cst.user_roles
(
  user_id 	INTEGER NOT NULL,
  role   	VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES cst.users (id) ON DELETE CASCADE --если удаляется пользователь из таблицы users то автомачически удаляется и из user_roles
);
COMMENT ON COLUMN cst.user_roles.user_id
    IS 'Id пользователя';
COMMENT ON COLUMN cst.user_roles.role
    IS 'Роль для пользователя';

---------------vehicles---------------3
CREATE TABLE cst.vehicles
(
  id                    INTEGER PRIMARY KEY,
  name_car              VARCHAR NOT NULL,
  vehicle_number        VARCHAR NOT NULL,
  year_issue            VARCHAR(4) NOT NULL,
  category              VARCHAR NOT NULL,
  color                 VARCHAR NOT NULL,
  fuel_consumption      INTEGER NOT NULL
);
CREATE UNIQUE INDEX vehicle_unique_name_idx ON cst.vehicles(name_car,vehicle_number); --Название должно быть уникальным
COMMENT ON TABLE cst.vehicles
    IS 'Транспортное средство';
COMMENT ON COLUMN cst.vehicles.name_car
    IS 'Название, модель ТС';
COMMENT ON COLUMN cst.vehicles.vehicle_number
    IS 'Номер ТС';
COMMENT ON COLUMN cst.vehicles.year_issue
    IS 'Год выпуска ТС';
COMMENT ON COLUMN cst.vehicles.category
    IS 'Легковая, грузовая, автобус и т.д.';
COMMENT ON COLUMN cst.vehicles.fuel_consumption
    IS 'Расход топлива л/100км';

---------------user_vehicles---------------4
CREATE TABLE cst.user_vehicles
(
	id                      INTEGER PRIMARY KEY,
	start_date              TIMESTAMP DEFAULT now() NOT NULL,
	end_date                TIMESTAMP,
 	id_user                 INTEGER NOT NULL,
 	id_vehicle              INTEGER NOT NULL,
	is_current_user_machine INTEGER NOT NULL,
	FOREIGN KEY (id_user) REFERENCES cst.users(id) ON DELETE CASCADE, --если удаляется пользователь из таблицы users то автомачически удаляется и из user_vehicles
	FOREIGN KEY (id_vehicle) REFERENCES cst.vehicles(id) ON DELETE CASCADE --если удаляется ТС из таблицы vehicles то автомачически удаляется и из user_vehicles
);
COMMENT ON TABLE cst.user_vehicles
    IS 'Какое у юзера транспортное средство(какая у водителя машина)';
COMMENT ON COLUMN cst.user_vehicles.start_date
    IS 'Дата когда пользователь взял машину';
COMMENT ON COLUMN cst.user_vehicles.end_date
    IS 'Дата когда пользователь вернул машину';
COMMENT ON COLUMN cst.user_vehicles.is_current_user_machine
    IS 'Машина текущего пользователя: 0 - есть машина; 1 - нету машины';

---------------user_state---------------5
CREATE TABLE cst.user_state
(
	id               INTEGER PRIMARY KEY,
	name_us          VARCHAR NOT NULL
);
COMMENT ON COLUMN cst.user_state.name_us
    IS 'Название состояния (здоровый, больной, и т.д.)';


---------------vehicle_state---------------6
CREATE TABLE cst.vehicle_state
(
	id              INTEGER PRIMARY KEY,
	name_vs         VARCHAR NOT NULL
);
COMMENT ON COLUMN cst.vehicle_state.name_vs
    IS 'Название состояния (робочая, не рабочая, и т.д.)';

---------------access_to_route---------------7
CREATE TABLE cst.access_to_route
(
  id                 INTEGER PRIMARY KEY,
  date_time          TIMESTAMP DEFAULT now() NOT NULL,
  id_user_vehicles   INTEGER NOT NULL,
  id_user_state      INTEGER NOT NULL,
  id_vehicle_state   INTEGER NOT NULL,
  is_access          INTEGER NOT NULL,
  FOREIGN KEY(id_user_vehicles) REFERENCES cst.user_vehicles (id) ON DELETE CASCADE,
  FOREIGN KEY(id_user_state) REFERENCES cst.user_state (id),
  FOREIGN KEY(id_vehicle_state) REFERENCES cst.vehicle_state (id)
);
COMMENT ON TABLE cst.access_to_route
  IS 'Допуск Юзера и ТС до маршрута';
COMMENT ON COLUMN cst.access_to_route.date_time
  IS 'Дата и время проверки Юзера и ТС к маршруту';
COMMENT ON COLUMN cst.access_to_route.is_access
  IS 'Допуск: 1 - yes, 0 - no';

---------------taxi_dispatchers---------------8
CREATE TABLE cst.taxi_dispatchers
(
  id                  INTEGER PRIMARY KEY,
  id_user             INTEGER NOT NULL,
  FOREIGN KEY (id_user) REFERENCES cst.users(id) ON DELETE CASCADE --если удаляется пользователь из таблицы users то автомачически удаляется и из taxi_dispatcher
);

---------------taxi_dispatcher_orders---------------9
CREATE TABLE cst.taxi_dispatcher_orders
(
  id                  INTEGER PRIMARY KEY,
  date_time_order     TIMESTAMP DEFAULT now() NOT NULL,
  id_taxi_dispatcher  INTEGER NOT NULL,
  user_name           VARCHAR NOT NULL,
  user_phone          VARCHAR NOT NULL,
  address_departure   VARCHAR NOT NULL,
  address_arrival     VARCHAR NOT NULL,
  start_date          TIMESTAMP NOT NULL,
  end_date            TIMESTAMP NOT NULL,
  fulfilled           INTEGER DEFAULT 0 NOT NULL,
  FOREIGN KEY (id_taxi_dispatcher) REFERENCES cst.taxi_dispatchers(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.taxi_dispatcher_orders
  IS 'Заказы заполняет диспечер';
COMMENT ON COLUMN cst.taxi_dispatcher_orders.date_time_order
  IS 'Дата и время заказа';
COMMENT ON COLUMN cst.taxi_dispatcher_orders.address_departure
  IS 'Адрес отправление';
COMMENT ON COLUMN cst.taxi_dispatcher_orders.address_arrival
  IS 'Адрес прибытие';
COMMENT ON COLUMN cst.taxi_dispatcher_orders.start_date
  IS 'Время отправление';
COMMENT ON COLUMN cst.taxi_dispatcher_orders.end_date
  IS 'Время прибытие';
COMMENT ON COLUMN cst.taxi_dispatcher_orders.fulfilled
  IS 'Выполнен заказ: 1 - да, 0 - нет';


---------------taxi_user_orders---------------10
CREATE TABLE cst.taxi_user_orders
(
  id                  INTEGER PRIMARY KEY,
  date_time_order     TIMESTAMP DEFAULT now() NOT NULL,
  id_user             INTEGER NOT NULL,
  address_departure   VARCHAR NOT NULL,
  address_arrival     VARCHAR NOT NULL,
  start_date          TIMESTAMP NOT NULL,
  end_date            TIMESTAMP NOT NULL,
  fulfilled           INTEGER DEFAULT 0 NOT NULL,
  FOREIGN KEY (id_user) REFERENCES cst.users(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.taxi_user_orders
  IS 'Заказы заполняет user';
COMMENT ON COLUMN cst.taxi_user_orders.date_time_order
  IS 'Дата и время заказа';
COMMENT ON COLUMN cst.taxi_user_orders.address_departure
  IS 'Адрес отправление';
COMMENT ON COLUMN cst.taxi_user_orders.address_arrival
  IS 'Адрес прибытие';
COMMENT ON COLUMN cst.taxi_user_orders.start_date
  IS 'Время отправление';
COMMENT ON COLUMN cst.taxi_user_orders.end_date
  IS 'Время прибытие';
COMMENT ON COLUMN cst.taxi_user_orders.fulfilled
  IS 'Выполнен заказ: 1 - да, 0 - нет';

---------------taxi_order_acceptance---------------11
CREATE TABLE cst.taxi_order_acceptance
(
  id                          INTEGER PRIMARY KEY,
  id_user_vehicle             INTEGER NOT NULL,
  id_taxi_dispatcher_order    INTEGER DEFAULT 0 NOT NULL,
  id_taxi_user_order          INTEGER DEFAULT 0 NOT NULL,
  execution_status            INTEGER NOT NULL,
  adoption_status             INTEGER NOT NULL,
  FOREIGN KEY (id_user_vehicle) REFERENCES cst.user_vehicles(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.taxi_order_acceptance
  IS 'Прием заказов от юзера или диспечера';
COMMENT ON COLUMN cst.taxi_order_acceptance.execution_status
  IS 'Статус выполнение: 1-выполнено, 0- не выполнено';
COMMENT ON COLUMN cst.taxi_order_acceptance.adoption_status
  IS 'Статус принятие: 1-принятый заказ, 0- не принятый заказ';

---------------coming_consuption_fuel---------------12
CREATE TABLE cst.coming_consuption_fuel
(
  id                          INTEGER PRIMARY KEY,
  date_time                   TIMESTAMP DEFAULT now() NOT NULL,
  id_user_vehicle             INTEGER NOT NULL,
  consuption                  INTEGER NOT NULL,
  coming                      INTEGER NOT NULL,
  remainder                   INTEGER NOT NULL,
  kilometer                   INTEGER NOT NULL,
  total_kilometer             INTEGER NOT NULL,
  FOREIGN KEY (id_user_vehicle) REFERENCES cst.user_vehicles(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.coming_consuption_fuel
  IS 'Приход, расход топлива';
COMMENT ON COLUMN cst.coming_consuption_fuel.consuption
  IS 'Расход топливо литров';
COMMENT ON COLUMN cst.coming_consuption_fuel.coming
  IS 'Приход топлива литров';
COMMENT ON COLUMN cst.coming_consuption_fuel.remainder
  IS 'Остаток топливо литров';
COMMENT ON COLUMN cst.coming_consuption_fuel.kilometer
  IS 'Сколько километров проехал юзер на ТС';
COMMENT ON COLUMN cst.coming_consuption_fuel.total_kilometer
  IS 'Сколько всего километов проехал юзер на ТС';

---------------taxi_route_on_orders---------------13
CREATE TABLE cst.taxi_route_on_orders
(
  id                          INTEGER PRIMARY KEY,
  id_user_vehicle             INTEGER NOT NULL,
  id_taxi_order_acceptance    INTEGER NOT NULL,
  landing                     INTEGER NOT NULL,
  tariff_per_kilometer        INTEGER NOT NULL,
  distance                    INTEGER NOT NULL,
  fare_payment                INTEGER NOT NULL,
  fuel_consuption             INTEGER NOT NULL,
  FOREIGN KEY (id_user_vehicle) REFERENCES cst.user_vehicles(id) ON DELETE CASCADE,
  FOREIGN KEY (id_taxi_order_acceptance) REFERENCES cst.taxi_order_acceptance(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.taxi_route_on_orders
  IS 'Маршруты по заказам';
COMMENT ON COLUMN cst.taxi_route_on_orders.landing
  IS 'Цена за посадку';
COMMENT ON COLUMN cst.taxi_route_on_orders.tariff_per_kilometer
  IS 'Тариф за километр';
COMMENT ON COLUMN cst.taxi_route_on_orders.distance
  IS 'Растояние маршрута в километрах';
COMMENT ON COLUMN cst.taxi_route_on_orders.fare_payment
  IS 'Оплата за проезд';
COMMENT ON COLUMN cst.taxi_route_on_orders.fuel_consuption
  IS 'Расход топлива';

---------------taxi_route---------------14
CREATE TABLE cst.taxi_route
(
  id                          INTEGER PRIMARY KEY,
  id_user_vehicle             INTEGER NOT NULL,
  departure_point             VARCHAR NOT NULL,
  arrival_point               VARCHAR NOT NULL,
  start_date                  TIMESTAMP NOT NULL,
  end_date                    TIMESTAMP NOT NULL,
  landing                     INTEGER NOT NULL,
  tariff_per_kilometer        INTEGER NOT NULL,
  distance                    INTEGER NOT NULL,
  fare_payment                INTEGER NOT NULL,
  fuel_consuption             INTEGER NOT NULL,
  FOREIGN KEY (id_user_vehicle) REFERENCES cst.user_vehicles(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.taxi_route
  IS 'Маршруты';
COMMENT ON COLUMN cst.taxi_route.departure_point
  IS 'Пунк отправление';
COMMENT ON COLUMN cst.taxi_route.arrival_point
  IS 'Пунк прибытие';
COMMENT ON COLUMN cst.taxi_route.landing
  IS 'Цена за посадку';
COMMENT ON COLUMN cst.taxi_route.tariff_per_kilometer
  IS 'Тариф за километр';
COMMENT ON COLUMN cst.taxi_route.distance
  IS 'Растояние маршрута в километрах';
COMMENT ON COLUMN cst.taxi_route.fare_payment
  IS 'Оплата за проезд';
COMMENT ON COLUMN cst.taxi_route.fuel_consuption
  IS 'Расход топлива';

---------------refilling_car---------------15
CREATE TABLE cst.refilling_car
(
  id                          INTEGER PRIMARY KEY,
  id_user_vehicle             INTEGER NOT NULL,
  date_time                   TIMESTAMP DEFAULT now() NOT NULL,
  liter                       INTEGER NOT NULL,
  price_per_liter             DECIMAL NOT NULL,
  payment_of_refueling        DECIMAL NOT NULL,
  FOREIGN KEY (id_user_vehicle) REFERENCES cst.user_vehicles(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.refilling_car
  IS 'Заправка ТС';
COMMENT ON COLUMN cst.refilling_car.date_time
  IS 'Дата и время заправки';
COMMENT ON COLUMN cst.refilling_car.liter
  IS 'Литров заправлено';
COMMENT ON COLUMN cst.refilling_car.price_per_liter
  IS 'Цена за литр';
COMMENT ON COLUMN cst.refilling_car.payment_of_refueling
  IS 'Оплата за дозапраку';

---------------type_payment---------------16
CREATE TABLE cst.type_payment
(
  id                          INTEGER PRIMARY KEY,
  name_tp                     VARCHAR NOT NULL
);
COMMENT ON TABLE cst.type_payment
  IS 'Тип выплаты';
COMMENT ON COLUMN cst.type_payment.name_tp
  IS 'Название выплаты (аванс, зарплата и т.д.)';

 ---------------type_bank_card---------------17
CREATE TABLE cst.type_bank_card
(
  id                          INTEGER PRIMARY KEY,
  type_name                   VARCHAR NOT NULL
);
COMMENT ON TABLE cst.type_bank_card
  IS 'Тип банковськой карты';
COMMENT ON COLUMN cst.type_bank_card.type_name
  IS 'Название типа (зарплатная, водителькая, заправная и т.д.)';

---------------bank_card---------------18
CREATE TABLE cst.bank_card
(
  id                          INTEGER PRIMARY KEY,
  bank_card_number            INTEGER NOT NULL,
  id_type_bank_card           INTEGER NOT NULL,
  money_balance               DECIMAL NOT NULL,
  is_active                   INTEGER NOT NULL,
  FOREIGN KEY (id_type_bank_card) REFERENCES cst.type_bank_card(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX bk_unique_bank_card_number_idx ON cst.bank_card (bank_card_number);
COMMENT ON TABLE cst.bank_card
  IS 'Банковськая карта для заправки';
COMMENT ON COLUMN cst.bank_card.bank_card_number
  IS 'Номер банковськой карты (Номер должен быть уникальным)';
COMMENT ON COLUMN cst.bank_card.id_type_bank_card
  IS 'Тип банковськой карты (refueling, driver, salary, company)';
COMMENT ON COLUMN cst.bank_card.money_balance
  IS 'Всего денег на банковськой карте';
COMMENT ON COLUMN cst.bank_card.is_active
  IS '1 - активная, 2 - не активная';

---------------user_bank_card---------------19
CREATE TABLE cst.user_bank_card
(
  id                          INTEGER PRIMARY KEY,
  id_user                     INTEGER NOT NULL,
  id_bank_card                INTEGER NOT NULL,
  FOREIGN KEY (id_user) REFERENCES cst.users(id) ON DELETE CASCADE,
  FOREIGN KEY (id_bank_card) REFERENCES cst.bank_card(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.user_bank_card
  IS 'Банковськая карта usera';

---------------bank_card_operations---------------20
CREATE TABLE cst.bank_card_operations
(
  id                          INTEGER PRIMARY KEY,
  id_user_bank_card           INTEGER NOT NULL,
  date_time_arrivel_money     TIMESTAMP NOT NULL,
  id_bank_card_arrivel        INTEGER NOT NULL,
  arrival_money               DECIMAL NOT NULL,
  date_time_spending_money    TIMESTAMP NOT NULL,
  id_bank_card_spending       INTEGER NOT NULL,
  spending_money              DECIMAL NOT NULL,
  money_balance               DECIMAL NOT NULL,
  FOREIGN KEY (id_bank_card_arrivel) REFERENCES cst.bank_card(id) ON DELETE CASCADE,
  FOREIGN KEY (id_bank_card_spending) REFERENCES cst.bank_card(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.bank_card_operations
  IS 'Банковськие операции';
COMMENT ON COLUMN cst.bank_card_operations.id_user_bank_card
  IS 'Юзер и его банковськая карта';
COMMENT ON COLUMN cst.bank_card_operations.date_time_arrivel_money
  IS 'Дата и время прихода денег';
COMMENT ON COLUMN cst.bank_card_operations.id_bank_card_arrivel
  IS 'От какой банковськой карты перечислили приход денег';
COMMENT ON COLUMN cst.bank_card_operations.arrival_money
  IS 'Приход денег';
COMMENT ON COLUMN cst.bank_card_operations.date_time_spending_money
  IS 'Дата и время расхода денег';
COMMENT ON COLUMN cst.bank_card_operations.id_bank_card_spending
  IS 'На какую банковськую карту перечислили расход денег';
COMMENT ON COLUMN cst.bank_card_operations.spending_money
  IS 'Расход денег';
COMMENT ON COLUMN cst.bank_card_operations.money_balance
  IS 'Всего денег на банковськой карте';

---------------taxi_job_status---------------21
CREATE TABLE cst.taxi_job_status
(
  id                          INTEGER PRIMARY KEY,
  name_tjs                    VARCHAR NOT NULL
);
COMMENT ON TABLE cst.taxi_job_status
  IS 'Роботчий статус такси';
COMMENT ON COLUMN cst.taxi_job_status.name_tjs
  IS 'Название статуса (1-свободный, 2-занятый, 3-на обеде)';

---------------taxi_user_vehicle_info---------------22
CREATE TABLE cst.taxi_user_vehicle_info
(
  id                          INTEGER PRIMARY KEY,
  id_user_vehicle             INTEGER NOT NULL,
  id_taxi_job_status          INTEGER NOT NULL,
  FOREIGN KEY (id_user_vehicle) REFERENCES cst.user_vehicles(id) ON DELETE CASCADE,
  FOREIGN KEY (id_taxi_job_status) REFERENCES cst.taxi_job_status(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.taxi_user_vehicle_info
  IS 'Вся информация о юзаре, ТС, а также текущий рабочий статус';

---------------departments_company---------------23
CREATE TABLE cst.departments_company
(
  id                          INTEGER PRIMARY KEY,
  name_dc                     VARCHAR NOT NULL
);
COMMENT ON TABLE cst.departments_company
  IS 'Отделы компании';
COMMENT ON COLUMN cst.departments_company.name_dc
  IS 'Название отдела';

---------------payroll_accounting---------------24
CREATE TABLE cst.payroll_accounting
(
  id                             INTEGER PRIMARY KEY,
  id_department_company          INTEGER NOT NULL,
  id_user                        INTEGER NOT NULL,
  id_bank_card                   INTEGER NOT NULL,
  month_year                     VARCHAR NOT NULL,
  norm_number_days_worked_month  INTEGER NOT NULL,
  number_days_worked_month       INTEGER NOT NULL,
  stake_days                     DECIMAL NOT NULL,
  norm_hours_worked_month        INTEGER NOT NULL,
  hours_worked_month             INTEGER NOT NULL,
  stake_hour                     DECIMAL NOT NULL,
  payment_date_time              TIMESTAMP NOT NULL,
  id_type_payment                INTEGER NOT NULL,
  payout                         DECIMAL NOT NULL,
  sum_payout_month               DECIMAL NOT NULL,
  FOREIGN KEY (id_department_company) REFERENCES cst.departments_company(id) ON DELETE CASCADE,
  FOREIGN KEY (id_user) REFERENCES cst.users(id) ON DELETE CASCADE,
  FOREIGN KEY (id_bank_card) REFERENCES cst.bank_card(id) ON DELETE CASCADE,
  FOREIGN KEY (id_type_payment) REFERENCES cst.type_payment(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.payroll_accounting
  IS 'Учет заработной платы';
COMMENT ON COLUMN cst.payroll_accounting.id_department_company
  IS 'Название отдела';
COMMENT ON COLUMN cst.payroll_accounting.id_user
  IS 'Юзер';
COMMENT ON COLUMN cst.payroll_accounting.id_bank_card
  IS 'Банковськая карта юзера';
COMMENT ON COLUMN cst.payroll_accounting.month_year
  IS 'За какой год и месяц начисляется заробатная плата';
COMMENT ON COLUMN cst.payroll_accounting.norm_number_days_worked_month
  IS 'Норма отработаных дней';
COMMENT ON COLUMN cst.payroll_accounting.number_days_worked_month
  IS 'Отработаные дни юзера';
COMMENT ON COLUMN cst.payroll_accounting.stake_days
  IS 'Ставка за день';
COMMENT ON COLUMN cst.payroll_accounting.norm_hours_worked_month
  IS 'Норма отработаных часов за месяц';
COMMENT ON COLUMN cst.payroll_accounting.hours_worked_month
  IS 'Отработаные часы за месяц';
COMMENT ON COLUMN cst.payroll_accounting.stake_hour
  IS 'Ставка за час';
COMMENT ON COLUMN cst.payroll_accounting.payment_date_time
  IS 'Дата и время оплаты';
COMMENT ON COLUMN cst.payroll_accounting.id_type_payment
  IS 'Тип оплаты';
COMMENT ON COLUMN cst.payroll_accounting.payout
  IS 'Выплата';
COMMENT ON COLUMN cst.payroll_accounting.sum_payout_month
  IS 'Всего выплачено за месяц';

---------------pay_sheet---------------25
CREATE TABLE cst.pay_sheet
(
  id                             INTEGER PRIMARY KEY,
  id_user                        INTEGER NOT NULL,
  id_bank_card                   INTEGER NOT NULL,
  id_department_company          INTEGER NOT NULL,
  month_year                     VARCHAR NOT NULL,
  prepayment                     DECIMAL NOT NULL,
  salary                         DECIMAL NOT NULL,
  premium                        DECIMAL NOT NULL,
  hospitals                      DECIMAL NOT NULL,
  holiday                        DECIMAL NOT NULL,
  amount_without_taxes           DECIMAL NOT NULL,
  ndfl                           DECIMAL NOT NULL,
  esv                            DECIMAL NOT NULL,
  vs                             DECIMAL NOT NULL,
  amount_with_texas              DECIMAL NOT NULL,
  norm_hours_worked              INTEGER NOT NULL,
  hours_worked                   INTEGER NOT NULL,
  hours_holiday                  INTEGER,
  FOREIGN KEY (id_department_company) REFERENCES cst.departments_company(id) ON DELETE CASCADE,
  FOREIGN KEY (id_user) REFERENCES cst.users(id) ON DELETE CASCADE,
  FOREIGN KEY (id_bank_card) REFERENCES cst.bank_card(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.pay_sheet
  IS 'Расчетный лист';
COMMENT ON COLUMN cst.pay_sheet.id_department_company
  IS 'Название отдела';
COMMENT ON COLUMN cst.pay_sheet.id_user
  IS 'Юзер';
COMMENT ON COLUMN cst.pay_sheet.id_bank_card
  IS 'Банковськая карта юзера';
COMMENT ON COLUMN cst.pay_sheet.month_year
  IS 'За какой год и месяц начисляется заробатная плата';
COMMENT ON COLUMN cst.pay_sheet.prepayment
  IS 'Аванс';
COMMENT ON COLUMN cst.pay_sheet.salary
  IS 'Зарплата';
COMMENT ON COLUMN cst.pay_sheet.premium
  IS 'Премия';
COMMENT ON COLUMN cst.pay_sheet.hospitals
  IS 'Больничные';
COMMENT ON COLUMN cst.pay_sheet.holiday
  IS 'Отпускные';
COMMENT ON COLUMN cst.pay_sheet.amount_without_taxes
  IS 'Начислена сумма без налогов';
COMMENT ON COLUMN cst.pay_sheet.ndfl
  IS 'Подоходный налог с зарплаты, или налог на доходы физических лиц (НДФЛ) установлен в размере 18-20%.';
COMMENT ON COLUMN cst.pay_sheet.esv
  IS 'Единый социальный взнос (ЕСВ). Ставка составляет от 22% до 41% в зависимости от категорий плательщиков и классов риска вида деятельности.';
COMMENT ON COLUMN cst.pay_sheet.vs
  IS 'Военный сбор – 1,5%.';
COMMENT ON COLUMN cst.pay_sheet.amount_with_texas
  IS 'Начислена сумма с налогами';
COMMENT ON COLUMN cst.pay_sheet.norm_hours_worked
  IS 'Норма отработаных часов';
COMMENT ON COLUMN cst.pay_sheet.hours_worked
  IS 'Отработаны часы';
COMMENT ON COLUMN cst.pay_sheet.hours_holiday
  IS 'Сколько часов был на больничном';

