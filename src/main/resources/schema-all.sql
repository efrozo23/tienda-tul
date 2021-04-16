DROP TABLE Producto IF EXISTS;

CREATE TABLE Producto  (
    id VARCHAR(80) not null primary key,
    nombre VARCHAR(30) NOT null,
    sku VARCHAR(30) NOT null,
    precio double(3) not null,
    descripcion VARCHAR(100) NOT NULL,
    tipo_producto  ENUM('SIMPLE', 'DESCUENTO') NOT NULL
);

insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234234','teclado','568465','563.2','teclado gamer','SIMPLE');
insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234235','teclado','568465','563.2','teclado gamer','SIMPLE');
insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234236','teclado','568465','563.2','teclado gamer','SIMPLE');
insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234237','teclado','568465','563.2','teclado gamer','SIMPLE');