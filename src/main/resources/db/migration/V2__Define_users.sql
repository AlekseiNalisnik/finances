insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (first_name, last_name, password, username)
values
('user', 'user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user'),
('admin', 'admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);
