insert into accident_type (name) values ('Две машины');
insert into accident_type (name) values ('Машина и человек');
insert into accident_type (name) values ('Машина и велосипед');

insert into accident_rule (name) values ('Статья. 1');
insert into accident_rule (name) values ('Статья. 2');
insert into accident_rule (name) values ('Статья. 3');

insert into accident (name, text, address, type_id) values ('Дтп на улице Козлова', 'Дтп на улице Козлова', 'ул. Козлова 22', 1);
insert into accident (name, text, address, type_id) values ('Сбили пешехода', 'Сбили пешехода', 'ул. Московская 36', 2);
insert into accident (name, text, address, type_id) values ('Сбили велосипедиста', 'Сбили велосипедиста', 'ул. Дегтярева 13', 3);

insert into accident_rules (accident_id, rule_id) values (1, 1);
insert into accident_rules (accident_id, rule_id) values (2, 2);
insert into accident_rules (accident_id, rule_id) values (3, 3);