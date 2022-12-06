package issueabroad.first.domain.Article;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("전체")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crawl extends Article{
    @Column(columnDefinition = "TEXT")
    private String originContent;
    @Column(columnDefinition = "TEXT")
    private String originTitle;
    @Column(columnDefinition = "TEXT")
    private String webSite;
    @Column(columnDefinition = "TEXT")
    private String url;

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Long viewCount;
    private Long commentCount;
    /**
     * dtoToEntity를 위해서 일단 정의.
     */
}
