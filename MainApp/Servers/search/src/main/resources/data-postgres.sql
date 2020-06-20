INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'BMW', false); /*1*/
INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Mercedes', false); /*2*/
INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Alfa Romeo', false); /*3*/
INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Mazda', false); /*4*/
INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Toyota', false); /*5*/
INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Tesla', false); /*6*/

INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'X5', 1, false); /*1*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'X4', 1, false); /*2*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Z8', 1, false); /*3*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'CLA 220', 2, false); /*4*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'SL 450', 2, false); /*5*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'GLS 550', 2, false); /*6*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Giulia', 3, false); /*7*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Stelvio', 3, false); /*8*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'MX-5', 4, false); /*9*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'CX-5', 4, false); /*10*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Prius', 5, false); /*11*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'RAV-4', 5, false); /*12*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Yaris', 5, false); /*13*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'Model S', 6, false); /*14*/

INSERT INTO search.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'SUV', false); /*1*/
INSERT INTO search.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'Compact', false); /*2*/
INSERT INTO search.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'Sedan', false); /*3*/

INSERT INTO search.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '5-speed manual', false); /*1*/
INSERT INTO search.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '6-speed manual', false); /*2*/
INSERT INTO search.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '6-speed automatic', false); /*3*/
INSERT INTO search.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '7-speed automatic', false); /*4*/
INSERT INTO search.transmission(
    id, name, deleted)
    VALUES (nextval('transmission_id_seq'), '8-speed automatic', false); /*5*/

INSERT INTO search.fuel(
    id, name, deleted)
    VALUES (nextval('fuel_id_seq'), 'Gas', false); /*1*/
INSERT INTO search.fuel(
    id, name, deleted)
    VALUES (nextval('fuel_id_seq'), 'Diesel', false); /*2*/
INSERT INTO search.fuel(
    id, name, deleted)
    VALUES (nextval('fuel_id_seq'), 'Electric', false); /*2*/

INSERT INTO search.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'BMW SUV 2020', 180, 1.2, true, 'Price for 2020 BMW SUV models.', false); /*1*/
INSERT INTO search.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Mercedes CLA', 220, 1.8, true, 'Price for Mercedes CLA models.', false); /*2*/
INSERT INTO search.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Lower class and old', 90, 0.9, false, 'Price for older models and lower class cars.', false); /*3*/
INSERT INTO search.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Compacts', 150, 1.1, false, 'Price for compact economic cars.', false); /*4*/
INSERT INTO search.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Modern sedans', 160, 1.5, true, 'Price for modern sedans', false); /*5*/

INSERT INTO search.vehicle( -- BMW X5 1
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 1, 1, 1, 5, 2, 5, 1, 100000, true, 1, 4, 1, 45.2553823, 19.8317609, false, 1, -1); /*1*/
INSERT INTO search.vehicle( -- Mercedes CLA 220 2
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 2, 4, 3, 4, 2, 4, 0, 130000, false, 2, 3, 1, 44.766226, 20.443431, false, 2, 2000); /*2*/
INSERT INTO search.vehicle( -- BMW Z8 3
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 1, 3, 3, 1, 2, 2, 0, 90000, false, 3, 5, 1, 44.766226, 20.443431, false, 1, 7000); /*3*/
INSERT INTO search.vehicle( -- Mercedes SL 450 4
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 2, 5, 3, 2, 1, 2, 0, 250000, true, 5, 2, 1, 44.766226, 20.443431, false, 2, -1); /*4*/
INSERT INTO search.vehicle( -- Mercedes GLS 550 5
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 2, 6, 1, 4, 2, 5, 0, 120000, false, 2, 3, 1, 45.2553823, 19.8317609, false, 1, 4000); /*5*/
INSERT INTO search.vehicle( -- Alfa Romeo Giulia 6
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 3, 7, 3, 2, 2, 4, 0, 135000, true, 5, 4, 1, 45.2553823, 19.8317609, false, 2, 9000); /*6*/
INSERT INTO search.vehicle( -- Alfa Romeo Stelvio 7
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 3, 8, 1, 4, 2, 5, 1, 190000, true, 5, 4, 1, 45.2553823, 19.8317609, false, 1, -1); /*7*/
INSERT INTO search.vehicle( -- Mazda MX-5 8
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 4, 9, 3, 1, 1, 2, 0, 200000, false, 3, 5, 1, 45.2553823, 19.8317609, false, 2, 15000); /*8*/
INSERT INTO search.vehicle( -- Mazda CX-5 9
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 4, 10, 1, 4, 2, 5, 1, 210000, true, 5, 3, 1, 45.2553823, 19.8317609, false, 1, 12000); /*9*/
INSERT INTO search.vehicle( -- Toyota Prius 10
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 5, 11, 2, 3, 3, 5, 1, 145000, false, 4, 5, 1, 45.2553823, 19.8317609, false, 2, -1); /*10*/
INSERT INTO search.vehicle( -- Toyota RAV-4 11
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 5, 12, 1, 4, 1, 5, 0, 168000, true, 5, 4, 1, 45.2553823, 19.8317609, false, 1, 11000); /*11*/
INSERT INTO search.vehicle( -- Toyota Yaris 12
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 5, 13, 2, 1, 1, 4, 1, 98000, false, 4, 5, 1, 45.2553823, 19.8317609, false, 2, 9500); /*12*/
INSERT INTO search.vehicle( -- Tesla Model S 13
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted, owner_id, available_mileage)
    VALUES (nextval('vehicle_id_seq'), 6, 14, 3, 4, 3, 4, 0, 196000, true, 5, 4, 1, 45.2553823, 19.8317609, false, 1, -1); /*13*/


