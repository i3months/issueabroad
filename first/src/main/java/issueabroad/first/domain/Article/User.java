package issueabroad.first.domain.Article;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("사용자")
public class User extends Article{

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Long viewCount;
    private Long commentCount;


    /**
     * dtoToEntity를 위해 일단 정의해둔다.
     */

}
