package com.vaya.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.domain.Meeting;
import com.vaya.repositories.MeetingRepository;
@Service
public class MeetingService {
	@Autowired 
	private MeetingRepository meetingRepository;
	
	public MeetingService(MeetingRepository meetingRepository) {
		this.meetingRepository = meetingRepository;
	}

	public List<Meeting> meetingList() {
		return meetingRepository.findByOrderByMeetingName();
	}

	public void save(Meeting meeting) {
		meetingRepository.save(meeting);
	}
}
