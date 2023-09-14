CREATE TABLE IF NOT EXISTS posts 
    (
        id       SERIAL NOT NULL,
        title    VARCHAR(255) NOT NULL,
        content  VARCHAR(255) NOT NULL,
        user_id  BIGINT NOT NULL,
        PRIMARY  KEY (id),
        FOREIGN  KEY (user_id) REFERENCES users(id)
    );