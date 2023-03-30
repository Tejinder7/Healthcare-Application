Date : 22/03/2023 BY DARSHAN

insert into hospital values (1, '400086', 'Rajawadi', NULL);
insert into authorization values (1, 'doctor1', '1', 'doctor');
insert into doctor values (1, 'General', '12345', 'Tejinder', '1234567890', 1, 1);

NOTE : Patient and PendingQueue entered via postman through APIs (Patient can be entered directly by entering pendingQueue requires HOSPITAL and PATIENT data)

-----------------------------------------------------------------------------------------------------------------------------------

insert into authorization values (999, 'SuperAdmin', 'SuperAdmin', 'Super Admin');
insert into super_admin values (999);
