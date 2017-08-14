package com.ozerian.recognizer.service;

import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public interface CheckRecognizeService {

    Double findCheckMaxSum(byte[] checkImageFile) throws IOException, TesseractException;
}
