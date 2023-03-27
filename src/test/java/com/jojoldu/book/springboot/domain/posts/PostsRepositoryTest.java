package com.jojoldu.book.springboot.domain.posts;


import org.aspectj.lang.annotation.After;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After(value =  "allMethodPointcut()")
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title="테스트 게시글";
        String content="테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());
        List<Posts> postsList=postsRepository.findAll();

        Posts posts =postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);


    }
    @Test
    public void BaseTimeEntity_등록(){
        LocalDateTime now=LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        List<Posts> postsLists=postsRepository.findAll();
        Posts posts=postsLists.get(0);

        System.out.println(">>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedData());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedData()).isAfter(now);


    }




}
