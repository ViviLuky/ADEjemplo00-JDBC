create database  if not exists granja;
use granja;

create table if not exists animales(
    id_animal int PRIMARY KEY ,
    tipo varchar(40)not null ,
    nombre varchar(40)not null ,
    color varchar(10),
    edad int not null ,
    num_enfermedades int not null
);
 update animales set tipo= ?,
                     nombre = ?,
                     color = ?,
                     edad = ?,
                     num_enfermedades = ?,
                     id_animal = ?;

create table if not exists empleados(
     DNI varchar(9) Primary Key,
    empleo varchar(40) not null ,
    nombre varchar(40) not null ,
    horas_semanales int not null
);