INSERT INTO search.vehicle_occupancy( -- 15.6. 15:00 -> 17.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592233200, 1592406000, 'MANUAL', 1, false); /*1*/
INSERT INTO search.vehicle_occupancy( -- 19.6. 15:00 -> 22.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592578800, 1592838000, 'MANUAL', 1, false); /*2*/
INSERT INTO search.vehicle_occupancy( -- 19.6. 15:00 -> 22.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592578800, 1592838000, 'MANUAL', 2, false); /*3*/

INSERT INTO search.review(
	id, customer_id, deleted, stars, status, text, vehicle_id, customer_name, date)
	VALUES (nextval('review_id_seq'), 1, false, 5, 'PENDING', 'Pohvala za vlasnika', 8, 'Vladimir', 1592233200);
INSERT INTO search.review(
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

INSERT INTO search.images(
    id, images)
    VALUES (1, 'bmwx5.jpg'); /*1*/
INSERT INTO search.images(
    id, images)
    VALUES (1, 'bmwx51.jpg'); /*1*/
INSERT INTO search.images(
    id, images)
    VALUES (1, 'bmwx52.jpg'); /*1*/
INSERT INTO search.images(
    id, images)
    VALUES (2, 'mercedescla220.jpg'); /*2*/
INSERT INTO search.images(
    id, images)
    VALUES (2, 'mercedescla2201.jpg'); /*2*/
INSERT INTO search.images(
    id, images)
    VALUES (2, 'mercedescla2202.jpg'); /*2*/
INSERT INTO search.images(
    id, images)
    VALUES (3, 'bmwz8.jpg'); /*3*/
INSERT INTO search.images(
    id, images)
    VALUES (3, 'bmwz81.jpg'); /*3*/
INSERT INTO search.images(
    id, images)
    VALUES (3, 'bmwz82.jpg'); /*3*/
INSERT INTO search.images(
    id, images)
    VALUES (4, 'mercedessl450.jpg'); /*4*/
INSERT INTO search.images(
    id, images)
    VALUES (4, 'mercedessl4501.jpg'); /*4*/
INSERT INTO search.images(
    id, images)
    VALUES (4, 'mercedessl4502.jpg'); /*4*/
INSERT INTO search.images(
    id, images)
    VALUES (5, 'mercedesgls550.jpg'); /*5*/
INSERT INTO search.images(
    id, images)
    VALUES (5, 'mercedesgls5501.jpg'); /*5*/
INSERT INTO search.images(
    id, images)
    VALUES (5, 'mercedesgls5502.jpg'); /*5*/
INSERT INTO search.images(
    id, images)
    VALUES (6, 'alfaromeogiulia.jpg'); /*6*/
INSERT INTO search.images(
    id, images)
    VALUES (6, 'alfaromeogiulia1.jpg'); /*6*/
INSERT INTO search.images(
    id, images)
    VALUES (6, 'alfaromeogiulia2.jpg'); /*6*/
INSERT INTO search.images(
    id, images)
    VALUES (7, 'alfaromeostelvio.jpg'); /*7*/
INSERT INTO search.images(
    id, images)
    VALUES (7, 'alfaromeostelvio1.jpg'); /*7*/
INSERT INTO search.images(
    id, images)
    VALUES (7, 'alfaromeostelvio2.jpg'); /*7*/
INSERT INTO search.images(
    id, images)
    VALUES (8, 'mazdamx5.jpg'); /*8*/
INSERT INTO search.images(
    id, images)
    VALUES (8, 'mazdamx51.jpg'); /*8*/
INSERT INTO search.images(
    id, images)
    VALUES (8, 'mazdamx52.jpg'); /*8*/
INSERT INTO search.images(
    id, images)
    VALUES (9, 'mazdacx5.jpg'); /*9*/
INSERT INTO search.images(
    id, images)
    VALUES (9, 'mazdacx51.jpg'); /*9*/
INSERT INTO search.images(
    id, images)
    VALUES (9, 'mazdacx52.jpg'); /*9*/
INSERT INTO search.images(
    id, images)
    VALUES (10, 'toyotaprius.jpg'); /*10*/
INSERT INTO search.images(
    id, images)
    VALUES (10, 'toyotaprius1.jpg'); /*10*/
INSERT INTO search.images(
    id, images)
    VALUES (10, 'toyotaprius2.jpg'); /*10*/
INSERT INTO search.images(
    id, images)
    VALUES (11, 'toyotarav4.jpg'); /*11*/
INSERT INTO search.images(
    id, images)
    VALUES (11, 'toyotarav41.jpg'); /*11*/
INSERT INTO search.images(
    id, images)
    VALUES (11, 'toyotarav42.jpg'); /*11*/
INSERT INTO search.images(
    id, images)
    VALUES (12, 'toyotayaris.jpg'); /*12*/
INSERT INTO search.images(
    id, images)
    VALUES (12, 'toyotayaris1.jpg'); /*12*/
INSERT INTO search.images(
    id, images)
    VALUES (12, 'toyotayaris2.jpg'); /*12*/
INSERT INTO search.images(
    id, images)
    VALUES (13, 'teslamodels.jpg'); /*13*/
INSERT INTO search.images(
    id, images)
    VALUES (13, 'teslamodels1.jpg'); /*13*/
INSERT INTO search.images(
    id, images)
    VALUES (13, 'teslamodels2.jpg'); /*13*/
