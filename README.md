# pruebapoo2
Heyer Tinoco
# Codigo del Database:
CREATE DATABASE POOPRUEBA2;
USE POOPRUEBA2;
CREATE TABLE est_prueba2(
	codigo_est INT(10) PRIMARY KEY,
    cedula INT(10),
    nombre varchar(50) NOT NULL,
    fecha_nac varchar(10),
    signo varchar(25)
);

INSERT INTO est_prueba2 (codigo_est, cedula, nombre, fecha_nac, signo)
VALUES
(2121228020, 1725523060,'Heyer Tinoco', '03/12/2002', 'Sagitario'),
(2121227012, 1777777777,'Selena Tinoco', '21/11/1997', 'Escorpio');

SELECT * FROM est_prueba2;CREATE DATABASE POOPRUEBA2;
USE POOPRUEBA2;
CREATE TABLE est_prueba2(
	codigo_est INT(10) PRIMARY KEY,
    cedula INT(10),
    nombre varchar(50) NOT NULL,
    fecha_nac varchar(10),
    signo varchar(25)
);

INSERT INTO est_prueba2 (codigo_est, cedula, nombre, fecha_nac, signo)
VALUES
(2121228020, 1725523060,'Heyer Tinoco', '03/12/2002', 'Sagitario'),
(2121227012, 1777777777,'Selena Tinoco', '21/11/1997', 'Escorpio');

SELECT * FROM est_prueba2;


# Capturas
