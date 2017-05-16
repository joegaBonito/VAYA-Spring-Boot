package com.vaya.meeting.services;

import java.util.List;

import com.vaya.meeting.domain.Meeting;

public interface MeetingService {
	public List<Meeting> meetingList();
	public void save(Meeting meeting);
}
