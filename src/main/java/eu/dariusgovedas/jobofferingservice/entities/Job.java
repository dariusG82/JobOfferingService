package eu.dariusgovedas.jobofferingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @Type(type = "uuid-char")
    private UUID jobID;
    private String jobTitle;
    private String jobType;
    private String deadline;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private JobDetails jobDetails;
}
