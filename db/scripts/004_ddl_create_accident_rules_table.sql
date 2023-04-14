CREATE TABLE accident_rules(
   id serial PRIMARY KEY,
   accident_id int not null REFERENCES accident(id),
   rule_id int not null REFERENCES accident_rule(id)
);