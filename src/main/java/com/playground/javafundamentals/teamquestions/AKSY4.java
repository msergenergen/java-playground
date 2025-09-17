package com.playground.javafundamentals.teamquestions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AKSY4 {

    public static class Person {
        private String name;
        private String city;
        private List<String> roles;

        public Person(String name, String city, List<String> roles) {
            this.name = name;
            this.city = city;
            this.roles = roles;
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

        public List<String> getRoles() {
            return roles;
        }

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }
    }

    public static void main(String[] args) {
        List<Person> team = List.of(
                new Person("Ali", "Bursa", List.of("ENGINEER")),
                new Person("Ahmet", "İstanbul", List.of("ADMIN", "USER")),
                new Person("Ayşe", "Ankara", List.of("ENGINEER")),
                new Person("Burak", "İstanbul", List.of("ADMIN", "USER")),
                new Person("Can", "İzmir", List.of("TEACHER")),
                new Person("Ece", "İstanbul", List.of("ADMIN")),
                new Person("Nehir", "Ankara", List.of("USER"))
        );

        Map<String, String> roleMap = team.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                AKSY4::mergeRolesToString
                        )
                ));

        System.out.println(roleMap);

    }

    private static String mergeRolesToString(List<Person> people) {
        return people.stream()
                .flatMap(person -> person.getRoles().stream())
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.joining(", "));
    }
}
