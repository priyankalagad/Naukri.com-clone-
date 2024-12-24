package in.hefshine.NaukriProject.module;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String username;

    @Column(unique = true)
    public String email;

    @Column(unique = true)
    public String mobileNumber;

    public String companyName;

    public String position;

    public String password;
}
