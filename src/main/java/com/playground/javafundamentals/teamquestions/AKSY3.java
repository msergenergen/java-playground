package com.playground.javafundamentals.teamquestions;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AKSY3 {

    private static Map<String, String> emailMap;

    public static class Person {
        private String name;
        private String city;
        private int age;
        private Optional<String> email;

        public Person(String name, String city, int age, Optional<String> email) {
            this.name = name;
            this.city = city;
            this.age = age;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Optional<String> getEmail() {
            return email;
        }

        public String getEmailNotOptional() {
            return email.orElse("");
        }

        public boolean isMailNotEmpty() {
            return email.isPresent();
        }

        public void setEmail(Optional<String> email) {
            this.email = email;
        }
    }

    public static String getAgeGroup(Person person) {
        String ageGroup = (person.getAge() > 0 && person.getAge() < 26) ? "YOUNG" :
                (person.getAge() > 25 && person.getAge() < 50) ? "MIDDLE AGE" :
                        (person.getAge() > 50) ? "OLD" :
                                "NO_AGE_GROUP";
        return ageGroup;
    }


    public static void main(String[] args) {
        List<Person> team = List.of(
                new Person("Ali", "Bursa", 22, Optional.of("ali.com")),
                new Person("Ahmet", "İstanbul", 55, Optional.of("ahmet.com")),
                new Person("Ayşe", "Ankara", 21, Optional.empty()),
                new Person("Burak", "İstanbul", 40, Optional.of("burak.com")),
                new Person("Can", "İzmir", 32, Optional.empty()),
                new Person("Ece", "İstanbul", 29, Optional.of("ece.com")),
                new Person("Nehir", "Ankara", 22, Optional.of("nehir.com"))
        );

        // Case 1: Count cities per person
        // City -> Count
        Map<String, Long> cityCount = team.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));

        // Case 2: Average age per person
        // Name -> Average Age
        Map<String, Double> averageAge = team.stream()
                .collect(Collectors.groupingBy(Person::getCity, Collectors.averagingDouble(Person::getAge)));

        // Case 3: Sort according to rule
        // City -> Age Group -> Names
        Map<String, Map<String, List<String>>> groupedPeople = team.stream()
                .collect(Collectors.groupingBy(
                        AKSY3.Person::getCity,
                        Collectors.groupingBy(
                                AKSY3::getAgeGroup,
                                Collectors.mapping(Person::getName, Collectors.toList())
                        )
                ));

        // Case4: Get E-Mails, if is present and older than
        // Names -> E-Mail
        Map<String, String> email = team.stream()
                .collect(Collectors.collectingAndThen(Collectors.toMap(
                        Person::getName,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Person::getAge))),
                        merge())
                );

        System.out.println(cityCount);
        System.out.println(averageAge);
        System.out.println(groupedPeople);
        System.out.println(email);

    }

    private static Function<Map<String, Person>, Map<String, String>> merge() {
        return stringPersonMap -> stringPersonMap
                .values()
                .stream()
                .filter(Person::isMailNotEmpty)
                .collect(Collectors.toMap(Person::getName, Person::getEmailNotOptional));
    }

}
