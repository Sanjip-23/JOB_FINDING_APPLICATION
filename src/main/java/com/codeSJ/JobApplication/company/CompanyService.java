package com.codeSJ.JobApplication.company;

import java.util.List;

public interface CompanyService {
	 void createCompany(Company company);
	 Company getCompanyById(long id);
     List<Company> getAllCompanies();
     boolean updateCompany(Company company, long id);
     boolean deleteCompanyById(long id);
}
