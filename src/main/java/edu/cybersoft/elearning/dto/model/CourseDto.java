package edu.cybersoft.elearning.dto.model;

import edu.cybersoft.elearning.domain.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    private String category;

    private Date lastUpdate;

    public CourseDto(Long id, String title, String image, int lecturesCount, int hourCount, int viewCount, double price, int discount, double promotionPrice, String description, String content, String category, Date lastUpdate) {
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
        this.category = category;
        this.lastUpdate = lastUpdate;
    }
}