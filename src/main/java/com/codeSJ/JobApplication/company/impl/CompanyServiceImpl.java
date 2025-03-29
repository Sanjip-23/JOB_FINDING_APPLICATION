package com.codeSJ.JobApplication.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeSJ.JobApplication.company.Company;
import com.codeSJ.JobApplication.company.CompanyRepository;
import com.codeSJ.JobApplication.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		super();
		this.companyRepository = companyRepository;
	}
	
	
	@Override
	public void createCompany(Company company) {
		companyRepository.save(company);
	}

	@Override
	public Company getCompanyById(long id) {
//		if(companyRepository.existsById(id))
//			return companyRepository.findById(id);
//		return null;
		return companyRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	
	@Override
	public boolean updateCompany(Company updatedCompany, long id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		
		if(companyOptional.isPresent()) {
			Company company = companyOptional.get();
			company.setName(updatedCompany.getName());
			company.setDescription(updatedCompany.getDescription());
			company.setJobs(updatedCompany.getJobs());
			companyRepository.save(company);
			
			return true;
		}
	return false;
	}


	@Override
	public boolean deleteCompanyById(long id) {
		if(companyRepository.existsById(id)) {
			companyRepository.deleteById(id);
			return true;
		}
		return false;
	}



}
