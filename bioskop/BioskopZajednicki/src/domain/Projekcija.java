/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Djole
 */
public class Projekcija extends AbstractDomainObject {

    private Long idProjekcija;
    private String nazivFilma;
    private double cena;
    private String tipProjekcije;
    private String zanr;
    private int trajanje;
    private Date datumVreme;

    public Projekcija() {
    }

    public Projekcija(Long idProjekcija, String nazivFilma, double cena, String tipProjekcije, String zanr, int trajanje, Date datumVreme) {
        this.idProjekcija = idProjekcija;
        this.nazivFilma = nazivFilma;
        this.cena = cena;
        this.tipProjekcije = tipProjekcije;
        this.zanr = zanr;
        this.trajanje = trajanje;
        this.datumVreme = datumVreme;
    }

    @Override
    public String nazivTabele() {
        return " Projekcija ";
    }

    @Override
    public String alijas() {
        return " P ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Projekcija p = new Projekcija(
                    rs.getLong("idProjekcija"),
                    rs.getString("P.nazivFilma"),
                    rs.getDouble("P.cena"),
                    rs.getString("P.tipProjekcije"),
                    rs.getString("P.zanr"),
                    rs.getInt("P.trajanje"),
                    rs.getTimestamp("P.datumVreme")
            );
            lista.add(p);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (nazivFilma, cena, tipProjekcije, zanr, trajanje, datumVreme) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + nazivFilma + "', " + cena + ", '" + tipProjekcije + "', '" + zanr + "', "
                + trajanje + ", '" + new Timestamp(datumVreme.getTime()) + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " nazivFilma = '" + nazivFilma + "', cena = " + cena + ", "
                + "tipProjekcije = '" + tipProjekcije + "', zanr = '" + zanr + "', "
                + "trajanje = " + trajanje + ", datumVreme = '" + new Timestamp(datumVreme.getTime()) + "' ";
    }

    @Override
    public String uslov() {
        return " idProjekcija = " + idProjekcija;
    }

    @Override
    public String uslovZaSelect() {
        if (nazivFilma != null && !nazivFilma.trim().isEmpty()) {
            return " WHERE (P.nazivFilma LIKE '" + nazivFilma + "%' OR P.zanr LIKE '" + nazivFilma
                    + "%' OR P.tipProjekcije LIKE '" + nazivFilma + "%') ";
        }
        return "";
    }

    @Override
    public String toString() {
        return nazivFilma + " - " + tipProjekcije + ", "
                + new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm").format(datumVreme)
                + " (" + cena + " din)";
    }

    public Long getIdProjekcija() {
        return idProjekcija;
    }

    public void setIdProjekcija(Long idProjekcija) {
        this.idProjekcija = idProjekcija;
    }

    public String getNazivFilma() {
        return nazivFilma;
    }

    public void setNazivFilma(String nazivFilma) {
        this.nazivFilma = nazivFilma;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getTipProjekcije() {
        return tipProjekcije;
    }

    public void setTipProjekcije(String tipProjekcije) {
        this.tipProjekcije = tipProjekcije;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }
}
