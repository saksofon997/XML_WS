INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'ACTIVATE_USER_PERMISSION'); /*1*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DEACTIVATE_USER_PERMISSION'); /*2*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'REMOVE_USER_PERMISSION'); /*3*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_USER_PERMISSION'); /*4*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_USER_PERMISSION'); /*5*/

INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_RENTAL_PERMISSION'); /*6*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_RENTAL_PERMISSION'); /*7*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DELETE_RENTAL_PERMISSION'); /*8*/

INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_REVIEW_PERMISSION'); /*9*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_REVIEW_PERMISSION'); /*10*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DELETE_REVIEW_PERMISSION'); /*11*/

INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'APPROVE_REVIEW_PERMISSION'); /*12*/

INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_VEHICLE_PART_PERMISSION'); /*13*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_VEHICLE_PART_PERMISSION'); /*14*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DELETE_VEHICLE_PART_PERMISSION'); /*15*/

INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_PRICELIST_PERMISSION'); /*16*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_PRICELIST_PERMISSION'); /*17*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DELETE_PRICELIST_PERMISSION'); /*18*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'VIEW_PRICELIST_PERMISSION'); /*19*/

INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_VEHICLE_OCCUPANCY'); /*20*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_VEHICLE_OCCUPANCY'); /*21*/
INSERT INTO agent.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DELETE_VEHICLE_OCCUPANCY'); /*22*/


INSERT INTO agent.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_ADMIN'); /*1*/
INSERT INTO agent.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_SIMPLE_USER'); /*2*/
INSERT INTO agent.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_VEHICLE_OWNER'); /*3 needed?*/
INSERT INTO agent.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_REGISTERED_USER'); /*4*/
INSERT INTO agent.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_AGENT_COMPANY'); /*5 not sure...*/


INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 1);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 2);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 3);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 4);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 5);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 12);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 13);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 14);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 15);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 16);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 17);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 18);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 19);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 20);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 21);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (1, 22);

/*Add permissions to other agent after consulting*/
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (2, 5);

INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 5);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 6);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 7);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 8);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 9);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 10);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 11);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 16);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 17);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 18);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 19);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 20);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 21);
INSERT INTO agent.role_permission(
    roles_id, permissions_id)
    VALUES (3, 22);

INSERT INTO agent.users(
	id, address, city, email, name, password, phone_number, state, surname, enabled, deleted ) /*1*/
	VALUES (nextval('users_id_seq'), 'Vladimira Iljica Lenjina 1917', 'Moskva', 'admin@admin.rs', 'Vladimir', '$2y$10$ahB446esJK/dBa0AoJlMq.F.i9s7D5/4089gX34SC4fEpvshC3T7S', '067/123',  'Srbija', 'Lenjin', true, false);
INSERT INTO agent.users(
	id, address, city, email, name, password, phone_number, state, surname, enabled, deleted ) /*2*/
	VALUES (nextval('users_id_seq'), 'Vladimira Iljica Lenjina 1917', 'Moskva', 'che@revolution.cu', 'Ernesto', '$2y$10$ahB446esJK/dBa0AoJlMq.F.i9s7D5/4089gX34SC4fEpvshC3T7S', '067/123',  'Srbija', 'Guevara', true, false);

/* LENJIN JE BOG */
INSERT INTO agent.user_role(
    user_id, role_id)
    VALUES (1, 1);
INSERT INTO agent.user_role(
    user_id, role_id)
    VALUES (1, 2);
INSERT INTO agent.user_role(
    user_id, role_id)
    VALUES (1, 3);
INSERT INTO agent.user_role(
    user_id, role_id)
    VALUES (1, 4);
INSERT INTO agent.user_role(
    user_id, role_id)
    VALUES (1, 5);
/* El CHE */
INSERT INTO agent.user_role(
    user_id, role_id)
    VALUES (2, 3);


INSERT INTO agent.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'BMW', false); /*1*/
INSERT INTO agent.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Mercedes', false); /*2*/
INSERT INTO agent.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Alfa Romeo', false); /*3*/
INSERT INTO agent.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Mazda', false); /*4*/
INSERT INTO agent.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Toyota', false); /*5*/
INSERT INTO agent.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Tesla', false); /*6*/

INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'X5', 1, false); /*1*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'X4', 1, false); /*2*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Z8', 1, false); /*3*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'CLA 220', 2, false); /*4*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'SL 450', 2, false); /*5*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'GLS 550', 2, false); /*6*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Giulia', 3, false); /*7*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Stelvio', 3, false); /*8*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'MX-5', 4, false); /*9*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'CX-5', 4, false); /*10*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Prius', 5, false); /*11*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'RAV-4', 5, false); /*12*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Yaris', 5, false); /*13*/
INSERT INTO agent.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Model S', 6, false); /*14*/

INSERT INTO agent.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'SUV', false); /*1*/
INSERT INTO agent.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'Compact', false); /*2*/
INSERT INTO agent.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'Sedan', false); /*3*/

INSERT INTO agent.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '5-speed manual', false); /*1*/
INSERT INTO agent.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '6-speed manual', false); /*2*/
INSERT INTO agent.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '6-speed automatic', false); /*3*/
INSERT INTO agent.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '7-speed automatic', false); /*4*/
INSERT INTO agent.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '8-speed automatic', false); /*5*/

INSERT INTO agent.fuel(
    id, name, deleted)
    VALUES (nextval('fuel_id_seq'), 'Gas', false); /*1*/
INSERT INTO agent.fuel(
    id, name, deleted)
    VALUES (nextval('fuel_id_seq'), 'Diesel', false); /*2*/
INSERT INTO agent.fuel(
    id, name, deleted)
    VALUES (nextval('fuel_id_seq'), 'Electric', false); /*2*/

INSERT INTO agent.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'BMW SUV 2020', 180, 1.2, true, 'Price for 2020 BMW SUV models.', false); /*1*/
INSERT INTO agent.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Mercedes CLA', 220, 1.8, true, 'Price for Mercedes CLA models.', false); /*2*/
INSERT INTO agent.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Lower class and old', 90, 0.9, false, 'Price for older models and lower class cars.', false); /*3*/
INSERT INTO agent.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Compacts', 150, 1.1, false, 'Price for compact economic cars.', false); /*4*/
INSERT INTO agent.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Modern sedans', 160, 1.5, true, 'Price for modern sedans', false); /*5*/

INSERT INTO agent.vehicle( -- BMW X5 1
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 1, 1, 1, 5, 2, 5, 1, 100000, true, 1, 4, 8, 45.2553823, 19.8317609, false, 1, -1); /*1*/
INSERT INTO agent.vehicle( -- Mercedes CLA 220 2
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 2, 4, 3, 4, 2, 4, 0, 130000, false, 2, 3, 7, 44.766226, 20.443431, false, 2, 200); /*2*/
INSERT INTO agent.vehicle( -- BMW Z8 3
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 1, 3, 3, 1, 2, 2, 0, 90000, false, 3, 5, 3, 44.766226, 20.443431, false, 1, 700); /*3*/
INSERT INTO agent.vehicle( -- Mercedes SL 450 4
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 2, 5, 3, 2, 1, 2, 0, 250000, true, 5, 2, 15, 44.766226, 20.443431, false, 2, -1); /*4*/
INSERT INTO agent.vehicle( -- Mercedes GLS 550 5
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 2, 6, 1, 4, 2, 5, 0, 120000, false, 2, 3, 4, 45.2553823, 19.8317609, false, 1, 400); /*5*/
INSERT INTO agent.vehicle( -- Alfa Romeo Giulia 6
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 3, 7, 3, 2, 2, 4, 0, 135000, true, 5, 4, 3, 45.2553823, 19.8317609, false, 2, 900); /*6*/
INSERT INTO agent.vehicle( -- Alfa Romeo Stelvio 7
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 3, 8, 1, 4, 2, 5, 1, 190000, true, 5, 4, 2, 45.2553823, 19.8317609, false, 1, 1000); /*7*/
INSERT INTO agent.vehicle( -- Mazda MX-5 8
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 4, 9, 3, 1, 1, 2, 0, 200000, false, 3, 5, 11, 45.2553823, 19.8317609, false, 2, -1); /*8*/
INSERT INTO agent.vehicle( -- Mazda CX-5 9
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 4, 10, 1, 4, 2, 5, 1, 250000, true, 5, 3, 4, 45.2553823, 19.8317609, false, 1, -1); /*9*/
INSERT INTO agent.vehicle( -- Toyota Prius 10
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 5, 11, 2, 3, 3, 5, 1, 68000, false, 4, 5, 6, 45.2553823, 19.8317609, false, 2, 1500); /*10*/
INSERT INTO agent.vehicle( -- Toyota RAV-4 11
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 5, 12, 1, 4, 1, 5, 0, 156000, true, 5, 4, 5, 45.2553823, 19.8317609, false, 1, 150); /*11*/
INSERT INTO agent.vehicle( -- Toyota Yaris 12
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 5, 13, 2, 1, 1, 4, 1, 270000, false, 4, 5, 1, 45.2553823, 19.8317609, false, 2, -1); /*12*/
INSERT INTO agent.vehicle( -- Tesla Model S 13
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 6, 14, 3, 4, 3, 4, 0, 87000, true, 5, 4, 2, 45.2553823, 19.8317609, false, 1, 1100); /*13*/


