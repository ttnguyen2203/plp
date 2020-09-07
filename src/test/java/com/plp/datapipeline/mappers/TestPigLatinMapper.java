package com.plp.datapipeline.mappers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class TestPigLatinMapper {

    private PigLatinMapper mapper;

    @BeforeEach
     void setupEnv() {
        this.mapper = new PigLatinMapper();
    }

    @AfterEach
    public void cleanupEnv() {
        //nothing to shutdown
    }


    @Test
    void testSimpleWords() {
        final Map<String, Object > inputs = new HashMap<>();

        inputs.put("a", "Happy"); // rule: start with consonant + vowel
        inputs.put("b", "Child"); // rule: start with 2 consonants
        inputs.put("c", "Awesome"); // rule: start with a vowel

        // lowercase of the same rules
        inputs.put("d", "happy");
        inputs.put("e", "child");
        inputs.put("f", "awesome");

        final Map<String, Object> result = this.mapper.map(inputs);

        Assert.isTrue(result.size() == 6, "Same number of entries as input");
        Assert.isTrue(Objects.equals(result.get("a"), "Appyhay"));
        Assert.isTrue(Objects.equals(result.get("b"), "Ildchay"));
        Assert.isTrue(Objects.equals(result.get("c"), "Awesomeway"));

        Assert.isTrue(Objects.equals(result.get("d"), "appyhay"));
        Assert.isTrue(Objects.equals(result.get("e"), "ildchay"));
        Assert.isTrue(Objects.equals(result.get("f"), "awesomeway"));
    }

    /**
     * If a word has invalid characters in it, ignore
     */
    @Test
    void testInvalidWords() {
        final Map<String, Object > inputs = new HashMap<>();
        inputs.put("a", "1Happy");
        inputs.put("b", "C4hild");
        inputs.put("c", "Awesod,sfame");

        final Map<String, Object> result = this.mapper.map(inputs);

        Assert.isTrue(result.size() == 3, "Same number of entries as input");
        Assert.isTrue(Objects.equals(result.get("a"), "1Happy"));
        Assert.isTrue(Objects.equals(result.get("b"), "C4hild"));
        Assert.isTrue(Objects.equals(result.get("c"), "Awesod,sfame"));
    }

    @Test
    void testSentences() {
        final Map<String, Object > inputs = new HashMap<>();
        inputs.put("a", "As a member of the Greek community");
        inputs.put("b", "As1 a m3mb3r of zee Gr33k comminit!");

        final Map<String, Object> result = this.mapper.map(inputs);

        Assert.isTrue(result.size() == 2, "Same number of entries as input");
        Assert.isTrue(Objects.equals(result.get("a"), "Asway away embermay ofway ethay Eekgray ommunitycay"));
        Assert.isTrue(Objects.equals(result.get("b"), "As1 away m3mb3r ofway eezay Gr33k comminit!"));
    }
}