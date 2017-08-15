package com.ozerian.recognizer.config;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class SpringCoreConfig {

    @Value("${config.tesseract.segmentation.mode}")
    private int segmentationMode;

    @Value("${config.tesseract.language}")
    private String recognitionLanguage;

    @Bean
    public ITesseract tesseract() {
        ITesseract tesseract = new Tesseract();
        tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_TESSERACT_ONLY);
        tesseract.setPageSegMode(segmentationMode);
        tesseract.setLanguage(recognitionLanguage);

        return tesseract;
    }
}
