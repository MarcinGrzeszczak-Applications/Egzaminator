package com.marti.dev.egzaminator.core;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class ParseJsonTest {
    @Test
    public void parse() throws Exception {

        String json = "{\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"possibleAnswers\": 3,\n" +
                "      \"questionText\": \"Co oznacza ten znak ?\",\n" +
                "      \"correctAnswer\": 0,\n" +
                "      \"imagePath\": null,\n" +
                "      \"answers\": [\n" +
                "        {\n" +
                "          \"id\": 0,\n" +
                "          \"answerText\": \"Zatrzymaj swój pojazd\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        TestModel testModel = ParseJson.parse(json);

        assertSame(3, testModel.questions.get(0).possibleAnswers);
        assertEquals("Co oznacza ten znak ?", testModel.questions.get(0).questionText);
        assertSame(0, testModel.questions.get(0).correctAnswer);
        assertNull(testModel.questions.get(0).imagePath);
        assertEquals("Zatrzymaj swój pojazd", testModel.questions.get(0).answers.get(0).answerText);

    }



}