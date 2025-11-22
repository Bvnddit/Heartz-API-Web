INSERT INTO vinilos (id_vin, nombre, artista, genero, anno, precio, formato, color_vinilo, stock, sello, pais, edicion, duracion, descripcion, img) VALUES
(1, 'Saturday Night Wrist', 'Deftones', 'Metal', 2006, 44900, '2LP', 'Negro - Dorado', 10, 'Maverick Records', 'EE.UU.', 'Original', '61:36', 'El quinto álbum de Deftones...', 'saturday_night_wrist_cover.jpg'),
(2, 'Blonde', 'Frank Ocean', 'R&B', 2016, 49900, '2LP', 'Beige', 8, 'Boys Don''t Cry', 'EE.UU.', 'Edición Limitada', '60:08', 'Obra maestra introspectiva de Frank Ocean...', 'blonde_cover.jpg'),
(3, 'The Bends', 'Radiohead', 'Rock', 1995, 45900, 'LP', 'Negro - Blanco', 12, 'Parlophone', 'Reino Unido', 'Original', '48:37', 'El segundo álbum de Radiohead...', 'the_bends_cover.jpg'),
(4, 'Imaginal Disk', 'Magdalena Bay', 'Electropop', 2021, 38900, 'LP', 'Transparente', 7, 'Labrador', 'EE.UU.', 'Original', '42:12', 'Electropop brillante y nostálgico...', 'imaginal_disk_cover.jpg'),
(5, 'Thriller', 'Michael Jackson', 'Pop', 1982, 52900, 'LP', 'Negro', 15, 'Epic', 'EE.UU.', 'Original', '42:19', 'El icónico álbum de Michael Jackson...', 'thriller_cover.jpg'),
(6, 'The Dark Side of the Moon', 'Pink Floyd', 'Rock Progresivo', 1973, 59900, 'LP', 'Negro', 9, 'Harvest Records', 'Reino Unido', 'Original', '43:00', 'Álbum conceptual legendario de Pink Floyd...', 'the_dark_side_of_the_moon_cover.jpg'),
(7, 'Whole Lotta Red', 'Playboi Carti', 'Hip Hop', 2020, 41900, 'LP', 'Rojo', 6, 'AWGE / Interscope', 'EE.UU.', 'Original', '52:35', 'Álbum de Playboi Carti...', 'whole_lotta_red_cover.jpg'),
(8, 'La Espada y la Pared', 'Los Tres', 'Rock', 1995, 39900, 'LP', 'Negro', 11, 'Sony', 'Chile', 'Original', '49:21', 'Álbum emblemático de Los Tres...', 'la_espada_y_la_pared_cover.jpg'),
(9, 'Casiopea', 'Casiopea', 'Jazz Fusion', 1985, 45900, 'LP', 'Azul', 5, 'Alfa Records', 'Japón', 'Original', '45:50', 'Jazz fusion instrumental de alto nivel...', 'casiopea_cover.jpg');

INSERT INTO usuarios (rut, nombre, correo, rol, contrasena) VALUES
('12345678-5', 'Juan Pérez', 'juan.perez@mail.com', 'Cliente', '123456'),
('87654321-K', 'María González', 'maria.gonzalez@mail.com', 'Empleado', '123456'),
('11223344-3', 'Pedro Ramírez', 'pedro.ramirez@mail.com', 'Cliente', '123456'),
('44332211-9', 'Ana Torres', 'admin@heartz.cl', 'Empleado', '123456'),
('55667788-0', 'Luis Fernández', 'cliente@heartz.cl', 'Cliente', '123456');
