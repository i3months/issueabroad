package issueabroad.first.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import issueabroad.first.domain.Article.Crawl;
import issueabroad.first.domain.Article.QCrawl;
import issueabroad.first.dto.CrawlDTO;
import issueabroad.first.dto.PageRequestDTO;
import issueabroad.first.dto.PageResultDTO;
import issueabroad.first.repository.CrawlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class CrawlServiceImpl implements CrawlService{

    private final CrawlRepository repository;

    @Override
    public PageResultDTO<CrawlDTO, Crawl> getListMain(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageableMain(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Crawl> result = repository.findAll(booleanBuilder, pageable);


        Function<Crawl, CrawlDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<CrawlDTO, Crawl> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Crawl> result = repository.findAll(booleanBuilder, pageable);


        Function<Crawl, CrawlDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<CrawlDTO, Crawl> getListViewCount(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("viewCount").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Crawl> result = repository.findAll(booleanBuilder, pageable);

        Function<Crawl, CrawlDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<CrawlDTO, Crawl> getListViewCountMain(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageableMain(Sort.by("viewCount").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Crawl> result = repository.findAll(booleanBuilder, pageable);

        Function<Crawl, CrawlDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public int updateViewCount(Long id) {
        return repository.updateViewCount(id);
    }

    @Override
    public CrawlDTO read(Long id) {
        Optional<Crawl> result = repository.findById(id);

        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QCrawl qCrawl = QCrawl.crawl;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qCrawl.id.gt(0L);

        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type.contains("d")) {
            conditionBuilder.or(qCrawl.type.contains(keyword));
        }

        if(type.contains("t")) {
            conditionBuilder.or(qCrawl.title.contains(keyword));
        }

        if(type.contains("o")) {
            conditionBuilder.or(qCrawl.originTitle.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
