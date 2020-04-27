package com.sample.bookstore.app;

import java.util.List;

import com.sample.bookstore.service.BookstoreService;
import com.sample.bookstore.util.KeyboardUtil;
import com.sample.bookstore.vo.Answer;
import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.Order;
import com.sample.bookstore.vo.Question;
import com.sample.bookstore.vo.User;

public class Bookstore {

	
	
	public static void main(String[] args) throws Exception{
		
		BookstoreService service = new BookstoreService();

		while(true) {
			
			System.out.println("---------------------------------------------------------------------");
			System.out.println("1.가입	2.책 검색	3.책 정보	4.주문	5.내주문	6.주문정보	7.문의게시판	0.종료");
			System.out.println("---------------------------------------------------------------------");
			System.out.print("메뉴를 선택해주세요 : 	");
			
			int menuNum = KeyboardUtil.nextInt();
			
			if (menuNum == 1) {
				
				System.out.println();
				System.out.println("[회원가입]");
				System.out.print("아이디를 입력해주세요 : ");
				String userId = KeyboardUtil.nextString();
				System.out.print("비밀번호를 입력해주세요 : ");
				String password = KeyboardUtil.nextString();
				System.out.print("이름 입력해주세요 : ");
				String userName = KeyboardUtil.nextString();
				System.out.print("이메일을 입력해주세요 : ");
				String email = KeyboardUtil.nextString();
				
				User newUser = new User();
				newUser.setUserId(userId);
				newUser.setPassword(password);
				newUser.setUserName(userName);
				newUser.setEmail(email);
				
				boolean isSuccess = service.isDuplicatedUser(newUser);
				if(isSuccess) {
					service.addNewUser(newUser);
					System.out.println("회원 등록이 완료되었습니다.");
				} else {
					System.out.println("이미 사용중인 아이디 입니다.");
				}
				
			} else if (menuNum == 2) {
				
				System.out.println();
				System.out.println("[책 검색하기]");
				System.out.println("키워드를 입력하세요 : ");
				String keyword = KeyboardUtil.nextString();
				List<Book> books = service.getBookByKeyword(keyword);
				if(books.isEmpty()) {
					System.out.println("검색결과가 존재하지 않습니다.");
				} else {
					System.out.println("["+books.size()+"]권 의 책이 조회되었습니다.");
					System.out.println("============================================");
					System.out.println("번호\t제목\t저자\t출판사\t가격");
					System.out.println("============================================");
					for(Book book : books) {
						System.out.println(book.getNo()+"\t"+book.getTitle()+"\t"+book.getWriter()
											+"\t"+book.getPublisher()+"\t"+book.getPrice());
					}
					System.out.println("------------------------------------------------");
				}
				
			} else if (menuNum == 3) {
				
				System.out.println();
				System.out.println("[책 상세 정보 조회]");
				System.out.print("책 번호를 입력하세요 : ");
				int bookNo = KeyboardUtil.nextInt();
				Book book = service.getBook(bookNo);
				if(book == null	) {
					System.out.println("["+bookNo+"] 책번호에 해당하는 책이 존재하지 않습니다.");
				} else {
					System.out.println();
					System.out.println("번호\t제목\t저자\t가격");
					 System.out.println(book.getNo()+"\t"+book.getTitle()+"\t"+book.getPublisher()+"\t"+book.getPrice());
				}
				
			} else if (menuNum == 4) {
				
				System.out.println();
				System.out.println("[주문하기]");
				System.out.print("사용자 아이디를 입력해주세요 : ");
				String userId = KeyboardUtil.nextString();
				System.out.print("주문하실 책 번호를 입력해주세요 : ");
				int bookNo = KeyboardUtil.nextInt();
				System.out.print("구매 수량을 입력해주세요 : ");
				int amount = KeyboardUtil.nextInt();
				boolean isOrderOk = service.newAddOrder(userId, bookNo, amount);
				if(isOrderOk) {
					System.out.println("구매가 완료되었습니다.");
				}else {
					System.out.println("구매가 실패하였습니다.");
				}
				
				
			} else if (menuNum == 5) {
				
				System.out.println();
				System.out.println("[내 주문 조회]");
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
				
				
			} else if (menuNum == 6) {
				
				System.out.println();
				System.out.println("[주문 정보]");
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
				
				
			} else if(menuNum == 7) {
				
				System.out.println("---------------------------------------------------------------------");
				System.out.println("1.질문전체조회	2.질문등록	3.질문조회	4.질문글삭제	5.답변전체조회	6.답변등록");
				System.out.println("---------------------------------------------------------------------");
				System.out.print("메뉴를 선택해주세요 : 	");
				int questionMenuNum = KeyboardUtil.nextInt();
				if(questionMenuNum == 1) {
					System.out.println();
					System.out.println("[질문 전체조회]");
					List<Question> questions = service.getAllQuestion();
					System.out.println("번호\t제목\t내용\t아이디\t이름\t조회수\t등록일\t질문답변상태\t질문종류\t답변번호\t답변내용");
					
					System.out.println(questions.size());
					for(Question question : questions) {
						System.out.println(question.getNo()+"\t"+question.getTitle()
						+"\t"+question.getContent()+"\t"+question.getUser().getUserId()
						+"\t"+question.getUser().getUserName()+"\t"+question.getViewCount()
						+"\t"+question.getRegisteredDate()+"\t"+question.getType()
						+"\t"+question.getAnswer().getNo()+"\t"+question.getAnswer().getContent());
					}
					
				} else if (questionMenuNum == 2) {
					
					System.out.println();
					System.out.println("[질문등록]");
					System.out.print("아이디를 입력해주세요 : ");
					String userId = KeyboardUtil.nextString();
					System.out.print("제목을 입력해주세요 : ");
					String title = KeyboardUtil.nextString();
					System.out.print("내용을 입력해주세요 : ");
					String content = KeyboardUtil.nextString();
					System.out.print("종류를 입력해주세요 (배송,결재) : ");
					String type = KeyboardUtil.nextString();
					Question question = new Question();
					User user = new User();
					user.setUserId(userId);
					question.setUser(user);
					question.setTitle(title);
					question.setContent(content);
					question.setType(type);
					service.newAddQuestion(question);
					
				} else if (questionMenuNum == 3) {
					
					System.out.println();
					System.out.println("[질문조회]");
					System.out.print("질문 번호를 입력해주세요 : ");
					int questionNo = KeyboardUtil.nextInt();
					Question question = service.getQuestion(questionNo);
					System.out.println("질문  번호 : " + question.getNo());
					System.out.println("질문  제목 : " + question.getTitle());
					System.out.println("아 이 디   : " + question.getUser().getUserId());
					System.out.println("유저이름   : " + question.getUser().getUserName());
					System.out.println("질문 상태  : " + question.getStatus());
					System.out.println("질문 타입  : " + question.getType());
					System.out.println("답변 번호  : " + question.getAnswer().getNo());
					System.out.println("답변 내용  : " + question.getAnswer().getContent());
					
					
				} else if (questionMenuNum == 4) {
					
					System.out.println();
					System.out.println("[질문삭제]");
					System.out.print("아이디를 입력해주세요 : ");
					String userId = KeyboardUtil.nextString();
					System.out.print("질문 번호를 입력해주세요 : ");
					int questionNo = KeyboardUtil.nextInt();
					boolean isSuccess = service.removeQuestion(questionNo, userId);
					if(isSuccess) {
						System.out.println("삭제가 완료되었습니다.");
					} else {
						System.out.println("아이디를 확인해주세요");
					}
					
					
				} else if (questionMenuNum == 5) {
					System.out.println();
					System.out.println("[전체 답변 조회]");
					System.out.print("질문 번호를 입력해주세요 : ");
					int questionNo = KeyboardUtil.nextInt();
										
					Answer answer = service.getAnswer(questionNo);
					if(answer == null) {
						System.out.println("없는 답변입니다.");
						
					}else {
						
						System.out.println("답변 번호 : " + answer.getNo());
						System.out.println("답변 내용 : " + answer.getContent());
						System.out.println("답변 일자 : " + answer.getRegisteredDate());
						System.out.println("질문 번호 : " + answer.getQuestionNo());
					}
					
				} else if (questionMenuNum == 6) {
					System.out.println();
					System.out.println("[답변하기]");
					System.out.print("질문 번호를 입력해주세요 : ");
					int questionNo = KeyboardUtil.nextInt();
					System.out.print("답변 내용을 입력해주세요 :	");
					String content = KeyboardUtil.nextString();
					boolean isSuccess = service.newAddAnswer(questionNo, content);
					if(isSuccess) {
						System.out.println("답변이 성공적으로 완료하였습니다.");
					}else {
						System.out.println("해당 질문에 답변이 이미완료되었습니다.");
					}
				}
				
				
			} else if (menuNum == 0) {
				
				System.out.println("프로그램 종료");
				KeyboardUtil.close();
				break;
			}
			
			
			
		}
		
		
	}
	
	
}
