START TRANSACTION;

DROP TABLE IF EXISTS player_characters CASCADE;


CREATE TABLE player_characters(

    id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    str int,
    dex int,
    con int,
    intel int,
    wis int,
    cha int,
    max_hp int,
    current_hp int,
    hit_die int,
    damage int,
    level int,


    CONSTRAINT UQ_name UNIQUE (name)
);


INSERT INTO player_characters(name, str, dex, con, intel, wis, cha, max_hp, current_hp, hit_die, damage, level)
VALUES ('Test Character', 10, 12, 14, 16, 18, 20, 200, 150, 12, 15, 8);


COMMIT;