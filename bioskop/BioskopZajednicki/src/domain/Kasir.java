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
public class Kasir extends AbstractDomainObject {

    private Long idKasir;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;

    public Kasir() {
    }

    public Kasir(Long idKasir, String ime, String prezime, String korisnickoIme, String sifra) {
        this.idKasir = idKasir;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    @Override
    public String nazivTabele() {
        return " Kasir ";
    }

    @Override
    public String alijas() {
        return " KAS ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Kasir k = new Kasir(
                    rs.getLong("idKasir"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("korisnickoIme"),
                    rs.getString("sifra")
            );
            lista.add(k);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ime, prezime, korisnickoIme, sifra) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + ime + "', '" + prezime + "', "
                + "'" + korisnickoIme + "', '" + sifra + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime + "', "
                + "korisnickoIme = '" + korisnickoIme + "', sifra = '" + sifra + "' ";
    }

    @Override
    public String uslov() {
        return " idKasir = " + idKasir;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Kasir)) {
            return false;
        }
        return ((Kasir) obj).getIdKasir().equals(this.idKasir);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Long getIdKasir() {
        return idKasir;
    }

    public void setIdKasir(Long idKasir) {
        this.idKasir = idKasir;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
