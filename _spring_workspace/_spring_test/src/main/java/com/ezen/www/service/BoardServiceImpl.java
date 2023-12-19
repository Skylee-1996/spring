package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j	//2.스프링 로그포제이
@Service // 1. 직접 immplements 후 서비스 어노테이션 추가 
public class BoardServiceImpl implements BoardService {
		
	@Inject
	private BoardDAO bdao; //3.인터페이스로 repository에 생성

	@Override
	public int register(BoardVO bvo) {
		log.info("register service impl");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		
		return bdao.selectList(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		int isOk = bdao.updateRead(bno);
		log.info(">>>readcount update check {}", isOk);
		return bdao.select(bno);
	}


	@Override
	public int update(BoardVO bvo) {
		 return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		return bdao.delete(bno);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return bdao.getTotalCount();
	}
	
}
