create schema if not exists atherysroleplay;

create table if not exists atherysroleplay.CharacterCard (
    player binary not null,
    age varchar(255),
    description varchar(255),
    name varchar(255),
    nationality varchar(255),
    nickname varchar(255),
    playerName varchar(255),
    primary key (player)
);