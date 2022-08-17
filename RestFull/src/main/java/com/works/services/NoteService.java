package com.works.services;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import com.works.utils.REnum;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository nRepo;

    public ResponseEntity save(Note note) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        nRepo.save(note);
        hm.put(REnum.status, true);
        hm.put(REnum.result, note);
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, nRepo.findAll() );
        return new ResponseEntity(hm, HttpStatus.OK);
    }


}
