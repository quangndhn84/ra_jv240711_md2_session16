create database EcommerceDB;
use EcommerceDB;
Create table Categories
(
    catalog_id          int primary key auto_increment,
    catalog_name        varchar(100) not null unique,
    catalog_description text,
    catalog_status      bit
);
create table Product
(
    product_id          char(5) primary key,
    product_name        varchar(100) not null unique,
    product_price       float check (product_price > 0),
    product_title       varchar(200),
    product_description text,
    catalog_id          int,
    foreign key (catalog_id) references Categories (catalog_id),
    product_status      bit
);
DELIMITER &&
Create procedure find_all_categories()
begin
    select * from categories;
end &&
DELIMITER  &&;
DELIMITER &&
Create procedure create_categories(
    cat_name_in varchar(100),
    cat_des_in text,
    cat_status_in bit
)
begin
    insert into Categories(catalog_name, catalog_description, catalog_status)
    values (cat_name_in, cat_des_in, cat_status_in);
end &&
DELIMITER  &&;
DELIMITER &&
Create procedure update_categories(
    cat_id_in int,
    cat_name_in varchar(100),
    cat_des_in text,
    cat_status_in bit
)
begin
    update Categories
    set catalog_name        = cat_name_in,
        catalog_description = cat_des_in,
        catalog_status      = cat_status_in
    where catalog_id = cat_id_in;
end &&
DELIMITER  &&;
DELIMITER &&
Create procedure delete_categories(cat_id_in int)
begin
    delete from categories where catalog_id = cat_id_in;
end &&
DELIMITER  &&;
DELIMITER &&
Create procedure statictic_categories(
    cat_status_in bit,
    OUT cnt_catalog int
)
begin
    set cnt_catalog = (select count(c.catalog_id)
                       from Categories c
                       where c.catalog_status = cat_status_in);
end &&
DELIMITER  &&;

DELIMITER &&
Create procedure find_categories_by_id(cat_id_in int)
begin
    select * from categories c where c.catalog_id = cat_id_in;
end &&
DELIMITER  &&;