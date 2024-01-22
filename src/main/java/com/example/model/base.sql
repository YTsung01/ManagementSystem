-- 建立 Service
select serviceId, levelId, serviceLocation, serviceSubject, serviceName, serviceUrl, sort from service;

insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(1, 'emp','首頁', '首頁', '連結',1,1);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(2, 'emp','打卡', '打卡', '連結',1,2);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(3, 'emp','請假','請假申請', '連結',1,31);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(4, 'emp','請假','請假查詢', '連結',1,32);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(5, 'boss','請假', '請假管理', '連結',2,33);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(6, 'emp','加班', '加班申請', '連結',1,41);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(7, 'emp','加班','加班查詢', '連結',1,42);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(8, 'boss','加班', '加班管理', '連結',2,43);
insert into service (serviceId, serviceLocation, serviceSubject,serviceName, serviceUrl,levelId,sort) values(9, 'emp','薪資', '薪資查詢', '連結',1,5);



-- 建立員工
select empId, empName, empPassword, empSex, empDepartment, empDeptno, empJob, levelId, DATE_FORMAT(hireDate, "%Y-%m-%d") hireDate, salary, overTimeLeftHour, overTimeTotalHour, takeoffTotalHours, empAcator from empbook;
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(101,"Solar","pass101","female","Sales",1,"Engineer",1,"1991-02-21",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(102,"Moonbyul","pass102","male","Sales",1,"Boss",2,"1992-12-22",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(103,"Wheein","pass103","male","Sales",1,"Engineer",1,"1995-04-17",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(104,"Hwasa","pass104","female","Sales",1,"Engineer",1,"1995-07-23",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(201,"Leeteuk","pass201","female","Admin",2,"Engineer",1,"1983-07-01",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(202,"Heechul","pass202","male","Admin",2,"Boss",2,"1983-07-10",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(203,"Yesung","pass203","male","Admin",2,"Engineer",1,"1984-08-24",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(204,"Shindong","pass204","female","Admin",2,"Engineer",1,"1985-09-28",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(205,"Siwon","pass205","male","Admin",2,"Engineer",1,"1986-04-07",80000);
insert into empBook(empId,empName,empPassword,empSex,empDepartment,empDeptno,empJob,levelId,hireDate,salary) values(206,"Ryeowook","pass206","female","Admin",2,"Engineer",1,"1987-06-21",80000);

-- 建立薪水
select id, empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate, createDate from salary;
insert into salary (empId, basicAmonut, takeoffAmount, overtimeAmount, totalAmount, salaryDate) values(101, 35000, 2000, 5000, 38000, '2024-01');

-- 建立表單(+請假表單+附件)
select formId, type, applier, applyDate from form;
-- insert into form(formId,type, applier, applyDate) values(UUID(),1,101,'2024-01-18'); -- b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b
insert into form(formId,type, applier, applyDate) values('b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b',1,101,'2024-01-18');


select formId, agent, takeoffType, startTime, endTime, reason, verifyState, checkReason, takeoffDay, takeoffHour from takeoff;
-- insert into takeoff(formId, agent,takeoffType, startTime, endTime, reason, takeoffDay, takeoffHour) values('b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b', 102, 2 ,'2024-01-18 09:00','2024-01-18 18:00','事假', 0, 8);
insert into takeoff(formId, agent,takeoffType, startTime, endTime, reason, takeoffDay, takeoffHour) values('b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b', 102, 2 ,'2024-01-18 09:00','2024-01-18 18:00','事假', 0, 8);


select attachId, form_id, filePath, createTime, updateTime from attachement;
-- insert into attachement(form_id, filePath) values('b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b','證明.pdf');
 insert into attachement(form_id, filePath) values('b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b','證明.pdf');
 
-- 建立表單(+加班表單)

select formId, type, applier, applyDate from form;
-- insert into form(formId,type, applier, applyDate) values(UUID(),1,101,'2024-01-18'); -- a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a
insert into form(formId,type, applier, applyDate) values('a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a',2,101,'2024-01-19');

select formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason, verifyState, checkReason from overtime;
-- insert into overtime(formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason) values('b1fd4ec1-b681-11ee-adf1-6c3c8c3db22b', '2024-01-19 17:00','2024-01-19 19:00',TIMESTAMPDIFF(HOUR,startTime,endTime),1, 1, '工作太多');
insert into overtime(formId, startTime, endTime, applyHour, overtimeType, dayOrHoilday, reason) 
values('a1fd4ec1-b681-11ee-adf1-6c3c8c3db22a','2024-01-19 17:00','2024-01-19 19:00',TIMESTAMPDIFF(HOUR,startTime,endTime),1, 1, '工作太多');

-- 建立CheckIN

select checkInId, empId, checkInTime, checkOutTime from checkin;
insert into checkin( empId) values ('101');
update checkin set checkOutTime = current_timestamp() where empId=101;

-- -------------------------------

-- 查詢用戶 101 請假的所有表單
select emp.empName, f.formId, f.type, t.* from empbook emp , form f, takeoff t 
where f.applier = emp.empId and f.formId = t.formId;

-- 查詢用戶 101 加班的所有表單

select emp.empName, f.formId, f.type, o.* from empbook emp , form f, overtime o
where f.applier = emp.empId and f.formId = o.formId;

-- 查詢用戶 101 的所有相關表單
select emp.empName, f.formId, f.type, o.* ,t.* from empbook emp , form f, overtime o,takeoff t 
where f.applier = emp.empId;





