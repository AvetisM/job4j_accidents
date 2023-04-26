CREATE TABLE IF NOT EXISTS accident (
  id serial primary key,
  name text,
  address text,
  text text,
  type_id int references accident_type(id)
);