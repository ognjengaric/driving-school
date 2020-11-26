insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_INSTRUCTOR');
insert into role (name) values ('ROLE_CANDIDATE');

insert into driving_school (id, name, short_name) values ('#2381934', 'Alunno', 'ALN');
insert into driving_school (id, name, short_name) values ('#324534', 'Auto Rad', 'ARD');
insert into driving_school (id, name, short_name) values ('#235343', 'Green', 'GRN');

insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 0);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 1);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 2);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 3);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 4);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 5);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 6);
insert into driving_school_available_categories (driving_school_id, available_categories) values ('#2381934', 7);

insert into usr (id, password, name, surname) values (-1, '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm', 'Vlada', 'Petrovic');
insert into usr (id, password, name, surname) values (-2, '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm', 'Milos', 'Vlatkovic');
insert into usr (id, password, name, surname) values (-3, '$2y$12$N9lpz5Pa/Eo8ToRGSch3OOvYnCYu6fPmWegTwy4HLasoc45jsYbvm', 'Jovan', 'Matic');

insert into instructor (id, driving_school_id) values (-2, '#2381934');
insert into candidate (id, current_licence, driving_school_id, instructor_id) values (-3, 2, '#2381934', -2);

insert into driving_school_candidates(driving_school_id, candidates_id) values ('#2381934', -3);
insert into driving_school_instructors(driving_school_id, instructors_id) values ('#2381934', -2);

insert into candidate_owned_licences (candidate_id, owned_licences) values (-3, 0);
insert into candidate_owned_licences (candidate_id, owned_licences) values (-3, 2);
insert into candidate_owned_licences (candidate_id, owned_licences) values (-3, 4);

insert into usr_roles (usr_id, roles_name) values (-1, 'ROLE_ADMIN');
insert into usr_roles (usr_id, roles_name) values (-2, 'ROLE_INSTRUCTOR');
insert into usr_roles (usr_id, roles_name) values (-3, 'ROLE_CANDIDATE');

