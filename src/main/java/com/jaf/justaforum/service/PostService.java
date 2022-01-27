package com.jaf.justaforum.service;

import com.jaf.justaforum.dao.PostDao;
import com.jaf.justaforum.dto.NewPostDto;
import com.jaf.justaforum.dto.PostDto;
import com.jaf.justaforum.exception.PostNotFoundException;
import com.jaf.justaforum.model.Post;
import com.jaf.justaforum.model.PostCategory;
import com.jaf.justaforum.util.PostConverter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {
    private final PostDao postDao = new PostDao();

    //zwraca listę postów o podanej kategorii
    public List<PostDto> getPostsByCategory(PostCategory postCategory) {
        List<Post> result = postDao.findByPostCategoryOrderByPublishedDateTimeDesc(postCategory);

        return result.stream().map(PostConverter::createShortPostDto).collect(Collectors.toList());
    }
    
	 //zwraca post o podanym id
    public PostDto getPostById(Long id) throws RuntimeException, PostNotFoundException {
        return PostConverter.createPostDto(postDao.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found.")));
    }

    //zwraca liczbę postów użytkownika o podanej nazwie
    public Long getNumberOfUserPosts(String username){
        return postDao.countByUsername(username);
    }

    public void addNewPost(NewPostDto newPostDto) {
        Post postToSave = PostMapper.map(newPostDto);

        postDao.savePost(postToSave);
    }

    //zwraca listę postów użytkownika o podanej nazwie
    public List<PostDto> getPostsByUsername(String username) {
        List<Post> userPosts = postDao.findByUsernameOrderByPublishedDateTimeDesc(username);

        return userPosts.stream().map(PostConverter::createShortPostDto).collect(Collectors.toList());
    }

    //usuwa post o podanym id
    public void delPostById(Long id){
        postDao.deletePostById(id);
    }

    //mapper konwertujący obiekt klasy NewPostDto na obiekt klasy Post
    private static class PostMapper {
        static Post map(NewPostDto newPostDto) {
            return new Post(
                    newPostDto.getTitle(),
                    newPostDto.getContent(),
                    PostCategory.valueOf(newPostDto.getPostCategory()),
                    LocalDateTime.now(),
                    newPostDto.getUsername()
            );
        }
    }

}
