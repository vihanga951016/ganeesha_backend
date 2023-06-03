package com.ganeesha.backend.repositories;

import com.ganeesha.backend.beans.StudentBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentBean, Integer> {

    @Query("SELECT s FROM StudentBean s WHERE s.id=:id")
    StudentBean getStudentById(@Param("id") Integer id);
}
