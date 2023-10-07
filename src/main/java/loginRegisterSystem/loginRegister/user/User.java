package loginRegisterSystem.loginRegister.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Setter
public class User {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
