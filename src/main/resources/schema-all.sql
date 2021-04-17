DROP TABLE Producto IF EXISTS;

CREATE TABLE Producto  (
    id VARCHAR(80) not null primary key,
    nombre VARCHAR(30) NOT null,
    sku VARCHAR(30) NOT null,
    precio double(3) not null,
    descripcion VARCHAR(100) NOT NULL,
    tipo_producto  ENUM('SIMPLE', 'DESCUENTO') NOT NULL
);


create table carrito(
	id VARCHAR(80) not null primary key,
	id_producto VARCHAR(80) not null,
	cantidad int not null,
	estado ENUM('PENDIENTE', 'COMPLETADO') NOT NULL
);

ALTER TABLE carrito ADD FOREIGN KEY (id_producto) REFERENCES Producto(id) ON DELETE CASCADE;

insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234234','teclado','568465','563.2','teclado gamer','SIMPLE');
insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234235','teclado','568465','563.2','teclado gamer','SIMPLE');
insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234236','teclado','568465','563.2','teclado gamer','SIMPLE');
insert into Producto (id,nombre,sku,precio,descripcion,tipo_producto) values ('wer234237','teclado','568465','563.2','teclado gamer','SIMPLE');

insert into carrito (id,id_producto,cantidad,estado)  values ('545452342sdf','wer234234','2','PENDIENTE');
insert into carrito (id,id_producto,cantidad,estado)  values ('545452342sdfg','wer234234','2','PENDIENTE');
insert into carrito (id,id_producto,cantidad,estado)  values ('545452342sdfm','wer234234','2','PENDIENTE');


select sum(val) from (
select p.id,p.precio,count(p.id),(p.precio*sum(c.cantidad)) val ,sum(c.cantidad)  from producto p 
inner join carrito c on c.id_producto=p.id
where c.estado = 'PENDIENTE'
group by  p.id,c.id_producto

)  t;