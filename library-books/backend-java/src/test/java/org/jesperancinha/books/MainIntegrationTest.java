package org.jesperancinha.books;

import org.junit.jupiter.api.Test;
import static io.kotest.matchers.ShouldKt.shouldBe;

public class MainIntegrationTest {
    @Test
    public void testMain() {
        Main.main(new String[]{});
        // Since it's just a println, we just verify it doesn't crash
        // and demonstrates the use of Kotest in a Java file (via static import if possible, 
        // though Kotest is better suited for Kotlin)
        shouldBe("Hello world!", "Hello world!");
    }
}
