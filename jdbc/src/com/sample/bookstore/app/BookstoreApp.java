package com.sample.bookstore.app;

import java.awt.DisplayMode;
import java.io.ObjectInputStream.GetField;
import java.util.List;

import com.sample.bookstore.dao.BookDAO;
import com.sample.bookstore.util.KeyboardUtil;
import com.sample.bookstore.vo.Book;

public class BookstoreApp {

	
	public static void main(String[] args) throws Exception{
		
		BookDAO dao = new BookDAO();
		
		while(true) {
			System.out.println("-----------------------------------------------");
			System.out.println("1.전체조회	2.검색	3.등록	4.수정	5.삭제	0.종료");
			System.out.println("-----------------------------------------------");
			System.out.print("메뉴를 선택해주세요 : 	");
			int menuNo = KeyboardUtil.nextInt();
			
			if (menuNo == 1) {
				System.out.println("[전체조회]");
				List<Book> books = dao.getAllBooks();
				
				System.out.println("전체 도서 갯수 : " + books.size());
				System.out.println("번호\t제목\t저자\t가격");
				
				for(Book book : books) {
					System.out.println(book.getNo()+"\t"+book.getTitle()
							+"\t"+book.getWriter()+"\t"+book.getPrice());
				}
				
			} else if (menuNo == 2) {
				System.out.println("[검색기능]");
				System.out.println("-----------------------------------------------");
				System.out.println("1.책 제목	2.장르	3.가격	4.책번호");
				System.out.println("-----------------------------------------------");
				System.out.print("검색 조건을 선택하세요 : ");
				int searchMenuNum = KeyboardUtil.nextInt();
				
				if (searchMenuNum == 1) {
					System.out.println();
					System.out.print("책 제목을 입력해주세요 : ");
					String title = KeyboardUtil.nextString();
					List<Book> books = dao.getBookByTitle(title);
					
					if(books.isEmpty()) {
						System.out.println("["+title+"] 에 해당하는 책정보가 존재하지 않습니다.");
					} else{
						displayBooks(books);
						
					}
				} else if (searchMenuNum == 2) {
					System.out.print("책 장르를 입력해주세요 : ");
					String genre = KeyboardUtil.nextString();
					List<Book> books = dao.getBooksByGenre(genre);
					
					
					System.out.println();
					System.out.println("전체 도서 갯수 : " + books.size());
					System.out.println("번호\t제목\t저자\t가격");
					
					for(Book book : books) {
						System.out.println(book.getNo()+"\t"+book.getTitle()
						+"\t"+book.getWriter()+"\t"+book.getPrice());
					}
					
				} else if (searchMenuNum == 3) {
					System.out.print("책 최저 가격을 입력해주세요 : ");
					int minPrice = KeyboardUtil.nextInt();
					System.out.print("책 최고 가격을 입력해주세요 : ");
					int maxPrice = KeyboardUtil.nextInt();
					List<Book> books = dao.getBooksByPriceRange(minPrice, maxPrice);
					System.out.println();
					System.out.println("전체 도서 갯수 : " + books.size());
					System.out.println("번호\t제목\t저자\t가격");
					
					for(Book book : books) {
						System.out.println(book.getNo()+"\t"+book.getTitle()
						+"\t"+book.getWriter()+"\t"+book.getPrice());
					}
				} else if (searchMenuNum == 4) {
					System.out.print("책 번호를 입력해주세요 : ");
					int bookNo = KeyboardUtil.nextInt();
					Book book = dao.getBookByNo(bookNo);
					if(book == null	) {
						System.out.println("["+bookNo+"] 책번호에 해당하는 책이 존재하지 않습니다.");
					} else {
						System.out.println();
						System.out.println("번호\t제목\t저자\t가격");
						 System.out.println(book.getNo()+"\t"+book.getTitle()+"\t"+book.getPublisher()+"\t"+book.getPrice());
					}
					
				}
				
				
			} else if (menuNo == 3) {
				

				System.out.println("책 등록하기");
				System.out.print("제목을 입력하세요  : ");
				String title = KeyboardUtil.nextString();
				System.out.print("저자를 입력하세요  : ");
				String writer = KeyboardUtil.nextString();
				System.out.print("장르를 입력하세요  : ");
				String genre = KeyboardUtil.nextString();
				System.out.print("출판사를 입력하세요  : ");
				String publisher = KeyboardUtil.nextString();
				System.out.print("가격을 입력하세요  : ");
				int price = KeyboardUtil.nextInt();
				
				Book book = new Book();
				book.setTitle(title);
				book.setWriter(writer);
				book.setGenre(genre);
				book.setPublisher(publisher);
				book.setPrice(price);
				book.setDiscountPrice((int)(price*0.9));
				dao.addBook(book);
				System.out.println("### 책 등록 완료");
				
			} else if (menuNo == 4) {
				System.out.println();
				System.out.print("수정할 책 번호를 입력해주세요 : ");
				int no = KeyboardUtil.nextInt();
				System.out.print("수정할 제목을 입력하세요 : ");
				String title = KeyboardUtil.nextString();
				System.out.print("수정할 저자을 입력하세요 : ");
				String writer = KeyboardUtil.nextString();
				System.out.print("수정할 장르을 입력하세요 : ");
				String genre = KeyboardUtil.nextString();
				System.out.print("수정할 출판사을 입력하세요 : ");
				String publisher = KeyboardUtil.nextString();
				System.out.print("수정할 가격을 입력하세요 : ");
				int price = KeyboardUtil.nextInt();
				Book book = new Book();
				book.setNo(no);
				book.setTitle(title);
				book.setWriter(writer);
				book.setGenre(genre);
				book.setPublisher(publisher);
				book.setPrice(price);
				book.setDiscountPrice((int)(price*0.9));
				dao.updateBook(book);
				
				System.out.println("수정이 완료되었습니다.");
				
			} else if (menuNo == 5) {
				System.out.println();
				System.out.print("삭제할 책 번호를 입력해주세요  : ");
				int bookNo = KeyboardUtil.nextInt();
				dao.removeBookByNo(bookNo);
				System.out.println("삭제가 완료되었습니다.");
				
			} else if (menuNo == 0) {
				System.out.println("프로그램 종료");
				KeyboardUtil.close();
				break;
			}
			
			
		}
		
		
	}
	private static void displayBooks(List<Book> books) {
		System.out.println();
		System.out.println("전체 도서 갯수 : " + books.size());
		System.out.println("번호\t제목\t저자\t가격");
		
		for(Book book : books) {
			System.out.println(book.getNo()+"\t"+book.getTitle()
			+"\t"+book.getWriter()+"\t"+book.getPrice());
		}
	}
}
