# Create database
CREATE SCHEMA `evozon` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

# Set password for root 
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('root');


# create table
use evozon;
create table `product` (
	`ID` int(10) UNSIGNED NOT NULL auto_increment,
    `FIRSTNAME` varchar(30) not null,
    `LASTNAME` varchar(30) not null,
    primary key (`ID`)
)


