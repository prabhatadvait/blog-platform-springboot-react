package com.prabhat.blog.repositories;

import com.prabhat.blog.domain.PostStatus;
import com.prabhat.blog.domain.entities.Category;
import com.prabhat.blog.domain.entities.Post;
import com.prabhat.blog.domain.entities.Tag;
import com.prabhat.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository  extends JpaRepository<Post, UUID> {
    //This is for postRepository when both the CategoryId and tag id is there
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);

    //This is for the PostRepository when only CategoryId is there
    List<Post> findAllByStatusAndCategory (PostStatus status, Category category);

    //This is for the PostRepository when only TagId is there
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);

    //IF none of categoryid and tag is present
    List<Post> findAllByStatus(PostStatus status);

    //For DraftPost we are doing that requires some author data also
    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}
