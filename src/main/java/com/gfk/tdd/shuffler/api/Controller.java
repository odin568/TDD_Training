package com.gfk.tdd.shuffler.api;


import com.gfk.tdd.shuffler.importer.CsvImporter;
import com.gfk.tdd.shuffler.shuffler.Shuffler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private CsvImporter importer;
    private Shuffler shuffler;

    @Autowired
    public Controller(CsvImporter importer, Shuffler shuffler) {
        this.importer = importer;
        this.shuffler = shuffler;
    }

    @PostMapping(value = "api/shuffle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> shuffle(@RequestBody String csvBody) {
        return ResponseEntity.ok(shuffler.ShuffleParticipants(importer.importCsv(csvBody)));
    }
}
