INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'BMW', false); /*1*/
INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Mercedes', false); /*2*/
INSERT INTO search.brand(
    id, name, deleted)
    VALUES (nextval('brand_id_seq'), 'Alfa Romeo', false); /*3*/

INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'X5', 1, false); /*1*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'X4', 1, false); /*2*/
INSERT INTO search.model(
    id, name, brand_id, deleted)
    VALUES (nextval('model_id_seq'), 'CLA 220', 2, false); /*3*/

INSERT INTO search.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'SUV', false); /*1*/
INSERT INTO search.category(
    id, name, deleted)
    VALUES (nextval('category_id_seq'), 'Compact executive car', false); /*2*/

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
    VALUES (nextval('fuel_id_seq'), 'Gasoline', false); /*1*/
INSERT INTO search.fuel(
    id, name, deleted)
    VALUES (nextval('fuel_id_seq'), 'Diesel', false); /*2*/

INSERT INTO search.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'BMW SUV 2020', 180, 1.2, 50, 'Price for 2020 BMW SUV models', false); /*1*/
INSERT INTO search.pricelist(
    id, owner, name, price_per_day, price_per_km, cdw, description, deleted)
    VALUES (nextval('pricelist_id_seq'), 1, 'Mercedes CLA', 220, 1.8, 60, 'Price for Mercedes CLA models', false); /*2*/

INSERT INTO search.vehicle( -- BMW X5
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted)
    VALUES (nextval('vehicle_id_seq'), 1, 1, 1, 5, 2, 5, 1, -1, 1, 1, 0, 0, 45.2553823, 19.8317609, false); /*1*/
INSERT INTO search.vehicle( -- Mercedes CLA 220
    id, brand_id, model_id, category_id, transmission_id, fuel_id, seats, child_seats, mileage, cdw, pricelist_id, number_of_stars, number_of_reviews, location_latitude, location_longitude, deleted)
    VALUES (nextval('vehicle_id_seq'), 2, 3, 2, 4, 2, 4, 0, -1, 1, 2, 0, 0, 45.2553823, 19.8317609, false); /*2*/

INSERT INTO search.vehicle_occupancy( -- 15.6. 15:00 -> 17.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592233200, 1592406000, 'MANUAL', 1, false); /*1*/
INSERT INTO search.vehicle_occupancy( -- 19.6. 15:00 -> 22.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592578800, 1592838000, 'MANUAL', 1, false); /*2*/
INSERT INTO search.vehicle_occupancy( -- 19.6. 15:00 -> 22.6. 15:00 UTC
    id, start_time, end_time, type, vehicle_id, deleted)
    VALUES (nextval('vehicle_occupancy_id_seq'), 1592578800, 1592838000, 'MANUAL', 2, false); /*3*/

INSERT INTO search.images(
    id, images)
    VALUES (1, 'bmwX5.jpg'); /*1*/
INSERT INTO search.images(
    id, images)
    VALUES (1, 'bmwX51.jpg'); /*1*/
