package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataStreamer {

    private List<Integer> numbers = new ArrayList<>();

    public Stream<Integer> getNumberStream() {
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        return numbers.stream();
    }

    public void doSomethingWithNumbers(Stream<Integer> numberStream) {
        numberStream.forEach(number -> {
            System.out.println("Received number: " + number);
        });
    }

    public static void main(String[] args) {
        DataStreamer streamer = new DataStreamer();
        Stream<Integer> numberStream = streamer.getNumberStream();
        streamer.doSomethingWithNumbers(numberStream);

        // The data processor will not need to wait for the entire stream to be returned
        // before it starts processing data.
        new Thread(() -> {
            numberStream.forEach(number -> {
                System.out.println("Received number from another thread: " + number);
            });
        }).start();
    }
}