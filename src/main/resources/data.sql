INSERT INTO users(username, password, enabled)
VALUES ('user', 'admin', true) ;

INSERT INTO users(username, password, enabled)
VALUES ('admin', 'admin', true) ;

INSERT INTO authorities(username, authority)
VALUES ('user', 'ROLE_USER') ; -- but We defined the role as USER in our code ?

INSERT INTO authorities(username, authority)
VALUES ('admin', 'ROLE_ADMIN') ; -- but We defined the role as ADMIN in our code ?