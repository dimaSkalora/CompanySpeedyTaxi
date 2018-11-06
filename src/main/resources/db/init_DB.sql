DROP TABLE IF EXISTS cst.users;
DROP TABLE IF EXISTS cst.user_roles;
DROP TABLE IF EXISTS cst.vehicles;
DROP TABLE IF EXISTS cst.user_vehicles;
DROP TABLE IF EXISTS cst.user_state;
DROP TABLE IF EXISTS cst.vehicle_state;

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
  user_id         INTEGER NOT NULL,
  role   	        VARCHAR,
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
  id 			          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name_car		      VARCHAR NOT NULL,
  year_issue		    VARCHAR(4) NOT NULL,
  category		      VARCHAR NOT NULL,
  color			        VARCHAR NOT NULL,
  fuel_consumption	INTEGER NOT NULL,
  amount_vehicles	  INTEGER NOT NULL
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
	id 				              INTEGER PRIMARY KEY,
	start_date       		    TIMESTAMP DEFAULT now() NOT NULL,
	end_date	       		    TIMESTAMP,
 	id_user				          INTEGER NOT NULL,
 	id_vehicle		          INTEGER NOT NULL,
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
	id					    INTEGER PRIMARY KEY,
	name_us				  VARCHAR NOT NULL
);
COMMENT ON COLUMN cst.user_state.name_us
    IS 'Название состояния (здоровый, больной, и т.д.)';

---------------vehicle_state---------------6
CREATE TABLE cst.vehicle_state
(
	id					    INTEGER PRIMARY KEY,
	name_vs				  VARCHAR NOT NULL
);
COMMENT ON COLUMN cst.vehicle_state.name_vs
    IS 'Название состояния (робочая, не рабочая, и т.д.)';

