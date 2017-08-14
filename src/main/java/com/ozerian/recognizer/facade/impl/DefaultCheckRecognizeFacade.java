package com.ozerian.recognizer.facade.impl;

import com.ozerian.recognizer.facade.CheckRecognizeFacade;
import com.ozerian.recognizer.model.CheckInfo;
import com.ozerian.recognizer.service.CheckRecognizeService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Component("checkRecognizeFacade")
public class DefaultCheckRecognizeFacade implements CheckRecognizeFacade {

    @Autowired
    private CheckRecognizeService checkRecognizeService;

    private AtomicInteger idCounter = new AtomicInteger();

    @Override
    public ResponseEntity<CheckInfo> recognizeCheck(MultipartFile file) throws IOException, TesseractException {

        Integer checkId = idCounter.incrementAndGet();

        Double maxSumValue = checkRecognizeService.findCheckMaxSum(file.getBytes());

        CheckInfo checkInfo = new CheckInfo(checkId, file.getOriginalFilename(), maxSumValue);

        return new ResponseEntity<>(checkInfo, HttpStatus.OK);
    }
}
