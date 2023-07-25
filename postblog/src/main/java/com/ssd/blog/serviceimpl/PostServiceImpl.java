package com.ssd.blog.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssd.blog.entity.Post;
import com.ssd.blog.exceptions.TitleExistetExcetion;
import com.ssd.blog.payload.PostDto;
import com.ssd.blog.repository.PostRepository;

@Service
public class PostServiceImpl {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PostDto sendPost(PostDto dto) {
		
		Post post = this.modelMapper.map(dto, Post.class);
		try {
		post = postRepository.save(post);
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			throw new TitleExistetExcetion(dto.getTitle(),"Allready  "+dto.getTitle()+"   Existed");
		}
		dto = this.modelMapper.map(post, PostDto.class);
		
		return dto;
	}
	
	public List<PostDto> getAllPosts(){
		List<PostDto> dtoList = new ArrayList<>();
		List<Post> postList =   postRepository.findAll();
		
		for(Post post:postList) {
			PostDto dto = new PostDto();
			dto = this.modelMapper.map(post, PostDto.class);
			dtoList.add(dto);
		}
		
		 return dtoList;
	}

}
