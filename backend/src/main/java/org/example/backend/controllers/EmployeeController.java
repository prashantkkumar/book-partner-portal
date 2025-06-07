package org.example.backend.controllers;

import org.example.backend.dto.EmployeeDto;
import org.example.backend.entities.Employee;
import org.example.backend.mapper.EmployeeMapper;
import org.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

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
    public EmployeeDto addEmployee(@RequestBody EmployeeDto dto) {
        Employee employee = employeeMapper.toEntity(dto);
        return employeeMapper.toDto(employeeRepository.save(employee));
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
