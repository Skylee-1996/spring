package com.ezen.www.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController  //rest controller 는 비동기를 처리하는 컨트롤러라고 생각하면 됨
public class CommentController {
	
	@Inject
	private CommentService csv;
}
