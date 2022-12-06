package issueabroad.first.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import issueabroad.first.domain.Article.QUser;
import issueabroad.first.domain.Article.User;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;
import issueabroad.first.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    @Override
    public Long write(UserDTO dto) {
        User entity = dtoToEntity(dto);

        log.info("test-----------------");
        log.info(dto);
        log.info(entity);

        repository.save(entity);

        return entity.getId();
    }

    @Override
    public PageResultDTO<UserDTO, User> getListMain(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageableMain(Sort.by("id").descending()); // article_id 일수도. 내림차순

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<User> result = repository.findAll(booleanBuilder, pageable);

        Function<User, UserDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<UserDTO, User> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending()); // article_id 일수도. 내림차순

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<User> result = repository.findAll(booleanBuilder, pageable); // findall 부분은 수정 필요함. 자유 / 건의 구분됐으니까

        Function<User, UserDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public UserDTO read(Long id) {
        Optional<User> result = repository.findById(id);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QUser qUser = QUser.user;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qUser.id.gt(0L);

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("d")) {
            conditionBuilder.or(qUser.type.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;

    }
}
