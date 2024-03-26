package com.example.library.controller.detail;


import com.example.library.controller.detail.dto.CreateDetailDto;
import com.example.library.controller.detail.dto.CreateDetailResponseDto;
import com.example.library.controller.detail.dto.GetDetailDto;
import com.example.library.service.detail.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/details")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_READER')"  )
public class DetailController {
    private final DetailService detailService;

    @Autowired
    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }
    @PostMapping()
    public ResponseEntity<CreateDetailResponseDto> create(@RequestBody @Validated CreateDetailDto detail){
        var  newDetail  = detailService.create(detail);
        return new ResponseEntity<>(newDetail, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDetailDto> getOneById(@PathVariable long id){
        GetDetailDto dto = detailService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<GetDetailDto>> getAll(){
        List<GetDetailDto> dto = detailService.getAll();
        return new ResponseEntity<>(dto, HttpStatus. OK);    }
}
