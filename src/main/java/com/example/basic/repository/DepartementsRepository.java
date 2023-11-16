package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.basic.model.Departments;



@Repository
public interface DepartementsRepository extends JpaRepository<Departments, Integer>{
    
    Departments findByDepartmentName(String departmentName);
    // 메소드생성( 테이블명 + findBy + 해당 테이블의 변수명(카멜케이스)(매개변수))
}
