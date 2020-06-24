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

INSERT INTO employees (id, name, password, job_id, development_area_id, skill_id, start_date, phone_number, last_login_date) VALUES
(8, 'Horváth Krisztina', 'ed20a3f3b8b4c67c848b6f53d9da31270cf3a20998944b2b7e0278dccbb9c18f', 1, 1, 4, '2020-02-01', '06701122345', '2020-03-07 19:28:22'),
(9, 'Faragó András', '598dbeb7ca96ccc4523ea8c997fc9cbd6be29baf571495ded2788fa71e8455e0', 2, 1, 3, '2020-02-01', '06701122345', '2020-03-05 00:32:46'),
(10, 'Lukáts Zoltán', '19f18324dfa42ec28807f6c031c9cb24c3883959e13deaa973b1fac04bc2cafb', 3, 1, 2, '2020-02-02', '06701122345', '2020-03-04 19:50:05'),
(11, 'Lakatos Géza', '598dbeb7ca96ccc4523ea8c997fc9cbd6be29baf571495ded2788fa71e8455e0', 2, 4, 4, '2020-02-01', '06302425505', '2020-02-28 23:23:39'),
(17, 'Csikós Csaba', '2c7a4c5f98d05a38a2baf9383930a19afe1d1f3ebd85bce370b15b45e5a37f57', 1, 1, 5, '2018-05-07', '06203254679', NULL),
(18, 'Kovács Annamária', '3247df76b73273030876b0a8533182731c5003502b99dd7efad3e93cbc900c83', 1, 4, 4, '2018-11-14', '06306237942', NULL),
(19, 'Bana Ferenc', '5cd6937c10c8be0c9274372fa9d5d70c429d78ad7e3c5ae6b6e788c64d333cfe', 2, 3, 3, '2018-03-23', '06207942653', NULL),
(20, 'Gál Endre', '72b6331a2b3fea9c0c5faa54b2254911cd1fc27170ac900f452e5e72d8176131', 3, 4, 2, '2019-02-18', '06207237689', NULL),
(21, 'Tóth Ádám', '852db2468a4b76030bef3ba429bf73e134b7112c5622fc3ee8fa6e0bcd15ed39', 3, 1, 1, '2019-11-29', '06706271345', NULL),
(22, 'Kerekes Eszter', '39c648259495b8e83c2856da31bc86153012b6e118944c78c2150167e8385666', 1, 2, 5, '2019-01-17', '06308942571', NULL),
(23, 'Kiss Elemér', '12feab7f6b26dba78fe2e6319aa5e6083455ea201d8070613929aa4490696af5', 3, 4, 3, '2019-05-23', '06705941739', NULL),
(24, 'Kováts Károly', '42baab9bd8afe059e4e5e9a2c9ba3ecaa9372ab3b20b5475e6ea3c76980662d4', 2, 2, 5, '2018-03-05', '06302465987', NULL),
(25, 'Szilágyi Péter', '8957c0e6cec0698f80692c2779bc57ac39916e9bbc310799d247b0e81f832c98', 3, 1, 2, '2019-09-20', '06304569841', NULL),
(26, 'Balogh Sándor', 'd704eb2b7affdbb9682fd34c6e3dbebb262e5ad0762dfe476d192b2d071b2461', 3, 4, 3, '2018-12-12', '06707893521', NULL),
(27, 'Vincze László', '33d5f92211e451e6e5cd66170a6baa1bc644d595604cea65e5b55daaca6c3f7e', 3, 1, 3, '2019-01-24', '06308945689', NULL),
(28, 'Balla Mihály', '982520eb0a3909b12aa8b1ccf1a8335d8772dfdcfa2482b4823ca30907a09c7c', 3, 2, 4, '2020-01-03', '06707768964', NULL);

INSERT INTO teams (id, name, team_leader_id) VALUES
(7, 'A team', 9),
(8, 'B team', 11),
(9, 'C team', 24);

INSERT INTO team_memberships (employee_id, start_date, end_date, team_id) VALUES
(20, '2020-03-07 16:04:33', NULL, 8),
(21, '2020-03-07 16:04:34', NULL, 8),
(23, '2020-03-07 16:03:27', NULL, 9),
(25, '2020-03-07 16:04:24', NULL, 7),
(26, '2020-03-07 16:04:34', NULL, 8),
(27, '2020-03-07 16:03:27', NULL, 9),
(28, '2020-03-07 16:04:24', NULL, 7);

