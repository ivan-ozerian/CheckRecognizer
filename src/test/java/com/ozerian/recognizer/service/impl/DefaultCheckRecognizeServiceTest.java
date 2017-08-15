package com.ozerian.recognizer.service.impl;

import net.sourceforge.tess4j.ITesseract;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DefaultCheckRecognizeServiceTest {

    @InjectMocks
    private DefaultCheckRecognizeService recognizeService;

    @Mock
    private ITesseract tesseractMock;

    @Before
    public void setUp() throws Exception {
        String testRegex = "([0-9]{2,4}[.][0-9]{2})";
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(recognizeService, "sumRecognitionRegex", testRegex);
    }

    @Test
    public void testFindCheckMaxSum_SingleResult() throws Exception {
        String testParsedString = "111.11";
        byte[] testImage = new byte[1];

        when(tesseractMock.doOCR(any(BufferedImage.class))).thenReturn(testParsedString);

        Double testMaxSum = recognizeService.findCheckMaxSum(testImage);

        verify(tesseractMock, times(1)).doOCR(any(BufferedImage.class));

        assertEquals(new Double(testParsedString), testMaxSum);
    }

    @Test
    public void testFindCheckMaxSum_FewInputNumbers() throws Exception {
        String testParsedString = "111.11  132.45 35.24";
        byte[] testImage = new byte[1];

        when(tesseractMock.doOCR(any(BufferedImage.class))).thenReturn(testParsedString);

        Double testMaxSum = recognizeService.findCheckMaxSum(testImage);

        verify(tesseractMock, times(1)).doOCR(any(BufferedImage.class));

        // result will be the max value
        assertEquals(new Double(132.45), testMaxSum);
    }

    @Test
    public void testFindCheckMaxSum_WhenNotFoundMaxValue() throws Exception {
        String testParsedString = "onlyTextString";
        byte[] testImage = new byte[1];

        when(tesseractMock.doOCR(any(BufferedImage.class))).thenReturn(testParsedString);

        Double testMaxSum = recognizeService.findCheckMaxSum(testImage);

        verify(tesseractMock, times(1)).doOCR(any(BufferedImage.class));

        assertEquals(new Double(-1), testMaxSum);
    }

}