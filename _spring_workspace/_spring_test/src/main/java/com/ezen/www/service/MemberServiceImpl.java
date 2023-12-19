package com.ezen.www.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.www.domain.MemberVO;
import com.ezen.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	private MemberDAO mdao;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	HttpServletRequest request;

	@Override
	public int signUp(MemberVO mvo) {
		//아이디가 중복되면 회원가입 실패
		// => 아이디만 주고 DB에서 같은 일치하는 mvo 객체를 리턴
		// 일치하는 유저가 있으면 가입 실패, 없다면 가입가능
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		if(tempMvo != null) {
			//기존아이디가 있는 경우
			return 0;
		}
		//아이디가 중복되지 않는 다면 회원가입 진행
		//password가 null이거나 값이 없다면 가입불가.
		
		if(mvo.getId()==null || mvo.getId().length() == 0) {
			return 0;
		}
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return 0;
			
		}
		//회원가입 진행
		//password는 암호화 하여 가입
		//암호화(encode) / matches(입력된 비번, 암호화된비번) => true / false
		String pw = mvo.getPw();
		
		String encodePw = passwordEncoder.encode(pw);//패스워드 암호화
		mvo.setPw(encodePw);
		
		//회원가입
		int isOk = mdao.insert(mvo);
		
		
		return isOk;
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		//로그인 유저 확인
		//아이디를 주고 해당 아이디의 객체를 리턴
		MemberVO tempMvo = mdao.getUser(mvo.getId()); // 회원가입할떄 했던 메서드 호출
		//해당 아이디가 없는 경우
		if(tempMvo == null) {
			return null;
			
		}
		//matches (원래비번, 암호화된 비번) 비교
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {	
		return tempMvo;
		}
		return null;
	}

	@Override
	public void lastLogin(String id) {
		mdao.lastLogin(id);
		
	}

	@Override
	public MemberVO getUser(String id) {
		return mdao.getUser(id);
	}

	@Override
	public int updateUser(MemberVO mvo) {
		
		//pw의 여부에 따라서 변경사항을 나누어 처리
		//pw가 없다면 기존값으로 설정 / pw가 있다면 암호화하여 수정
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			MemberVO sesMvo = (MemberVO)request.getSession().getAttribute("ses");
			mvo.setPw(sesMvo.getPw());
		}else {
		
		String pw = mvo.getPw();
		String encodePw = passwordEncoder.encode(pw);//패스워드 암호화
		mvo.setPw(encodePw);
		}
		return mdao.update(mvo);
	}

	@Override
	public int deleteUser(String id) {
		
		return mdao.delete(id);
	}

	
}
