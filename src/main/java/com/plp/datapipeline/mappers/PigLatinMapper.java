package com.plp.datapipeline.mappers;

import com.plp.datapipeline.mappers.config.PigLatinMapperConfig;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PigLatinMapper extends IMapper<PigLatinMapperConfig> {

    private static final Set<Character> FIRST_CHAR_VOWELS = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U', 'y', 'Y'));
    private static final String VOWEL_POST_FIX = "way";
    private static final String CONSONANT_POST_FIX = "ay";

    //todo: punctuation

    @Override
    public void init(final PigLatinMapperConfig config) {
        //no settings to init
    }

    @Override
    public Map<String, Object> map(final Map<String, Object> data) {
        return data.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        e -> transformSentence((String) e.getValue())

                ));
    }

    protected String transformSentence(@NonNull final String sentence) {
        final List<String> transformed = Arrays.stream(sentence.split(" "))
                .map(this::transformWord)
                .collect(Collectors.toList());
        return String.join(" ", transformed);

    }

    @SuppressWarnings("WeakerAccess")
    protected String transformWord(@NonNull final String word) {
        if (word.length() < 1) {
            return word;
        }

        //if not a word, don't convert
        final char[] charArr = word.toCharArray();
        for (char c : charArr) {
            if (!isAlphabetic(c)) {
                return word;
            }
        }

        //handle case
        final boolean upperCase = Character.isUpperCase(charArr[0]);
        charArr[0] = Character.toLowerCase(charArr[0]);

        if (FIRST_CHAR_VOWELS.contains(charArr[0])) {
            // if word starts with a vowel, append "-yay" to the end of the word and return
            return word + VOWEL_POST_FIX;
        } else {
            // if word starts with a consonant, add all consonants until the first vowel to the end and append postfix
            final List<Character> moveToBack = new ArrayList<>();
            final AtomicInteger vowelIndex = new AtomicInteger(-1);
            for (int i = 0; i < charArr.length; i++) {
                if (FIRST_CHAR_VOWELS.contains(charArr[i])) {
                    vowelIndex.set(i);
                    break;
                } else {
                    moveToBack.add(charArr[i]);
                }
            }

            //if no vowel found, return as is
            if (vowelIndex.get() < 0) {
                return word;
            }
            final String ending = charListToString(moveToBack) + CONSONANT_POST_FIX;

            final String constructedWord = word.substring(vowelIndex.get()) + ending;

            return upperCase ?
                    constructedWord.substring(0, 1).toUpperCase() + constructedWord.substring(1) : constructedWord;
        }
    }


    protected boolean isAlphabetic(final char c) {
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }

    protected String charListToString(final List<Character> charList) {
        final StringBuilder builder = new StringBuilder(charList.size());
        charList.forEach(builder::append);
        return builder.toString();
    }
}
