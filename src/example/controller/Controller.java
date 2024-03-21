package example.controller;

import java.util.Scanner;

import example.dto.Member;

public abstract class Controller {
	public Scanner sc; 
	public static Member loginedMember;//로그인 여부 확인
	
	public static boolean isLogined() { //추상클래스도 일반구상메서드 가질 수 있음
		return loginedMember != null;
	}
		
	public abstract void doAction(String cmd, String methodName);
	public abstract void makeTestData();
	
}
