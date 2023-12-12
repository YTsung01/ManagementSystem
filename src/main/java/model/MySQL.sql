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

insert into empBook(empId,empName,empSex,empDepartment,empJob) values(001,"Solar","female","Sales","Manager");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(002,"Moonbyul","male","Sales","Boss");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(003,"Wheein","male","Sales","Engineer");
insert into empBook(empId,empName,empSex,empDepartment,empJob) values(004,"Hwasa","female","Sales","Engineer")


