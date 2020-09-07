package com.plp.http;

import com.plp.dto.ConsumerResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Objects;

@SpringBootTest
class TestPlpServlet {

    @Autowired
    private PlpServlet plpServlet;

    @Test
    void applyPigLatin() {

        final String input = "As a member of the Greek community";
        final String expectedOutput = "Asway away embermay ofway ethay Eekgray ommunitycay";

        final String output  = plpServlet.applyPigLatin(input);

        Assert.isTrue(Objects.equals(expectedOutput, output));
    }
}