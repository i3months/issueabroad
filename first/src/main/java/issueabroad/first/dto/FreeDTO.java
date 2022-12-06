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
public class FreeDTO {

    private Long id;
    private String title;
    private String type;
    private String content;
    private LocalDateTime createDate;
    private Long viewCount;
    private Long commentCount;

}