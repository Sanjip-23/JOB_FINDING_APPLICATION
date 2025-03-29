package com.codeSJ.JobApplication.company;

import java.util.List;

import org.hibernate.grammars.hql.HqlParser.RootEntityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}
	
	@PostMapping
	public ResponseEntity<String> addCompany(@RequestBody Company company) {
		companyService.createCompany(company);
		return new ResponseEntity<String>("COMPANY CREATED SUCCESSFULLY",HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> getCompaniesById(@PathVariable long id){
		Company company=companyService.getCompanyById(id);
		if(company!=null)
		    return new ResponseEntity<Company>(companyService.getCompanyById(id),HttpStatus.OK);
		return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
	}
    
	@GetMapping
	public ResponseEntity<List<Company>> getAllCompanies(){
		return new ResponseEntity<List<Company>>(companyService.getAllCompanies(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCompany(@RequestBody Company company, @PathVariable long id){
		boolean updateCompany = companyService.updateCompany(company, id);
        if(updateCompany == true) {
    		return new ResponseEntity<String>("Company Updated successfully",HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<String>("Not Updated",HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompanyById(@PathVariable long id){
		boolean deleteCompany = companyService.deleteCompanyById(id);
		if(deleteCompany) {
			return new ResponseEntity<String>("Company Deleted", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Company Not Found",HttpStatus.NOT_FOUND);
		}
	}
}
