package com.ganeesha.backend.repositories;

import com.ganeesha.backend.beans.CourseBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseBean, Integer> {

    @Query("SELECT c FROM CourseBean c WHERE c.id=:id")
    CourseBean getCourseById(@Param("id") Integer id);
}
