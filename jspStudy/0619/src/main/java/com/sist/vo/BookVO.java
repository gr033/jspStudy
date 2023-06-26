package com.sist.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
	private int bookid;
	private String bookname;
	private String publisher;
	private int price;
	
}
