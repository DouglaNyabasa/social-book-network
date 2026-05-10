package com.doug.socialbooknetwork.repository;

import com.doug.socialbooknetwork.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
