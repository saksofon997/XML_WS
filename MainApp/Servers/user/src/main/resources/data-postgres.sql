CREATE SCHEMA IF NOT EXISTS users;

INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'READ_PERMISSION'); /*1*/
INSERT INTO users.permission(
    id, name)
    VALUES (nextval('permissions_id_seq'), 'WRITE_PERMISSION'); /*2*/

INSERT INTO users.role(
    id, name)
    VALUES (nextval('roles_id_seq'), 'ROLE_ADMIN'); /*1*/

INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (1, 1);
INSERT INTO users.role_permission(
    roles_id, permissions_id)
    VALUES (1, 2);

INSERT INTO users.users(
	id, address, city, email, name, password, phone_number, state, surname, enabled ) /*1*/
	VALUES (nextval('users_id_seq'), 'Vladimira Iljica Lenjina 1917', 'Moskva', 'admin@admin.rs', 'Vladimir', '$2y$10$ahB446esJK/dBa0AoJlMq.F.i9s7D5/4089gX34SC4fEpvshC3T7S', '067/123',  'Srbija', 'Lenjin', true);

INSERT INTO users.user_role(
    user_id, role_id)
    VALUES (1, 1);
