INSERT INTO roles (role_id, name) VALUES 
(1, 'ADMIN'),
(2, 'BASIC')
ON CONFLICT (role_id) DO NOTHING;

INSERT INTO marca (nome, inspiracao) VALUES
('Albany', 'Cadillac'),
('Annis', 'Nissan'),
('BF - Bürgerfahrzeug', 'Volkswagen'),
('Bollokan', 'Hyundai'),
('Bravado', 'Dodge'),
('Schyster', 'Chrysler'),
('Brute', 'GMC e Chevrolet'),
('Buckingham', 'Learjet'),
('Canis', 'Jeep'),
('Cheval', 'Holden'),
('Coil', 'Tesla Motors'),
('Declasse', 'Chevrolet'),
('Dewbauchee', 'Aston Martin'),
('Dinka', 'Honda'),
('DUDE', 'Cartepillar'),
('Dundreary', 'Lincoln'),
('Emperror', 'Lexus'),
('Enus', 'Rolls Royce'),
('Fathom', 'Infiniti'),
('Grotti', 'Ferrari'),
('Hijak', 'Fisker Automotive'),
('HVY', 'Oshkosh'),
('Imponte', 'Pontiac'),
('Invetero', 'Chevrolet'),
('Jobilt', 'Peterbilt'),
('Karin', 'Toyota'),
('Kraken', 'Triton Submarines'),
('Lampadati', 'Maserati'),
('Liberty City Cycles', 'Harley Davidson, Orange County Choppers e West Coast Choppers'),
('Maibatsu', 'Mitsubishi'),
('Mammouth', 'Hummer'),
('MTL', 'Mack'),
('Nagasaki', 'Kawasaki'),
('Obey', 'Audi'),
('Ocelot', 'Jaguar'),
('Overflod', 'Koenigsegg'),
('Pegassi', 'Lamborghini, Pagani e Ducati'),
('Pfister', 'Porsche'),
('Principe', 'Piaggio e Ducati'),
('Progen', 'McLaren'),
('Shitzu', 'Suzuki, Honda e Kawasaki'),
('Speedophile', 'Bombardier Recreations Products e Yamaha'),
('Stanley', 'Deere & Company'),
('Truffade', 'Bugatti'),
('Ubermacht', 'BMW'),
('Vapid', 'Ford'),
('Weeny', 'MINI'),
('Vulcar', 'Volvo'),
('Western Company', 'Sikorsky Aircraft Bell Helicopter e Boeing'),
('Western Motocycle Company', 'Harley Davidson'),
('Willard', 'Buick')
ON CONFLICT (nome) DO NOTHING;

-- INSERIR MODELOS DOS CARROS.
INSERT INTO modelo (nome, inspiracao, classe, capacidade, imagem, marca_id) VALUES
('9F', 'Audi R8', 9, 2, '/src/main/resources/static/assets/imgs/9F-GTAV-front.png', 34)
ON CONFLICT (nome) DO NOTHING;