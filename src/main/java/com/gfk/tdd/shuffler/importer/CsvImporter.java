package com.gfk.tdd.shuffler.importer;

import com.gfk.tdd.shuffler.entities.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvImporter {

    public LineExtractor lineExtractor;

    @Autowired
    public CsvImporter(LineExtractor extractor)
    {
        lineExtractor = extractor;
    }

    public List<Participant> importCsv(String csv) {
        List<Participant> result = new ArrayList<>();
        String[] lines = csv.split("\\r?\\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (!line.isEmpty()) {
                result.add(lineExtractor.extractParticipant(line));
            }
        }

        return result;
    }
}
