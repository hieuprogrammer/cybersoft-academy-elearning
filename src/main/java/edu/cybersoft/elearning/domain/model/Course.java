package edu.cybersoft.elearning.domain.model;

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

    private Date lastUpdate;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<User>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Target> targets = new ArrayList<Target>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Video> videos = new ArrayList<Video>();
}