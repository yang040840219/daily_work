package com.test;

public class Student {
	
	
   public Student(String date, int reqNum, int finishNum) {
		super();
		this.date = date;
		this.reqNum = reqNum;
		this.finishNum = finishNum;
	}
   
   

public Student() {
}



private String date ;
   /**
	 * 订单数
	 */
	private int reqNum ;
	
	/**
	 * 成单数
	 */
	private int finishNum;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getReqNum() {
		return reqNum;
	}

	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}

	public int getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(int finishNum) {
		this.finishNum = finishNum;
	}

	@Override
	public String toString() {
		return "Student [date=" + date + ", reqNum=" + reqNum + ", finishNum=" + finishNum + "]";
	}
	
	

}
