package com.example.dumbrothers.repository;

import com.example.dumbrothers.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface DumRepository extends JpaRepository<Link,Long> {

    @Query("SELECT d FROM Link d ORDER BY d.id DESC")
    ArrayList<Link> findAllOrderByDescId();

}
