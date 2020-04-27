package com.sample.bookstore.app;

import java.util.List;

import com.sample.bookstore.service.BookstoreService;
import com.sample.bookstore.util.KeyboardUtil;
import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.Order;
import com.sample.bookstore.vo.User;

public class BookstoreOrderApp {

	
	public static void main(String[] args) throws Exception {
		
		BookstoreService service = new BookstoreService();

		while(true) {
			System.out.println("-----------------------------------------------");
			System.out.println("1.주문	2.주문정보조회	3.회원구매정보	0.종료");
			System.out.println("-----------------------------------------------");
			System.out.print("메뉴를 선택해주세요 : 	");
			int menuNum = KeyboardUtil.nextInt();
			
			if (menuNum == 1) {
				
				System.out.println("[주 문]");
				System.out.print("아이디를 입력해주세요 : ");
				String userId = KeyboardUtil.nextString();
				System.out.print("주문하실 책 번호를 입력해주세요 : ");
				int bookNo = KeyboardUtil.nextInt();
				
				Book book = service.getBook(bookNo);
				
				if ( book == null ) {
					System.out.println("존재하지 않는 책입니다.");
					return;
				}
				
				String bookTitle = book.getTitle();
				int bookPrice = book.getPrice();
				
				System.out.println("["+bookTitle+"] 은 ["+bookPrice+"] 원 입니다.");
				System.out.print("구매 가격을 입력해주세요 : ");
				int orderPrice = KeyboardUtil.nextInt();
				
				System.out.print("구매 수량을 입력해주세요 : ");
				int orderAmount = KeyboardUtil.nextInt();
				
				
				if ( orderPrice != book.getPrice()) {
					System.out.println("구매 가격이 다릅니다.");
				} else {
				
					Order order = new Order();
					User user = new User();
					user.setUserId(userId);
					
					order.setUser(user);
					order.setBook(book);
					order.setOrderPrice(orderPrice * orderAmount);
					order.setOrderAmount(orderAmount);
					
					service.addNewOrder(order);
				}
				
				System.out.println("구매가 완료되었습니다.");
				
				
			} else if ( menuNum == 2) {
				
				System.out.println("[주문정보조회]");
				System.out.print("주문 번호를 입력해주세요 : ");
				int orderNo = KeyboardUtil.nextInt();
			
				Order order = service.getOrder(orderNo);
				Book book = order.getBook();
				User user = order.getUser();
				System.out.println("==================================");
				System.out.println("주문번호 : " + order.getNo());
				System.out.println("회원번호 : " + user.getUserId());
				System.out.println("회원이름 : " + user.getUserName());
				System.out.println("책 번 호 : " + book.getNo());
				System.out.println("책 가 격 : " + book.getDiscountPrice());
				System.out.println("구매가격 : " + order.getOrderPrice());
				System.out.println("구매수량 : " + order.getOrderAmount());
				System.out.println("구매일시 : " + order.getOrderRegisteredDate());
				System.out.println("==================================");
				
				
			} else if ( menuNum == 3) {
				
				System.out.println("[회원주문정보조회]");
				System.out.print("회원 번호를 입력하세요 : ");
				String userId = KeyboardUtil.nextString();
				
				List<Order> orders = service.getMyOrders(userId);
				
				System.out.println("주문번호\t회원번호\t회원이름\t책번호\t책이름\t책가격\t구매가격\t구매수량\t구매일시");
				
				for(Order order : orders) {
					Book book = order.getBook();
					User user = order.getUser();
					
					System.out.println(order.getNo()+"\t"+user.getUserId()
							+"\t"+user.getUserName()+"\t"+book.getNo()
							+"\t"+book.getDiscountPrice()+"\t"+order.getOrderPrice()
							+"\t"+order.getOrderRegisteredDate());
				}
				
			} else if ( menuNum == 0) {
				System.out.println("프로그램 종료");
				KeyboardUtil.close();
				break;
			}
			
			
			
		}
		
		
		
	}
	
	
}
