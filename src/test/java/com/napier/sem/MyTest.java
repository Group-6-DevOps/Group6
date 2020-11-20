package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class MyTest {
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
    }
    //Query 9
    @Test
    void checkcitylist_region_Null(){
        System.out.println("Null Testing\n");
        app.print_popucitylist_regionLS(null);
        System.out.println("\nNo city in the region");
    }
    @Test
    void checkcityarray_region_Null(){
        System.out.println("Array List Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_popucitylist_regionLS(Cities);
        System.out.println("\nNo array list in the region");
    }
    @Test
    void displayRegionCitylist(){
        System.out.println("Manual Insert Data Testing\n");
        ArrayList<City> citylist = new ArrayList<City>();
        City cty = new City();
        cty.setName("Chou Chou");
        cty.setCountry("Thailand");
        cty.setPopulation(910000000);
        cty.setContinent("Asia");
        cty.setDistrict("THA");
        citylist.add(cty);
        app.print_popucitylist_regionLS(citylist);
        System.out.println("\nList of cities in a region");
    }
    //End

    //Function 10
    @Test
    void checkcitylist_country_Null(){
        System.out.println("Null Testing\n");
        app.print_popucitylist_CountryLS(null);
        System.out.println("\nNo city in a country");
    }
    @Test
    void checkcityarray_country_Null(){
        System.out.println("ArrayList Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_popucitylist_CountryLS(Cities);
        System.out.println("\nNo array list in the country");
    }

    //Function 11
    @Test
    void checkcitylist_district_Null(){
        System.out.println("Null Testing\n");
        app.print_popucitylist_districtLS(null);
        System.out.println("\nNo city list in a district");
    }
    @Test
    void checkcityarray_district_Null(){
        System.out.println("ArrayList Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_popucitylist_districtLS(Cities);
        System.out.println("\nNo array list in the district");
    }

    //Function 12
    @Test
    void checkcitylist_world_Null(){
        System.out.println("Null Testing\n");
        app.print_pcity_world(null);
        System.out.println("\nNo city in the world");
    }
    @Test
    void checkcityarray_world_Null(){
        System.out.println("ArrayList Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_pcity_world(Cities);
        System.out.println("\nNo array list in the world");
    }

    //Function 13
    @Test
    void checkpopcitylist_continent_Null(){
        System.out.println("Null Testing\n");
        app.print_pcity_continentLS(null);
        System.out.println("\nNo city in the continent");
    }
    @Test
    void checkpopcityarray_continent_Null(){
        System.out.println("ArrayList Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_pcity_continentLS(Cities);
        System.out.println("\nNo array list in the continent");
    }
    //Function 14
    @Test
    void checkpopcitylist_region_Null(){
        System.out.println("Null Testing\n");
        app.print_pcity_regionLS(null);
        System.out.println("\nNo city in the region");
    }
    @Test
    void checkpopcityarray_region_Null(){
        System.out.println("ArrayList Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_pcity_regionLS(Cities);
        System.out.println("\nNo array list in the region");
    }
    //Function 15
    @Test
    void checkpopcitylist_country_Null(){
        System.out.println("Null Testing\n");
        app.print_populationcity_countryLS(null);
        System.out.println("\nNo city in the country");
    }
    @Test
    void checkpopcityarray_country_Null(){
        System.out.println("ArrayList Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_populationcity_countryLS(Cities);
        System.out.println("\nNo array list in the country");
    }
    //Function 16
    @Test
    void checkpopcitylist_district_Null(){
        System.out.println("Null Testing\n");
        app.print_populationcity_DistrictLS(null);
        System.out.println("\nNo city in the district");
    }
    @Test
    void checkpopcityarray_district_Null(){
        System.out.println("ArrayList Null Testing\n");
        ArrayList<City> Cities = new ArrayList<City>();
        Cities.add(null);
        app.print_populationcity_DistrictLS(Cities);
        System.out.println("\nNo array list in the district");
    }
}
