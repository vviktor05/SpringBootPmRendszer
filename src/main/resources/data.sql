INSERT INTO jobs (name) VALUES
('Projektvezető'),
('Csapatvezető'),
('Fejlesztő');

INSERT INTO development_areas (name) VALUES
('Asztali alkalmazás'),
('Adatbázisok fejlesztése'),
('Alkalmazás iOS és Android'),
('Web fejlesztés'),
('Mesterséges intelligencia'),
('Adatbányászat');


INSERT INTO project_statuses (name) VALUES
('Vevői Specifikáció'),
('Szoftveres Specifikáció'),
('Rendszertervezés'),
('Implementáció'),
('Tesztelés'),
('Kész'),
('Továbbfejlesztés');

INSERT INTO priorities (name) VALUES
('Alacsony'),
('Normál'),
('Magas'),
('SÜRGŐS!');

INSERT INTO skills (name) VALUES
('Gyakornok'),
('Junior'),
('Medior'),
('Senior'),
('Arhitect');

INSERT INTO statuses (name) VALUES
('Leállítva'),
('Szüneteltetve'),
('Folyamatban'),
('Kész');

INSERT INTO customers (name, phone, email, website, zip_code, locality, street_address) VALUES
('Takacs Kft.', '06302425344', 'takacs@gmail.com', 'www.takacs.hu', '2322', 'Foktő', 'Petőfi Sándor utca 230.'),
('Balla Bt.', '06302425505', 'balla@gmail.com', NULL, '3424', 'Kecskemét', 'Bethlen Gábor út 32'),
('Straus kft.', '06705324169', 'straus@hotmail.com', 'www.straus.markt.com', '2092', 'Budakeszi', 'Arany János utca 73.'),
('Argo Kft.', '06309953241', 'argo@mailbox.hu', 'argokft.hu', '4024', 'Debrecen', 'Városház utca 14.'),
('Cargo Network Kft.', '06203214672', 'cargo.n@yahoo.com', 'cargonetwork.com', '2370', 'Dabas', 'Liget sor 6.'),
('Duna Control Kft.', '06708941269', 'info@dunacontrol.hu', 'www.duna-control.hu', '2400', 'Dunaújváros', 'Táncsics Mihály utca 14. Fsz. 2.'),
('Data Hungária Kft.', '06205478941', 'datainfo@hungaria.hu', 'www.datahun.hu', '5000', 'Szolnok', 'Dózsa György út 67.'),
('IRP International Kft.', '06303264589', 'info@irp.com', 'www.irp.com', '1027', 'Budapest II. kerület', 'Bem József utca 12.');

