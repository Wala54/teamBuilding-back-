package com.stage.teamb.repository;

import com.stage.teamb.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeReprository extends JpaRepository<Employee,Long> {

}
