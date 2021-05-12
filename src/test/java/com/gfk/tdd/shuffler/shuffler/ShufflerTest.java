package com.gfk.tdd.shuffler.shuffler;

import com.gfk.tdd.shuffler.entities.Pair;
import com.gfk.tdd.shuffler.entities.Participant;
import com.gfk.tdd.shuffler.importer.CsvImporter;
import com.gfk.tdd.shuffler.importer.LineExtractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ShufflerTest {

    private List<Participant> prepareFromCsv(String csv) {
        CsvImporter importer = new CsvImporter(new LineExtractor());
        return importer.importCsv(csv);
    }

    private int countDistinctParticipants(List<Pair> pairs) {
        List<Participant> distinctParticipants = new ArrayList<>();
        for(var pair : pairs) {
            for (var par : pair.getParticipants()) {
                if (!distinctParticipants.contains(par)) {
                    distinctParticipants.add(par);
                }
            }
        }
        return distinctParticipants.size();
    }


    @Test
    public void shuffleParticipants_juniorAndAverage_onePair() {

        String csv = """
             Dwayne Johnson,Java,1
             Arnold Schwarzenegger,Java,2
             """;

        Shuffler shuffler = new Shuffler();

        var pairs = shuffler.ShuffleParticipants(prepareFromCsv(csv));

        Assertions.assertEquals(1, pairs.size());
        Assertions.assertEquals(2, countDistinctParticipants(pairs));
    }

    @Test
    public void shuffleParticipants_twoLanguages_twoPairs() {

        String csv = """
             Dwayne Johnson,Java,1
             Arnold Schwarzenegger,Java,2
             Bruce Willis,.NET,3
             """;

        Shuffler shuffler = new Shuffler();

        var pairs = shuffler.ShuffleParticipants(prepareFromCsv(csv));

        Assertions.assertEquals(2, pairs.size());
        Assertions.assertEquals(3, countDistinctParticipants(pairs));
    }

    @Test
    public void shuffleParticipants_twoLanguagesDifferentSkills_twoPairsJavaPrefered() {

        String csv = """
             Dwayne Johnson,Java,1
             Arnold Schwarzenegger,.NET,3,Java,2
             Bruce Willis,.NET,3
             """;

        Shuffler shuffler = new Shuffler();

        var pairs = shuffler.ShuffleParticipants(prepareFromCsv(csv));

        Assertions.assertEquals(2, pairs.size());
        Assertions.assertTrue(pairs.stream().filter(i -> i.getLanguage().equals("Java")).findFirst().get().getParticipants().size() == 2);
        Assertions.assertEquals(3, countDistinctParticipants(pairs));
    }

    @Test
    public void shuffleParticipants_twoLanguagesSameSkills_twoPairsEvenIfJustJuniorsJustSeniors() {

        String csv = """
             Dwayne Johnson,Java,1
             Arnold Schwarzenegger,.NET,3,Java,1
             Bruce Willis,.NET,3
             """;

        Shuffler shuffler = new Shuffler();

        var pairs = shuffler.ShuffleParticipants(prepareFromCsv(csv));

        Assertions.assertEquals(2, pairs.size());
        Assertions.assertEquals(3, countDistinctParticipants(pairs));
    }

    @Test
    public void shuffleParticipants_threeLanguagesSameSkills_twoPairsEvenIfJustJuniorsJustSeniors() {

        String csv = """
             Dwayne Johnson,Java,1
             Arnold Schwarzenegger,.NET,3,Java,1
             Bruce Willis,.NET,3,Python,1
             """;

        Shuffler shuffler = new Shuffler();

        var pairs = shuffler.ShuffleParticipants(prepareFromCsv(csv));

        Assertions.assertEquals(2, pairs.size());
        Assertions.assertEquals(3, countDistinctParticipants(pairs));
    }
}