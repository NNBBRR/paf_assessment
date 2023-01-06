
CREATE database eshop;

USE eshop;

CREATE TABLE customers
(
    id int auto_increment not null,
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    primary key(id)
);

CREATE TABLE order
(
    order_id char(8) not null,
    delivery_id varchar(128) not null,
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    status varchar(32),
    order_date date not null,
    primary key(order_id)
);

CREATE TABLE order_status
(
    order_id char(8) not null,
    delivery_id varchar(128) not null,
    status varchar(32),
    status_update datetime not null,
    primary key(delivery_id),
    constraint fk_order_id
        foreign key(order_id) references order(order_id)
)