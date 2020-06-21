INSERT INTO chat.conversation(
    id, user_1_id, user_2_id, deleted)
    VALUES (nextval('conversation_id_seq'), 1, 2, false); /*1*/

INSERT INTO chat.message(
    id, conversation_id, sender_id, receiver_id, text, timestamp, deleted)
    VALUES (nextval('message_id_seq'), 1, 1, 2, 'Poozdrav', 1592578800, false); /*1*/
INSERT INTO chat.message(
    id, conversation_id, sender_id, receiver_id, text, timestamp, deleted)
    VALUES (nextval('message_id_seq'), 1, 2, 1, 'Ooooo', 1592578800, false); /*1*/

