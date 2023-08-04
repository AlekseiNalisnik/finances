drop sequence IF EXISTS users_seq;

create sequence users_seq start with 3 increment by 1;

create sequence tokens_seq start with 3 increment by 1;

create table tokens (
    expired boolean,
    revoked boolean,
    id bigserial not null,
    token varchar(255),
    primary key (id)
);

insert into tokens (id, token, expired, revoked)
values
(1, null, true, true),
(2, null, true, true);
