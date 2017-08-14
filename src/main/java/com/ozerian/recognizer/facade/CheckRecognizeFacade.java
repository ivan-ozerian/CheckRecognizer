package com.ozerian.recognizer.facade;

import com.ozerian.recognizer.model.CheckInfo;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CheckRecognizeFacade {

    ResponseEntity<CheckInfo> recognizeCheck(MultipartFile file) throws IOException, TesseractException;

}
