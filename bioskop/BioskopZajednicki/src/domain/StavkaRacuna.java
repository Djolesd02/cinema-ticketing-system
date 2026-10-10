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
public class StavkaRacuna extends AbstractDomainObject {

    private Racun racun;
    private int rb;
    private int kolicina;
    private double iznos;
    private Projekcija projekcija;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rb, int kolicina, double iznos, Projekcija projekcija) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.iznos = iznos;
        this.projekcija = projekcija;
    }

    @Override
    public String nazivTabele() {
        return " StavkaRacuna ";
    }

    @Override
    public String alijas() {
        return " SR ";
    }

    @Override
    public String join() {
        return " JOIN Racun R ON ( R.idRacun = SR.idRacun ) "
                + " JOIN Kasir KAS ON ( KAS.idKasir = R.idKasir ) "
                + " JOIN Musterija M ON ( M.idMusterija = R.idMusterija ) "
                + " JOIN StarosnoDoba SD ON ( SD.idStarosnoDoba = M.idStarosnoDoba ) "
                + " JOIN Projekcija P ON ( P.idProjekcija = SR.idProjekcija ) ";
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
            Kasir k = new Kasir(
                    rs.getLong("KAS.idKasir"),
                    rs.getString("KAS.ime"),
                    rs.getString("KAS.prezime"),
                    rs.getString("KAS.korisnickoIme"),
                    rs.getString("KAS.sifra")
            );
            Racun r = new Racun(
                    rs.getLong("R.idRacun"),
                    rs.getTimestamp("R.datumVreme"),
                    rs.getBoolean("R.storniran"),
                    rs.getDouble("R.ukupnaCena"),
                    k, m, new ArrayList<>()
            );
            Projekcija p = new Projekcija(
                    rs.getLong("P.idProjekcija"),
                    rs.getString("P.nazivFilma"),
                    rs.getDouble("P.cena"),
                    rs.getString("P.tipProjekcije"),
                    rs.getString("P.zanr"),
                    rs.getInt("P.trajanje"),
                    rs.getTimestamp("P.datumVreme")
            );
            StavkaRacuna sr = new StavkaRacuna(
                    r,
                    rs.getInt("SR.rb"),
                    rs.getInt("SR.kolicina"),
                    rs.getDouble("SR.iznos"),
                    p
            );
            lista.add(sr);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (idRacun, rb, kolicina, iznos, idProjekcija) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + racun.getIdRacun()
                + ", " + rb
                + ", " + kolicina
                + ", " + iznos
                + ", " + projekcija.getIdProjekcija();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " kolicina = " + kolicina
                + ", iznos = " + iznos
                + ", idProjekcija = " + projekcija.getIdProjekcija() + " ";
    }

    @Override
    public String uslov() {
        return " idRacun = " + racun.getIdRacun() + " AND rb = " + rb;
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE R.idRacun = " + racun.getIdRacun();
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Projekcija getProjekcija() {
        return projekcija;
    }

    public void setProjekcija(Projekcija projekcija) {
        this.projekcija = projekcija;
    }
}
