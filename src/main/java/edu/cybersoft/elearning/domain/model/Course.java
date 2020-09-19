package edu.cybersoft.elearning.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {
    private String title;

    private String image;

    private int lecturesCount;

    private int hourCount;

    private int viewCount;

    private double price;

    private int discount;

    private double promotionPrice;

    private String description;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date lastUpdate = new Date();

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<User> users = new ArrayList<User>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Target> targets = new ArrayList<Target>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Video> videos = new ArrayList<Video>();

    public Course(Long id) {
        super(id);
    }
}