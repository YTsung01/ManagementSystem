drop table if exists empBook;

-- 建立 Room
create table if not exists empBook(
empId int primary key,
empName varchar(50) not null unique,
empSex varchar(50) not null,
empDepartment varchar(50) not null,
empJob varchar(50) not null,
createDate timestamp default current_timestamp
);

insert into empBook(empId,empName,empSex,empDepartment,empJob) values(201,"Solar","female","Sales","Engineer");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(202,"Moonbyul","male","Sales","Boss");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(203,"Wheein","male","Sales","Engineer");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(204,"Hwasa","female","Sales","Engineer");

insert into empBook(empId,empName,empSex,empDepartment,empJob) values(101,"Leeteuk","male","Admin","Engineer");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(102,"Heechul","male","Admin","Engineer");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(103,"Yesung","female","Admin","Engineer");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(104,"Shindong","male","Admin","Engineer");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(105,"Siwon","male","Admin","Boss");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(106,"Ryeowook","female","Admin","Engineer")

