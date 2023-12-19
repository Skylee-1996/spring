package com.ezen.www.handler;

import com.ezen.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private boolean prev,next;
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		//pgvo, totalcount 컨트롤러에서 받아서 넣기
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		//(1~10)=>10 (11~20) =>20 1/10 => 0.1 => 1*10 =>10
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty())*pgvo.getQty() ;
		this.startPage = endPage - 9;
		
		
		//한 페이지의 값이 다 차지 않은 나머지의 페이지를 하나의 페이지로 만들기위해
		//올림을 사용
		int realEndPage = (int)Math.ceil(totalCount / (double)pgvo.getQty());
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		this.prev = this.startPage >1;
		this.next = this.endPage < realEndPage;
		
	}
}
