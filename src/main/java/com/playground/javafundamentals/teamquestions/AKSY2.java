package com.playground.javafundamentals.teamquestions;

import java.util.Optional;

public class AKSY2 {

        private Optional<String> getOptEmpty() {
            return Optional.empty();
        }

        private Optional<String> getOpt1() {
            return Optional.of("Optional1");
        }

        private Optional<String> getOpt2() {
            return Optional.of("Optional2");
        }

        public void findFirstPresentOptional() {
            Optional<String> firstPresent = getOptEmpty()
                    .or(this::getOpt1)
                    .or(this::getOpt2);

            firstPresent.ifPresent(value -> {
                System.out.println("Non Empty Value: " + value);
            });
        }

        public static void main(String[] args) {
            new AKSY2().findFirstPresentOptional();
        }
}
