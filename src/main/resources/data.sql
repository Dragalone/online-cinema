
INSERT INTO cinema_schema.genre(id,"name") VALUES
                                               ('37bb059c-4d18-4caa-9ddf-152ba8dfab8b','Name1'),
                                               ('7c2ffbf3-d2cb-4229-bf89-387ff7d512e4','Name2'),
                                               ('a5439166-1969-434e-9910-f30bafc5b796','Name3');

INSERT INTO cinema_schema.actor(id,birthdate,information,"name") VALUES
                                                                     ('c77ad2be-0281-4458-9d66-892393ea4999','2024-02-01','1Lorem ipsum dolor sit amet','Name1'),
                                                                     ('f1515473-464f-4d33-a7c4-3caccfe140be','2024-03-02','2Lorem ipsum dolor sit amet','Name2'),
                                                                     ('2decd551-1a35-4fa3-ac2b-a0302bcb8a97','2024-04-03','3Lorem ipsum dolor sit amet','Name3');

INSERT INTO cinema_schema.subscription(id,cost,end_date,start_date) VALUES
                                                                        ('8f8e1169-46b8-4abd-b1af-9f2f46dad773',300.00,'2024-01-01','2024-02-01'),
                                                                        ('f71b18a9-2b6a-4c1b-a915-90018739c3bf',200.00,'2024-02-02','2024-03-01'),
                                                                        ('e9281520-8791-4630-90da-81f3c47ee8e4',100.00,'2024-03-03','2024-04-01');

INSERT INTO cinema_schema.app_user(id,email,"name","password",phone,subscription_id) VALUES
                                                                                         ('014128c1-4c97-4f3c-a020-35219c85ba32','test@test.test','Ivan','123','89997776655','8f8e1169-46b8-4abd-b1af-9f2f46dad773'),
                                                                                         ('f3b2365f-ab4f-497a-95f7-c5ecdea68396','test1@test.test','Vlad','554','81117776655','f71b18a9-2b6a-4c1b-a915-90018739c3bf'),
                                                                                         ('ba41512c-a21b-46d5-8628-9b24eba24a12','test2@test.test','Alex','254','89227776655','e9281520-8791-4630-90da-81f3c47ee8e4');

INSERT INTO cinema_schema.director(id,birthdate,information,"name") VALUES
                                                                        ('f7f39016-e209-41f5-9e49-eef23eff3a68','2024-02-01','Lorem ipsum dolor sit amet','Name1'),
                                                                        ('42a8e31d-aac5-4707-9955-6cbffc53ab13','2024-03-02','2Lorem ipsum dolor sit amet','Name2');

INSERT INTO cinema_schema.film(id,"cost",description,"resource_link",release_date,title,director_id) VALUES
                                                                                                         ('659cdb4d-c174-40d6-a5e3-eecc46bed3cf',123,'Lorem ipsum dolor sit amet','path1','2024-02-01','Title1','f7f39016-e209-41f5-9e49-eef23eff3a68'),
                                                                                                         ('7b3d033d-bfd1-4d59-b4a9-893de91bf0d6',123,'Lorem ipsum dolor sit amet','path2','2024-05-05','Title2','42a8e31d-aac5-4707-9955-6cbffc53ab13'),
                                                                                                         ('77384c6c-f990-451f-a8bd-a67f10ef75b9',123,'Lorem ipsum dolor sit amet','path3','2024-03-06','Title3','42a8e31d-aac5-4707-9955-6cbffc53ab13'),
                                                                                                         ('15467cdf-e85a-4857-911f-334ccc948f4d',123,'Lorem ipsum dolor sit amet','path4','2024-01-02','Title4','f7f39016-e209-41f5-9e49-eef23eff3a68');

