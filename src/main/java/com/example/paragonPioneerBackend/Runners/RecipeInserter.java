package com.example.paragonPioneerBackend.Runners;

import com.example.paragonPioneerBackend.Entity.Good;
import com.example.paragonPioneerBackend.Entity.Recipe;
import com.example.paragonPioneerBackend.Repository.GoodRepository;
import com.example.paragonPioneerBackend.Repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeInserter {
    private final RecipeRepository repository;
    private final GoodRepository goodRepository;

    private record Inserter(String i1, int q1, String i2, int q2, String i3, int q3, String i4, int q4, String i5,
                            int q5, String i6, int q6, String i7, int q7, String i8, int q8, String i9, int q9,
                            String i10, int q10, String output) {
    }

    Inserter[] inserts = {
            new Inserter("Land tile", 1, "Water tile", 4, null, 0, null, 0, null, 0, null, 0, null, 0, null, 0, null, 0, null, 0, "Fish")
    };

    public void run() {
        for (Inserter insert : inserts) {
            repository.save(Recipe.builder()
                    .output(goodRepository.findByNameIs(insert.output))
                    .input1(goodRepository.findByNameIs(insert.i1))
                    .input2(goodRepository.findByNameIs(insert.i2))
                    .input3(goodRepository.findByNameIs(insert.i3))
                    .input4(goodRepository.findByNameIs(insert.i4))
                    .input5(goodRepository.findByNameIs(insert.i5))
                    .input6(goodRepository.findByNameIs(insert.i6))
                    .input7(goodRepository.findByNameIs(insert.i7))
                    .input8(goodRepository.findByNameIs(insert.i8))
                    .input9(goodRepository.findByNameIs(insert.i9))
                    .input10(goodRepository.findByNameIs(insert.i10))
                    .quantityOfInput1(insert.q1)
                    .quantityOfInput2(insert.q2)
                    .quantityOfInput3(insert.q3)
                    .quantityOfInput4(insert.q4)
                    .quantityOfInput5(insert.q5)
                    .quantityOfInput6(insert.q6)
                    .quantityOfInput7(insert.q7)
                    .quantityOfInput8(insert.q8)
                    .quantityOfInput9(insert.q9)
                    .quantityOfInput10(insert.q10)
                    .build());
        }
    }
}
