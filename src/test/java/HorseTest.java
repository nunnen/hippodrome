import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

class HorseTest {
    Horse testHorse;

    @BeforeEach
    void setUp() {
        testHorse = new Horse("TestName", 10, 100);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void HorseThrowsExceptionAndWritesMessage() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> new Horse(null, 1)
        );
        assertEquals("Name cannot be null.", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void HorseThrowsExceptionIfBlankAndWritesMessage(String input) {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> new Horse(input, 1));
        assertEquals("Name cannot be blank.", thrown.getMessage());
    }


    @Test
    void HorseThrowsExceptionIfSpeedNegativeAndWritesMessage() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> new Horse("TestName", -1));
        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }


    @Test
    void HorseThrowsExceptionIfDistanceNegativeAndWritesMessage() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> new Horse("TestName", 1, -1));
        assertEquals("Distance cannot be negative.", thrown.getMessage());
    }


    @Test
    void getName() {
        assertEquals("TestName", testHorse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(10, testHorse.getSpeed());
    }

    @Test
    void getDistanceReturnsValue() {
        assertEquals(100, testHorse.getDistance());
    }

    @Test
    void getDistanceReturnsZeroIfNoDistanceInConstructor() {
        Horse limitedHorse = new Horse("LimitedHorse", 5);
        assertEquals(0, limitedHorse.getDistance());
    }

    @Test
    void moveVerifyGetRandomDoubleUsed() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            testHorse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.7, 0.9})
    void moveReturnsCorrectValue(double args) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(args);
            Double expected = testHorse.getDistance() + testHorse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            testHorse.move();
            assertEquals(expected, testHorse.getDistance());

        }
    }
}