insert into task5.user (name, login, password) VALUES ('Masha', 'masha_log', 'mash_pass');
insert into task5.user (name, login, password) VALUES ('1', '1', '1');
insert into task5.user (name, login, password) VALUES ('mockUser', 'admin', '1');

insert into task5.role(role_description) value ('ADMIN');
insert into task5.role(role_description) value ('USER');

CREATE TABLE task5.user_role
(
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    constraint role_fk foreign key (role_id) references task5.role(role_id),
    constraint user_fk foreign key (id) references task5.user (id)

);

insert into task5.user_role value (1,1);
insert into task5.user_role value (1,2);
insert into task5.user_role value (6,2);
insert into task5.user_role value (19,1);

CREATE TABLE user_role
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    primary key (user_id, role_id),
    constraint user_fk foreign key (user_id) references task5.user (id),
    constraint role_fk foreign key (role_id) references task5.role (role_id)
);

CREATE TABLE userLogin_roleDesc
(
    userlogin varchar(255) unique NOT NULL,
    roledesc varchar(255) NOT NULL,
    primary key (userlogin, roledesc),
    constraint user_lg foreign key (userlogin) references task5.user (login),
    constraint role_ds foreign key (roledesc) references task5.role (role_description)
);