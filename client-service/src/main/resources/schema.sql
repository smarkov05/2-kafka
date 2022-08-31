CREATE TABLE client (
    id bigint auto_increment ,
    fullname VARCHAR(255),
    phone VARCHAR(15),
    address VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE pizza_order (
    id bigint auto_increment ,
    name VARCHAR(255),
    quantity bigint,
    status VARCHAR(255),
    client_id bigint,
    PRIMARY KEY (id)
);

ALTER TABLE pizza_order
   ADD CONSTRAINT FK54uo82jnot7ye32pyc8dcj2eh
   FOREIGN KEY (client_id)
   REFERENCES client (id);