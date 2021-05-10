package com.gfk.tdd.shuffler.importer;

import com.gfk.tdd.shuffler.entities.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineExtractorTest {

    @Test
    void extractParticipant_ParticipantProvided_NameExtracted() {

        String input = "Dwayne Johnson";

        LineExtractor extractor = new LineExtractor();

        Participant participant = extractor.extractParticipant(input);

        Assertions.assertEquals(input, participant.getName());
    }

    @Test
    void extractParticipant_ParticipantProvided_NumberOfSkillsCorrect() {

        String input = "Dwayne Johnson,Assembler,3";

        LineExtractor extractor = new LineExtractor();

        Participant participant = extractor.extractParticipant(input);

        Assertions.assertEquals(1, participant.getSkills().size());
    }

    @Test
    void extractParticipant_ParticipantProvided_SkillIsCorrect() {

        String input = "Dwayne Johnson,Assembler,3";

        LineExtractor extractor = new LineExtractor();

        Participant participant = extractor.extractParticipant(input);

        Assertions.assertEquals("Assembler", participant.getSkills().get(0).getLanguage());
        Assertions.assertEquals(3, participant.getSkills().get(0).getLevel());
    }

    @Test
    void extractParticipant_ParticipantsProvided_MultipleSkillsReturned() {

        String input = "Dwayne Johnson,Assembler,3,vb,2";

        LineExtractor extractor = new LineExtractor();

        Participant participant = extractor.extractParticipant(input);

        Assertions.assertEquals(2, participant.getSkills().size());
    }
}