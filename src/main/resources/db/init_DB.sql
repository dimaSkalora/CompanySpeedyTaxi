DROP TABLE IF EXISTS cst.users CASCADE;
DROP TABLE IF EXISTS cst.user_roles CASCADE;
DROP TABLE IF EXISTS cst.vehicles CASCADE;
DROP TABLE IF EXISTS cst.user_vehicles CASCADE;
DROP TABLE IF EXISTS cst.user_state CASCADE;
DROP TABLE IF EXISTS cst.vehicle_state CASCADE;
DROP TABLE IF EXISTS cst.access_to_route;
DROP TABLE IF EXISTS cst.taxi_dispatchers CASCADE;
DROP TABLE IF EXISTS cst.taxi_dispatcher_orders;
DROP TABLE IF EXISTS cst.taxi_user_orders;
DROP TABLE IF EXISTS cst.taxi_order_acceptance;
DROP TABLE IF EXISTS cst.coming_consuption_fuel;

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
  id                    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name_car              VARCHAR NOT NULL,
  year_issue            VARCHAR(4) NOT NULL,
  category              VARCHAR NOT NULL,
  color                 VARCHAR NOT NULL,
  fuel_consumption      INTEGER NOT NULL,
  amount_vehicles       INTEGER NOT NULL
);
CREATE UNIQUE INDEX vehicle_unique_name_idx ON cst.vehicles(name_car); --Название должно быть уникальным
COMMENT ON TABLE cst.vehicles
    IS 'Транспортное средство';
COMMENT ON COLUMN cst.vehicles.name_car
    IS 'Название, модель ТС';
COMMENT ON COLUMN cst.vehicles.year_issue
    IS 'Год выпуска ТС';
COMMENT ON COLUMN cst.vehicles.category
    IS 'Легковая, грузовая, автобус и т.д.';
COMMENT ON COLUMN cst.vehicles.fuel_consumption
    IS 'Расход топлива л/100км';
COMMENT ON COLUMN cst.vehicles.amount_vehicles
    IS 'Количество ТС';

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
  fulfilled           INTEGER NOT NULL,
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
  fulfilled           INTEGER NOT NULL,
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
  id_taxi_dispatcher_order    INTEGER NOT NULL,
  id_taxi_user_order          INTEGER NOT NULL,
  execution_status            INTEGER NOT NULL,
  adoption_status             INTEGER NOT NULL,
  FOREIGN KEY (id_user_vehicle) REFERENCES cst.user_vehicles(id) ON DELETE CASCADE,
  FOREIGN KEY (id_taxi_dispatcher_order) REFERENCES cst.taxi_dispatcher_orders(id) ON DELETE CASCADE,
  FOREIGN KEY (id_taxi_user_order) REFERENCES cst.taxi_user_orders(id) ON DELETE CASCADE
);
COMMENT ON TABLE cst.taxi_order_acceptance
  IS 'Прием заказов от юзера и диспечера';
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



