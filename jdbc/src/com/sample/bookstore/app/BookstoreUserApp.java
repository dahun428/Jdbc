package com.sample.bookstore.app;

import com.sample.bookstore.service.BookstoreService;
import com.sample.bookstore.util.KeyboardUtil;
import com.sample.bookstore.vo.User;

public class BookstoreUserApp {

	public static void main(String[] args) throws Exception{
		
		BookstoreService service = new BookstoreService();
		
		while(true) {
			System.out.println("-----------------------------------------------");
			System.out.println("1.회원가입	2.개인정보조회 3.회원수정 0.종료");
			System.out.println("-----------------------------------------------");
			System.out.print("메뉴를 선택해주세요 : 	");
			int menuNum = KeyboardUtil.nextInt();
			
			if (menuNum == 1) {
				
				System.out.print("아이디를 입력해주세요 : ");
				String userId = KeyboardUtil.nextString();
				System.out.print("비밀번호를 입력해주세요 : ");
				String password = KeyboardUtil.nextString();
				System.out.print("이름 입력해주세요 : ");
				String userName = KeyboardUtil.nextString();
				System.out.print("이메일을 입력해주세요 : ");
				String email = KeyboardUtil.nextString();
				
				User user = new User();
				user.setUserId(userId);
				user.setPassword(password);
				user.setUserName(userName);
				user.setEmail(email);
				service.addNewUser(user);
							
				System.out.println("회원 등록이 완료되었습니다.");
				
			} else if ( menuNum == 2) {
			
				System.out.print("조회하실 아이디를 입력하세요 : ");
				String userId = KeyboardUtil.nextString();
				User user = service.getUser(userId);
				
				System.out.println("====================================");
				System.out.println("아이디 : " + user.getUserId());
				System.out.println("이  름 : " + user.getUserName());
				System.out.println("이메일 : " + user.getEmail());
				System.out.println("포인트 : " + user.getPoint());
				System.out.println("가입일 : " + user.getUserRegisteredDate());
				System.out.println("====================================");
				
			} else if (menuNum == 3) {
				
				System.out.print("수정할 아이디를 입력하세요 : ");
				String userId = KeyboardUtil.nextString();
				User user = service.getUser(userId);
				
				if(user == null) {
					
					System.out.println("["+userId+"] 는 없는 아이디입니다.");
					
				} else {
					
					System.out.print("수정할 비밀번호를 입력해주세요 : ");
					String password = KeyboardUtil.nextString();
					System.out.print("수정할 이메일을 입력해주세요 : ");
					String email = KeyboardUtil.nextString();

					User newUser = new User();
					newUser.setUserId(userId);
					newUser.setPassword(password);
					newUser.setEmail(email);
					newUser.setPoint(user.getPoint());
					newUser.setUserRegisteredDate(user.getUserRegisteredDate());
					service.updateUser(newUser);
					
				}
				
				
				
			} else if ( menuNum == 0) {
				System.out.println("프로그램 종료");
				KeyboardUtil.close();
				break;
			}
			
			
			
		}
	}
	
}
