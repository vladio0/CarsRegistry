DROP TABLE IF EXISTS vehicle_owner;
DROP TABLE IF EXISTS vehicle_document;
DROP TABLE IF EXISTS truck_car;
DROP TABLE IF EXISTS passenger_car;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS owner;

DROP TABLE IF EXISTS register_office;
DROP TABLE IF EXISTS mvs_office;
DROP TABLE IF EXISTS offices_struct;

DROP TABLE IF EXISTS street;

CREATE TABLE street(
		street_code integer not null,
		street_name varchar(200),
		PRIMARY KEY(street_code)
);

CREATE TABLE offices_struct(
		city_id char(12) not null,
		city_name varchar(200),
		PRIMARY KEY(city_id)
);

CREATE TABLE mvs_office(
		mvs_office_id integer not null,
		mvs_office_city_id char(100) not null,
		mvs_office_name varchar(200),
		is_active boolean,
		PRIMARY KEY(mvs_office_id),
		FOREIGN KEY(mvs_office_city_id) REFERENCES offices_struct(city_id) ON DELETE RESTRICT
);

CREATE TABLE register_office(
        r_office_id integer not null,
        r_office_city_id char(100) not null,
        r_office_name varchar(200),
        PRIMARY KEY (r_office_id),
        FOREIGN KEY (r_office_city_id) REFERENCES offices_struct(city_id) ON DELETE RESTRICT
);

CREATE TABLE owner(
        owner_id integer not null,
        owner_name varchar(50) not null,
        owner_surname varchar(50) not null,
        birth_date date not null,
        phone varchar(20),
        email varchar(100),
        PRIMARY KEY(owner_id)
);

CREATE TABLE vehicle(
        vehicle_id SERIAL,
        car_order_status int not null,
        car_order_date timestamp not null,
        p_car_mark varchar(100) not null,
        p_car_model varchar(100) not null,
        p_car_country varchar(50) not null,
        p_license_plate varchar(50) not null,
        p_car_year integer not null,
        p_car_colour varchar(50) not null,
        p_fuel_type varchar(50) not null,
        p_weight integer,
        p_max_allowed_weight integer not null,
        p_street_code integer not null,
        p_building varchar(10),
        p_apartment varchar(10),
        t_car_mark varchar(100) not null,
        t_car_model varchar(100) not null,
        t_car_country varchar(50) not null,
        t_license_plate varchar(50) not null,
        t_car_year integer not null,
        t_car_colour varchar(50) not null,
        t_fuel_type varchar(50) not null,
        t_weight integer,
        t_max_allowed_weight integer not null,
        t_street_code integer not null,
        t_building varchar(25),
        t_apartment varchar (25),
        register_office_id integer,
        registration_date date not null,
        PRIMARY KEY(vehicle_id),
        FOREIGN KEY (p_street_code) REFERENCES street(street_code) ON DELETE RESTRICT,
        FOREIGN KEY (t_street_code) REFERENCES street(street_code) ON DELETE RESTRICT,
        FOREIGN KEY (register_office_id) REFERENCES register_office(r_office_id) ON DELETE RESTRICT
);


CREATE TABLE vehicle_document(
        document_car_id SERIAL,
        vehicle_id integer not null,
        d_car_mark varchar(100) not null,
        d_car_model varchar(100) not null,
        d_car_country varchar(50) not null,
        d_license_plate varchar(50) not null,
        d_car_year integer not null,
        d_car_colour varchar(50) not null,
        d_fuel_type varchar(50) not null,
        d_weight integer not null,
        doc_type varchar(50) not null,
        d_issued_at date,
        d_expires_at date,
        d_register_office_id integer,
        is_active boolean not null default true,
        d_street_code integer not null,
        d_building varchar(25),
        d_apartment varchar(25),
        PRIMARY KEY(document_car_id),
        FOREIGN KEY (d_street_code) REFERENCES street(street_code) ON DELETE RESTRICT,
        FOREIGN KEY (d_register_office_id) REFERENCES register_office(r_office_id) ON DELETE RESTRICT
);

CREATE TABLE vehicle_owner(
        vehicle_id integer not null,
        owner_id integer not null,
        date_from date,
        date_to date,
        PRIMARY KEY (vehicle_id, owner_id, date_from),
        FOREIGN KEY (vehicle_id) REFERENCES vehicle(vehicle_id) ON DELETE RESTRICT,
        FOREIGN KEY (owner_id) REFERENCES owner(owner_id) ON DELETE RESTRICT
);
