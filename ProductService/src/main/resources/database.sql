CREATE DATABSE shopapp;
USE shopapp;
create table users
(
    Id int identity primary key,
    FullName    nvarchar(255) not null,
    Address     nvarchar(255),
    Password    nvarchar(255) not null,
    NumberPhone varchar(10)   not null,
    DateOfBirth date          not null,
    CreatedAt   datetime default getdate(),
    UpdatedAt   datetime default getdate(),
    IsActive    tinyint  default 0
)
-- Create table on mysql
CREATE TABLE users(
   id int primary key AUTO_INCREMENT,
   full_name varchar(255),
   address varchar(255),
   password varchar(255),
   number_phone varchar(10),
   date_of_birth datetime default current_timestamp,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp
);

ALTER TABLE users ADD COLUMN role_id INT NOT NULL DEFAULT 2;
ALTER TABLE users ADD CONSTRAINT FOREIGN KEY(role_id) REFERENCES Roles(id);
ALTER TABLE users ADD COLUMN is_active TINYINT(1) DEFAULT 0;
ALTER TABLE users ADD COLUMN facebook_account_id INT DEFAULT 0;
ALTER TABLE users ADD COLUMN google_account_id INT DEFAULT 0;
alter table users
    change number_phone phone_number varchar(10) null;
ALTER TABLE users MODIFY phone_number VARCHAR(10) UNIQUE
ALTER TABLE users modify is_active NVARCHAR(255) NOT NULL ;

INSERT INTO users(full_name, address, password, number_phone) value ("Nguyen van A", "Nghe An", "123456", "0987487845");

CREATE TABLE categories(
   id int primary key auto_increment,
   name nvarchar(255),
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp
);
ALTER TABLE categories ADD COLUMN description NVARCHAR(255) null;

CREATE TABLE product(
    id INT primary key AUTO_INCREMENT,
    category_id int check (category_id >0),
    name nvarchar(255) NOT NULL,
    description longtext,
    thumbnail varchar(255) default '',
    price float not null check ( price >0 ),
    number_of_product int default 0,
    is_active tinyint(1) default 1,
    created_at datetime default current_timestamp,
    updated_at datetime default current_timestamp,
    foreign key(category_id) references categories(id)
);
rename table product to products;
ALTER table products modify is_active VARCHAR(255) NOT NULL;
ALTER TABLE products ADD COLUMN product_id VARCHAR(300) NOT NULL UNIQUE ;
ALTER TABLE products drop constraint products_ibfk_1;

CREATE TABLE product_images (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL CHECK ( product_id > 0 ),
    image_url VARCHAR(300) NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE Roles(
    id int primary key,
    name varchar(255),
);
rename table Roles to roles;

CREATE TABLE tokens(
    id int primary key auto_increment,
    token varchar(255) UNIQUE not null ,
    token_type varchar(50) not null ,
    expiration_date datetime not null,
    revoked tinyint(1) not null ,
    expired tinyint(1) not null,
    user_id int check ( user_id >0 ),
    foreign key(user_id) references users(id)
);
