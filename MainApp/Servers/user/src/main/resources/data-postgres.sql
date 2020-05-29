INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'ACTIVATE_USER_PERMISSION'); /*1*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DEACTIVATE_USER_PERMISSION'); /*2*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'REMOVE_USER_PERMISSION'); /*3*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_USER_PERMISSION'); /*4*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_USER_PERMISSION'); /*5*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CREATE_RENTAL_PERMISSION'); /*6*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'CHANGE_RENTAL_PERMISSION'); /*7*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'DELETE_RENTAL_PERMISSION'); /*8*/

INSERT INTO users.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_ADMIN'); /*1*/
INSERT INTO users.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_SIMPLE_USER'); /*2*/
INSERT INTO users.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_VEHICLE_OWNER'); /*3*/


INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (1, 5);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (2, 5);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (1, 1);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (1, 2);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (1, 3);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (1, 4);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (3, 6);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (3, 7);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (3, 8);

INSERT INTO users.users(
	id, address, city, email, name, password, phone_number, state, surname, enabled, deleted ) /*1*/
	VALUES (nextval('users_id_seq'), 'Vladimira Iljica Lenjina 1917', 'Moskva', 'admin@admin.rs', 'Vladimir', '$2y$10$ahB446esJK/dBa0AoJlMq.F.i9s7D5/4089gX34SC4fEpvshC3T7S', '067/123',  'Srbija', 'Lenjin', true, false);

INSERT INTO users.user_role(
    user_id, role_id)
    VALUES (1, 1);
INSERT INTO users.user_role(
    user_id, role_id)
    VALUES (1, 2);
INSERT INTO users.user_role(
    user_id, role_id)
    VALUES (1, 3);