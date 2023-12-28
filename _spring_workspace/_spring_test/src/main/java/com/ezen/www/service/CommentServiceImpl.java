package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService{
	
	@Inject
	private CommentDAO cdao;
	
	@Inject
	private BoardDAO bdao;

	@Override
	public int post(CommentVO cvo) {
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return cdao.getList(bno);
	}

	@Override
	public int delete(int cno) {
		return cdao.delete(cno);
	}

	@Override
	public int update(CommentVO cvo) {
		return cdao.update(cvo);
	}
}
