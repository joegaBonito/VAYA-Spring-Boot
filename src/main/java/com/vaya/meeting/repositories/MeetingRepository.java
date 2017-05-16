package com.vaya.meeting.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.meeting.domain.Meeting;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting,Long>{
	List<Meeting> findByOrderByMeetingName();
}
