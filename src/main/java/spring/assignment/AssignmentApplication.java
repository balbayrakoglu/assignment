package spring.assignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import spring.assignment.model.Announcement;
import spring.assignment.model.News;
import spring.assignment.repository.AnnouncementRepository;
import spring.assignment.repository.NewsRepository;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Bean
	CommandLineRunner initialData(NewsRepository newsRepository, AnnouncementRepository announcementRepository){
		return args -> {
			newsRepository.save(new News((long) 1,"Haber1","DenemeHaber1"));
			announcementRepository.save(new Announcement((long) 1,"Duyuru1","DenemeDuyuru1"));
		};
	}

}
