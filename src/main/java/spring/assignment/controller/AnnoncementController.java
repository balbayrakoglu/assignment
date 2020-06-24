package spring.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import spring.assignment.model.Announcement;
import spring.assignment.repository.AnnouncementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/")
public class AnnoncementController {

    @Autowired
    AnnouncementRepository announcementRepository;

    @GetMapping("announcement")
    public ResponseEntity<List<Announcement>> getAllAnnouncement(@RequestParam(required = false) String name) {
        try {
            List<Announcement> announcement = new ArrayList<Announcement>();

            if (name == null)
                announcementRepository.findAll().forEach(announcement::add);
            else
                announcementRepository.findByName(name).forEach(announcement::add);

            if (announcement.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(announcement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("announcement/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable("id") long id) {
        Optional<Announcement> announcementData = announcementRepository.findById(id);

        if (announcementData.isPresent()) {
            return new ResponseEntity<>(announcementData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("announcement")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        try {
            Announcement announcement1 = announcementRepository.save(new Announcement(announcement.getName(), announcement.getDescription()));
            return new ResponseEntity<>(announcement1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("announcement/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable("id") long id, @RequestBody Announcement announcement) {
        Optional<Announcement> announcementData = announcementRepository.findById(id);

        if (announcementData.isPresent()) {
            Announcement announcement1 = announcementData.get();
            announcement1.setName(announcement.getName());
            announcement1.setDescription(announcement.getDescription());

            return new ResponseEntity<>(announcementRepository.save(announcement1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("announcement/{id}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAnnouncement(@PathVariable("id") long id) {
        try {
            announcementRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("announcement")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAllAnnouncement() {
        try {
            announcementRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("announcement/name")
    public ResponseEntity<List<Announcement>> findByName(String name) {
        try {
            List<Announcement> announcement = announcementRepository.findByName(name);

            if (announcement.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(announcement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
