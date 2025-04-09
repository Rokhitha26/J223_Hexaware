package entity;

import java.util.Date;

public class jobListing {
    private int jobID;
    private int companyID;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private double salary;
    private String jobType;
    private Date postedDate;

    // Parameterized constructor
    public jobListing(int jobID, int companyID, String jobTitle, String jobDescription, 
                      String jobLocation, double salary, String jobType, Date postedDate) {
        this.jobID = jobID;
        this.companyID = companyID;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.salary = salary;
        this.jobType = jobType;
        this.postedDate = postedDate;
    }
public jobListing(int companyID, String jobTitle,double salary, String jobLocation) {
	this.companyID=companyID;
	this.jobTitle=jobTitle;
	this.salary=salary;
	this.jobLocation=jobLocation;
}

public jobListing(String jobTitle, String jobLocation, double salary, java.sql.Date jobPostDate) {
    this.jobTitle = jobTitle;
    this.jobLocation = jobLocation;
    this.salary = salary;
    this.postedDate = jobPostDate;
}

    // Getters and Setters
    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Job Title: " + jobTitle +
               "\nLocation: " + jobLocation +
               "\nSalary: ₹" + salary +
               "\n--------------------------";
    }
}
