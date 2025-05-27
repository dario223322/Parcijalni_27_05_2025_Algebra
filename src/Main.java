import java.sql.*;
import java.util.Scanner;


public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n Odaberite opciju:");
            System.out.println("1. Unesi novog polaznika");
            System.out.println("2. Unesi novi program obrazovanja");
            System.out.println("3. Upisi polaznika na program");
            System.out.println("4. Prebaci polaznika na drugi program");
            System.out.println("5. Ispisi polaznike za program");
            System.out.println("0. Izlaz");

            int izbor = sc.nextInt();
            sc.nextLine();

            try {
                switch (izbor) {
                    case 1 -> unesiPolaznika();
                    case 2 -> unesiProgram();
                    case 3 -> upisiPolaznika();
                    case 4 -> prebaciPolaznika();
                    case 5 -> ispisiPolaznikeZaProgram();
                    case 0 -> System.exit(0);
                    default -> System.out.println("Nepoznata opcija.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    static void unesiPolaznika() throws SQLException {
        System.out.println("Ime: ");
        String ime = sc.nextLine();
        System.out.println("Prezime: ");
        String prezime = sc.nextLine();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call UnesiPolaznika(?, ?)}")) {
            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            stmt.execute();
            System.out.println("Polaznik unesen.");
        }
    }
    static void unesiProgram() throws SQLException {
        System.out.print("Naziv programa: ");
        String naziv = sc.nextLine();
        System.out.print("CSVET: ");
        int csvet = sc.nextInt();
        sc.nextLine();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call UnesiProgram(?, ?)}")) {
            stmt.setString(1, naziv);
            stmt.setInt(2, csvet);
            stmt.execute();
            System.out.println("Program unesen.");
        }
    }
    static void upisiPolaznika() throws SQLException {
        System.out.print("ID Polaznika: ");
        int idPolaznik = sc.nextInt();
        System.out.print("ID Programa: ");
        int idProgram = sc.nextInt();
        sc.nextLine();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call UpisiPolaznika(?, ?)}")) {
            stmt.setInt(1, idPolaznik);
            stmt.setInt(2, idProgram);
            stmt.execute();
            System.out.println("Polaznik upisan.");
        }
    }
    static void prebaciPolaznika() throws SQLException {
        System.out.print("ID Polaznika: ");
        int idPolaznik = sc.nextInt();
        System.out.print("ID Novog Programa: ");
        int noviProgram = sc.nextInt();
        sc.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (CallableStatement stmt = conn.prepareCall("{call PrebaciPolaznika(?, ?)}")) {
                stmt.setInt(1, idPolaznik);
                stmt.setInt(2, noviProgram);
                stmt.execute();
                conn.commit();
                System.out.println("Polaznik prebačen.");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
    static void ispisiPolaznikeZaProgram() throws SQLException {
        System.out.print("ID Programa: ");
        int idProgram = sc.nextInt();
        sc.nextLine();

        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall("{call IspisiPolaznikeZaProgram(?)}")) {
            stmt.setInt(1, idProgram);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Ime: %s, Prezime: %s, Program: %s, CSVET: %d%n",
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("Naziv"),
                        rs.getInt("CSVET"));
            }
        }
    }
}