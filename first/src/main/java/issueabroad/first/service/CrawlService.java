package issueabroad.first.service;

import issueabroad.first.domain.Article.Crawl;
import issueabroad.first.domain.Article.User;
import issueabroad.first.dto.CrawlDTO;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

public interface CrawlService {

    CrawlDTO read(Long id);

    PageResultDTO<CrawlDTO, Crawl> getList(PageRequestDTO requestDTO);
    PageResultDTO<CrawlDTO, Crawl> getListMain(PageRequestDTO requestDTO);
    PageResultDTO<CrawlDTO, Crawl> getListViewCount(PageRequestDTO requestDTO);
    PageResultDTO<CrawlDTO, Crawl> getListViewCountMain(PageRequestDTO requestDTO);

    @Transactional
    public int updateViewCount(Long id);

    default Crawl dtoToEntity(CrawlDTO dto) {
        Crawl entity = Crawl.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createDate(dto.getCreateDate())
                .viewCount(dto.getViewCount())
                .commentCount(dto.getCommentCount())
                .originContent(dto.getOriginContent())
                .originTitle((dto.getOriginTitle()))
                .webSite(dto.getWebSite())
                .url(dto.getUrl())
                .build();
        return entity;
    }

    default CrawlDTO entityToDto(Crawl entity) {
        CrawlDTO dto = CrawlDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createDate(entity.getCreateDate())
                .viewCount(entity.getViewCount())
                .commentCount(entity.getCommentCount())
                .originContent(entity.getOriginContent())
                .originTitle((entity.getOriginTitle()))
                .webSite(entity.getWebSite())
                .url(entity.getUrl())
                .build();
        return dto;
    }
}
