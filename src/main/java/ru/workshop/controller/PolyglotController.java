package ru.workshop.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.workshop.domain.CodeInfo;
import ru.workshop.service.PolyglotService;

@RestController
@RequestMapping("/polyglot")
@RequiredArgsConstructor
public class PolyglotController {

    private final PolyglotService polyglotService;

    @PostMapping("/js")
    @CrossOrigin
    public ResponseEntity<String> getAnswer(@RequestBody CodeInfo codeInfo) {
        if (StringUtils.isNotEmpty(codeInfo.getCode())) {
            return ResponseEntity.ok(polyglotService.executeCode(codeInfo.getCode()));
        } else {
            return ResponseEntity.ok("Пожалуйста, введите код для выполнения");
        }

    }

}
