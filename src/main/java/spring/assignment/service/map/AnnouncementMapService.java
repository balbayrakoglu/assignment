package spring.assignment.service.map;

import org.springframework.transaction.annotation.Transactional;
import spring.assignment.model.Announcement;
import spring.assignment.service.AnnouncementService;

import java.util.Set;

public class AnnouncementMapService extends AbstractMapService<Announcement, Long> implements AnnouncementService {

    @Override
    @Transactional(readOnly = true)
    public Set<Announcement> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteById(Long id) {

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Announcement object) {

    }

    @Override
    @Transactional(readOnly = true)
    public Announcement save(Announcement object) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Announcement findById(Long id) {
        return null;
    }
}
