--------------------------------------------------------- empBook----------------------------------------------------------------------
drop table if exists empBook;
drop table if exists service;

-- 建立 empBook
create table if not exists empBook(
	empId int not null primary key,
	empName varchar(50) not null unique,
	emppassword varchar(50) not null,
	empSex varchar(50) not null,
	empDepartment varchar(50) not null,
	empDeptno int not null,
	empJob varchar(50) not null,
	levelId int not null,
	hireDate date not null,
	salary float not null
);

insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(101,"Solar","pass101","female","Sales",1,"Engineer",1,"1991-02-21",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(102,"Moonbyul","pass102","male","Sales",1,"Boss",2,"1992-12-22",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(103,"Wheein","pass103","male","Sales",1,"Engineer",1,"1995-04-17",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(104,"Hwasa","pass104","female","Sales",1,"Engineer",1,"1995-07-23",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(201,"Leeteuk","pass201","female","Admin",2,"Engineer",1,"1983-07-01",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(202,"Heechul","pass202","male","Admin",2,"Boss",2,"1983-07-10",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(203,"Yesung","pass203","male","Admin",2,"Engineer",1,"1984-08-24",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(204,"Shindong","pass204","female","Admin",2,"Engineer",1,"1985-09-28",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(205,"Siwon","pass205","male","Admin",2,"Engineer",1,"1986-04-07",80000);
insert into empBook(empId,empName,emppassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(206,"Ryeowook","pass206","female","Admin",2,"Engineer",1,"1987-06-21",80000);


-- 建立 Service table
create table if not exists service(
    serviceId int primary key,
    serviceLocation varchar(50),
    serviceSubject varchar(50),
    serviceName varchar(50),
    serviceUrl varchar(50),
    levelId int not null,
    sort varchar(20)
);

-- 預設資料
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(1, 'emp','首頁', '首頁', '連結',1,1);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(2, 'emp','打卡', '打卡', '連結',1,2);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(3, 'emp','請假','請假申請', '連結',1,31);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(4, 'emp','請假','請假查詢', '連結',1,32);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(5, 'boss','請假', '請假管理', '連結',2,33);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(6, 'emp','加班', '加班申請', '連結',1,41);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(7, 'emp','加班','加班查詢', '連結',1,42);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(8, 'boss','加班', '加班管理', '連結',2,43);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(9, 'emp','薪資', '薪資查詢', '連結',1,5);


-- normal: 根據姓名查找 menu
SELECT * FROM managementsystem.service where levelId <= (select levelId from empbook where empName = 'Yesung') order by sort;

-- boss: 根據姓名查找 menu
SELECT * FROM managementsystem.service where levelId <= (select levelId from empbook where empName = 'Heechul') order by sort;

--根據部門ID查找主管
select empId,empName,empDeptno,levelId
from empBook where empDeptno = 1 && levelId = 2







------------------------------------------------------------------- overTimeList-----------------------------------------------------------------------
drop table if exists overTimeList;


-- 建立 overTimeList
create table if not exists overTimeList(
	overTimeFormId int,
	overTimeDate date ,
	empId int,
	empName varchar(50) ,
	empDepartment varchar(50) ,
    empDeptno int,
    empJob varchar(50) ,
    
    overTimeStart datetime ,
    overTimeEnd datetime ,
    overTimeHour int,
    overTimeLeftHour int ,
    overTimeType varchar(50) ,
    overTimeTypeId int,
    overTimeTypeForDay varchar(50) ,
	overTimeReason varchar(200) ,
    verifyState int ,
    overTimeCheckReason varchar(200)
);


-- 預設假資料
insert into overTimeList(overTimeFormId, overTimeDate, empId, empName, empDepartment, empDeptno, empJob,overTimeStart,overTimeEnd,overTimeHour, overTimeLeftHour,overTimeType, overTimeTypeId, overTimeTypeForDay, overTimeReason, verifyState, overTimeCheckReason) 
				   values(1,"2024-02-21",101,"Solar","Sales",1,"Engineer","2024-02-01 18:00:00","2024-02-01 20:00:00",TIMESTAMPDIFF(HOUR,overTimeStart,overTimeEnd),46- (overTimeHour), "加班費/補修","平日","加班原因",1,"審核不通過原因");


-- 利用empId來查找已審核通過的加班時數
SELECT overTimeHour FROM managementsystem.overTimeList where empId =101 && verifyState=1;


-----------------------------------------------------overTimeTypeData----------------------------------------------------------------------------
drop table if exists overTimeTypeData;


-- 建立 ooverTimeTypeData
create table if not exists overTimeTypeData(

	groupName varchar(50),
    overTimeTypeId int,
    overTimeType varchar(50)

	
);



insert into overTimeTypeData(groupName,  overTimeTypeId,  overTimeType) values('overTimeType', 1, "加班");
insert into overTimeTypeData(groupName,  overTimeTypeId,  overTimeType) values('overTimeType', 2, "補修");
insert into overTimeTypeData(groupName,  overTimeTypeId,  overTimeType) values('overTimeTypeForDay', 1, "平日");
insert into overTimeTypeData(groupName,  overTimeTypeId,  overTimeType) values('overTimeTypeForDay', 2, "假日")				   
				
				   
