drop table if exists empBook;

-- 建立 Room
create table if not exists empBook(
EmpId int primary key,
empName varchar(50) not null unique,
password varchar(50) not null,
empSex varchar(50) not null,
empDepartment varchar(50) not null,
empDeptno int not null,

empJob varchar(50) not null,
levelId int not null,
hireDate date not null,
salary float not null
);

insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(101,"Solar","pass101","female","Sales",1,"Engineer",1,"1991-02-21",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(102,"Moonbyul","pass102","male","Sales",1,"Boss",2,"1992-12-22",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(103,"Wheein","pass103","male","Sales",1,"Engineer",1,"1995-04-17",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(104,"Hwasa","pass104","female","Sales",1,"Engineer",1,"1995-07-23",80000);

insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(201,"Leeteuk","pass201","female","Admin",2,"Engineer",1,"1983-07-01",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(202,"Heechul","pass202","male","Admin",2,"Boss",2,"1983-07-10",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(203,"Yesung","pass203","male","Admin",2,"Engineer",1,"1984-08-24",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(204,"Shindong","pass204","female","Admin",2,"Engineer",1,"1985-09-28",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(205,"Siwon","pass205","male","Admin",2,"Engineer",1,"1986-04-07",80000);
insert into empBook(empId,empName,password,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(206,"Ryeowook","pass206","female","Admin",2,"Engineer",1,"1987-06-21",80000);


-- 建立 Service table
create table if not exists service(
    serviceId int primary key,
    serviceLocation varchar(50),
    serviceName varchar(50),
    serviceUrl varchar(50)
);

-- 建立 Service 與 emp 之間的關聯表 table
create table if not exists level_ref_service(
    levelId int not null,
    serviceId int not null,
    sort int default 1,
    foreign key (levelId) references level(levelId),
    foreign key (serviceId) references service(serviceId),
    constraint unique_sid_and_aid UNIQUE(levelId, serviceId)
);

-- 預設資料
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(1, 'frontend', '團購首頁', '/mvc/group_buy/frontend/main');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(2, 'frontend', '🛒 購物車', '/mvc/group_buy/frontend/cart');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(3, 'frontend', '🔞 登出', '/mvc/group_buy/logout');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(4, 'frontend', '👼 Profile', '/mvc/group_buy/frontend/profile');
insert into service (serviceId, serviceLocation, serviceName, serviceUrl) values(51, 'backend', '後台報告', '/mvc/group_buy/backend/report');


insert into level_ref_service(levelId, serviceId, sort) values(1, 1, 1);
insert into level_ref_service(levelId, serviceId, sort) values(1, 2, 2);
insert into level_ref_service(levelId, serviceId, sort) values(1, 3, 4);
insert into level_ref_service(levelId, serviceId, sort) values(1, 4, 3);
insert into level_ref_service(levelId, serviceId, sort) values(2, 1, 1);
insert into level_ref_service(levelId, serviceId, sort) values(2, 2, 2);
insert into level_ref_service(levelId, serviceId, sort) values(2, 3, 5);
insert into level_ref_service(levelId, serviceId, sort) values(2, 4, 4);
insert into level_ref_service(levelId, serviceId, sort) values(2, 51, 3);
