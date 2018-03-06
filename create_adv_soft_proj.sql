DROP DATABASE IF EXISTS adv_soft_proj;
CREATE DATABaSE adv_soft_proj;
USE adv_soft_proj;

-- Create tables for this database
-- Creates the members table
CREATE TABLE members (
    idmembers INT PRIMARY KEY AUTO_INCREMENT,
    family_name VARCHAR(45) NOT NULL,
    family_size INT NOT NULL
);

-- Creates the bar chits table
CREATE TABLE bar_reciepts (
    idmembers INT,
    date_of_order DATE NOT NULL,
    order_desc VARCHAR(600) NOT NULL,
    order_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT bar_reciepts_fk_members FOREIGN KEY (idmembers)
        REFERENCES members (idmembers)
);

-- Creates the kitchen chits table
CREATE TABLE kitchen_reciepts (
    idmembers INT,
    date_of_order DATE NOT NULL,
    order_desc VARCHAR(600) NOT NULL,
    order_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT kitcehn_reciepts_fk_members FOREIGN KEY (idmembers)
        REFERENCES members (idmembers)
);

-- Creates the south stand chits table
CREATE TABLE south_stand_reciepts (
    idmembers INT NOT NULL,
    date_of_order DATE NOT NULL,
    order_desc VARCHAR(600) NOT NULL,
    order_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT south_stand_reciepts_fk_members FOREIGN KEY (idmembers)
        REFERENCES members (idmembers)
);
