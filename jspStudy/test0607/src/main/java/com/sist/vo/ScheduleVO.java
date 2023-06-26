package com.sist.vo;


public class ScheduleVO {
	private int no;
	private String event_String;
	private String event_content;
	public ScheduleVO(int no, String event_date, String event_content) {
		super();
		this.no = no;
		this.event_String = event_date;
		this.event_content = event_content;
	}
	public ScheduleVO() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEvent_String() {
		return event_String;
	}
	public void setEvent_String(String event_String) {
		this.event_String = event_String;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	@Override
	public String toString() {
		return "{\"no\":\""+no + "\", \"event_String\":\"" + event_String + "\", \"event_content\":\"" + event_content + "\"}";
	}
	
}
