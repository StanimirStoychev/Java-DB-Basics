package com.example.springintro.model.dto;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.EditionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BookInfo {

    private String title;

    private EditionType editionType;

    private AgeRestriction ageRestriction;

    private BigDecimal price;

    @Override
    public String toString() {
        return this.title + " "
                + this.editionType.name() + " "
                + this.ageRestriction.name() + " "
                + this.price;
    }
}
