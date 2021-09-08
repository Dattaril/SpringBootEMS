package com.example.EMS_UST;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EMPUSTController 
{
	
	
	AtomicInteger counter=new AtomicInteger();
	
	
	@Autowired
	EMSRepository repository;
	@Autowired
	EMSAddService EMSadd;
	
	
	
	
	@GetMapping("/getEmployee")
	public List<EMS> getEMployeeByName(@RequestParam (value="EmployeeName")String Employeename)
{
	return repository.findAllByeName(Employeename);
}
	
	//to add records valid scenario-case3
	@PostMapping("/addEmployee")
    public ResponseEntity<AddResponse> addEmployeeImplementation(@RequestBody EMS ems)	{
		
		String id= ems.getEname()+counter.getAndIncrement();
		
		ems.seteID(id);
		repository.save(ems);

	//Now to get the response we will ceate beanclass with IDand MSG (Class name AddResponse)
		HttpHeaders headers= new HttpHeaders();
		AddResponse add= new AddResponse();
		add.setId(id);
		add.setMsg("Success: Employee Is Added");
		headers.add("Unique", id);
	
		
		return new  ResponseEntity<AddResponse>(add,headers,HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/getEmployee/{id}")
	public Object getEmployeeById(@PathVariable(value="id") String id)
	{
	try
	{
	EMS ems = repository.findById(id).get();
	return ems;
	}
	catch (Exception e) {
	return new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	}

	
//to update employee name and salary correpsonding to Id (path paramter)
	
	@PutMapping("/updateEmployee/{id}")
	
	public ResponseEntity<EMS> updateEmployee(@PathVariable(value="id") String id,@RequestBody EMS ems) {
		
		EMS empRecord=repository.findById(id).get();
		empRecord.setEname(ems.getEname());
		empRecord.setEsal(ems.getEsal());
		repository.save(empRecord);
		return new  ResponseEntity<EMS>(empRecord, HttpStatus.OK);
		
		
	}
	//to delete correpsonding to ID column
	@DeleteMapping("/deleteEmployee")
public  ResponseEntity<String> deleteEmplyee(@RequestBody EMS ems)
{
	EMS delrec=repository.findById(ems.geteID()).get();
	repository.delete(delrec);
	return new ResponseEntity<>("Employee Record is removed",HttpStatus.CREATED);
	
	
	
}
	
	//to delete all the records
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllEmployee()
	{
		repository.deleteAll();
		return new ResponseEntity<>("All Employee Record Deleted", HttpStatus.CREATED);
	}
	
	
	

}

	


	
//http://localhost:8080/getEmployee?EmployeeName=SAM


