package home.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import home.LogInController;
import home.appointments.Appointment;
import home.appointments.Contact;
import home.appointments.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class that encapsulates the logic for interacting with database data.
 */
public class DataManager
{
    public static List<Customer> customers = new ArrayList<>();
    public static List<Country> countries = getAllCountries();
    public static List<Division> divisions = getAllDivisions();
    public static List<Contact> contacts = getAllContacts();
    public static List<User> users = getAllUsers();
    public static List<Appointment> appointments = new ArrayList<>();
    //    public static List<Appointment> appointments = getAllAppointments();
    private static DataManager instance = null;
    //private static Connection connection;

//    static {
//        try {
//            connection = DriverManager.getConnection("","", "");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * private constructor to define singleton
     */
    private DataManager() {
    }

    /**
     * creates an instance of this class only when its null
     * otherwise it will return the same instance
     *
     * @return
     */
    public static DataManager getInstance() {
        if (instance == null) {
            return new DataManager();
        } else {
            return instance;
        }
    }

    /**
     * Method that retrieves all appointments from database
     * Lambda expression used to find user from list of users
     * Lamdba expression used to find contact from list of contacts
     * @return list of appointments from database
     */
//    public static List<Appointment> getAllAppointments() {
//        // TODO load data from DB
//        // TODO get from DB
//        List<Appointment> appointments = new ArrayList<>();
//        try {
//            String query = "select appointment_id, title, description, location, type, " +
//                "start, end, customer_id, user_id, contact_id from apppointments";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            ResultSet resultSet = myStmt.executeQuery();
//            while (resultSet.next()) {
//                int appointmentId = resultSet.getInt("appointment_id");
//                String title = resultSet.getString("title");
//                String description = resultSet.getString("description");
//                String location = resultSet.getString("lcoation");
//                String type = resultSet.getString("type");
//                int customerId = resultSet.getInt("customer_id");
//                int userId = resultSet.getInt("user_id");
//                int contactId = resultSet.getInt("contact_id");
//                Timestamp startTimestamp = resultSet.getTimestamp("start");
//                ZonedDateTime startZonedDateTime = ZonedDateTime.ofInstant(startTimestamp.toInstant(), ZoneId.systemDefault());
//                Timestamp endTimestamp = resultSet.getTimestamp("end");
//                ZonedDateTime endZonedDateTime = ZonedDateTime.ofInstant(endTimestamp.toInstant(), ZoneId.systemDefault());
//                User user = users.stream().filter(u -> u.getId() == userId).findFirst().get();
//                Contact contact = contacts.stream().filter(c -> c.getId() == contactId).findFirst().get();
//                Appointment appointment = new Appointment(appointmentId, title, description, location, type, startZonedDateTime,
//                    endZonedDateTime, customerId, contact, user);
//                appointments.add(appointment);
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with adding contacts " + e.getMessage());
//        }
//        return  appointments;
//    }
    /**
     * Loads the customers from the database and stores a local copy
     */
    public void loadCustomers() throws SQLException {
        //TODO load data from DB

//        Connection connection = DriverManager.getConnection("","", "");
//        String query = "select customer_id, customer_name, address, postal_code, phone, division_id from customers";
//        PreparedStatement myStmt = connection.prepareStatement(query);
//
//        ResultSet resultSet = myStmt.executeQuery();
//        while (resultSet.next()) {
//            int customerId = resultSet.getInt("customer_id");
//            String customerName = resultSet.getString("customer_name");
//            String customerAddress = resultSet.getString("address");
//            String customerPhone = resultSet.getString("phone");
//            String customerPostal = resultSet.getString("postal_code");
//            int divisionId = resultSet.getInt("division_id");
//            Division division = getDivisionById(divisionId);
//            List<Appointment> customerApppointments = appointments.stream().
//                filter(a -> a.getCustomerId() == customerId).collect(Collectors.toList());
//            Customer customer = new Customer(customerId, customerName, customerAddress, customerPostal, customerPhone,
//                customerApppointments, division);
//            customers.add(customer);
//        }
//        connection.close();

        //
        List<Appointment> appointmentList = new ArrayList<>();
        Contact contact1 = new Contact(1, "One Contact", "One email");
        Contact contact2 = new Contact(2, "Two Contact", "Two email");
        Contact contact3 = new Contact(3, "Three Contact", "Three email");


        // TODO: Retrieve apointments dates from DB UTC and switch them to default zone on system.

        Appointment appointment1 = new Appointment(1,
            "One",
            "One description",
            "One location",
            "One Type",
            ZonedDateTime.now().withZoneSameInstant(ZoneId.systemDefault()),
            ZonedDateTime.now().plusHours(1).withZoneSameInstant(ZoneId.systemDefault()),
            1,
            contact1,
            getUserById(1));
        Appointment appointment2 = new Appointment(2,
            "Two",
            "Twp description",
            "Two location",
            "Twp Type",
            ZonedDateTime.now().withZoneSameInstant(ZoneId.systemDefault()),
            ZonedDateTime.now().plusHours(1).withZoneSameInstant(ZoneId.systemDefault()),
            2,
            contact2,
            getUserById(2));
        Appointment appointment3 = new Appointment(3,
            "Three",
            "Three description",
            "Three location",
            "Three Type",
            ZonedDateTime.now().withZoneSameInstant(ZoneId.systemDefault()),
            ZonedDateTime.now().plusDays(3).withZoneSameInstant(ZoneId.systemDefault()),
            3,
            contact3,
            getUserById(3));
        Appointment appointment4 = new Appointment(4, "Four", "Four description", "Four location", "Four Type",
            ZonedDateTime.of(2023, 3, 1, 10, 30, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2023, 3, 1, 11, 0, 0, 0, ZoneId.systemDefault()), 4,contact1, getUserById(1));
        Appointment appointment5 = new Appointment(5, "Five", "Five description", "Five location", "Five Type",
            ZonedDateTime.of(2023, 3, 29, 16, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2023, 3, 29, 17, 0, 0, 0, ZoneId.systemDefault()), 5, contact2, getUserById(2));
        Appointment appointment6 = new Appointment(6, "Six", "Six description", "Six location", "Six Type",
            ZonedDateTime.of(2023, 3, 25, 9, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2023, 3, 25, 10, 0, 0, 0, ZoneId.systemDefault()), 6, contact3, getUserById(3));
        Appointment appointment7 = new Appointment(7, "Four", "Four description", "Four location", "Four Type",
            ZonedDateTime.of(2023, 2, 21, 12, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2023, 2, 21, 13, 0, 0, 0, ZoneId.systemDefault()), 4, contact1, getUserById(1));
        Appointment appointment8 = new Appointment(8, "Five", "Five description", "Five location", "Five Type",
            ZonedDateTime.of(2023, 4, 11, 13, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2023, 4, 11, 14, 0, 0, 0, ZoneId.systemDefault()), 5, contact2, getUserById(2));
        Appointment appointment9 = new Appointment(9, "Six", "Six description", "Six location", "Six Type",
            ZonedDateTime.of(2023, 5, 1, 8, 0, 0, 0, ZoneId.systemDefault()),
            ZonedDateTime.of(2023, 5, 1, 9, 0, 0, 0, ZoneId.systemDefault()), 6, contact3, getUserById(3));
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        appointmentList.add(appointment3);
        appointmentList.add(appointment4);
        appointmentList.add(appointment5);
        appointmentList.add(appointment6);
        appointmentList.add(appointment7);
        appointmentList.add(appointment8);
        appointmentList.add(appointment9);
        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        appointments.add(appointment4);
        appointments.add(appointment5);
        appointments.add(appointment6);
        appointments.add(appointment7);
        appointments.add(appointment8);
        appointments.add(appointment9);


        Division division1 = getDivisionById(1);
        Division division2 = getDivisionById(2);
        Division division3 = getDivisionById(4);
        Division division4 = getDivisionById(5);
        Division division5 = getDivisionById(7);
        Division division6 = getDivisionById(8);

        Customer customer1 = new Customer(
            1,
            "Bebetona Brito",
            "21924 Greentree Terrace",
            "20164",
            "703-509-5745",
            appointmentList,
            division1);
        Customer customer2 = new Customer(
            2,
            "Ganso Brito",
            "300 North Argonne Ave",
            "20165",
            "703-423-9823",
            appointmentList,
            division2);
        Customer customer3 = new Customer(
            3,
            "Biela Yonson",
            "123 Macoon Avenue ",
            "85621",
            "703-555-1425",
            appointmentList,
            division3);

        List<Appointment> appointments1 = new ArrayList<>();
        appointments1.add(appointment1);
        appointments1.add(appointment2);
        appointments1.add(appointment3);

        Customer customer4 = new Customer(
            4,
            "Gorito Johan",
            "12 Parkway Street",
            "96257",
            "703-222-7814",
            appointments1,
            division4);

        List<Appointment> appointments2 = new ArrayList<>();
        appointments2.add(appointment3);
        appointments2.add(appointment4);
        appointments2.add(appointment5);
        Customer customer5 = new Customer(
            5,
            "Papucho Morales",
            "1234 Whirlpool Ave",
            "85761",
            "703-978-2545",
            appointmentList,
            division5);
        Customer customer6 = new Customer(
            6,
            "Puto Tito",
            "Arlington VA",
            "25456",
            "201-222-3645",
            appointmentList,
            division6);

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
        customers.add(customer6);
    }

