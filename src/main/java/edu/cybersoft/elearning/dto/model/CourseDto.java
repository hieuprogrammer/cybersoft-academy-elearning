package edu.cybersoft.elearning.dto.model;

import edu.cybersoft.elearning.domain.model.BaseEntity;
import edu.cybersoft.elearning.domain.model.Target;
import edu.cybersoft.elearning.domain.model.User;
import edu.cybersoft.elearning.domain.model.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto extends BaseEntity {
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

    private Long categoryId;

    private Date lastUpdate;

    private List<User> users = new ArrayList<User>();

    private List<Target> targets = new ArrayList<Target>();

    private List<Video> videos = new ArrayList<Video>();

    public CourseDto(Long id, String title, String image, int lecturesCount, int hourCount, int viewCount, double price, int discount, double promotionPrice, String description, String content, Long categoryId, Date lastUpdate, List<User> users, List<Target> targets, List<Video> videos) {
        super(id);
        this.title = title;
        this.image = image;
        this.lecturesCount = lecturesCount;
        this.hourCount = hourCount;
        this.viewCount = viewCount;
        this.price = price;
        this.discount = discount;
        this.promotionPrice = promotionPrice;
        this.description = description;
        this.content = content;
        this.categoryId = categoryId;
        this.lastUpdate = lastUpdate;
        this.users = users;
        this.targets = targets;
        this.videos = videos;
    }
}