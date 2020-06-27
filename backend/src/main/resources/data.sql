insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_INSTRUCTOR');
insert into role (name) values ('ROLE_CANDIDATE');

insert into usr (id, password) values ('1', '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm');
insert into usr (id, password) values ('2', '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm');
insert into usr (id, password) values ('3', '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm');

insert into usr_roles (usr_id, roles_name) values (1, 'ROLE_ADMIN');
insert into usr_roles (usr_id, roles_name) values (2, 'ROLE_INSTRUCTOR');
insert into usr_roles (usr_id, roles_name) values (3, 'ROLE_CANDIDATE');

insert into driving_school (id, name, short_name) values ('#2381934', 'Alunno', 'ALN');

insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 0);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 1);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 2);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 3);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 4);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 5);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 6);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 7);