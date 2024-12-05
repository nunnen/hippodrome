import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    Hippodrome testHippodrome;

    @BeforeEach
    void setUp() {
    }

    @Test
    void ifNull_hippodromeThrowsExceptionAndWritesMessage() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    void ifEmpty_hippodromeThrowsExceptionAndWritesMessage() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        assertEquals("Horses cannot be empty.", thrown.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> expected = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            expected.add(new Horse("TestHorse " + i, i));
        }
        testHippodrome = new Hippodrome(expected);
        assertIterableEquals(expected, testHippodrome.getHorses());
    }

//    @Test
//    void move() {
//        List<Horse> horseList = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            horseList.add(Mockito.mock(Horse.class));
//        }
//        testHippodrome = new Hippodrome(horseList);
//        testHippodrome.move();
//        testHippodrome.getHorses().forEach(x -> Mockito.verify(x).move());
//    }
//

    @Test
    void getWinner() {
    }
}