package ru.kravchenko.sb;

import org.junit.Test;
import ru.kravchenko.sb.utils.Utils;

import java.io.IOException;

public class UtilsTest {

    @Test
    public void readFileTest() throws IOException {
        String result = Utils.readFile(getClass().getResourceAsStream("/ex3.json"));
        System.out.println(result);
    }

}