INSERT INTO agent.vehicle_occupancy( -- 15.6. 15:00 -> 17.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592233200, 1592406000, 'MANUAL', 1, false); /*1*/
INSERT INTO agent.vehicle_occupancy( -- 19.6. 15:00 -> 22.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592578800, 1592838000, 'MANUAL', 1, false); /*2*/
INSERT INTO agent.vehicle_occupancy( -- 19.6. 15:00 -> 22.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592578800, 1592838000, 'MANUAL', 2, false); /*3*/

INSERT INTO agent.review(
	id, customer_id, deleted, stars, status, text, vehicle_id, customer_name, date)
	VALUES (nextval('review_id_seq'), 1, false, 5, 'PENDING', 'Pohvala za vlasnika', 8, 'Vladimir', 1592233200);
INSERT INTO agent.review(
	id, customer_id, deleted, stars, status, text, vehicle_id, customer_name, date)
	VALUES (nextval('review_id_seq'), 2, false, 5, 'PUBLISHED', 'Ma auto je strava', 8, 'Petko', 1592233200);
/*INSERT INTO vehicle.review(
	id, customer_id, deleted, stars, status, text, vehicle_id, customer_name, date)
	VALUES (nextval('review_id_seq'), 3, false, 3, 'PENDING', 'Ma auto je onako', 8, 'Pavle', 1592233200);
INSERT INTO vehicle.review(
	id, customer_id, deleted, stars, status, text, vehicle_id, customer_name, date)
	VALUES (nextval('review_id_seq'), 4, false, 1, 'PUBLISHED', 'Ne valja usluga', 8, 'Petar', 1592233200);
INSERT INTO vehicle.review(
	id, customer_id, deleted, stars, status, text, vehicle_id, customer_name, date)
	VALUES (nextval('review_id_seq'), 5, false, 2, 'PUBLISHED', 'Ma auto je bezveze', 8, 'Mihajlo', 1592233200);*/

INSERT INTO agent.images(
    id, images)
    VALUES (1, 'bmwx5.jpg'); /*1*/
INSERT INTO agent.images(
    id, images)
    VALUES (1, 'bmwx51.jpg'); /*1*/
INSERT INTO agent.images(
    id, images)
    VALUES (1, 'bmwx52.jpg'); /*1*/
INSERT INTO agent.images(
    id, images)
    VALUES (2, 'mercedescla220.jpg'); /*2*/
INSERT INTO agent.images(
    id, images)
    VALUES (2, 'mercedescla2201.jpg'); /*2*/
INSERT INTO agent.images(
    id, images)
    VALUES (2, 'mercedescla2202.jpg'); /*2*/
INSERT INTO agent.images(
    id, images)
    VALUES (3, 'bmwz8.jpg'); /*3*/
INSERT INTO agent.images(
    id, images)
    VALUES (3, 'bmwz81.jpg'); /*3*/
INSERT INTO agent.images(
    id, images)
    VALUES (3, 'bmwz82.jpg'); /*3*/
INSERT INTO agent.images(
    id, images)
    VALUES (4, 'mercedessl450.jpg'); /*4*/
INSERT INTO agent.images(
    id, images)
    VALUES (4, 'mercedessl4501.jpg'); /*4*/
INSERT INTO agent.images(
    id, images)
    VALUES (4, 'mercedessl4502.jpg'); /*4*/
INSERT INTO agent.images(
    id, images)
    VALUES (5, 'mercedesgls550.jpg'); /*5*/
INSERT INTO agent.images(
    id, images)
    VALUES (5, 'mercedesgls5501.jpg'); /*5*/
INSERT INTO agent.images(
    id, images)
    VALUES (5, 'mercedesgls5502.jpg'); /*5*/
INSERT INTO agent.images(
    id, images)
    VALUES (6, 'alfaromeogiulia.jpg'); /*6*/
INSERT INTO agent.images(
    id, images)
    VALUES (6, 'alfaromeogiulia1.jpg'); /*6*/
