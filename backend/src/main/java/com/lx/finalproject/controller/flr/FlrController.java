package com.lx.finalproject.controller.flr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; // 올바른 Path 임포트
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.lx.finalproject.dto.flr.FlrDTO;
import com.lx.finalproject.service.createaccount.UserService;
import com.lx.finalproject.service.flr.FlrService;
import com.lx.finalproject.service.rec.RecService;
import com.lx.finalproject.vo.UserVo;

@RestController
@RequestMapping("/api/flr")
public class FlrController {
    
    @Autowired
    private FlrService flrService;

    @PostMapping("/save")
    public ResponseEntity<FlrDTO> saveFlrData(@RequestBody FlrDTO flrDto) {
        try {
            int generatedFlrPk = flrService.saveFlrData(flrDto);
            flrDto.setFlrPk(generatedFlrPk);
            return ResponseEntity.ok(flrDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    // recPk가 NULL인지 확인하는 API
    @GetMapping("/check-rec-pk/{flrPk}")
    public ResponseEntity<Boolean> checkRecPk(@PathVariable int flrPk) {
        try {
            boolean isRecPkNull = flrService.isRecPkNull(flrPk);
            return ResponseEntity.ok(isRecPkNull);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(false);
        }
    }
    
}
