package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import entity.Applicant;
import entity.Company;
import entity.jobListing;
import util.DBConnUtil;

public class careerHubImpl implements careerHub{

	public boolean insertApplicant(Applicant applicant) throws IOException {
	    String sql = "INSERT INTO applicants (firstname, lastname, email, phone, resume_path, experience, city, state) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, applicant.getFirstName());
	        ps.setString(2, applicant.getLastName());
	        ps.setString(3, applicant.getEmail());
	        ps.setString(4, applicant.getPhone());
	        ps.setString(5, applicant.getResumePath());
	        ps.setInt(6, applicant.getExperience());
	        ps.setString(7, applicant.getCity());
	        ps.setString(8, applicant.getState());

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public int verifyApplicantByEmail(String email) throws SQLException, IOException {
	    // Validate email format
	    if (email == null || !email.toLowerCase().endsWith("@gmail.com")) {
	        System.out.println("Invalid email format. Email must end with '@gmail.com'");
	        return -1;
	    }

	    String sql = "SELECT applicantid FROM applicants WHERE email = ?";

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int id = rs.getInt("applicantid");
	            System.out.println(" Logged in successfully. Applicant ID: " + id);
	            return id;
	        } else {
	            System.out.println("Email not registered. Please create an account.");
	            return -1;
	        }
	    }
	}

	public int verifyCompanyByName(String companyName) throws SQLException, IOException {
	    String sql = "SELECT companyID FROM companies WHERE companyName = ?";

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, companyName);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("companyID");
	        } else {
	            return -1; // Company not found
	        }
	    }
	}
	public boolean insertCompany(Company company) throws SQLException, IOException {
	    String sql = "INSERT INTO companies (companyName, location) VALUES (?, ?)";

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, company.getCompanyName());
	        ps.setString(2, company.getLocation());

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public void postJob(int companyID, Scanner get) {
	   

	    try {
	        System.out.print("Enter Job Title: ");
	        String jobTitle = get.nextLine();

	        System.out.print("Enter Job Description: ");
	        String jobDescription = get.nextLine();

	        System.out.print("Enter Job Location: ");
	        String jobLocation = get.nextLine();

	        System.out.print("Enter Salary: ");
	        double jobSalary = get.nextDouble();
	        get.nextLine(); // consume newline

	        System.out.print("Enter Job Type (e.g., Full-time, Part-time, Contract): ");
	        String jobType = get.nextLine();

	        java.sql.Date postDate = new java.sql.Date(System.currentTimeMillis());

	        String sql = "INSERT INTO jobs (companyID, jobTitle, jobDescription, jobLocation, jobSalary, jobType, jobPostDate) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

	        try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, companyID);
	            ps.setString(2, jobTitle);
	            ps.setString(3, jobDescription);
	            ps.setString(4, jobLocation);
	            ps.setDouble(5, jobSalary);
	            ps.setString(6, jobType);
	            ps.setDate(7, postDate);

	            int rows = ps.executeUpdate();

	            if (rows > 0) {
	                System.out.println("Job posted successfully!");
	            } else {
	                System.out.println("Failed to post the job.");
	            }

	        } catch (SQLException | IOException e) {
	            System.out.println("Error during job posting:");
	            e.printStackTrace();
	        }

	    } catch (Exception e) {
	        System.out.println("Invalid input. Please try again.");
	    }
	}

	public void listPostedJobs(int companyID) throws SQLException, IOException {
	    String sql = "SELECT jobTitle, jobSalary, jobLocation FROM jobs WHERE companyID = ?";
	    java.util.List<jobListing> jobList = new ArrayList<>();

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, companyID);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String title = rs.getString("jobTitle");
	            double salary = rs.getDouble("jobSalary");
	            String location = rs.getString("jobLocation");

	            jobList.add(new jobListing(companyID, title, salary, location));
	        }

	        if (jobList.isEmpty()) {
	            System.out.println("No jobs posted yet.");
	        } else {
	            System.out.println("Jobs posted by you:");
	            System.out.println("--------------------------");
	            for (jobListing job : jobList) {
	                System.out.println(job);
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println(" Error while fetching jobs.");
	        e.printStackTrace();
	    }
	}

	public void listAllJobs() throws SQLException, IOException {
	    String sql = "SELECT jobTitle, jobLocation, jobSalary, jobPostDate FROM jobs";
	    List<jobListing> jobs = new ArrayList<>();

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            String title = rs.getString("jobTitle");
	            String location = rs.getString("jobLocation");
	            double salary = rs.getDouble("jobSalary");
	            java.sql.Date postDate = rs.getDate("jobPostDate");

	            jobs.add(new jobListing(title, location, salary, postDate));
	        }

	        if (jobs.isEmpty()) {
	            System.out.println( "No jobs available right now.");
	        } else {
	            System.out.println(" Available Jobs:");
	            System.out.println("-----------------------------");
	            for (jobListing job : jobs) {
	                System.out.println(job); // uses your custom toString()
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println("❌ Error while fetching job listings.");
	        e.printStackTrace();
	    }
	}
	public void applyToJob(int applicantID, String jobTitle, Scanner get) throws SQLException, IOException {
	    

	    String jobSql = "SELECT jobID FROM jobs WHERE jobTitle = ?";
	    int jobID = -1;

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(jobSql)) {

	        ps.setString(1, jobTitle);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            jobID = rs.getInt("jobID");
	        } else {
	            System.out.println("Job title not found. Please enter a valid job.");
	            return;
	        }

	    } catch (SQLException e) {
	        System.out.println("Error checking job title.");
	        e.printStackTrace();
	        return;
	    }

	    // If job exists, ask for cover letter
	    System.out.print("Enter your Cover Letter: ");
	    String coverLetter = get.nextLine();

	    java.sql.Date applyDate = new java.sql.Date(System.currentTimeMillis());

	    // Insert into applications table
	    String appSql = "INSERT INTO applications (jobID, applicantID, applicationDate, coverLetter) VALUES (?, ?, ?, ?)";

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(appSql)) {

	        ps.setInt(1, jobID);
	        ps.setInt(2, applicantID);
	        ps.setDate(3, applyDate);
	        ps.setString(4, coverLetter);

	        int rows = ps.executeUpdate();
	        if (rows > 0) {
	            System.out.println("Successfully applied to the job!");
	        } else {
	            System.out.println(" Failed to apply for the job.");
	        }

	    } catch (SQLException e) {
	        System.out.println("Error while applying for the job.");
	        e.printStackTrace();
	    }
	}

	public void viewApplicationStatus(int applicantID) throws SQLException, IOException {
	    String sql = "SELECT applicationID, status FROM applications WHERE applicantID = ?";
	    List<String> statusList = new ArrayList<>();

	    try (Connection conn = DBConnUtil.getConnection("src/util/application.properties");
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, applicantID);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            int appID = rs.getInt("applicationID");
	            String status = rs.getString("status");
	            statusList.add("Application ID: " + appID + " | Status: " + status);
	        }

	        if (statusList.isEmpty()) {
	            System.out.println(" No applications found.");
	        } else {
	            System.out.println(" Your Applications:");
	            for (String s : statusList) {
	                System.out.println(s);
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println("Error fetching application status.");
	        e.printStackTrace();
	    }
	}

}
