package spring.assignment.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "announcement")
public class Announcement extends BaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="file")
    protected  File file;

    public Announcement(String name, String description) {
        this.name = name ;
        this.description = description;
    }
}
