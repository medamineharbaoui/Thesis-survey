package com.harbaoui.thesis_survey.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.harbaoui.thesis_survey.model.SurveyResponse;
import com.harbaoui.thesis_survey.repository.SurveyResponseRepository;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SurveyController {

    @Autowired
    private SurveyResponseRepository repository;

    @PostMapping("/survey")
    public void submitResponses(@RequestBody List<SurveyResponse> responses) {
        repository.saveAll(responses);
    }

     @GetMapping("/survey")
    public List<SurveyResponse> getAllResponses() {
        return repository.findAll();
    }

    @GetMapping("/audio/files")
    public List<String> getAudioFiles() {
        File audioDir = new File("src/main/resources/static/audio");
        if (!audioDir.exists()) {
            return List.of();
        }
        return Arrays.stream(audioDir.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(".mp3"))
                .map(File::getName)
                .collect(Collectors.toList());
    }
}
