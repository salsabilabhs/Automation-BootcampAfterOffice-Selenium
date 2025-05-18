package testng;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.Product;

public class AssertTest {

    @Test
    public void testMethod() {

        // Test for Product
        Product actualProduct = new Product("Labubu", 200000, 5);
        Product expectedProduct = new Product("Labubu", 200000, 5);
        Assert.assertEquals(actualProduct, expectedProduct);

        // Test Array List
        List<String> actualList = new ArrayList<String>();
        actualList.add("Banana");
        actualList.add("Labubu");

        Predicate<String> expectedList = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("Labubu");
            }
        };

        Assert.assertTrue(actualList.contains("Labubu"), "List contains 'Labubu'");
    }
}
