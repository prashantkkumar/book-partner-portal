package org.example.backend.controllers;

import org.example.backend.dto.EmployeeDto;
import org.example.backend.entities.Employee;
import org.example.backend.entities.Job;
import org.example.backend.entities.Publisher;
import org.example.backend.mapper.EmployeeMapper;
import org.example.backend.repository.EmployeeRepository;
import org.example.backend.repository.JobRepository;
import org.example.backend.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    //  Get all employees
    @GetMapping
    public List<EmployeeDto> getAlgitlEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    // . Add employee


    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto req) {
        Job job = jobRepository.findById(req.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Publisher pub = publisherRepository.findById(req.getPubId())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        Employee emp = new Employee();
        emp.setEmpId(req.getEmpId());
        emp.setFname(req.getFname());
        emp.setMinit(req.getMinit());
        emp.setLname(req.getLname());
        emp.setJobLvl(req.getJobLvl());
        emp.setHireDate(req.getHireDate() != null ? req.getHireDate() : Instant.now());
        emp.setJob(job);
        emp.setPub(pub);

        employeeRepository.save(emp);

        return ResponseEntity.ok("Employee saved successfully.");
    }




// Update employee
@PutMapping("/{emp_id}")
public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable String emp_id, @RequestBody EmployeeDto dto) {
    Optional<Employee> optional = employeeRepository.findById(emp_id);
    if (optional.isPresent()) {
        Employee employee = optional.get();
        employee.setFname(dto.getFname());
        employee.setMinit(dto.getMinit());
        employee.setLname(dto.getLname());
        employee.setJobLvl(dto.getJobLvl());
        return ResponseEntity.ok(employeeMapper.toDto(employeeRepository.save(employee)));
    }
    return ResponseEntity.notFound().build();
}
// Get employee by ID
@GetMapping("/{emp_id}")
public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String emp_id) {
    return employeeRepository.findById(emp_id)
            .map(employeeMapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

//  Get employees by publisher
@GetMapping("/pub/{pub_id}")
public List<EmployeeDto> getEmployeesByPublisher(@PathVariable String pub_id) {
    return employeeRepository.findByPub_PubId(pub_id)
            .stream()
            .map(employeeMapper::toDto)
            .collect(Collectors.toList());
}

//  Get employees by job
@GetMapping("/job/{job_id}")
public List<EmployeeDto> getEmployeesByJob(@PathVariable Long job_id) {
    return employeeRepository.findByJob_Id(job_id)
            .stream()
            .map(employeeMapper::toDto)
            .collect(Collectors.toList());
}

}
