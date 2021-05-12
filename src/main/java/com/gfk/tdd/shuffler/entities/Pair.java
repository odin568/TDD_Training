package com.gfk.tdd.shuffler.entities;

import java.util.List;

public class Pair {

    private String language;

    private List<Participant> participants;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
