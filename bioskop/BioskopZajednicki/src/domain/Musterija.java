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
public class Musterija extends AbstractDomainObject {

    private Long idMusterija;
    private String ime;
    private String prezime;
    private String imePrezime;
    private String email;
    private String kontaktTelefon;
    private StarosnoDoba starosnoDoba;

    public Musterija() {
    }

    public Musterija(Long idMusterija, String ime, String prezime, String imePrezime, String email, String kontaktTelefon, StarosnoDoba starosnoDoba) {
        this.idMusterija = idMusterija;
        this.ime = ime;
        this.prezime = prezime;
        this.imePrezime = imePrezime;
        this.email = email;
        this.kontaktTelefon = kontaktTelefon;
        this.starosnoDoba = starosnoDoba;
    }

    @Override
    public String nazivTabele() {
        return " Musterija ";
    }

    @Override
    public String alijas() {
        return " M ";
    }

    @Override
    public String join() {
        return " JOIN StarosnoDoba SD ON ( SD.idStarosnoDoba = M.idStarosnoDoba ) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            StarosnoDoba sd = new StarosnoDoba(
                    rs.getLong("SD.idStarosnoDoba"),
                    rs.getString("SD.naziv"),
                    rs.getInt("SD.godinaOd"),
                    rs.getInt("SD.godinaDo")
            );
            Musterija m = new Musterija(
                    rs.getLong("M.idMusterija"),
                    rs.getString("M.ime"),
                    rs.getString("M.prezime"),
                    rs.getString("M.imePrezime"),
                    rs.getString("M.email"),
                    rs.getString("M.kontaktTelefon"),
                    sd
            );
            lista.add(m);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ime, prezime, imePrezime, email, kontaktTelefon, idStarosnoDoba) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + ime + "', '" + prezime + "', '" + (ime + " " + prezime) + "', "
                + "'" + email + "', '" + kontaktTelefon + "', " + starosnoDoba.getIdStarosnoDoba();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', kontaktTelefon = '" + kontaktTelefon + "', "
                + "idStarosnoDoba = " + starosnoDoba.getIdStarosnoDoba();
    }

    @Override
    public String uslov() {
        return " idMusterija = " + idMusterija;
    }

    @Override
    public String uslovZaSelect() {
        String uslov = "";
        if (imePrezime != null && !imePrezime.trim().isEmpty()) {
            uslov += " (M.ime LIKE '%" + imePrezime + "%' OR M.prezime LIKE '%" + imePrezime + "%') ";
        }
        if (starosnoDoba != null) {
            if (!uslov.isEmpty()) {
                uslov += " AND ";
            }
            uslov += " M.idStarosnoDoba = " + starosnoDoba.getIdStarosnoDoba() + " ";
        }
        if (uslov.isEmpty()) {
            return "";
        }
        return " WHERE " + uslov;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Musterija)) {
            return false;
        }
        return ((Musterija) obj).getIdMusterija().equals(this.idMusterija);
    }

    public Long getIdMusterija() {
        return idMusterija;
    }

    public void setIdMusterija(Long idMusterija) {
        this.idMusterija = idMusterija;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
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

    public StarosnoDoba getStarosnoDoba() {
        return starosnoDoba;
    }

    public void setStarosnoDoba(StarosnoDoba starosnoDoba) {
        this.starosnoDoba = starosnoDoba;
    }
}
