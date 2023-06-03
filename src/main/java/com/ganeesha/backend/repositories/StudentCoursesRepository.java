package com.ganeesha.backend.repositories;

import com.ganeesha.backend.beans.StudentCoursesBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCoursesRepository extends JpaRepository<StudentCoursesBean, Integer> {

    @Query("SELECT sc FROM StudentCoursesBean sc WHERE sc.studentBean.id=:id")
    List<StudentCoursesBean> findAllByStudentId(@Param("id") Integer id);

    @Query("SELECT sc FROM StudentCoursesBean sc WHERE sc.studentBean.id=:sid AND sc.courseBean.id=:cid")
    StudentCoursesBean findByStudentCourseIds(@Param("sid") Integer sid, @Param("cid")Integer cid);

}
