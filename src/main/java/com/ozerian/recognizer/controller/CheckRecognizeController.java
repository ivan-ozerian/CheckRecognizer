package com.ozerian.recognizer.controller;

import com.ozerian.recognizer.model.CheckInfo;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CheckRecognizeController {

    String uploadImage(Model model);

    ResponseEntity<CheckInfo> recognizeCheck(MultipartFile file) throws IOException, TesseractException;
}
