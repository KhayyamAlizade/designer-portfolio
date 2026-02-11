CREATE TABLE PUBLIC.media_item_entity (
                                   id BIGINT PRIMARY KEY,
                                   image_name VARCHAR(255),
                                   title VARCHAR(255),
                                   author VARCHAR(255),
                                   published_date VARCHAR(50),
                                   page_type varchar(12),
                                   image_path VARCHAR(255),
                                   group_index INT,
                                   row_index INT,
                                   column_index INT
);