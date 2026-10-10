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
public class StarosnoDoba extends AbstractDomainObject {
    private Long idStarosnoDoba;
    private String naziv;
    private int godinaOd;
    private int godinaDo;

    public StarosnoDoba() {
    }

    public StarosnoDoba(Long idStarosnoDoba, String naziv, int godinaOd, int godinaDo) {
        this.idStarosnoDoba = idStarosnoDoba;
        this.naziv = naziv;
        this.godinaOd = godinaOd;
        this.godinaDo = godinaDo;
    }
    
    @Override
    public String nazivTabele() {
        return " StarosnoDoba ";
    }
    @Override
    public String toString() {
        return naziv;
    }
    public Long getIdStarosnoDoba() {
        return idStarosnoDoba;
    }

    public void setIdStarosnoDoba(Long idStarosnoDoba) {
        this.idStarosnoDoba = idStarosnoDoba;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodinaOd() {
        return godinaOd;
    }

    public void setGodinaOd(int godinaOd) {
        this.godinaOd = godinaOd;
    }

    public int getGodinaDo() {
        return godinaDo;
    }

    public void setGodinaDo(int godinaDo) {
        this.godinaDo = godinaDo;
    }

    @Override
    public String alijas() {
          return " SD ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            StarosnoDoba sd = new StarosnoDoba(
                    rs.getLong("idStarosnoDoba"),
                    rs.getString("SD.naziv"),
                    rs.getInt("SD.godinaOd"),
                    rs.getInt("SD.godinaDo")
            );
            lista.add(sd);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
       return " (naziv, godinaOd, godinaDo) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "', " + godinaOd + ", " + godinaDo;
    }

    @Override
    public String vrednostiZaUpdate() {
          return " naziv = '" + naziv + "', godinaOd = " + godinaOd + ", godinaDo = " + godinaDo;
    }

    @Override
    public String uslov() {
       return " idStarosnoDoba = " + idStarosnoDoba;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof StarosnoDoba)) {
            return false;
        }
        return ((StarosnoDoba) obj).getIdStarosnoDoba().equals(this.idStarosnoDoba);
    }

}
