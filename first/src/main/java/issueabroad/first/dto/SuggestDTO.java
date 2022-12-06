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
public class SuggestDTO {

    private Long id;
    private String title;
    private String content;
    private String type;
    private LocalDateTime createDate;
    private Long viewCount;
    private Long commentCount;

}