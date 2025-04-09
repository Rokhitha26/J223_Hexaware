package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import entity.Applicant;
import entity.Company;

public interface careerHub {

	boolean insertApplicant(Applicant applicant) throws FileNotFoundException, IOException ;
	int verifyApplicantByEmail(String email) throws SQLException, IOException;
	int verifyCompanyByName(String loginCompanyName) throws SQLException, IOException;
	boolean insertCompany(Company company) throws SQLException, IOException;
	void postJob(int loggedInCompanyID,Scanner scanner);
	void listPostedJobs(int loggedInCompanyID) throws SQLException, IOException;
	void listAllJobs() throws SQLException, IOException;
	void applyToJob(int applicantID, String selectedJobTitle,Scanner scanner) throws SQLException, IOException;
	void viewApplicationStatus(int applicantID) throws SQLException, IOException;
}
