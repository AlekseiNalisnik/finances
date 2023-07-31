create table transactions (
    money numeric(38,2) not null,
    date_created timestamp(6) not null,
    id bigserial not null,
    wallet_id bigint,
    name varchar(50) not null unique,
    type varchar(50) not null,
    category varchar(75) not null,
    primary key (id)
);

create table roles (
    id bigserial not null,
    name varchar(255) not null unique,
    primary key (id)
);

create sequence users_seq start with 1 increment by 50;

create table users (
    id bigserial not null unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null unique,
    primary key (id)
);

create table users_roles (
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table users_wallets (
    user_id bigint not null,
    wallet_id bigint not null,
    primary key (user_id, wallet_id)
);

create table wallets (
    balance numeric(38,2) not null,
    date_created timestamp(6) not null,
    id bigserial not null,
    name varchar(100) not null unique,
    description varchar(250) not null,
    primary key (id)
);

create table wallets_transactions (
    transactions_id bigint not null unique,
    wallet_id bigint not null
);

alter table if exists transactions
   add constraint transactions_wallets_fk
   foreign key (wallet_id)
   references wallets;

alter table if exists users_wallets
   add constraint users_wallets_users_fk
   foreign key (user_id)
   references users;

alter table if exists users_wallets
   add constraint users_wallets_wallets_fk
   foreign key (wallet_id)
   references wallets;

alter table if exists wallets_transactions
   add constraint wallets_transactions_transactions_fk
   foreign key (transactions_id)
   references transactions;

alter table if exists wallets_transactions
   add constraint wallets_transactions_wallets_fk
   foreign key (wallet_id)
   references wallets;
