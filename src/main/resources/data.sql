INSERT INTO roles (role_id, name) VALUES 
(1, 'ADMIN'),
(2, 'BASIC')
ON CONFLICT (role_id) DO NOTHING;

INSERT INTO marca (nome, inspiracao) VALUES
('Albany', 'Cadillac'), -- 1
('Annis', 'Nissan'), -- 2
('BF - Bürgerfahrzeug', 'Volkswagen'), -- 3
('Bollokan', 'Hyundai'), -- 4
('Bravado', 'Dodge'), -- 5
('Schyster', 'Chrysler'), -- 6
('Brute', 'GMC e Chevrolet'), -- 7
('Buckingham', 'Learjet'), -- 8
('Canis', 'Jeep'), -- 9
('Cheval', 'Holden'), -- 10
('Coil', 'Tesla Motors'), -- 11
('Declasse', 'Chevrolet'), -- 12
('Dewbauchee', 'Aston Martin'), -- 13
('Dinka', 'Honda'), -- 14
('DUDE', 'Cartepillar'), -- 15
('Dundreary', 'Lincoln'), -- 16
('Emperror', 'Lexus'), -- 17
('Enus', 'Rolls Royce'), -- 18
('Fathom', 'Infiniti'), -- 19
('Grotti', 'Ferrari'), -- 20
('Hijak', 'Fisker Automotive'), -- 21
('HVY', 'Oshkosh'), -- 22
('Imponte', 'Pontiac'), -- 23
('Invetero', 'Chevrolet'), -- 24
('Jobilt', 'Peterbilt'), -- 25
('Karin', 'Toyota'), -- 26
('Kraken', 'Triton Submarines'), -- 27
('Lampadati', 'Maserati'), -- 28
('Liberty City Cycles', 'Harley Davidson, Orange County Choppers e West Coast Choppers'), -- 29
('Maibatsu', 'Mitsubishi'), -- 30
('Mammouth', 'Hummer'), -- 31
('MTL', 'Mack'), -- 32
('Nagasaki', 'Kawasaki'), -- 33
('Obey', 'Audi'), -- 34
('Ocelot', 'Jaguar'), -- 35
('Overflod', 'Koenigsegg'), -- 36
('Pegassi', 'Lamborghini, Pagani e Ducati'), -- 37
('Pfister', 'Porsche'), -- 38
('Principe', 'Piaggio e Ducati'), -- 39
('Progen', 'McLaren'), -- 40
('Shitzu', 'Suzuki, Honda e Kawasaki'), -- 41
('Speedophile', 'Bombardier Recreations Products e Yamaha'), -- 42
('Stanley', 'Deere & Company'), -- 43
('Truffade', 'Bugatti'), -- 44
('Ubermacht', 'BMW'), -- 45
('Vapid', 'Ford'), -- 46
('Weeny', 'MINI'), -- 47
('Vulcar', 'Volvo'), -- 48
('Western Company', 'Sikorsky Aircraft Bell Helicopter e Boeing'), -- 49
('Western Motocycle Company', 'Harley Davidson'), -- 50
('Willard', 'Buick') -- 51
ON CONFLICT (nome) DO NOTHING;

-- INSERIR MODELOS DOS CARROS.
INSERT INTO modelo (nome, inspiracao, classe, capacidade, marca_id) VALUES
('9F', 'Audi R8 Coupé 2007-2013', 9, 2, 34), -- 1
('300R', 'Nissan Z (RZ34), Fairlady Z Customized, Nissan S30 Series', 9, 2, 2) -- 2
ON CONFLICT (nome) DO NOTHING;

INSERT INTO imagem (img_url, modelo_id, carro_id) VALUES
('https://static.wikia.nocookie.net/gtawiki/images/0/06/9F-GTAV-FrontQuarter.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/c/c8/9F-GTAV-Top.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/0/0c/9F-GTAV-RearQuarter.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/c/ce/9F-GTAV-Front.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/7/71/9F-GTAV-Side.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/7/78/9F-GTAV-Rear.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/4/4d/9F-GTAV-Engine.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/4/46/9F-GTAV-Inside.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/0/05/9F-GTAV-Other.png', 1, null),
('https://static.wikia.nocookie.net/gtawiki/images/3/37/9F-GTAV-Dashboard.png', 1, null);