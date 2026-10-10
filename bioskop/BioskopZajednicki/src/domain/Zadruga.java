/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Djole
 */
public class Zadruga extends AbstractDomainObject {

    private Long idZadruga;
    private String naziv;
    private String PIB;
    private String email;
    private String kontaktTelefon;

    public Zadruga() {
    }

    public Zadruga(Long idZadruga, String naziv, String PIB, String email, String kontaktTelefon) {
        this.idZadruga = idZadruga;
        this.naziv = naziv;
        this.PIB = PIB;
        this.email = email;
        this.kontaktTelefon = kontaktTelefon;
    }

    @Override
    public String nazivTabele() {
        return " Zadruga ";
    }

    @Override
    public String alijas() {
        return " Z ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Zadruga z = new Zadruga(
                    rs.getLong("idZadruga"),
                    rs.getString("naziv"),
                    rs.getString("PIB"),
                    rs.getString("email"),
                    rs.getString("kontaktTelefon")
            );
            lista.add(z);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, PIB, email, kontaktTelefon) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "', '" + PIB + "', '" + email + "', '" + kontaktTelefon + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "', PIB = '" + PIB + "', "
                + "email = '" + email + "', kontaktTelefon = '" + kontaktTelefon + "' ";
    }

    @Override
    public String uslov() {
        return " idZadruga = " + idZadruga;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public String toString() {
        return naziv;
    }

    public Long getIdZadruga() {
        return idZadruga;
    }

    public void setIdZadruga(Long idZadruga) {
        this.idZadruga = idZadruga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }
}
