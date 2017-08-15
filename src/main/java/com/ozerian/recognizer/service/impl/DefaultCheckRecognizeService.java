package com.ozerian.recognizer.service.impl;

import com.ozerian.recognizer.service.CheckRecognizeService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("checkRecognizeService")
public class DefaultCheckRecognizeService implements CheckRecognizeService {

    @Value("${config.tesseract.recognition.sum.regexp}")
    private String sumRecognitionRegex;

    @Autowired
    private ITesseract tesseract;

    @Override
    public Double findCheckMaxSum(byte[] checkImageFile) throws IOException, TesseractException {
        try (InputStream imageInputStream = new ByteArrayInputStream(checkImageFile)) {
            BufferedImage image = ImageIO.read(imageInputStream);
            String parsedCheckImage = tesseract.doOCR(image);

            List<Double> sums = findSumValuesOnCheck(parsedCheckImage);

            return getMaxCheckSumResult(sums);
        }

    }

    private List<Double> findSumValuesOnCheck(String parsedCheck) {
        List<Double> sumValues = new ArrayList<>();

        Pattern pattern = Pattern.compile(sumRecognitionRegex);
        Matcher matcher = pattern.matcher(parsedCheck);

        while (matcher.find()) {
            String matchSum = matcher.group(1);

            if (matchSum != null) {
                Double sum = Double.valueOf(matchSum.replace(" ", "").trim());
                sumValues.add(sum);
            }
        }

        return sumValues;
    }

    private double getMaxCheckSumResult(List<Double> sums) {
        double maxSum;

        if (sums.isEmpty() || sums == null) {
            maxSum = -1;
        } else {
            maxSum = Collections.max(sums);
        }

        return maxSum;
    }
}
