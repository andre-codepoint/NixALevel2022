package com.nixalevel.csvhometask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CSVTableTest {

    private CSVParser parser;

    private CSVMapper mapper;

    @BeforeEach
    void setUp() {
        parser = new CSVParser(";");
        mapper = new CSVMapper();
    }

    @Test
    void testCreateCSVTablefromFile() throws Exception {
        Path source = Paths.get(getClass().getResource("/cars.csv").toURI());
        CSVTable table = new CSVParser(";").parse(source);
        assertEquals("Mazda6", table.getItem(0, 0));
        assertEquals("gasoline", table.getItem(0, 1));
        assertEquals("5000$", table.getItem(0, 5));
        assertEquals("Nissan Leaf", table.getItem(3, 0));
        assertEquals("electro", table.getItem(3, 1));
        assertEquals("10000$", table.getItem(3, 5));
        assertEquals("10000$", table.getItem(3, "Price"));
        assertEquals("Mazda6", table.getItem(0, "Model"));
        assertEquals(new ArrayList<>(Arrays.asList("Model", "Fuel", "Engine", "MaxSpeed", "Age", "Price")), table.getHeader());
    }

    @Test
    void testMapCSVTableTo() throws Exception {
        Path source = Paths.get(getClass().getResource("/cars.csv").toURI());
        CSVTable table = new CSVParser(";").parse(source);
        assertEquals(3.0, Float.parseFloat("3.0"));
        List<CarsData> carsData = mapper.map(table, CarsData.class);
        assertEquals(4, carsData.size());
        assertEquals("Mazda6", carsData.get(0).getModel());
        assertEquals("gasoline", carsData.get(0).getFuel());
        assertEquals(Float.parseFloat("3.0"), carsData.get(0).getEngine());
        assertEquals(180, carsData.get(0).getMaxspeed());
        assertEquals(12, carsData.get(0).getAge());
        assertEquals(5000, carsData.get(0).getPrice());
        assertEquals("Nissan Leaf", carsData.get(3).getModel());
        assertEquals("electro", carsData.get(3).getFuel());
        assertEquals(Float.parseFloat("1.8"), carsData.get(3).getEngine());
        assertEquals(150, carsData.get(3).getMaxspeed());
        assertEquals(10, carsData.get(3).getAge());
        assertEquals(10000, carsData.get(3).getPrice());
    }
}