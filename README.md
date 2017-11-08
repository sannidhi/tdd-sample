### Pre-populated car db with mysql:

```
Create database demo;
Create user 'cars'@'localhost' identified by 'demo';
Grant all privileges ON demo . * to 'cars'@'localhost';
Flush privileges;
```

```
Create table cars (
     id int NOT NULL AUTO_INCREMENT,
     name varchar(255) NOT NULL,
     description varchar(255),
     PRIMARY KEY (id)
)
```

```
insert into car("model-x","full-sized, all-electric");
insert into car values(1, "model-x","full-sized, all-electric");
```
