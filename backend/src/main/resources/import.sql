insert into zone (nom) values ('Gers');
insert into zone (nom) values ('Haute-Garonne');
insert into zone (nom) values ('Landes');
insert into zone (nom) values ('Aveyron');
insert into zone (nom) values ('Ariege');

insert into technicien (first_name, last_name, user_name, password, mail, sexe, qualification, adresse_postale, mobile) values ('Igor', 'Ponchel','igor','igor', 'igor.ponchel@astek.fr', 'H', 'Maître', '37 chemin des ramassiers, 31770 Colomiers', true);
insert into technicien (first_name, last_name, user_name, password, mail, sexe, qualification, adresse_postale, mobile) values ('Geoffrey', 'Pousse','geoffrey','geoffrey', 'geoffrey.pousse@astek.fr', 'H', 'Expert', 'blabla', true);
insert into technicien (first_name, last_name, user_name, password, mail, sexe, qualification, adresse_postale, mobile) values ('Gautier', 'Ribery','gautier','gautier', 'gautier.ribery@astek.fr', 'H', 'Junior', 'blabla', false);
insert into technicien (first_name, last_name, user_name, password, mail, sexe, qualification, adresse_postale, mobile) values ('Julie', 'Durand','julie','julie', 'julie.durand@astek.fr', 'F', 'Junior', 'blabla', false);
insert into technicien (first_name, last_name, user_name, password, mail, sexe, qualification, adresse_postale, mobile) values ('Alexandre', 'Lim','alexandre','alexandre', 'alexandre.lim@astek.fr', 'F', 'Junior', 'blabla', true);
insert into technicien (first_name, last_name, user_name, password, mail, sexe, qualification, adresse_postale, mobile) values ('Benjamin', 'Boulouloubie','benjamin','benjamin', 'benjamin.boulouloubie@astek.fr', 'H', 'Junior', 'blabla', false);

insert into tech_zones (tech_id, zones_id) values (1, 1);
insert into tech_zones (tech_id, zones_id) values (1, 2);
insert into tech_zones (tech_id, zones_id) values (1, 3);

insert into parametrage (libelle, valeur, commentaire) values ('nbZone', '36', 'Nombre de Zone');
insert into parametrage (libelle, valeur, commentaire) values ('nbUI', '512', 'Nombre de UI');
insert into parametrage (libelle, valeur, commentaire) values ('pourcentageTravauxHauteurAutorise', '40', 'Pourcentrage de travaux en hauteur autorisés');
insert into parametrage (libelle, valeur, commentaire) values ('reoAutorise' , 'true', 'Détermine si réo autorisé');