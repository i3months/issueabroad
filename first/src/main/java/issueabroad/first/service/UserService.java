package issueabroad.first.service;

import issueabroad.first.domain.Article.User;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;

public interface UserService {
    Long write(UserDTO dto);
    UserDTO read(Long id);

    PageResultDTO<UserDTO, User> getList(PageRequestDTO requestDTO);
    PageResultDTO<UserDTO, User> getListMain(PageRequestDTO requestDTO);

    default User dtoToEntity(UserDTO dto) {
        User entity = User.builder()
                .id(dto.getId()) // article_id
                .title(dto.getTitle())
                .content(dto.getContent())
                .createDate(dto.getCreateDate())
                .viewCount(dto.getViewCount())
                .commentCount(dto.getCommentCount())
                .build();
        return entity;
    }

    default UserDTO entityToDto(User entity) {
        UserDTO dto = UserDTO.builder()
                .id(entity.getId()) // article_id
                .title(entity.getTitle())
                .content(entity.getContent())
                .createDate(entity.getCreateDate())
                .viewCount(entity.getViewCount())
                .commentCount(entity.getCommentCount())
                .build();
        return dto;

    }
}
