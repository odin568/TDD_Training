package com.gfk.tdd.shuffler.importer;

import com.gfk.tdd.shuffler.entities.LanguageSkill;
import com.gfk.tdd.shuffler.entities.Participant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LineExtractor {

    public Participant extractParticipant(String csvLine) {

        String[] chunk = csvLine.split(",");

        Participant participant = new Participant();

        participant.setName(chunk[0]);
        int nrSkills = chunk.length/2;

        List<LanguageSkill> skills = new ArrayList<>();
        for (int i = 0; i < nrSkills; i++) {
            LanguageSkill skill = new LanguageSkill();
            skill.setLanguage(chunk[i * 2 + 1]);
            skill.setLevel(Integer.parseInt(chunk[i * 2 + 2]));
            skills.add(skill);
        }
        participant.setSkills(skills);

        return participant;
    }

}
