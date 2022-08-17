package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteRestController {

    final NoteService nService;
    public NoteRestController(NoteService nService) {
        this.nService = nService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Note note) {
        return nService.save(note);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return nService.list();
    }

}
