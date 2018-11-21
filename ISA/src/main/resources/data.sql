insert into role(name) values ("GUEST");
insert into role(name) values ("WAITER");
insert into role(name) values ("SYSTEM_MANAGER");
insert into role(name) values ("BARTENDER");
insert into role(name) values ("COOK");
insert into role(name) values ("RESTAURAN_MANAGER");
insert into role(name) values ("BIDDER");
insert into role(name) values ("BOSS");


insert into user(email,first_name,last_name,password,role) values ('danilo@a.com', 'b', 'b', 'b',1);
insert into guest(confirmed_registration,user_id) values(1,1);
insert into user(email,first_name,last_name,password,role) values('abc@d.com' , 'Branko','Dragas','123',1);
insert into guest(confirmed_registration,user_id) values(1,2);


insert into user(email,first_name,last_name,password,role) values ('laza@l.com', 'laza', 'lazic', 'b',1);
insert into guest(confirmed_registration,user_id) values(1,3);
insert into user(email,first_name,last_name,password,role) values('ttt@t.com' , 'Toni','Tonic','123',1);
insert into guest(confirmed_registration,user_id) values(1,4);

insert into user(email,first_name,last_name,password,role) values ('www@w.com', 'ludi', 'ludic', 'b',1);
insert into guest(confirmed_registration,user_id) values(1,5);
insert into user(email,first_name,last_name,password,role) values('beli@b.com' , 'Beli','belic','123',1);
insert into guest(confirmed_registration,user_id) values(1,6);



insert into user(email,first_name,last_name,password,role)  values('efg@h.com','janko','jankovic','123',2);
insert into waiter(date_of_birth,dress_size,shoes_size,user_id) values('2/Jan/2016',6,44,7);
insert into user(email,first_name,last_name,password,role)  values('122@22.com','marko','markovic','456',2);
insert into waiter(date_of_birth,dress_size,shoes_size,user_id) values('24/Feb/2011',7,43,8);


insert into user(email,first_name,last_name,password,role) values('sys@man.com','scepan','scekic','123',3);
insert into system_manager(user_id) values(9);
insert into user(email,first_name,last_name,password,role) values('man@sys.com','Bogoljub','Gagic','456',3);
insert into system_manager(user_id) values(10);

insert into user(email,first_name,last_name,password,role) values('bart1@ender.com','Pera','Peric','789',4);
insert into bartender(date_of_birth,dress_size,shoes_size,user_id) values('10/Nov/1999',11,47,11);
insert into user(email,first_name,last_name,password,role) values('bart2@ender.com','Suma','Suma','000',4);
insert into bartender(date_of_birth,dress_size,shoes_size,user_id) values('11/Nov/1999',12,48,12);


insert into user(email,first_name,last_name,password,role) values('cook@1.com','Dzejmi','Oliver','111',5);
insert into cook(date_of_birth,dress_size,shoes_size,user_id) values('1/Mar/1989',2,55,13);
insert into user(email,first_name,last_name,password,role) values('cook@2.com','Petar','Petrovic','222',5);
insert into cook(date_of_birth,dress_size,shoes_size,user_id) values('6/Mar/1989',3,45,14);


insert into user(email,first_name,last_name,password,role) values('manag1@rest.com','Miljenko','Miljenkovic','123',6);
insert into restauran_manager(confirmed_registration,user_id) values(1,15);
insert into user(email,first_name,last_name,password,role) values('manag2@rest.com','Vojislav','Seselj','123',6);
insert into restauran_manager(confirmed_registration,user_id) values(1,16);

insert into user(email,first_name,last_name,password,role) values('seller1@rest.com','Nikola','Smiljanic','456',7);
insert into bidder(user_id) values(17);
insert into user(email,first_name,last_name,password,role) values('seller2@rest.com','Vojislav','Rakocevic','789',7);
insert into bidder(user_id) values(18);

insert into restaurant(restaurant_id,name,type_of_restaurant) values(1,'kacara','raznovrsno');
insert into restaurant(restaurant_id,name,type_of_restaurant) values(2,'vegetarijano','vegan restoran');
insert into restaurant(restaurant_id,name,type_of_restaurant) values(3,'kutlaca','kuvana jela');
insert into restaurant(restaurant_id,name,type_of_restaurant) values(4,'arsenWenger','francuska kuhinja');

insert into drink(drink_id,amount,name,price,text) values(1,50,'sprajt',120,'gazirani');
insert into drink(drink_id,amount,name,price,text) values(2,50,'kola',130,'gazirani');
insert into drink(drink_id,amount,name,price,text) values(3,50,'voda',100,'rosa');
insert into drink(drink_id,amount,name,price,text) values(4,50,'pivo',160,'toceno');
insert into drink(drink_id,amount,name,price,text) values(5,50,'vino',250,'crno');

insert into foodstuffs(foodstuffs_id,amount,name,price,text) values(1,40,'cevapi',300,'rolovani');

insert into restaurant_drinks(restaurant_id,drink_id) values(1,2);
insert into restaurant_drinks(restaurant_id,drink_id) values(1,5);
insert into restaurant_drinks(restaurant_id,drink_id) values(1,3);

insert into menu(id,description,name,price,restaurant_id,food_id) values(1,'10komada','cevapi',200,1,1);