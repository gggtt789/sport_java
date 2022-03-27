\c sport_test

INSERT INTO events(name, venue, result, start_at)
VALUES
('event1', 'venue1', 'result1', '2011-01-01'),
('event2', 'venue2', 'result2', '2021-01-01 19:10:25');


INSERT INTO seats(name, price, total_number, available_number, event_id)
VALUES
('seat1', 1111, 111, 11, 1),
('seat2', 2222, 222, 22, 1);


INSERT INTO teams(name, birthday, sport, is_member)
VALUES
('team1_1', '2001-01-01', 'sport1', false),
('team1_2', '2001-01-02', 'sport1', false),
('team2_sportsman1', '2001-02-01', 'sport2', true),
('team2_sportsman2', '2001-02-02', 'sport2', true);


INSERT INTO event_team(team_id, event_id)
VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2);


INSERT INTO members(name, birthday)
VALUES
('member1', '1990-01-01'),
('member2', '1990-02-01'),
('member3', '1990-03-01'),
('member4', '1990-04-01');


INSERT INTO member_team(member_id, team_id, joined_at, left_at, role)
VALUES
(1, 1, '2010-01-01', '2019-01-01', 'role1'),
(1, 2, '2020-01-02', NULL, 'role1'),
(2, 1, '2020-02-01', NULL, 'role2'),
(3, 3, '2010-03-03', '2019-03-03', 'role3'),
(4, 4, '2010-04-04', NULL, 'role4');


GRANT ALL ON ALL TABLES IN SCHEMA public TO sport_admin_test;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO sport_admin_test;
