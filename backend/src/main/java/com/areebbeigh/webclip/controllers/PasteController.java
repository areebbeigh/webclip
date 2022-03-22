package com.areebbeigh.webclip.controllers;

import com.areebbeigh.webclip.dtos.PasteDto;
import com.areebbeigh.webclip.services.PasteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/private")
@Slf4j
public class PasteController {
    private final PasteService pasteService;

    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping("pastes")
    public ResponseEntity<Object> create(
            @RequestBody @Valid PasteDto paste) {
        log.info("Got paste and principal: " + paste);
        pasteService.create(paste);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("pastes")
    public ResponseEntity<Page<PasteDto>> list(Pageable pageable) {
        return new ResponseEntity<>(pasteService.findAllBy(pageable), HttpStatus.OK);
    }
}
