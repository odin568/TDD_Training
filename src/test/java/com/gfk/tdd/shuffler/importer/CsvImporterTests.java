package com.gfk.tdd.shuffler.importer;

import com.gfk.tdd.shuffler.entities.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CsvImporterTests {

    @Test
    public void importCsv_csvWithOneParticipantProvided_returnsOneParticipant()
    {
        LineExtractor stub = mock(LineExtractor.class);
        when(stub.extractParticipant(anyString())).thenReturn(new Participant());

        CsvImporter importer = new CsvImporter(stub);
        String exampleCSV =
                "John Doe,Java,1";

        List<Participant> participantsList = importer.importCsv(exampleCSV);

        Assertions.assertEquals(1, participantsList.size());
    }

    @Test
    public void importCsv_csvWithMultipleParticiantsProvided_returnsCorrectNumberOfParticipants()
    {
        LineExtractor stub = mock(LineExtractor.class);
        when(stub.extractParticipant(anyString())).thenReturn(new Participant());

        CsvImporter importer = new CsvImporter(stub);
        String exampleCSV =
                "John Doe\r\n" +
                "Uliana Eli\r\n" +
                "Dwayne Johnson";

        List<Participant> participantsList = importer.importCsv(exampleCSV);

        Assertions.assertEquals(3, participantsList.size());
    }

    @Test
    public void importCsv_emptyLinesInCsv_getIgnored()
    {
        LineExtractor stub = mock(LineExtractor.class);
        when(stub.extractParticipant(anyString())).thenReturn(new Participant());

        CsvImporter importer = new CsvImporter(stub);
        String exampleCSV =
                "John Doe\r\n" +
                "Uliana Eli\r\n" +
                "\r\n" +
                "Dwayne Johnson" +
                "\r\n";

        List<Participant> participantsList = importer.importCsv(exampleCSV);

        Assertions.assertEquals(3, participantsList.size());
    }

}



