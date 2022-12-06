package issueabroad.first.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrawlDTO {

    private Long id;
    private String title;
    private String type;
    private String content;
    private LocalDateTime createDate;
    private Long viewCount;
    private Long commentCount;
    private String originContent;
    private String originTitle;
    private String webSite;
    private String url;

}