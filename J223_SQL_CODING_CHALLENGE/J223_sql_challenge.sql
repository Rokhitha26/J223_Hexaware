create database careerhub;
use careerhub;
--  1. Provide a SQL script that initializes the database for the Job Board scenario “CareerHub”. 
-- 2.Create tables for Companies, Jobs, Applicants and Applications. 
-- 3.  Define appropriate primary keys, foreign keys, and constraints.
-- 4.  Ensure the script handles potential errors, such as if the database or tables already exist. 

create table companies(
companyID int primary key auto_increment,
companyName varchar(50),
location varchar(100));

create table jobs(
jobid int primary key auto_increment,
companyid int,
jobtitle varchar(50),
jobDescription varchar(200),
joblocation varchar(50),
jobsalary decimal(10,2),
jobtype varchar(100),
jobpostdate date,
 foreign  key(companyid) references companies(companyid)
 on delete  cascade
);
alter table jobs auto_increment=100;

create table applicants(
applicantid int primary key auto_increment,
firstname varchar(100),
lastname varchar(100),
email varchar(50),
phone varchar(9),
aresume varchar(200));
alter table applicants auto_increment=1000;
alter table applicants add column experience int;



create table applications(
applicationid int primary key auto_increment,
jobid int,
applicantid int,
applicationdate datetime,
coverletter varchar(200),
foreign key(jobid) references jobs(jobid) on delete cascade

);
alter table applications auto_increment=2000;

ALTER TABLE applications  ADD CONSTRAINT fk_applicant
FOREIGN KEY (applicantid) REFERENCES applicants(applicantid)
ON DELETE CASCADE;

insert into companies (companyname, location) values
("Hexaware","Mumbai"),
("Hybe","South Korea"),
("YG","South Korea"),
("Netflix","Los Angeles"),
("Google","Chicago"),
("Apple","California"),
("Tesla","Austin"),
("IBM","NYC"),
("Facebook","Seattle"),
("Amazon","Mexico");

INSERT INTO jobs (companyid, jobtitle, jobdescription, joblocation, jobsalary, jobtype, jobpostdate) VALUES
(1, "Software Engineer", " maintain software applications", "Mumbai", 80000, "Full-Time", "2024-04-01"),
(2, "Music Producer", "Manage  artist coordination", "Seoul", 95000, "Full-Time", "2024-03-15"),
(3, "Talent Manager", "Handle contracts and schedule for artists", "Seoul", 75000, "Full-Time", "2024-03-20"),
(4, "Content Analyst", " recommend digital content", "Los Angeles", 8500, "Full-Time", "2024-02-10"),
(5, "Data Scientist", "Work with large datasets ", "Chicago", 11000, "Full-Time", "2024-01-25"),
(6, "UX Designer", "Design interfaces ", "California", 10000, "Full-Time", "2024-02-28"),
(7, "Automotive Engineer", "Develop new car designs ", "Austin", 1200, "Full-Time", "2024-03-05"),
(8, "AI Researcher", "Work on AI models ", "NYC", 1150, "Full-Time", "2024-03-12"),
(9, "Social Media Manager", "Manage  social media engagement", "Seattle", 6500, "Full-Time", "2024-02-20"),
(10, "E-commerce Analyst", "digital strategies", "Mexico", 7000, "Full-Time", "2024-01-30");

INSERT INTO applicants (firstname, lastname, email, phone, aresume) VALUES
("Ross", "Geller", "rahul.sharma@email.com", "912345678", "resume_rahul.pdf"),
("Monica", "Geller", "soojin.kim@email.com", "923456789", "resume_soojin.pdf"),
("Chandler", "Bing", "daniel.lee@email.com", "934567890", "resume_daniel.pdf"),
("Rachel", "Green", "emily.clark@email.com", "945678901", "resume_emily.pdf"),
("Joe", "Tibiyani", "john.doe@email.com", "956789012", "resume_john.pdf"),
("Phoebe", "Buffae", "sophia.martinez@email.com", "967890123", "resume_sophia.pdf"),
("Amir", "Kahn", "amir.khan@email.com", "978901234", "resume_amir.pdf"),
("Harini", "Suresh", "hana.yamamoto@email.com", "989012345", "resume_hana.pdf"),
("Girha", "Lekshmi", "carlos.gomez@email.com", "990123456", "resume_carlos.pdf"),
("Gautham", "M", "linda.scott@email.com", "901234567", "resume_linda.pdf");

UPDATE applicants SET city = 'New York', state = 'NY' WHERE applicantid = 1000;
UPDATE applicants SET city = 'Los Angeles', state = 'CA' WHERE applicantid = 1001;
UPDATE applicants SET city = 'Chicago', state = 'IL' WHERE applicantid = 1002;
UPDATE applicants SET city = 'Houston', state = 'TX' WHERE applicantid = 1003;
UPDATE applicants SET city = 'Miami', state = 'FL' WHERE applicantid = 1004;
UPDATE applicants SET city = 'San Francisco', state = 'CA' WHERE applicantid = 1005;
UPDATE applicants SET city = 'Dallas', state = 'TX' WHERE applicantid = 1006;
UPDATE applicants SET city = 'Seattle', state = 'WA' WHERE applicantid = 1007;
UPDATE applicants SET city = 'Denver', state = 'CO' WHERE applicantid = 1008;
UPDATE applicants SET city = 'Boston', state = 'MA' WHERE applicantid = 1009;


