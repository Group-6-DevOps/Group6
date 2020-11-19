package com.napier.sem;
//import sql Package
import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        //Display The list of Country by population from largest to smallest
        System.out.println("\n");
        System.out.println("The list of country by population from largest to smallest");
        ArrayList<Country> country = a.CountryLS();
        a.printCountryLS(country);

        //Display the countries in Asia by population from largest to smallest
        System.out.println("\n");
        System.out.println("Country list in Asia by population from largest to smallest");
        ArrayList<Country> continent = a.continent_countryLS();
        a.print_countrylist_continentLS(continent);

        //Display the countries in Southeast Asia by population from largest to smallest
        System.out.println("\n");
        System.out.println("country list in Southeast Asia by population from largest to smallest");
        ArrayList<Country> clist_region = a.Region_CountryLS();
        a.print_countrylist_regionLS(clist_region);

        //Display Top 10 populated countries in the world
        System.out.println("\n");
        System.out.println("Top 10 Populated countries in the world");
        ArrayList<Country> popcnty_world = a.mostpopcountry_worldLS();
        a.print_countrylist_regionLS(popcnty_world);

        //Display Top 10 populated countries in Asia
        System.out.println("\n");
        System.out.println("Top 10 Populated countries in Asia");
        ArrayList<Country> popcnty_continent = a.mostpopcnty_continent();
        a.print_countrylist_continentLS(popcnty_continent);

        //Display Top 10 populated countries in Southeast Asia
        System.out.println("\n");
        System.out.println("Top 10 Populated countries in Southeast Asia");
        ArrayList<Country> popcity = a.popcity_world();
        a.print_popcity_world(popcity);

        //Display cities in the world organized by population from largest to smallest
        System.out.println("\n");
        System.out.println("City list in the world organized by population from largest to smallest");
        ArrayList<City> popcnty_region = a.mostpopcnty_region();
        a.prin_popcnty_region(popcnty_region);

        //Display cities in a continent organized by population from largest to smallest
        System.out.println("\n");
        System.out.println("City list in a continent organized by population from largest to smallest");
        ArrayList<City> popcity_continent = a.popcity_continent();
        a.print_popcity_continent(popcity_continent);

        //Display Top 10 populated cities in the world
        System.out.println("\n");
        System.out.println("Top 10 Populated cities in the world");
        ArrayList<City> pocity_world = a.mostpopcity_worldLS();
        a.print_pcity_world(pocity_world);

        //Display Top 10 populated cities in the continent
        System.out.println("\n");
        System.out.println("Top 10 Populated cities in Asia continent");
        ArrayList<City> pocity_continent = a.mostpopcity_continentLS();
        a.print_pcity_continentLS(pocity_continent);

        //Display Top 10 populated cities in a region
        System.out.println("\n");
        System.out.println("Top 10 Populated cities in Eastern Asia region");
        ArrayList<City> pocity_region = a.mostpopcity_regionLS();
        a.print_pcity_regionLS(pocity_region);

        //Display all cities in a region organized by population from largest to smallest
        System.out.println("\n");
        System.out.println("City list in Southeast Asia organized by population from largest to smallest");
        ArrayList<City> popcity_region = a.pcity_regionLS();
        a.print_popucitylist_CountryLS(popcity_region);

        //Display all cities in a country organized by population from largest to smallest
        System.out.println("\n");
        System.out.println("City list in Japan organized by population from largest to smallest");
        ArrayList<City> popcity_country = a.pcity_CountryLS();
        a.print_popucitylist_regionLS(popcity_country);

        //Display all cities in a district organized by population from largest to smallest
        System.out.println("\n");
        System.out.println("City list in Osaka organized by population from largest to smallest");
        ArrayList<City> popcity_district = a.pcity_districtLS();
        a.print_popucitylist_districtLS(popcity_district);

        // Disconnect from database
        a.disconnect();
    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(3000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
    //Query 1
    public ArrayList<Country> CountryLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String ctlist = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(ctlist);
            // Extract employee information
            ArrayList<Country> Country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                cntry.setCode(rset.getString("Code"));
                cntry.setContinent(rset.getString("Continent"));
                cntry.setName(rset.getString("Name"));
                cntry.setRegion(rset.getString("Region"));
                cntry.setPopulation(rset.getInt("Population"));
                cntry.setCapital(rset.getInt("Capital"));
                Country.add(cntry);
            }
            return Country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void printCountryLS(ArrayList<Country> Country)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s %-60s %-65s", "Population", "Code", "Country Name", "continent", "region", "capital" ));
        // Loop over all employees in the list
        for (Country emp : Country) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s %-60s %-65s",
                            emp.getPopulation(), emp.getCode(), emp.getName(), emp.getContinent(), emp.getRegion(), emp.getCapital());
            System.out.println(emp_string);
        }
    }
    //End


    //Query 2
    public ArrayList<Country> continent_countryLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String conlist = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = 'Asia' ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(conlist);
            // Extract employee information
            ArrayList<Country> cty = new ArrayList<Country>();
            while (rset.next())
            {
                Country cont = new Country();
                cont.setCode(rset.getString("Code"));
                cont.setContinent(rset.getString("Continent"));
                cont.setName(rset.getString("Name"));
                cont.setRegion(rset.getString("Region"));
                cont.setPopulation(rset.getInt("Population"));
                cont.setCapital(rset.getInt("Capital"));
                cty.add(cont);
            }
            return cty;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_countrylist_continentLS(ArrayList<Country> Country)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s %-60s %-65s", "Population", "Code", "Country Name", "continent", "region", "capital" ));
        // Loop over all employees in the list
        for (Country emp : Country) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s %-60s %-65s",
                            emp.getPopulation(), emp.getCode(), emp.getName(), emp.getContinent(), emp.getRegion(), emp.getCapital());
            System.out.println(emp_string);
        }
    }
    //End


    //Query 3
    public ArrayList<Country> Region_CountryLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = 'Southeast Asia' ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<Country> Country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                cntry.setCode(rset.getString("Code"));
                cntry.setContinent(rset.getString("Continent"));
                cntry.setName(rset.getString("Name"));
                cntry.setRegion(rset.getString("Region"));
                cntry.setPopulation(rset.getInt("Population"));
                cntry.setCapital(rset.getInt("Capital"));
                Country.add(cntry);
            }
            return Country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_countrylist_regionLS(ArrayList<Country> Country)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s %-60s %-65s", "Population", "Code", "Country Name", "continent", "region", "capital" ));
        // Loop over all employees in the list
        for (Country emp : Country) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s %-60s %-65s",
                            emp.getPopulation(), emp.getCode(), emp.getName(), emp.getContinent(), emp.getRegion(), emp.getCapital());
            System.out.println(emp_string);
        }
    }
    //End


    //Query 4
    public ArrayList<Country> mostpopcountry_worldLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER by Population DESC limit 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<Country> Country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                cntry.setCode(rset.getString("Code"));
                cntry.setContinent(rset.getString("Continent"));
                cntry.setName(rset.getString("Name"));
                cntry.setRegion(rset.getString("Region"));
                cntry.setPopulation(rset.getInt("Population"));
                cntry.setCapital(rset.getInt("Capital"));
                Country.add(cntry);
            }
            return Country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_popcnty_worldLS(ArrayList<Country> Country1)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s %-60s %-65s", "Population", "Code", "Country Name", "continent", "region", "capital" ));
        // Loop over all employees in the list
        for (Country emp : Country1) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s %-60s %-65s",
                            emp.getPopulation(), emp.getCode(), emp.getName(), emp.getContinent(), emp.getRegion(), emp.getCapital());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 5
    public ArrayList<Country> mostpopcnty_continent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = 'Asia' ORDER by Population DESC limit 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<Country> Country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                cntry.setCode(rset.getString("Code"));
                cntry.setContinent(rset.getString("Continent"));
                cntry.setName(rset.getString("Name"));
                cntry.setRegion(rset.getString("Region"));
                cntry.setPopulation(rset.getInt("Population"));
                cntry.setCapital(rset.getInt("Capital"));
                Country.add(cntry);
            }
            return Country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void prin_popcnty_continent(ArrayList<Country> Country2)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s %-60s %-65s", "Population", "Code", "Country Name", "continent", "region", "capital" ));
        // Loop over all employees in the list
        for (Country emp : Country2) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s %-60s %-65s",
                            emp.getPopulation(), emp.getCode(), emp.getName(), emp.getContinent(), emp.getRegion(), emp.getCapital());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 6
    public ArrayList<Country> popcity_world()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = 'Southeast Asia' ORDER by Population DESC limit 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<Country> Country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                cntry.setCode(rset.getString("Code"));
                cntry.setContinent(rset.getString("Continent"));
                cntry.setName(rset.getString("Name"));
                cntry.setRegion(rset.getString("Region"));
                cntry.setPopulation(rset.getInt("Population"));
                cntry.setCapital(rset.getInt("Capital"));
                Country.add(cntry);
            }
            return Country;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_popcity_world(ArrayList<Country> Country3)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s %-60s %-65s", "Population", "Code", "Country Name", "continent", "region", "capital" ));
        // Loop over all employees in the list
        for (Country emp : Country3) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s %-60s %-65s",
                            emp.getPopulation(), emp.getCode(), emp.getName(), emp.getContinent(), emp.getRegion(), emp.getCapital());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 7
    public ArrayList<City> mostpopcnty_region()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT Name, CountryCode, District, Population FROM city ORDER by Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setDistrict(rset.getString("District"));
                city.setName(rset.getString("Name"));
                city.setPopulation(rset.getInt("Population"));
                city.setCountry(rset.getString("CountryCode"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void prin_popcnty_region(ArrayList<City> City1)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s", "Population", "Country Name", "Country Code", "District" ));
        // Loop over all employees in the list
        for (City emp : City1) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s",
                            emp.getPopulation(), emp.getName(), emp.getDistrict(), emp.getCountry());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 8
    public ArrayList<City> popcity_continent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            //String clist = "SELECT Code FROM country where Continent='Asia'";
            //Execute SQL statement
            //ResultSet code = stmt.executeQuery(clist);
            //System.out.println(code);
            //Create String for SQL statement1
            String regionlist = "SELECT city.Name, city.CountryCode, city.District, city.Population,country.Code, country.Continent FROM city,country Where city.CountryCode = country.code and country.Continent = 'Asia' ORDER by Population DESC";
            // Execute SQL statement1
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setDistrict(rset.getString("District"));
                city.setName(rset.getString("Name"));
                city.setPopulation(rset.getInt("Population"));
                city.setCountry(rset.getString("CountryCode"));
                city.setContinent(rset.getString("country.Continent"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_popcity_continent(ArrayList<City> City1)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s %-60s", "Population", "Country Name", "Country Code", "District", "Continent" ));
        // Loop over all employees in the list
        for (City emp : City1) {
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s %-60s",
                            emp.getPopulation(), emp.getName(), emp.getDistrict(), emp.getCountry(), emp.getContinent());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 9
    public ArrayList<City> pcity_regionLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT city.Name, city.CountryCode, city.District, city.Population,country.Code, country.Continent FROM city,country Where city.CountryCode = country.code and country.Region = 'Southeast Asia' ORDER by Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                //city.setCode(rset.getString("Code"));
                city.setContinent(rset.getString("Continent"));
                city.setName(rset.getString("Name"));
                //city.setRegion(rset.getString("Region"));
                city.setPopulation(rset.getInt("Population"));
                city.setDistrict(rset.getString("District"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_popucitylist_regionLS(ArrayList<City> City1)
    {
        // Check employees is not null
        if (City1 == null)
        {
            System.out.println("No City Information!");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s", "Population", "Country Name", "continent","district" ));
        // Loop over all employees in the list
        for (City emp : City1) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s",
                            emp.getPopulation(), emp.getName(), emp.getContinent(),emp.getDistrict());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 10
    public ArrayList<City> pcity_CountryLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT city.Name, city.CountryCode, city.District, city.Population,country.Code, country.Continent FROM city,country Where city.CountryCode = country.code and country.Name = 'Japan' ORDER by Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
//                city.setCode(rset.getString("Code"));
                city.setContinent(rset.getString("Continent"));
                city.setName(rset.getString("Name"));
//                city.setRegion(rset.getString("Region"));
                city.setPopulation(rset.getInt("Population"));
                city.setDistrict(rset.getString("District"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_popucitylist_CountryLS(ArrayList<City> City1)
    {
        //Check null
        if(City1 == null){
            System.out.println("No City List information in China country");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s", "Population", "Country Name", "continent","district" ));
        // Loop over all employees in the list
        for (City emp : City1) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s",
                            emp.getPopulation(), emp.getName(), emp.getContinent(),emp.getDistrict());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 11
    public ArrayList<City> pcity_districtLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT city.Name, city.CountryCode, city.District, city.Population FROM city WHERE city.District = 'Osaka' ORDER by Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
//                city.setCode(rset.getString("Code"));
                city.setCountry(rset.getString("CountryCode"));
                city.setName(rset.getString("Name"));
//                city.setRegion(rset.getString("Region"));
                city.setPopulation(rset.getInt("Population"));
                city.setDistrict(rset.getString("District"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_popucitylist_districtLS(ArrayList<City> City1)
    {
        //Check null
        if(City1 == null){
            System.out.println("No City List information in District");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s", "Population", "Country Name", "continent","district" ));
        // Loop over all employees in the list
        for (City emp : City1) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s",
                            emp.getPopulation(), emp.getName(), emp.getCountry(),emp.getDistrict());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 12
    public ArrayList<City> mostpopcity_worldLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT city.Name, city.CountryCode, city.District, city.Population FROM city ORDER by Population DESC Limit 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract city information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setName(rset.getString("Name"));
                city.setCountry(rset.getString("CountryCode"));
//                city.setContinent(rset.getString("Continent"));
                city.setPopulation(rset.getInt("Population"));
                city.setDistrict(rset.getString("District"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print City list");
            return null;
        }
    }
    public void print_pcity_world(ArrayList<City> City1)
    {
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s", "Population", "Country Name", "Continent", "District"));
        // Loop over all cities in the list
        for (City emp : City1) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s",
                            emp.getPopulation(), emp.getName(), emp.getCountry(), emp.getDistrict());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 13
    public ArrayList<City> mostpopcity_continentLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT city.Name, city.CountryCode, city.District, city.Population,country.Code, country.Continent FROM city,country Where city.CountryCode = country.code and country.Continent = 'Asia' ORDER by Population DESC Limit 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setContinent(rset.getString("Continent"));
                city.setName(rset.getString("Name"));
                city.setPopulation(rset.getInt("Population"));
                city.setDistrict(rset.getString("District"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_pcity_continentLS(ArrayList<City> City1)
    {
        //Check null
        if(City1 == null){
            System.out.println("No Population City List information in Continent");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s", "Population", "Country Name", "continent","district" ));
        // Loop over all employees in the list
        for (City emp : City1) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s",
                            emp.getPopulation(), emp.getName(), emp.getContinent(),emp.getDistrict());
            System.out.println(emp_string);
        }
    }
    //End

    //Query 14
    public ArrayList<City> mostpopcity_regionLS()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String regionlist = "SELECT city.Name, city.CountryCode, city.District, city.Population,country.Code, country.Continent FROM city,country Where city.CountryCode = country.code and country.Region = 'Eastern Asia' ORDER by Population DESC Limit 10";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(regionlist);
            // Extract employee information
            ArrayList<City> City = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.setContinent(rset.getString("Continent"));
                city.setName(rset.getString("Name"));
                city.setPopulation(rset.getInt("Population"));
                city.setDistrict(rset.getString("District"));
                City.add(city);
            }
            return City;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Fail to print Country list");
            return null;
        }
    }
    public void print_pcity_regionLS(ArrayList<City> City1)
    {
        //Check null
        if(City1 == null){
            System.out.println("No Population City List information in Region");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-30s %-45s %-55s", "Population", "Country Name", "continent","district" ));
        // Loop over all employees in the list
        for (City emp : City1) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-30s %-45s %-55s",
                            emp.getPopulation(), emp.getName(), emp.getContinent(),emp.getDistrict());
            System.out.println(emp_string);
        }
    }
    //End

}