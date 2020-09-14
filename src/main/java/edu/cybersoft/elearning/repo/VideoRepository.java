package edu.cybersoft.elearning.repo;

import edu.cybersoft.elearning.domain.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}