package com.ezen.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j	//2.스프링 로그포제이
@Service // 1. 직접 immplements 후 서비스 어노테이션 추가 
public class BoardServiceImpl implements BoardService {
		
	@Inject
	private BoardDAO bdao; //3.인터페이스로 repository에 생성
	
	@Inject
	private FileDAO fdao;

	@Override
	public int register(BoardDTO bdto) {
		log.info("register service impl");
		//기존보드 내용을 DB에 저장
		int isOk = bdao.insert(bdto.getBvo());
		
		//flist를 db에 저장
		if(bdto.getFlist() == null) {
			//파일의 값이 없다면..
			isOk *= 1; //그냥 성공한걸로 처리...
		}else {
			//파일 저장
			if(isOk > 0 && bdto.getFlist().size()>0) {
				//fvo는 bno는 아직 설정되기 전.
				//현재 bdto 시점에서는 아직 bno가 생성되지 않음.
				//insert를 통해 자동생성 => db에서 검색해서 가져오기
				int bno = bdao.selectBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					//파일저장
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		
		return bdao.selectList(pgvo);
	}

	@Override
	public BoardDTO getDetail(int bno) {
		int isOk = bdao.updateRead(bno);
		log.info(">>>readcount update check {}", isOk);
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBvo(bdao.select(bno)); //게시글 내용 채우기
		boardDTO.setFlist(fdao.getFileList(bno));// 파일리스트 불러오기
		
		return boardDTO;
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
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int delete(String uuid) {
		return fdao.deleteFile(uuid);
	}
	
}
