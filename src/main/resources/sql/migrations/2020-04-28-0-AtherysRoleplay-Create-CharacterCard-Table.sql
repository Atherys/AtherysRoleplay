CREATE TABLE IF NOT EXISTS atherys.charactercard (
    player uuid NOT NULL,
    age character varying(255),
    description character varying(255),
    name character varying(255),
    nationality character varying(255),
    nickname character varying(255),
    playername character varying(255)
);

ALTER TABLE atherys.charactercard DROP CONSTRAINT IF EXISTS charactercard_pkey;
ALTER TABLE ONLY atherys.charactercard ADD CONSTRAINT charactercard_pkey PRIMARY KEY (player);