\c sport_test


CREATE TABLE events (
    id serial PRIMARY KEY,
    name text NOT NULL,
    venue text NOT NULL,
    result text,
    start_at TIMESTAMP NOT NULL
);


CREATE TABLE seats (
    id serial PRIMARY KEY,
    name text NOT NULL,
    price numeric NOT NULL,
    total_number int NOT NULL,
    available_number int NOT NULL,
    event_id int REFERENCES events ON DELETE CASCADE
);


CREATE TABLE teams (
    id serial PRIMARY KEY,
    name text NOT NULL,
    birthday date NOT NULL,
    sport text NOT NULL,
    is_member bool NOT NULL DEFAULT false
);


CREATE TABLE event_team (
    team_id int REFERENCES teams ON DELETE CASCADE, 
    event_id int REFERENCES events ON DELETE CASCADE,
    PRIMARY KEY(team_id, event_id)
);


CREATE TABLE members (
    id serial PRIMARY KEY,
    name text NOT NULL,
    birthday date NOT NULL
);


CREATE TABLE member_team (
    id serial PRIMARY KEY,
    team_id int REFERENCES teams ON DELETE CASCADE,
    member_id int REFERENCES members ON DELETE CASCADE,
    joined_at date NOT NULL,
    left_at date,
    role text NOT NULL,
    UNIQUE (team_id, member_id, joined_at)
);


GRANT ALL ON ALL TABLES IN SCHEMA public TO sport_admin_test;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO sport_admin_test;
