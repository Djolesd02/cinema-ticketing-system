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
public class Zaposlenje extends AbstractDomainObject {

    private Long idZaposlenje;
    private Kasir kasir;
    private Zadruga zadruga;
    private String brojUgovora;
    private Date datumSaradnje;

    public Zaposlenje() {
    }

    public Zaposlenje(Long idZaposlenje, Kasir kasir, Zadruga zadruga, String brojUgovora, Date datumSaradnje) {
        this.idZaposlenje = idZaposlenje;
        this.kasir = kasir;
        this.zadruga = zadruga;
        this.brojUgovora = brojUgovora;
        this.datumSaradnje = datumSaradnje;
    }

    @Override
    public String nazivTabele() {
        return " Zaposlenje ";
    }

    @Override
    public String alijas() {
        return " ZAP ";
    }

    @Override
    public String join() {
        return " JOIN Kasir KAS ON ( KAS.idKasir = ZAP.idKasir ) "
                + " JOIN Zadruga Z ON ( Z.idZadruga = ZAP.idZadruga ) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Kasir k = new Kasir(
                    rs.getLong("KAS.idKasir"),
                    rs.getString("KAS.ime"),
                    rs.getString("KAS.prezime"),
                    rs.getString("KAS.korisnickoIme"),
                    rs.getString("KAS.sifra")
            );
            Zadruga z = new Zadruga(
                    rs.getLong("Z.idZadruga"),
                    rs.getString("Z.naziv"),
                    rs.getString("Z.PIB"),
                    rs.getString("Z.email"),
                    rs.getString("Z.kontaktTelefon")
            );
            Zaposlenje zap = new Zaposlenje(
                    rs.getLong("ZAP.idZaposlenje"),
                    k,
                    z,
                    rs.getString("ZAP.brojUgovora"),
                    rs.getTimestamp("ZAP.datumSaradnje")
            );
            lista.add(zap);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (idKasir, idZadruga, brojUgovora, datumSaradnje) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + kasir.getIdKasir() + ", " + zadruga.getIdZadruga() + ", "
                + "'" + brojUgovora + "', '" + new Timestamp(datumSaradnje.getTime()) + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " brojUgovora = '" + brojUgovora + "', "
                + "datumSaradnje = '" + new Timestamp(datumSaradnje.getTime()) + "' ";
    }

    @Override
    public String uslov() {
        return " idZaposlenje = " + idZaposlenje;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getIdZaposlenje() {
        return idZaposlenje;
    }

    public void setIdZaposlenje(Long idZaposlenje) {
        this.idZaposlenje = idZaposlenje;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    public Zadruga getZadruga() {
        return zadruga;
    }

    public void setZadruga(Zadruga zadruga) {
        this.zadruga = zadruga;
    }

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public Date getDatumSaradnje() {
        return datumSaradnje;
    }

    public void setDatumSaradnje(Date datumSaradnje) {
        this.datumSaradnje = datumSaradnje;
    }
}
