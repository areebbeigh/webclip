package com.areebbeigh.webclip.services;

import com.areebbeigh.webclip.dtos.PasteDto;
import com.areebbeigh.webclip.entities.PasteEntity;
import com.areebbeigh.webclip.entities.UserEntity;
import com.areebbeigh.webclip.respositories.PasteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class PasteService {
    private final PasteRepository pasteRepository;
    private final AuthenticationService authenticationService;

    public PasteService(PasteRepository pasteRepository, AuthenticationService authenticationService) {
        this.pasteRepository = pasteRepository;
        this.authenticationService = authenticationService;
    }

    public void create(@Valid PasteDto paste) {
        final UserEntity user = authenticationService.getCurrentUserEntity();
        final PasteEntity pasteEntity = new PasteEntity();
        BeanUtils.copyProperties(paste, pasteEntity);
        pasteEntity.setUser(user);
        pasteRepository.save(pasteEntity);
    }

    public Page<PasteDto> findAllBy(Pageable pageable) {
        return pasteRepository.findAllBy(pageable);
    }
}
