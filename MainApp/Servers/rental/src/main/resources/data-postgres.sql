INSERT INTO rental.bundle(
    id, name, deleted)
    VALUES (nextval('bundles_id_seq'), 'Test', false); /*1*/

INSERT INTO rental.rental( -- 17.6. 16:00 -> 17.6. 19:00 UTC
    id, start_time, end_time, vehicle_id, customer_id, owner_id, bundle_id, status, deleted)
    VALUES (nextval('rentals_id_seq'), 1592409600, 1592420400, 1, 1, 1, 1, 'PENDING', false); /*1*/
INSERT INTO rental.rental( -- 17.6. 16:00 -> 17.6. 19:00 UTC
    id, start_time, end_time, vehicle_id, customer_id, owner_id, bundle_id, status, deleted)
    VALUES (nextval('rentals_id_seq'), 1592409600, 1592420400, 2, 1, 1, 1, 'PENDING', false); /*2*/
