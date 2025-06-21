package com.prabhat.blog.mappers;

import com.prabhat.blog.domain.CreatePostRequest;
import com.prabhat.blog.domain.UpdatePostRequest;
import com.prabhat.blog.domain.dtos.CreatePostRequestDto;
import com.prabhat.blog.domain.dtos.PostDto;
import com.prabhat.blog.domain.dtos.UpdatePostRequestDto;
import com.prabhat.blog.domain.entities.Post;
import com.prabhat.blog.services.PostService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "status", source = "status")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);
    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);

}