INSERT INTO employees (name, email, password, job_id, development_area_id, skill_id, start_date, phone_number, last_login_date) VALUES
('Horváth Krisztina', 'horvath@gmail.com', '$2a$10$PMZZVq5qVaC7BrvUKx889O/OpSR15ojylURVvSx.b61t/tZRtQ7jC', 1, 1, 4, '2020-02-01', '06701122345', '2020-03-07 19:28:22'),
('Faragó András', 'farago@gmail.com', '598dbeb7ca96ccc4523ea8c997fc9cbd6be29baf571495ded2788fa71e8455e0', 2, 1, 3, '2020-02-01', '06701122345', '2020-03-05 00:32:46'),
('Lukáts Zoltán', 'lukats@gmail.com', '19f18324dfa42ec28807f6c031c9cb24c3883959e13deaa973b1fac04bc2cafb', 3, 1, 2, '2020-02-02', '06701122345', '2020-03-04 19:50:05'),
('Lakatos Géza', 'lakatos@gmail.com', '598dbeb7ca96ccc4523ea8c997fc9cbd6be29baf571495ded2788fa71e8455e0', 2, 4, 4, '2020-02-01', '06302425505', '2020-02-28 23:23:39'),
('Csikós Csaba', 'csikos@gmail.com', '2c7a4c5f98d05a38a2baf9383930a19afe1d1f3ebd85bce370b15b45e5a37f57', 1, 1, 5, '2018-05-07', '06203254679', NULL),
('Kovács Annamária', 'kovacs@gmail.com', '3247df76b73273030876b0a8533182731c5003502b99dd7efad3e93cbc900c83', 1, 4, 4, '2018-11-14', '06306237942', NULL),
('Bana Ferenc', 'bana@gmail.com', '5cd6937c10c8be0c9274372fa9d5d70c429d78ad7e3c5ae6b6e788c64d333cfe', 2, 3, 3, '2018-03-23', '06207942653', NULL),
('Gál Endre', 'gal@gmail.com', '72b6331a2b3fea9c0c5faa54b2254911cd1fc27170ac900f452e5e72d8176131', 3, 4, 2, '2019-02-18', '06207237689', NULL),
('Tóth Ádám', 'toth@gmail.com', '852db2468a4b76030bef3ba429bf73e134b7112c5622fc3ee8fa6e0bcd15ed39', 3, 1, 1, '2019-11-29', '06706271345', NULL),
('Kerekes Eszter', 'kerekes@gmail.com', '39c648259495b8e83c2856da31bc86153012b6e118944c78c2150167e8385666', 1, 2, 5, '2019-01-17', '06308942571', NULL),
('Kiss Elemér', 'kiss@gmail.com', '12feab7f6b26dba78fe2e6319aa5e6083455ea201d8070613929aa4490696af5', 3, 4, 3, '2019-05-23', '06705941739', NULL),
('Kováts Károly', 'kovats@gmail.com', '42baab9bd8afe059e4e5e9a2c9ba3ecaa9372ab3b20b5475e6ea3c76980662d4', 2, 2, 5, '2018-03-05', '06302465987', NULL),
('Szilágyi Péter', 'szilagyi@gmail.com', '8957c0e6cec0698f80692c2779bc57ac39916e9bbc310799d247b0e81f832c98', 3, 1, 2, '2019-09-20', '06304569841', NULL),
('Balogh Sándor', 'balogh@gmail.com', 'd704eb2b7affdbb9682fd34c6e3dbebb262e5ad0762dfe476d192b2d071b2461', 3, 4, 3, '2018-12-12', '06707893521', NULL),
('Vincze László', 'vincze@gmail.com', '33d5f92211e451e6e5cd66170a6baa1bc644d595604cea65e5b55daaca6c3f7e', 3, 1, 3, '2019-01-24', '06308945689', NULL),
('Balla Mihály', 'balla@gmail.com', '982520eb0a3909b12aa8b1ccf1a8335d8772dfdcfa2482b4823ca30907a09c7c', 3, 2, 4, '2020-01-03', '06707768964', NULL);

INSERT INTO teams (name, team_leader_id) VALUES
('A team', 2),
('B team', 4),
('C team', 12);

INSERT INTO team_memberships (employee_id, team_id) VALUES
(8, 2),
(9, 2),
(11, 3),
(13, 1),
(14, 2),
(15, 3),
(16, 1);

