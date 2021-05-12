package com.gfk.tdd.shuffler.api;

import com.gfk.tdd.shuffler.importer.CsvImporter;
import com.gfk.tdd.shuffler.shuffler.Shuffler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class ShuffleControllerTest {

    @Test
    void shuffle() {

        CsvImporter importer = mock(CsvImporter.class);
        Shuffler shuffler = mock(Shuffler.class);


        ShuffleController ctr = new ShuffleController(importer, shuffler);

        ResponseEntity<Object> result = ctr.shuffle("");

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(importer, times(1)).importCsv(anyString());
        verify(shuffler, times(1)).ShuffleParticipants(any());
    }
}