INSERT INTO applications (jobid, applicantid, applicationdate, coverletter) VALUES
(100, 1000, "2024-04-02 10:30:05", "I have 5 years of experience in software engineering"),
(101, 1001, "2024-03-16 11:45:01", "I have experience producing albums for K-pop artists."),
(102, 1002, "2024-03-21 09:15:01", "I have 5 years of experience in talent management"),
(103, 1003, "2024-02-11 14:20:07", "I have 5 years of experience in content analysis"),
(104, 1004, "2024-01-26 16:00:09", "I have 5 years of experience in data analyssis"),
(105, 1005, "2024-02-29 13:10:50", "I have 5 years of experience in UX design."),
(106, 1006, "2024-03-06 08:30:50", "I have 5 years of experience in AI develeopment."),
(107, 1007, "2024-03-13 15:45:05", "I have 5 years of experience in AI development."),
(108, 1008, "2024-02-21 10:00:00", "I have 5 years of experience in social media management"),
(109, 1009, "2024-01-31 12:25:50", "I have 5 years of experience in ecom management");


-- 5.Write an SQL query to count the number of applications received for each job listing in the "Jobs" table. Display the job title and the corresponding application count. Ensure that it lists all jobs, even if they have no applications. 
select j.jobtitle as job, count(a.applicationid) as JCount from jobs j
left join applications a on j.jobid=a.jobid group by j.jobtitle;

-- 6.Develop an SQL query that retrieves job listings from the "Jobs" table within a specified salary range. Allow parameters for the minimum and maximum salary values. Display the job title, company name, location, and salary for each matching job. 
select j.jobtitle, c.companyname, j.joblocation, j.jobsalary
from jobs j join companies c on c.companyid=j.companyid 
where j.jobsalary between 10000 and 50000;

-- 7. Write an SQL query that retrieves the job application history for a specific applicant. Allow a parameter for the ApplicantID, and return a result set with the job titles, company names, and application dates for all the jobs the applicant has applied to. 
select j.jobtitle, c.companyname, a.applicationdate from jobs j
join applications a on j.jobid=a.jobid
join companies c on c.companyid=j.companyid 
where applicantid=1008;

-- 8. SQL query that calculates and displays the average salary offered by all companies for job listings in the "Jobs" table. Ensure that the query filters out jobs with a salary of zero. 
select c.companyname, j.jobtitle, avg(j.jobsalary) from companies c
join jobs j on c.companyid=j.companyid 
where j.jobsalary>0
group by c.companyname, j.jobtitle;

-- 9  SQL query to identify the company that has posted the most job listings. Display the company name along with the count of job listings they have posted. Handle ties if multiple companies have the same maximum count. 
select c.companyname, count(j.jobid) as jobCount from companies c
join jobs j on c.companyid=j.companyid group by c.companyname
order by jobCount desc limit 1;

-- 10 Find the applicants who have applied for positions in companies located in 'CityX' and have at least 3 years of experience

select a.firstname, a.lastname from applicants a
join applications ap on  ap.applicantid=a.applicantid
join jobs j on j.jobid=ap.jobid
join companies c on c.companyid=j.companyid
where c.location="South Korea" and a.experience>=3;

-- 11  Retrieve a list of distinct job titles with salaries between $60,000 and $80,000. 
select distinct(jobtitle) from jobs where jobsalary between 
60000 and 80000;

-- 12  Find the jobs that have not received any applications. 
select j.jobtitle, j.joblocation from jobs j left join applications a
on j.jobid=a.jobid where a.jobid is null;

-- 13 Retrieve a list of job applicants along with the companies they have applied to and the positions they have applied for. 
select a.firstname, c.companyname, j.jobtitle from applications ap
join jobs j on ap.jobid=j.jobid
join companies c on  c.companyid= j.companyid
join applicants a on a.applicantid=ap.applicantid;

-- 14 Retrieve a list of companies along with the count of jobs they have posted, even if they have not received any applications. 
select c.companyname, count(j.jobid) from companies c
left join jobs j on j.companyid=c.companyid group by c.companyname;

-- 15 List all applicants along with the companies and positions they have applied for, including those who have not applied. 
select ap.firstname, c.companyname, j.jobtitle from applicants ap
left join applications a on a.applicantid=ap.applicantid
left join jobs j on j.jobid=a.jobid
left join companies c on c.companyid=j.companyid;

-- 16  Find companies that have posted jobs with a salary higher than the average salary of all jobs.
select c.companyname from companies c join jobs j on
c.companyid=j.companyid
where j.jobsalary>(select avg(jobsalary) from jobs j);

-- 17 Display a list of applicants with their names and a concatenated string of their city and state. 
select a.firstname, concat(city,",",state)as location from applicants a;

-- 18 Retrieve a list of jobs with titles containing either 'Developer' or 'Engineer'
select *from jobs where jobtitle like "%engineer%" or jobtitle 
like "%developer%";

-- 19  Retrieve a list of applicants and the jobs they have applied for, including those who have not applied and jobs without applicants.
select a.applicantid, a.firstname, a.lastname, j.jobid, j.jobtitle, c.companyname
from applicants a
left join applications ap on a.applicantid = ap.applicantid
left join jobs j on ap.jobid = j.jobid
left join companies c on j.companyid = c.companyid
union
select a.applicantid, a.firstname, a.lastname, j.jobid, j.jobtitle, c.companyname
from jobs j
left join applications ap on j.jobid = ap.jobid
left join applicants a on ap.applicantid = a.applicantid
left join companies c on j.companyid = c.companyid
where a.applicantid is null;

-- 20.List all combinations of applicants and companies where the company is in a specific city and the applicant has more than 2 years of experience. For example: city=Chennai 
select a.firstname, c.companyname, a.experience from 
applications ap
join applicants a on a.applicantid=ap.applicantid
join jobs j on j.jobid=ap.jobid
join companies c on c.companyid=j.companyid
where experience>2 and j.joblocation="Mexico";