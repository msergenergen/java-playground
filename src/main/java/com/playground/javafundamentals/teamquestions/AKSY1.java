package com.playground.javafundamentals.teamquestions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Person {
    private Car car;
    public Person(Car car) { this.car = car; }
    public Car getCar() { return car; }
}

class Car {
    private Insurance insurance;
    public Car(Insurance insurance) { this.insurance = insurance; }
    public Insurance getInsurance() { return insurance; }
}

class Insurance {
    private String name;
    public Insurance(String name) { this.name = name; }
    public String getName() { return name; }
}

public class AKSY1 {

    public static void main(String[] args) {
        Person personWithEverything = new Person(new Car(new Insurance("Fully Insurance")));
        Person personWithNoInsurance = new Person(new Car(null));
        Person personWithNoCar = new Person(null);
        Person personWithNullInsuranceName = new Person(new Car(new Insurance(null)));

        List<Person> persons = Arrays.asList(
                personWithEverything,
                personWithNoInsurance,
                personWithNoCar,
                personWithNullInsuranceName,
                null
        );

        List<String> insuranceNames = persons.stream()
                .map(person ->
                        Optional.ofNullable(person)
                                .flatMap(p -> Optional.ofNullable(p.getCar()))
                                .flatMap(c -> Optional.ofNullable(c.getInsurance()))
                                .flatMap(i -> Optional.ofNullable(i.getName()))
                                .orElse("Unknown")
                )
                .collect(Collectors.toList());

        System.out.println(insuranceNames);
    }
}

