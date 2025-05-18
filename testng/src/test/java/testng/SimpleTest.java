package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SimpleTest {

    // Untuk set up database, test data, dll
    // Untuk menyiapkan data yang diperlukan sebelum pengujian dimulai
    @BeforeSuite
    public void setUp() {
        System.out.println("Setting up the Suite...");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("Tearing down the Suite...");
    }

    // Sama kayak suite biasanya, tapi bisa pilih salah satu aja
    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test method...");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test method...");
    }

    // Untuk set up data yang berhubungan dengan class
    @BeforeClass    
    public void beforeClass() {
        System.out.println("Before Class method...");
    }

    // Untuk reset data setelah running class --> contoh reset inputan
    @AfterClass
    public void afterClass() {         
        System.out.println("After Class method...");
    }

    // Pre-conditions login biasanya disini sebelum test, bisa dipake ke semuanya
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method...");
    }   

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method...");
    }   

    
    @Test
    public void testMethod() {
        System.out.println("Running Simple method 1");
    }

}
