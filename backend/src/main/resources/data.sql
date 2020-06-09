insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_INSTRUCTOR');
insert into role (name) values ('ROLE_CANDIDATE');

insert into usr (id, password) values ('1', '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm');
insert into usr (id, password) values ('2', '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm');
insert into usr (id, password) values ('3', '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm');

insert into usr_roles (usr_id, roles_name) values (1, 'ROLE_ADMIN');
insert into usr_roles (usr_id, roles_name) values (2, 'ROLE_INSTRUCTOR');
insert into usr_roles (usr_id, roles_name) values (3, 'ROLE_CANDIDATE');