CREATE DATABASE myshop; 
CREATE USER 'myshopuser'@'%' IDENTIFIED BY '123456'; 
GRANT ALL PRIVILEGES ON myshop.* TO 'myshopuser'@'%';
