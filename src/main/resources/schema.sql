create table person (
    id int not null auto_increment,
    name varchar(100) not null,
    last_name varchar(100) not null,
    phone varchar(100) not null,
    address varchar(255) not null,
    primary key (id)
);

CREATE TABLE employment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    position VARCHAR(100) NOT NULL,
    company VARCHAR(100) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    person_id INT NOT NULL,
    CONSTRAINT fk_employment_person FOREIGN KEY (person_id)
        REFERENCES person(id) ON DELETE CASCADE
);
