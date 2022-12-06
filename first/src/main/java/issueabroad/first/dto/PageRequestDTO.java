package issueabroad.first.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;
    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageableMain(Sort sort) {
        return PageRequest.of(0, 7, sort);
    }
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
    // 페이지 번호가 0부터 시작하니 -1 해주기

    // pagable 타입을 생성하는 역할.
}
