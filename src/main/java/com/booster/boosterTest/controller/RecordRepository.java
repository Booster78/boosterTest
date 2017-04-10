package com.booster.boosterTest.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Podcast, Long> {

    List<Podcast> findByUserId(String userId);
}