package com.exemple.language.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Document(collection = "LanguagesDB")
public class Language {

    @Id
    private String id;
    private String title;
    private String image;
    private int ranking;

    public Language(String title, String image, int ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }

    public void updateFrom( Language newLanguage) {

        this.title = newLanguage.getTitle();
        this.image = newLanguage.getImage();
        this.ranking = newLanguage.getRanking();
    }


}