INSERT INTO projects (id, name, customer_id, development_area_id, order_date, deadline, project_status_id, priority_id, project_leader_id, status_id, description) VALUES
(1, 'Nyílvántartó rendszer', 1, 2, '2020-02-01', '2020-07-21', 3, 4, 8, 2, 'A BMR ügyviteli rendszer a magyar kisvállalkozások igényeire kialakított megoldás, amely ötvözi a nagyvállalati rendszerek kényelmi funkciót az egyszerűsített, logikus folyamatokkal és gyors, költséghatékony bevezetéssel. Fontosabb funkciói: tételek egyszerű, gyors felvitele; korlátlan számú vállalkozás könyvelése; eredménykimutatás; bérszámfejtés; 30 napos áfa figyelése; tárgyieszköz nyilvántartás.'),
(2, 'Webáruház', 2, 4, '2020-02-01', '2020-04-15', 4, 2, 17, 3, 'Megrendelőnk egy bútorokkal foglalkozó webáruház honlapjának létrehozásával bízott meg bennünket. A honlap megjelenése testreszabható; ügyfelülnk egyéni igényeire épülve készítjük el a web korszerű és felhasználóbarát platformját.'),
(6, 'Építőipari szoftver', 3, 1, '2019-10-01', '2020-01-08', 6, 1, 18, 4, 'Az Easy Office egy olyan 3D program, amelyet az építőipari projektmenedzsment könnyű kezelésére hoztunk létre a megrendelő számára. Az eltérő építési szakaszok szükségletének kielégítése érdekében az Easy Office egy egységes módon lett létrehozva, amely könnyedén testre szabható, bővíthető és egyszerűen használható kezelői felületet nyújt.'),
(14, 'Business Card Designer ', 4, 1, '2020-03-10', '2020-04-10', 1, 1, 22, 3, 'A megrendelőnk által kért Business Card Designer egyolyan névjegykártya készítő program, amely könnyű tervezést biztosít. A program lépésről lépésre végigvezet a névjegykártya készítés összes fázisán.'),
(18, 'Projekt menedzsment szoftver', 5, 2, '2020-02-04', '2020-04-27', 2, 3, 17, 3, 'A Trend Menedzsment egy web alapú menedzsment platform, amely összevont megoldást nyújt a projektek dokumentációjának és kommunikációjának kezelésére. Segíta vállalatoknak a projektek elszámoltathatóságának és hatékonyságának növelésében.'),
(19, 'Könyvviteli szoftver', 6, 2, '2020-02-20', '2020-08-11', 5, 3, 18, 1, 'Az ügyfél kérésére egy olyan összetett szoftveren dolgozunk, amely könnyen kezelhető és átlátható. Ebbena komplex programban  megtalálható a kettős könyvviteli program, a bérszámfejtő program, az eszköz nyilvántartás,és a projekt irányítás programja.'),
(20, 'Weboldal', 8, 4, '2019-06-19', '2019-09-13', 6, 2, 8, 4, 'A megrendelő kérésére egy olyan modern a lehetőségekhez igazodó honlapot hoztunk létre, amely könnyedén és a megfelelő módon válaszol az adatokat kérő eszköznek, és annak adottságai szerinti legmegfelelőbb formában jelenik meg rajta.');

INSERT INTO projects_teams (project_id, team_id) VALUES
(1, 7),
(1, 8),
(2, 7),
(6, 7),
(6, 8),
(6, 9),
(14, 8),
(18, 8),
(18, 9),
(19, 7),
(19, 8),
(19, 9),
(20, 9);

INSERT INTO tasks (id, topic, deadline, team_leader_id, project_id, status_id, description) VALUES
(1, 'Nyílvántartó rendszer specifikáció', '2020-07-21', 9, 1, 2, ''),
(2, 'Webáruház rendszer specifikáció meghatározása', '2020-04-15', 11, 2, 3, ''),
(45, 'Építőipari szoftver', '2020-01-08', 9, 1, 4, 'Az Easy Office egy olyan 3D program, amelyet az építőipari projektmenedzsment könnyű kezelésére hoztunk létre a megrendelő számára. Az eltérő építési szakaszok szükségletének kielégítése érdekében az Easy Office egy egységes módon lett létrehozva, amely könnyedén testre szabható, bővíthető és egyszerűen használható kezelői felületet nyújt.'),
(47, 'Business Card Designer program létrehozása', '2020-04-10', 19, 14, 3, '');

INSERT INTO reports (id, project_id, recording_date, employee_id, text) VALUES
(2, 2, '2020-02-02 13:46:05', 11, 'Elkezdtük a web alkalmazással szemben támasztott pontos vevői elvárások definiálását.'),
(3, 1, '2020-02-12 20:41:04', 8, 'asxsx'),
(5, 6, '2020-02-17 00:33:33', 8, 'fdsss'),
(7, 19, '2020-02-18 10:07:50', 8, 'qweqe'),
(8, 1, '2020-02-28 22:56:23', 8, 'asdasdasd'),
(9, 1, '2020-02-28 23:03:52', 8, 'asd'),
(10, 1, '2020-02-28 23:10:52', 8, 'dsa'),
(11, 1, '2020-02-28 23:21:28', 9, 'asdasd'),
(12, 1, '2020-02-28 23:23:54', 11, 'dfrgh');