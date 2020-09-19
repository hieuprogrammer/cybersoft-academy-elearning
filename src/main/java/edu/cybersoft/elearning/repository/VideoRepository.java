package edu.cybersoft.elearning.repository;

import edu.cybersoft.elearning.domain.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}