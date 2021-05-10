package com.gfk.tdd.shuffler.entities;

import java.util.List;

public class Participant {
    private String name;
    private List<LanguageSkill> skills;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LanguageSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<LanguageSkill> skills) {
        this.skills = skills;
    }
}