INSERT INTO projects (name, customer_id, development_area_id, order_date, deadline, project_status_id, priority_id, project_leader_id, status_id, description) VALUES
('Nyílvántartó rendszer', 1, 2, '2020-02-01', '2020-07-21', 3, 4, 1, 2, 'A BMR ügyviteli rendszer a magyar kisvállalkozások igényeire kialakított megoldás, amely ötvözi a nagyvállalati rendszerek kényelmi funkciót az egyszerűsített, logikus folyamatokkal és gyors, költséghatékony bevezetéssel. Fontosabb funkciói: tételek egyszerű, gyors felvitele; korlátlan számú vállalkozás könyvelése; eredménykimutatás; bérszámfejtés; 30 napos áfa figyelése; tárgyieszköz nyilvántartás.'),
('Webáruház', 2, 4, '2020-02-01', '2020-04-15', 4, 2, 5, 3, 'Megrendelőnk egy bútorokkal foglalkozó webáruház honlapjának létrehozásával bízott meg bennünket. A honlap megjelenése testreszabható; ügyfelülnk egyéni igényeire épülve készítjük el a web korszerű és felhasználóbarát platformját.'),
('Építőipari szoftver', 3, 1, '2019-10-01', '2020-01-08', 6, 1, 6, 4, 'Az Easy Office egy olyan 3D program, amelyet az építőipari projektmenedzsment könnyű kezelésére hoztunk létre a megrendelő számára. Az eltérő építési szakaszok szükségletének kielégítése érdekében az Easy Office egy egységes módon lett létrehozva, amely könnyedén testre szabható, bővíthető és egyszerűen használható kezelői felületet nyújt.'),
('Business Card Designer ', 4, 1, '2020-03-10', '2020-04-10', 1, 1, 10, 3, 'A megrendelőnk által kért Business Card Designer egyolyan névjegykártya készítő program, amely könnyű tervezést biztosít. A program lépésről lépésre végigvezet a névjegykártya készítés összes fázisán.'),
('Projekt menedzsment szoftver', 5, 2, '2020-02-04', '2020-04-27', 2, 3, 5, 3, 'A Trend Menedzsment egy web alapú menedzsment platform, amely összevont megoldást nyújt a projektek dokumentációjának és kommunikációjának kezelésére. Segíta vállalatoknak a projektek elszámoltathatóságának és hatékonyságának növelésében.'),
('Könyvviteli szoftver', 6, 2, '2020-02-20', '2020-08-11', 5, 3, 6, 1, 'Az ügyfél kérésére egy olyan összetett szoftveren dolgozunk, amely könnyen kezelhető és átlátható. Ebbena komplex programban  megtalálható a kettős könyvviteli program, a bérszámfejtő program, az eszköz nyilvántartás,és a projekt irányítás programja.'),
('Weboldal', 8, 4, '2019-06-19', '2019-09-13', 6, 2, 1, 4, 'A megrendelő kérésére egy olyan modern a lehetőségekhez igazodó honlapot hoztunk létre, amely könnyedén és a megfelelő módon válaszol az adatokat kérő eszköznek, és annak adottságai szerinti legmegfelelőbb formában jelenik meg rajta.');

INSERT INTO projects_teams (project_id, team_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(3, 2),
(3, 3),
(4, 2),
(5, 2),
(5, 3),
(6, 1),
(6, 2),
(6, 3),
(7, 3);

INSERT INTO tasks (topic, deadline, team_leader_id, project_id, status_id, description) VALUES
('Nyílvántartó rendszer specifikáció', '2020-07-21', 2, 1, 2, ''),
('Webáruház rendszer specifikáció meghatározása', '2020-04-15', 4, 2, 3, ''),
('Építőipari szoftver', '2020-01-08', 2, 1, 4, 'Az Easy Office egy olyan 3D program, amelyet az építőipari projektmenedzsment könnyű kezelésére hoztunk létre a megrendelő számára. Az eltérő építési szakaszok szükségletének kielégítése érdekében az Easy Office egy egységes módon lett létrehozva, amely könnyedén testre szabható, bővíthető és egyszerűen használható kezelői felületet nyújt.'),
('Business Card Designer program létrehozása', '2020-04-10', 7, 4, 3, '');

INSERT INTO reports (project_id, recording_date, employee_id, text) VALUES
(2, '2020-02-02 13:46:05', 4, 'Elkezdtük a web alkalmazással szemben támasztott pontos vevői elvárások definiálását.'),
(1, '2020-02-12 20:41:04', 1, 'asxsx'),
(3, '2020-02-17 00:33:33', 1, 'fdsss'),
(6, '2020-02-18 10:07:50', 1, 'qweqe'),
(1, '2020-02-28 22:56:23', 1, 'asdasdasd'),
(1, '2020-02-28 23:03:52', 1, 'asd'),
(1, '2020-02-28 23:10:52', 1, 'dsa'),
(1, '2020-02-28 23:21:28', 2, 'asdasd'),
(1, '2020-02-28 23:23:54', 4, 'dfrgh');