INSERT INTO agent.images(
    id, images)
    VALUES (6, 'alfaromeogiulia2.jpg'); /*6*/
INSERT INTO agent.images(
    id, images)
    VALUES (7, 'alfaromeostelvio.jpg'); /*7*/
INSERT INTO agent.images(
    id, images)
    VALUES (7, 'alfaromeostelvio1.jpg'); /*7*/
INSERT INTO agent.images(
    id, images)
    VALUES (7, 'alfaromeostelvio2.jpg'); /*7*/
INSERT INTO agent.images(
    id, images)
    VALUES (8, 'mazdamx5.jpg'); /*8*/
INSERT INTO agent.images(
    id, images)
    VALUES (8, 'mazdamx51.jpg'); /*8*/
INSERT INTO agent.images(
    id, images)
    VALUES (8, 'mazdamx52.jpg'); /*8*/
INSERT INTO agent.images(
    id, images)
    VALUES (9, 'mazdacx5.jpg'); /*9*/
INSERT INTO agent.images(
    id, images)
    VALUES (9, 'mazdacx51.jpg'); /*9*/
INSERT INTO agent.images(
    id, images)
    VALUES (9, 'mazdacx52.jpg'); /*9*/
INSERT INTO agent.images(
    id, images)
    VALUES (10, 'toyotaprius.jpg'); /*10*/
INSERT INTO agent.images(
    id, images)
    VALUES (10, 'toyotaprius1.jpg'); /*10*/
INSERT INTO agent.images(
    id, images)
    VALUES (10, 'toyotaprius2.jpg'); /*10*/
INSERT INTO agent.images(
    id, images)
    VALUES (11, 'toyotarav4.jpg'); /*11*/
INSERT INTO agent.images(
    id, images)
    VALUES (11, 'toyotarav41.jpg'); /*11*/
INSERT INTO agent.images(
    id, images)
    VALUES (11, 'toyotarav42.jpg'); /*11*/
INSERT INTO agent.images(
    id, images)
    VALUES (12, 'toyotayaris.jpg'); /*12*/
INSERT INTO agent.images(
    id, images)
    VALUES (12, 'toyotayaris1.jpg'); /*12*/
INSERT INTO agent.images(
    id, images)
    VALUES (12, 'toyotayaris2.jpg'); /*12*/
INSERT INTO agent.images(
    id, images)
    VALUES (13, 'teslamodels.jpg'); /*13*/
INSERT INTO agent.images(
    id, images)
    VALUES (13, 'teslamodels1.jpg'); /*13*/
INSERT INTO agent.images(
    id, images)
    VALUES (13, 'teslamodels2.jpg'); /*13*/

INSERT INTO agent.bundle(
    id, name, deleted)
    VALUES (nextval('bundles_id_seq'), 'Test', false); /*1*/

INSERT INTO agent.rental( -- 17.6. 16:00 -> 17.6. 19:00 UTC
    id, start_time, end_time, vehicle_id, customer_id, owner_id, bundle_id, status, deleted)
    VALUES (nextval('rentals_id_seq'), 1592409600, 1592420400, 1, 1, 1, 1, 'FINISHED', false); /*1*/
INSERT INTO agent.rental( -- 17.6. 16:00 -> 17.6. 19:00 UTC
    id, start_time, end_time, vehicle_id, customer_id, owner_id, bundle_id, status, deleted)
    VALUES (nextval('rentals_id_seq'), 1592409600, 1592420400, 2, 1, 1, 1, 'FINISHED', false); /*2*/
INSERT INTO agent.rental( -- 30.6. 15:00 -> 3.7. 15:00 UTC
    id, start_time, end_time, vehicle_id, customer_id, owner_id, bundle_id, status, deleted)
    VALUES (nextval('rentals_id_seq'), 1593529200, 1593788400, 2, 1, 1, null, 'RESERVED', false); /*3*/
INSERT INTO agent.rental( -- 3.7. 15:00 -> 7.7. 15:00 UTC
    id, start_time, end_time, vehicle_id, customer_id, owner_id, bundle_id, status, deleted)
    VALUES (nextval('rentals_id_seq'), 1593788400, 1594134000, 2, 1, 1, null, 'PENDING', false); /*4*/
INSERT INTO agent.rental( -- 3.7. 15:00 -> 7.7. 15:00 UTC
    id, start_time, end_time, vehicle_id, customer_id, owner_id, bundle_id, status, deleted)
    VALUES (nextval('rentals_id_seq'), 1593788400, 1594134000, 2, 1, 1, null, 'PENDING', false); /*5*/
