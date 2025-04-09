create database java;
use java;




CREATE TABLE companies (
    companyID INT AUTO_INCREMENT PRIMARY KEY,
    companyName VARCHAR(50),
    location VARCHAR(100)
);


CREATE TABLE applicants (
    applicantID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    email VARCHAR(50) UNIQUE,
    phone VARCHAR(15),
    resume_path VARCHAR(200),
    experience INT,
    city VARCHAR(20),
    state VARCHAR(20)
);


CREATE TABLE jobs (
    jobID INT AUTO_INCREMENT PRIMARY KEY,
    companyID INT,
    jobTitle VARCHAR(50),
    jobDescription VARCHAR(200),
    jobLocation VARCHAR(50),
    jobSalary DECIMAL(10,2),
    jobType VARCHAR(100),
    jobPostDate DATE,
    CONSTRAINT chk_jobs_salary_nonnegative CHECK (jobSalary >= 0),
    CONSTRAINT fk_jobs_companyid FOREIGN KEY (companyID) REFERENCES companies(companyID) ON DELETE CASCADE
);

CREATE TABLE applications (
    applicationID INT AUTO_INCREMENT PRIMARY KEY,
    jobID INT,
    applicantID INT,
    applicationDate DATE,
    coverLetter VARCHAR(200),
    CONSTRAINT uq_applications_unique_applicant_job UNIQUE (jobID, applicantID),
    CONSTRAINT fk_applications_jobid FOREIGN KEY (jobID) REFERENCES jobs(jobID) ON DELETE CASCADE,
    CONSTRAINT fk_applications_applicantid FOREIGN KEY (applicantID) REFERENCES applicants(applicantID) ON DELETE CASCADE
);
desc applications;
desc applicants;
desc jobs;
desc company;