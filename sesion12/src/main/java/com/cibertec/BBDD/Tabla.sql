drop database if exists bd_imagen;
create database bd_imagen;
use bd_imagen;

create table bd_imagen.imagen(
	idImagen int primary key auto_increment,
    nombre varchar(100),
    tamano  int,
    archivo longblob
)
