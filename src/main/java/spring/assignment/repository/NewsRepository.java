package spring.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.assignment.model.News;

import java.util.List;

@Repository
public interface  NewsRepository extends JpaRepository<News, Long> {
    List<News>  findByName(String news);
}
