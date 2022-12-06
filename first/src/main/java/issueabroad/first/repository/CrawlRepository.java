package issueabroad.first.repository;

import issueabroad.first.domain.Article.Crawl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CrawlRepository extends JpaRepository<Crawl, Long>, QuerydslPredicateExecutor<Crawl> {

    @Modifying
    @Query("update Crawl c set c.viewCount = c.viewCount + 1 where c.id = :id")
    int updateViewCount(Long id);
}
