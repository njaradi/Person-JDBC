package person.model.utility;

import person.model.*;
import person.model.base.Server;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class JDBCUtils {

    public static Connection connection = null;

    public static void connect() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zus", properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Karta> selectAllFromKarte() {
        List<Karta> karte = new ArrayList<>();
        String query = "select id_korisnika,id_putovanja from karte";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_korisnika = resultSet.getInt(1);
                int id_putovanja = resultSet.getInt(2);
                Karta karta = new Karta(id_korisnika,id_putovanja);
                karte.add(karta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return karte;
    }

    public static List<Korisnik> selectAllFromKorisnici() {
        List<Korisnik> korisnici = new ArrayList<>();
        String query = "select id_korisnika, korisnicko_ime, lozinka, ime, prezime," +
                " datum_rodjenja, datum_smrti from korisnici";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_korisnika = resultSet.getInt(1);
                String korisnicko_ime = resultSet.getString(2);
                String lozinka = resultSet.getString(3);
                String ime = resultSet.getString(4);
                String prezime = resultSet.getString(5);
                LocalDate datum_rodjenja = resultSet.getDate(6).toLocalDate();
                LocalDate datum_smrti = resultSet.getDate(7).toLocalDate();
                Korisnik korisnik = new Korisnik(id_korisnika, korisnicko_ime, lozinka, ime, prezime, datum_rodjenja, datum_smrti);
                korisnici.add(korisnik);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return korisnici;
    }

//    public static List<Person> selectAllFromPerson() {
//        List<Person> people = new ArrayList<>();
//        String query = "select * from person";
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                int personId = resultSet.getInt(1);
//                String firstName = resultSet.getString(2);
//                String lastName = resultSet.getString(3);
//                LocalDate dateOfBirth = resultSet.getDate(4).toLocalDate();
//                Person person = new Person(personId, firstName, lastName, dateOfBirth);
//                people.add(person);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return people;
//    }

//    public static List<Person> selectFromPerson(String firstName, String lastName, String yearOfBirth) {
//        List<Person> oldPeople = selectAllFromPerson();
//        Server.SERVER.setPeople(oldPeople);
//        List<Person> people = new ArrayList<>();
//        for (Person oldPerson : oldPeople) {
//            if (yearOfBirth == null || yearOfBirth.length() != 4) {
//                if (oldPerson.getFirstName().toLowerCase().contains(firstName.toLowerCase())
//                        && oldPerson.getLastName().toLowerCase().contains(lastName.toLowerCase()))
//                    people.add(oldPerson);
//                continue;
//            }
//            if (oldPerson.getFirstName().toLowerCase().contains(firstName.toLowerCase())
//                    && oldPerson.getLastName().toLowerCase().contains(lastName.toLowerCase())
//                    && oldPerson.getDateOfBirth().getYear() == Integer.parseInt(yearOfBirth))
//                people.add(oldPerson);
//        }
//        return people;
//    }

    public static void insertIntoPerson(String firstName, String lastName, LocalDate dateOfBirth) {
        String query = "insert into person (first_name, last_name, date_of_birth)" +
                "values (?, ?, str_to_date(?, '%m/%d/%Y'))";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3,
                    dateOfBirth.getMonthValue() + "/" +
                    dateOfBirth.getDayOfMonth() + "/" + dateOfBirth.getYear());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private JDBCUtils() {

    }

    public static boolean checkCredentials(String username, String password) {
        List<Korisnik> korisnici = new ArrayList<>();
        String query = "select id_korisnika, korisnicko_ime, lozinka, ime, prezime, datum_rodjenja from korisnici where korisnicko_ime = ? and lozinka = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id_korisnika = resultSet.getInt(1);
                String korisnicko_ime = resultSet.getString(2);
                String lozinka = resultSet.getString(3);
                String ime = resultSet.getString(4);
                String prezime = resultSet.getString(5);
                LocalDate datum_rodjenja = resultSet.getDate(6).toLocalDate();
                Korisnik korisnik = new Korisnik(id_korisnika, korisnicko_ime, lozinka, ime, prezime, datum_rodjenja);
                Server.currUser = korisnik;
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(String name, String last, String username,
                                  String password, LocalDate birthday) {
        String query = "insert into korisnici (ime, prezime, datum_rodjenja, korisnicko_ime, lozinka)" +
                "values (?, ?, str_to_date(?, '%Y-%m-%d'), ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setString(1, name);
            statement.setString(2, last);
            statement.setString(3, birthday.getYear() + "-" +
                    birthday.getMonthValue() + "-" +
                            birthday.getDayOfMonth());
            statement.setString(4, username);
            statement.setString(5, password);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addKupovinaObjekta(int id_objekta, int id_korisnika) {
        String query = "insert into kupovine_objekata (id_korisnika, id_objekta) " +
                "values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setInt(1, id_korisnika);
            statement.setInt(2, id_objekta);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addKarta(int id_korisnika, int id_putovanja) {
        String query = "insert into karte (id_korisnika, id_putovanja) " +
                "values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setInt(1, id_korisnika);
            statement.setInt(2, id_putovanja);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public static Collection<Planelit> selectPutovanja() {
        List<Planelit> planeliti = new ArrayList<>();
        String query = "select id_planelita, ime, udaljenost, min_temp, max_temp, " +
                "procenat_kiseonika, gas, procenat_gasa, visina_gravitacije, brzina_orbitiranja, " +
                "tip from planeliti WHERE udaljenost BETWEEN 100 AND 200 AND min_temp BETWEEN 150 " +
                "AND 250 AND max_temp BETWEEN 250 AND 350 AND (max_temp - min_temp) <= 120" +
                " AND procenat_kiseonika BETWEEN 15 AND 25 " +
                "AND gas IN ('Carbon Dioxide', 'Nitrous Oxide', 'Methane', 'Argon', 'Krypton', 'Xenon', 'Helium', 'Neon', 'Hydrogen','Gas') " +
                "AND (procenat_kiseonika + procenat_gasa) BETWEEN 90 AND 99 " +
                "AND visina_gravitacije > 1000 " +
                "AND brzina_orbitiranja BETWEEN 25 AND 35";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_planelita = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                double udaljenost  = resultSet.getDouble(3);
                double min_temp = resultSet.getDouble(4);
                double max_temp = resultSet.getDouble(5);
                double procenat_kiseonika = resultSet.getDouble(6);
                String gas =  resultSet.getString(7);
                double procenat_gasa = resultSet.getDouble(8);
                double visina_grav = resultSet.getDouble(9);
                double brzina_orb = resultSet.getDouble(10);
                String tip = resultSet.getString(11);
                Planelit planelit = new Planelit(id_planelita, ime, udaljenost, min_temp, max_temp, procenat_kiseonika, gas, procenat_gasa, visina_grav, brzina_orb, tip);
                planeliti.add(planelit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return planeliti;
    }
    public static Collection<Planelit> selectLivablePlaneliti() {
        List<Planelit> planeliti = new ArrayList<>();
        String query = "select id_planelita, ime, udaljenost, min_temp, max_temp, " +
                "procenat_kiseonika, gas, procenat_gasa, visina_gravitacije, brzina_orbitiranja, " +
                "tip from planeliti WHERE udaljenost BETWEEN 100 AND 200 AND min_temp BETWEEN 150 " +
                "AND 250 AND max_temp BETWEEN 250 AND 350 AND (max_temp - min_temp) <= 120" +
                " AND procenat_kiseonika BETWEEN 15 AND 25 " +
                "AND gas IN ('Carbon Dioxide', 'Nitrous Oxide', 'Methane', 'Argon', 'Krypton', 'Xenon', 'Helium', 'Neon', 'Hydrogen','Gas') " +
                "AND (procenat_kiseonika + procenat_gasa) BETWEEN 90 AND 99 " +
                "AND visina_gravitacije > 1000 " +
                "AND brzina_orbitiranja BETWEEN 25 AND 35";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_planelita = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                double udaljenost  = resultSet.getDouble(3);
                double min_temp = resultSet.getDouble(4);
                double max_temp = resultSet.getDouble(5);
                double procenat_kiseonika = resultSet.getDouble(6);
                String gas =  resultSet.getString(7);
                double procenat_gasa = resultSet.getDouble(8);
                double visina_grav = resultSet.getDouble(9);
                double brzina_orb = resultSet.getDouble(10);
                String tip = resultSet.getString(11);
                Planelit planelit = new Planelit(id_planelita, ime, udaljenost, min_temp, max_temp, procenat_kiseonika, gas, procenat_gasa, visina_grav, brzina_orb, tip);
                planeliti.add(planelit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return planeliti;
    }

    public static Collection<Putovanje> selectPutovanjaPlanelit(int idPlanelita) {
        List<Putovanje> putovanja = new ArrayList<>();
        String query = "select id_putovanja, sifra_vozila, datum, vreme from putovanja " +
                "where id_planelita = "+ idPlanelita;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_putovanja = resultSet.getInt(1);
                int siftra_vozila = resultSet.getInt(2);
                LocalDate datum  = resultSet.getDate(3).toLocalDate();
                LocalTime vreme = resultSet.getTime(4).toLocalTime();

                Putovanje putovanje = new Putovanje(id_putovanja, siftra_vozila, datum, vreme, idPlanelita);
                putovanja.add(putovanje);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return putovanja;
    }


    public static Collection<StambeniObjekat> selectObjektiPlanelit(int idPlanelita) {
        List<StambeniObjekat> stambeniObjekats = new ArrayList<>();
        String query = "select id_objekta, naziv from stambeni_objekti " +
                "where id_planelita = "+ idPlanelita;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id_objekta = resultSet.getInt(1);
                String naziv = resultSet.getString(2);

                StambeniObjekat stambeniObjekat = new StambeniObjekat(id_objekta, idPlanelita, naziv);
                stambeniObjekats.add(stambeniObjekat);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stambeniObjekats;
    }

    public static Collection<KartaHybrid> selectKupljeneKarte(int id_korisnika) {
        List<KartaHybrid> kartaHybrids = new ArrayList<>();
        String query = "select ime, sifra_vozila, datum, vreme from karte JOIN putovanja " +
                "USING (id_putovanja) JOIN planeliti USING (id_planelita) where id_korisnika = "
                + id_korisnika;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String ime = resultSet.getString(1);
                int sifra = resultSet.getInt(2);
                LocalDate datum = resultSet.getDate(3).toLocalDate();
                LocalTime vreme = resultSet.getTime(4).toLocalTime();

                KartaHybrid kartaHybrid = new KartaHybrid(ime, sifra, datum, vreme);
                kartaHybrids.add(kartaHybrid);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kartaHybrids;
    }

    public static Collection<ObjektiHybrid> selectKupljeniObjekti(int id_korisnika) {
        List<ObjektiHybrid> objektiHybrids = new ArrayList<>();
        String query = "select ime, naziv from kupovine_objekata JOIN stambeni_objekti USING (id_objekta) " +
                "JOIN planeliti USING (id_planelita) where id_korisnika = "
                + id_korisnika;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String ime_planelita = resultSet.getString(1);
                String ime_objekta = resultSet.getString(2);

                ObjektiHybrid objektiHybrid = new ObjektiHybrid(ime_planelita, ime_objekta);
                objektiHybrids.add(objektiHybrid);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objektiHybrids;
    }
}
