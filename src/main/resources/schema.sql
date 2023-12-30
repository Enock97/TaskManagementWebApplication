CREATE TABLE Task
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    is_completed BOOLEAN,
    PRIMARY KEY (id)
);