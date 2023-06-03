package com.ganeesha.backend.repositories;

import com.ganeesha.backend.beans.LectureBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<LectureBean, Integer> {

    @Query("SELECT lb FROM LectureBean lb WHERE lb.id=:id")
    LectureBean getLectureById(@Param("id") Integer id);
}
