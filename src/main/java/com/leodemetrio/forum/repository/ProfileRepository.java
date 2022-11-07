package com.leodemetrio.forum.repository;

import com.leodemetrio.forum.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