INSERT INTO cinema_schema.series(id,"cost",description,release_date,title,director_id) VALUES
    ('e4f7e3dc-b18e-4690-bb64-2748b78ad7bb',150.00,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','2024-02-01','title1','f7f39016-e209-41f5-9e49-eef23eff3a68');

INSERT INTO cinema_schema.season(id,description,"title",start_date,series_id) VALUES
                                                                                  ('74b67f02-f716-468c-a7f8-08615dd91e00','1Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','Name1','2024-02-01','e4f7e3dc-b18e-4690-bb64-2748b78ad7bb'),
                                                                                  ('377a9d12-3ca7-4497-8ec7-5a57de79b5ac','2Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','Name2','2024-03-01','e4f7e3dc-b18e-4690-bb64-2748b78ad7bb'),
                                                                                  ('f5e4efd1-67a2-4409-8a29-cd1a9f8c6dfd','3Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','Name3','2024-04-01','e4f7e3dc-b18e-4690-bb64-2748b78ad7bb');

INSERT INTO cinema_schema.episode(id,description,"resource_link",release_date,title,season_id) VALUES
                                                                                                   ('f6ebf7f2-85b1-4950-8344-cff7d07b285c','1Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','path1','2024-02-01','title1','74b67f02-f716-468c-a7f8-08615dd91e00'),
                                                                                                   ('3b4d4037-9dfa-4aa0-a0b8-98b7b7b6ee01','5Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','path6','2024-06-01','title2','74b67f02-f716-468c-a7f8-08615dd91e00'),
                                                                                                   ('e1a55cdf-80f4-4c33-9afe-08655ae023d2','2Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','path2','2024-03-01','title1','377a9d12-3ca7-4497-8ec7-5a57de79b5ac'),
                                                                                                   ('22e9c61e-7deb-4956-9d57-7488bb35b9a4','3Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.','path3','2024-04-01','title1','f5e4efd1-67a2-4409-8a29-cd1a9f8c6dfd');


INSERT INTO cinema_schema.rating(id,"comment",rating,film_id,series_id,user_id) VALUES
                                                                                    ('4c2a57bf-2469-411e-be07-ba2eac1dd62c','1Lorem ipsum dolor sit amet',5,'659cdb4d-c174-40d6-a5e3-eecc46bed3cf',NULL,'014128c1-4c97-4f3c-a020-35219c85ba32'),
                                                                                    ('da165cd1-fa6b-49cd-b1f9-4f462ea6b28c','2Lorem ipsum dolor sit amet',4,NULL,'e4f7e3dc-b18e-4690-bb64-2748b78ad7bb','014128c1-4c97-4f3c-a020-35219c85ba32');

INSERT INTO cinema_schema.film_genre(film_id,genre_id) VALUES
                                                           ('659cdb4d-c174-40d6-a5e3-eecc46bed3cf','37bb059c-4d18-4caa-9ddf-152ba8dfab8b'),
                                                           ('659cdb4d-c174-40d6-a5e3-eecc46bed3cf','7c2ffbf3-d2cb-4229-bf89-387ff7d512e4'),
                                                           ('659cdb4d-c174-40d6-a5e3-eecc46bed3cf','a5439166-1969-434e-9910-f30bafc5b796'),
                                                           ('7b3d033d-bfd1-4d59-b4a9-893de91bf0d6','37bb059c-4d18-4caa-9ddf-152ba8dfab8b'),
                                                           ('7b3d033d-bfd1-4d59-b4a9-893de91bf0d6','7c2ffbf3-d2cb-4229-bf89-387ff7d512e4'),
                                                           ('77384c6c-f990-451f-a8bd-a67f10ef75b9','a5439166-1969-434e-9910-f30bafc5b796'),
                                                           ('15467cdf-e85a-4857-911f-334ccc948f4d','a5439166-1969-434e-9910-f30bafc5b796');

INSERT INTO cinema_schema.series_genre(series_id,genre_id) VALUES
                                                               ('e4f7e3dc-b18e-4690-bb64-2748b78ad7bb','37bb059c-4d18-4caa-9ddf-152ba8dfab8b'),
                                                               ('e4f7e3dc-b18e-4690-bb64-2748b78ad7bb','7c2ffbf3-d2cb-4229-bf89-387ff7d512e4');

INSERT INTO cinema_schema.series_actors(series_id,actor_id) VALUES
                                                                ('e4f7e3dc-b18e-4690-bb64-2748b78ad7bb','c77ad2be-0281-4458-9d66-892393ea4999'),
                                                                ('e4f7e3dc-b18e-4690-bb64-2748b78ad7bb','f1515473-464f-4d33-a7c4-3caccfe140be');

INSERT INTO cinema_schema.film_actors(film_id,actor_id) VALUES
                                                            ('659cdb4d-c174-40d6-a5e3-eecc46bed3cf','c77ad2be-0281-4458-9d66-892393ea4999'),
                                                            ('659cdb4d-c174-40d6-a5e3-eecc46bed3cf','f1515473-464f-4d33-a7c4-3caccfe140be'),
                                                            ('7b3d033d-bfd1-4d59-b4a9-893de91bf0d6','f1515473-464f-4d33-a7c4-3caccfe140be'),
                                                            ('77384c6c-f990-451f-a8bd-a67f10ef75b9','2decd551-1a35-4fa3-ac2b-a0302bcb8a97'),
                                                            ('15467cdf-e85a-4857-911f-334ccc948f4d','c77ad2be-0281-4458-9d66-892393ea4999');
