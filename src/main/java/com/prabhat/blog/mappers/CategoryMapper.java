package com.prabhat.blog.mappers;

import com.prabhat.blog.domain.PostStatus;
import com.prabhat.blog.domain.dtos.CategoryDto;
import com.prabhat.blog.domain.dtos.CreateCategoryRequest;
import com.prabhat.blog.domain.entities.Category;
import com.prabhat.blog.domain.entities.    Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

//is an annotation from MapStruct, a Java annotation processor that automatically
// generates mapping code between Java beans (DTOs, entities, etc.).
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category); // This will convert Category class into dto that is data

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long   calculatePostCount(List<Post> posts){ // this is to count the no of post
        if (null == posts)
            return 0;
        return posts
                .stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
