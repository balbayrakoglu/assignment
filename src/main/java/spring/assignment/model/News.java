package spring.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "news")

public class News extends BaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "link")
    protected String link;


    public News(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public News(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

}
