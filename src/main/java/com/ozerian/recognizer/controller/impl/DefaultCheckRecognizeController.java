package com.ozerian.recognizer.controller.impl;

import com.ozerian.recognizer.controller.CheckRecognizeController;
import com.ozerian.recognizer.facade.CheckRecognizeFacade;
import com.ozerian.recognizer.model.CheckInfo;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
public class DefaultCheckRecognizeController implements CheckRecognizeController {

    @Autowired
    private CheckRecognizeFacade checkRecognizeFacade;

    @Override
    @RequestMapping("/")
    public String uploadImage(Model model) {
        return "upload-check";
    }

    @Override
    @RequestMapping(value = "/recognize", method = RequestMethod.POST)
    public ResponseEntity<CheckInfo> recognizeCheck(@RequestParam("image") MultipartFile image)
            throws IOException, TesseractException, ExecutionException, InterruptedException {

        return checkRecognizeFacade.recognizeCheck(image);
    }
}