    /**
     * Method to retrieve a user by id
     * Lambda expression used her to find a user by its id from list of users
     * @param id user id
     * @return user object found otherwise null
     */
    public User getUserById(int id) {
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    /**
     * Method to retrieve a customer by id
     * Lambda expression used here to find a customer by its id from list of customers
     *
     * @param id identifier to filter by
     * @return the customer found if nothing found will return null
     */
    public Customer getCustomerById(int id) {
        Optional<Customer> customer = customers.stream().filter(c -> c.getId() == id).findFirst();
        if (customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

    /**
     * returns the list of customers in the application
     *
     * @return observable list of customers
     */
    public ObservableList<Customer> getCustomers()
    {
        return FXCollections.observableArrayList(customers);
    }

    /**
     * logic to add a customer to the list of customers
     *
     * @param customer
     */
    public void addCustomer(Customer customer) {
        customer.setCreateDate(ZonedDateTime.now());
        customer.setCreatedBy(LogInController.userLoggedOn.getUsername());
        customer.setLastUpdate(ZonedDateTime.now());
        customer.setLastUpdatedBy(LogInController.userLoggedOn.getUsername());
        int customerId = insertCustomer(customer);
        customer.setId(customerId);
        customers.add(customer);
    }

    /**
     * logic to remove a customer from the list of customers
     *
     * @param customer
     */
    public void deleteCustomer(Customer customer) {
        removeCustomer(customer);
        //appointments.removeAll(customer.getAppointments());
        customers.remove(customer);
    }

    /**
     * logic to edit a customer from the list of customers
     * Lambda expression used here to find a customer by its id from list of customers
     *
     * @param customer
     */
    public void editCustomer(Customer customer) {
        // TODO Convert Local Time Zone to UTC time zone for appointments dates
        customer.setLastUpdate(ZonedDateTime.now());
        customer.setLastUpdatedBy(LogInController.userLoggedOn.getUsername());
        updateCustomer(customer);
        Optional<Customer> existingCustomer = customers.stream().filter(c -> c.getId() == customer.getId()).findFirst();
        if (existingCustomer.isPresent()) {
            customers.set(customers.indexOf(existingCustomer.get()), customer);
        }
    }

    /**
     * method to retrieve a division by its id
     * Lambda expression heer used to find a division by its id from list of divisions
     *
     * @param divisionId division id
     * @return the division found otherwise null
     */
    public static Division getDivisionById(int divisionId) {
        Optional<Division> division = divisions.stream().filter(d -> d.getDivisionId() == divisionId).findFirst();
        if (division.get() != null) {
            return division.get();
        }
        return null;
    }

    /**
     * method to retrieve list of divisions by country name
     * Lambda expression used here to find all divisions with a specified country name
     *
     * @param countryName country name to filter by
     * @return list of divisions by country
     */
    public static List<Division> getDivisionsByCountryName(String countryName) {
        return divisions.stream()
            .filter(d -> d.getCountry().getCountryName().equals(countryName))
            .collect(Collectors.toList());
    }

    /**
     * method to retrieve a division by its name and country name
     * Lambda expression used here to find a division by its name and an specified country name
     *
     * @param divisionName division name
     * @param countryName  country name
     * @return the division found otherwise null
     */
    public static Division getDivisionByNameAndCountryName(String divisionName, String countryName) {
        Optional<Division> division = divisions.stream().filter(d -> d.getDivisionName().equals(divisionName)
            && d.getCountry().getCountryName().equals(countryName)).findFirst();
        if (division.get() != null) {
            return division.get();
        }
        return null;
    }

    /**
     * method to retrieve all divisions from DB
     * Lambda expression used to retrieve country based on id from list of countries
     * @return list of divisions
     */
    public static List<Division> getAllDivisions() {
        // TODO get from DB
//        List<Division> divisionsFromDB = new ArrayList<>();
//        try {
//            String query = "select divsion_id, divsion, country_id from first_level_divisions";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            ResultSet resultSet = myStmt.executeQuery();
//            while (resultSet.next()) {
//                int divsionId = resultSet.getInt("division_id");
//                String divisionName = resultSet.getString("division");
//                int countryId = resultSet.getInt("country_id");
//                Country country = countries.stream().filter(c -> c.getCountryId() == countryId).findFirst().get();
//                Division division = new Division(divsionId, divisionName, country);
//                divisionsFromDB.add(division);
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with adding contacts " + e.getMessage());
//        }
//        return  divisionsFromDB;




        List<Division> divisions = new ArrayList<>();
        Country country = countries.stream().filter(c -> c.getCountryId() == 1).findFirst().get();
        Division division = new Division(1, "Nevada", country);
        Division division1 = new Division(2, "Virginia", country);
        Division division2 = new Division(3, "Florida", country);

        Country country2 = countries.stream().filter(c -> c.getCountryId() == 2).findFirst().get();
        Division division22 = new Division(4, "Ottawa", country2);
        Division division21 = new Division(5, "Quebec", country2);
        Division division23 = new Division(6, "Toronto", country2);

        Country country3 = countries.stream().filter(c -> c.getCountryId() == 3).findFirst().get();
        Division division32 = new Division(7, "Pichincha", country3);
        Division division31 = new Division(8, "Guayas", country3);
        Division division33 = new Division(9, "Galapagos", country3);

        divisions.add(division);
        divisions.add(division1);
        divisions.add(division2);
        divisions.add(division22);
        divisions.add(division21);
        divisions.add(division23);
        divisions.add(division32);
        divisions.add(division31);
        divisions.add(division33);

        return divisions;
    }

    /**
     * method to retrieve all countries from db
     *
     * @return list of all countries
     */
    public static List<Country> getAllCountries() {
        // TODO get from DB
//        List<Country> countriesFromDB = new ArrayList<>();
//        try {
//            String query = "select country_id, country from countries";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            ResultSet resultSet = myStmt.executeQuery();
//            while (resultSet.next()) {
//                int countryId = resultSet.getInt("country_id");
//                String countryName = resultSet.getString("country");
//                Country country = new Country(countryId, countryName);
//                countriesFromDB.add(country);
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with adding countries " + e.getMessage());
//        }
//        return  countriesFromDB;


        Country country = new Country(1, "USA");
        Country country1 = new Country(2, "Canada");
        Country country2 = new Country(3, "Banana");

        List<Country> countries = new ArrayList<>();
        countries.add(country);
        countries.add(country1);
        countries.add(country2);

        return countries;
    }

    /**
     * Method that helps retrieve all contacts from Database
     *
     * @return list of contacts from database
     */
    public static List<Contact> getAllContacts(){
        // TODO retrieve from DB
//        List<Contact> contactsFromDB = new ArrayList<>();
//        try {
//           String query = "select contact_id, contact_name, contact_email from contacts";
//           PreparedStatement myStmt = connection.prepareStatement(query);
//           ResultSet resultSet = myStmt.executeQuery();
//           while (resultSet.next()) {
//               int contactId = resultSet.getInt("contact_id");
//               String contactName = resultSet.getString("contact_name");
//               String contactEmail = resultSet.getString("contact_email");
//               Contact contact = new Contact(contactId, contactName, contactEmail);
//               contactsFromDB.add(contact);
//           }
//           connection.close();
//        } catch (SQLException e) {
//              System.out.println("Error with adding contacts " + e.getMessage());
//        }
//        return  contactsFromDB;


        List<Contact> contacts = new ArrayList<>();
        Contact contact1 = new Contact(1, "One Contact", "One email");
        Contact contact2 = new Contact(2, "Two Contact", "Two email");
        Contact contact3 = new Contact(3, "Three Contact", "Three email");
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        return contacts;
    }

    /**
     * Method that helps retrieve all users from Database
     * @return  list of users from database
     */
    public static List<User> getAllUsers() {
        // TODO retrieve from DB
//        List<User> usersFromDB = new ArrayList<>();
//        try {
//            String query = "select user_id, user_name, password from users";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            ResultSet resultSet = myStmt.executeQuery();
//            while (resultSet.next()) {
//                int userId = resultSet.getInt("user_id");
//                String userName = resultSet.getString("user_name");
//                String password = resultSet.getString("password");
//                User user = new User(userId, userName, password);
//                usersFromDB.add(user);
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with adding users " + e.getMessage());
//        }
//        return  usersFromDB;



        List<User> users = new ArrayList<>();
        User user1 = new User(1,"user1", "user1");
        User user2 = new User(2,"user2", "user2");
        User user3 = new User(3, "user3", "user3");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

    /**
     * Inserts a new customer record on database
     * @param customer customer to insert into database
     * @return new customer id generated from database
     */
    public static int insertCustomer(Customer customer) {
        //TODO save to DB
        int customerId = 0;
//        try {
//            String query = "insert into customers " +
//                "(customer_name, address, postal_code, phone, create_date, created_by, last_udpdate, " +
//                "last_updated_by, division_id) values (?,?,?,?,?,?,?,?,?)";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            myStmt.setString(1, customer.getName());
//            myStmt.setString(2, customer.getAddress());
//            myStmt.setString(3, customer.getPostalCode());
//            ZonedDateTime utcCreate = customer.getCreateDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(4, Timestamp.from(utcCreate.toInstant()));
//            myStmt.setString(5, customer.getCreatedBy());
//            ZonedDateTime utcLastUpdate = customer.getLastUpdate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(6, Timestamp.from(utcLastUpdate.toInstant()));
//            myStmt.setString(7, customer.getLastUpdatedBy());
//            myStmt.setInt(8, customer.getDivision().getDivisionId());
//            customerId = myStmt.executeUpdate();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with inserting a new customer " + e.getMessage());
//        }
        return customerId;
    }

    /**
     * Updates a customer record on database
     * @param customer customer to update
     */
    public static void updateCustomer(Customer customer) {
        // TODO edit to DB
//        try {
//            String query = "update customers set customer_name = ?, set address = ?, set postal_code = ?, " +
//                "set phone = ?, set create_date = ?, set created_by = ?, set last_update = ?, " +
//                "set last_updated_by = ?, set division_id = ? where customer_id = ?";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            myStmt.setString(1, customer.getName());
//            myStmt.setString(2, customer.getAddress());
//            myStmt.setString(3, customer.getPostalCode());
//            ZonedDateTime utcCreate = customer.getCreateDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(4, Timestamp.from(utcCreate.toInstant()));
//            myStmt.setString(5, customer.getCreatedBy());
//            ZonedDateTime utcLastUpdate = customer.getLastUpdate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(6, Timestamp.from(utcLastUpdate.toInstant()));
//            myStmt.setString(7, customer.getLastUpdatedBy());
//            myStmt.setInt(8, customer.getDivision().getDivisionId());
//            myStmt.setInt(9, customer.getId());
//            myStmt.executeUpdate();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with updating a customer " + e.getMessage());
//        }
    }

    /**
     * Deletes a customer record from database
     * @param customer customer to delete from database
     */
    public static void removeCustomer(Customer customer) {
        // TODO delete from DB
//        List<Appointment> customerAppointments = customer.getAppointments();
//
//        for (Appointment appointment : customerAppointments) {
//            try {
//                String query = "delete from appointments where appointment_id = ?";
//                PreparedStatement myStmt = connection.prepareStatement(query);
//                myStmt.setInt(1, appointment.getId());
//                myStmt.executeUpdate();
//            } catch (SQLException e) {
//                System.out.println("Error with deleting appointments" + e.getMessage());
//            }
//        }
//        try {
//            String query = "delete from customers where customer_id = ?";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            myStmt.setInt(1, customer.getId());
//            myStmt.executeUpdate();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with deleting customer" + e.getMessage());
//        }
    }

    /**
     * Updates an appointment record on database
     * @param appointment appointment to update
     */
    public static void updateAppointment(Appointment appointment) {
        // TODO edit appoint in DB
//        try {
//            String query = "update appointments set title = ?, set description = ?, set location = ?, " +
//                "set type = ?, set start = ?, set end = ?, set create_date = ?, " +
//                "set created_by = ?, set last_update= ? where last_updated_by = ? " +
//                "set customer_id = ?, set user_id = ?, set contact_id = ?";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            myStmt.setString(1, appointment.getTitle());
//            myStmt.setString(2, appointment.getDescription());
//            myStmt.setString(3, appointment.getLocationAppointment());
//            myStmt.setString(4, appointment.getType());
//            ZonedDateTime utcStart = appointment.getStartDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(5, Timestamp.from(utcStart.toInstant()));
//            ZonedDateTime utcEnd = appointment.getEndDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(6, Timestamp.from(utcEnd.toInstant()));
//            ZonedDateTime utcCreate = appointment.getCreateDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(7, Timestamp.from(utcCreate.toInstant()));
//            myStmt.setString(8, appointment.getCreatedBy());
//            ZonedDateTime utcLastUpdate = appointment.getLastUpdate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(9, Timestamp.from(utcLastUpdate.toInstant()));
//            myStmt.setString(10, appointment.getLastUpdatedBy());
//            myStmt.setInt(11, appointment.getCustomerId());
//            myStmt.setInt(12, appointment.getUserId());
//            myStmt.setInt(13, appointment.getContact().getId());
//            myStmt.executeUpdate();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with updating an appointment " + e.getMessage());
//        }
    }

    /**
     * Deletes an appointment record on database
     * @param appointment appointment to remove
     */
    public static void removeAppointment(Appointment appointment) {
        // TODO remove from DB
//        try {
//            String query = "delete from appointments where appointment_id = ?";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            myStmt.setInt(1, appointment.getId());
//            myStmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Error with deleting one appointment" + e.getMessage());
//        }
    }

    /**
     * Inserts a new record appointment in database
     * @param appointment apppointment to insert in database
     * @return new generated appointment id
     */
    public static int insertAppointment(Appointment appointment) {
        // TODO add appoint to DB
        int appointmentId = 0;
//        try {
//            String query = "insert into appointments " +
//                "(title, description, location, type, start, end, create_date, created_by, last_udpdate, " +
//                "last_updated_by, customer_id, user_id, contact_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
//            PreparedStatement myStmt = connection.prepareStatement(query);
//            myStmt.setString(1, appointment.getTitle());
//            myStmt.setString(2, appointment.getDescription());
//            myStmt.setString(3, appointment.getLocationAppointment());
//            myStmt.setString(4, appointment.getType());
//            ZonedDateTime utcStart = appointment.getStartDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(5, Timestamp.from(utcStart.toInstant()));
//            ZonedDateTime utcEnd = appointment.getEndDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(6, Timestamp.from(utcEnd.toInstant()));
//            ZonedDateTime utcCreate = appointment.getCreateDate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(7, Timestamp.from(utcCreate.toInstant()));
//            myStmt.setString(8, appointment.getCreatedBy());
//            ZonedDateTime utcLastUpdate = appointment.getLastUpdate().withZoneSameInstant(ZoneId.of("UTC"));
//            myStmt.setTimestamp(9, Timestamp.from(utcLastUpdate.toInstant()));
//            myStmt.setString(10, appointment.getLastUpdatedBy());
//            myStmt.setInt(11, appointment.getCustomerId());
//            myStmt.setInt(12, appointment.getUserId());
//            myStmt.setInt(13, appointment.getContact().getId());
//            appointmentId = myStmt.executeUpdate();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("Error with inserting a new appointment " + e.getMessage());
//        }
        return appointmentId;
    }
}