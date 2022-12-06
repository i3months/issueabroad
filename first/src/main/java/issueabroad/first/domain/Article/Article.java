package issueabroad.first.domain.Article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@DiscriminatorColumn(name = "dtype")
@EntityListeners(value = {AuditingEntityListener.class}) // 엔티티의 변화를 감지해 createDate를 갱신
@MappedSuperclass
@ToString
public abstract class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    private LocalDateTime createDate;
    private Long viewCount;
    private Long commentCount;
    private String type;

    /**
     * 나중에 몇 가지 더 추가하기
     * 종이에 적은거 참고해서.. 댓글 달려면 멤버도 추가해야되겠지..
     * 댓글 데이터베이스의 아이디도 추가해야되고..
     * 일단은 핵심 기능에 집중
     */
}
