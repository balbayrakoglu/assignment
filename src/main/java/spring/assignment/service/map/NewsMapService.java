package spring.assignment.service.map;

import org.springframework.transaction.annotation.Transactional;
import spring.assignment.model.News;
import spring.assignment.service.NewsService;

import java.util.Set;

public class NewsMapService extends AbstractMapService<News,Long>  implements NewsService {

    @Override
    @Transactional(readOnly = true)
    public Set<News> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteById(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(News object) {

    }

    @Override
    @Transactional(readOnly = true)
    public News save(News object) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public News findById(Long id) {
        return null;
    }
}
