package com.pros.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String studentId;

    @NotBlank(message = "Student Name Cannot be blank!!!")
    @Size(min = 2, max = 50, message = "Name must contain minimum 2 or maximum 50 characters")
    private String studentName;

    @NotBlank(message = "Student Email Cannot be blank!!!")
    @Column(unique = true)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email format is invalid, should be in john@email.com")
    @Size(min = 10, max = 200, message = "Email must contain minimum 10 or maximum 200 characters")
    private  String email;

    @NotBlank(message = "Profile picture URL cannot blank!!!")
    @URL(message = "Profile picture URL must be valid")
    private String profilePic;

    @NotEmpty(message = "At least one tech should be included!!!")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "student_technologies", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "technology")
    private List<Tech> techStacks;
}
