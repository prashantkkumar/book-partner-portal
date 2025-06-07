package org.example.backend.repository;




import org.example.backend.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByPub_PubId(String pubId);  // pub.getPubId()

    List<Employee> findByJob_Id(Long id);  // job.getId()
}


