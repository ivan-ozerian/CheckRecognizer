package com.ozerian.recognizer.service.impl;

import com.ozerian.recognizer.service.CheckRecognizeService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("checkRecognizeService")
public class DefaultCheckRecognizeService implements CheckRecognizeService {

    @Autowired
    private ITesseract tesseract;

    @Override
    public Double findCheckMaxSum(byte[] checkImageFile) throws IOException, TesseractException {
        try (InputStream imageInputStream = new ByteArrayInputStream(checkImageFile)) {
            BufferedImage image = ImageIO.read(imageInputStream);
            String parsedCheck = tesseract.doOCR(image);
            System.out.println(parsedCheck);
            Pattern pattern = Pattern.compile("([0-9]{1,13}[\\s]?[\\.,][\\s]?[0-9]{2})");
            Matcher matcher = pattern.matcher(parsedCheck);

            List<Double> sums = new ArrayList<>();

            while (matcher.find()) {
                String matchSum = matcher.group(1);

                if (matchSum != null) {
                    Double sum = Double.valueOf(matchSum.replace(" ", "").trim());
                    sums.add(sum);
                }
            }
            System.out.println(sums.toString());

            return Optional.ofNullable(Collections.max(sums)).orElse(-1d);
        }

    }
}
