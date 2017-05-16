package com.vaya.meeting.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.meeting.domain.Meeting;
import com.vaya.meeting.repositories.MeetingRepository;
import com.vaya.meeting.services.MeetingService;
@Service
public class MeetingServiceImpl implements MeetingService {
	@Autowired 
	private MeetingRepository meetingRepository;
	
	public MeetingServiceImpl(MeetingRepository meetingRepository) {
		this.meetingRepository = meetingRepository;
	}

	public List<Meeting> meetingList() {
		return meetingRepository.findByOrderByMeetingName();
	}

	public void save(Meeting meeting) {
		meetingRepository.save(meeting);
	}
}
