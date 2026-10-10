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
public class Racun extends AbstractDomainObject {

    private Long idRacun;
    private Date datumVreme;
    private boolean storniran;
    private double ukupnaCena;
    private Kasir kasir;
    private Musterija musterija;
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun() {
    }

    public Racun(Long idRacun, Date datumVreme, boolean storniran, double ukupnaCena, Kasir kasir, Musterija musterija, ArrayList<StavkaRacuna> stavkeRacuna) {
        this.idRacun = idRacun;
        this.datumVreme = datumVreme;
        this.storniran = storniran;
        this.ukupnaCena = ukupnaCena;
        this.kasir = kasir;
        this.musterija = musterija;
        this.stavkeRacuna = stavkeRacuna;
    }

    @Override
    public String nazivTabele() {
        return " Racun ";
    }

    @Override
    public String alijas() {
        return " R ";
    }

    @Override
    public String join() {
        return " JOIN Kasir KAS ON ( KAS.idKasir = R.idKasir ) "
                + " JOIN Musterija M ON ( M.idMusterija = R.idMusterija ) "
                + " JOIN StarosnoDoba SD ON ( SD.idStarosnoDoba = M.idStarosnoDoba ) ";
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
            lista.add(r);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, storniran, ukupnaCena, idKasir, idMusterija) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new Timestamp(datumVreme.getTime()) + "', " + storniran + ", "
                + ukupnaCena + ", " + kasir.getIdKasir() + ", " + musterija.getIdMusterija();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ukupnaCena = " + ukupnaCena + ", storniran = " + storniran + ", "
                + "datumVreme = '" + new Timestamp(datumVreme.getTime()) + "' ";
    }

    @Override
    public String uslov() {
        return " idRacun = " + idRacun;
    }

    @Override
    public String uslovZaSelect() {
        if (musterija != null && musterija.getImePrezime() != null && !musterija.getImePrezime().trim().isEmpty()) {
            return " WHERE (M.ime LIKE '%" + musterija.getImePrezime() + "%' OR M.prezime LIKE '%"
                    + musterija.getImePrezime() + "%') ORDER BY R.idRacun ASC ";
        }
        return " ORDER BY R.idRacun ASC ";
    }

    public Long getIdRacun() {
        return idRacun;
    }

    public void setIdRacun(Long idRacun) {
        this.idRacun = idRacun;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public boolean isStorniran() {
        return storniran;
    }

    public void setStorniran(boolean storniran) {
        this.storniran = storniran;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    public Musterija getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterija musterija) {
        this.musterija = musterija;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = (stavkeRacuna != null) ? stavkeRacuna : new ArrayList<>();
    }
}
