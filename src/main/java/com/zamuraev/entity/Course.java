package com.zamuraev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="course")
@NamedQueries(value = {
        @NamedQuery(name="query_get_all_courses", query="Select c From Course c"),
        @NamedQuery(name="query_get_all_hibernate_courses", query="Select c From Course c where c.name like '%Hib%'")
})
@Cacheable
@SQLDelete(sql="update course set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

   @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
   private List<Review> reviews = new ArrayList<>();

   @ManyToMany(mappedBy = "courses")
   @JsonIgnore
   private List<Student> students = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    @PreRemove
    private void preRemove(){
        this.isDeleted=true;
    }

    public Course(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Course[%s] Review[%s]", name, reviews);
    }
}
