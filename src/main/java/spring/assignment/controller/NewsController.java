package spring.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import spring.assignment.model.News;
import spring.assignment.repository.NewsRepository;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;

    @GetMapping("news")
    public ResponseEntity<List<News>> getAllNews(@RequestParam(required = false) String name) {
        try {
            List<News> news = new ArrayList<News>();

            if (name == null)
                newsRepository.findAll().forEach(news::add);
            else
                newsRepository.findByName(name).forEach(news::add);

            if (news.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable("id") long id) {
        Optional<News> newsData = newsRepository.findById(id);

        if (newsData.isPresent()) {
            return new ResponseEntity<>(newsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("news")
    public ResponseEntity<News> createNews(@RequestBody News news) {
        Date date ;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        try {
            News news1 = newsRepository.save(new News(news.getName(), news.getDescription(),date));
            return new ResponseEntity<>(news1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("news/{id}")
    public ResponseEntity<News> updateNews(@PathVariable("id") long id, @RequestBody News news) {
        Optional<News> newsData = newsRepository.findById(id);

        if (newsData.isPresent()) {
            News news1 = newsData.get();
            news1.setName(news.getName());
            news1.setDescription(news.getDescription());

            return new ResponseEntity<>(newsRepository.save(news1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("news/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteNews(@PathVariable("id") long id) {
        try {
            newsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/news")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAllNews() {
        try {
            newsRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("news/name")
    public ResponseEntity<List<News>> findByName(String name) {
        try {
            List<News> news = newsRepository.findByName(name);

            if (news.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}

