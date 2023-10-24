package com.example.springlastproject.storycategory;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.springlastproject.book.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "storycategory_tb")
@Entity
public class StoryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String storyCategoryName;

    @Builder
    public StoryCategory(Integer id, String storyCategoryName) {
        this.id = id;
        this.storyCategoryName = storyCategoryName;
    }

}
