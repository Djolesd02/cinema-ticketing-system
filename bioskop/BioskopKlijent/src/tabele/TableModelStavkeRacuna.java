/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domain.StavkaRacuna;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Djole
 */
public class TableModelStavkeRacuna extends AbstractTableModel {

    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Projekcija", "Cena", "Kolicina", "Iznos"};
    private int rb = 0;

    public TableModelStavkeRacuna() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        lista = stavkeRacuna;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getProjekcija().getNazivFilma();
            case 2:
                return String.format("%.2f din", sr.getProjekcija().getCena());
            case 3:
                return sr.getKolicina();
            case 4:
                return String.format("%.2f din", sr.getIznos());

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaRacuna sr) {

        for (StavkaRacuna stavkaRacuna : lista) {
            if (stavkaRacuna.getProjekcija().getIdProjekcija().equals(sr.getProjekcija().getIdProjekcija())) {
                stavkaRacuna.setKolicina(stavkaRacuna.getKolicina() + sr.getKolicina());
                stavkaRacuna.setIznos(stavkaRacuna.getKolicina() * stavkaRacuna.getProjekcija().getCena());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sr.setRb(++rb);
        lista.add(sr);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaRacuna stavkaRacuna : lista) {
            stavkaRacuna.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaRacuna stavkaRacuna : lista) {
            ukupanIznos += stavkaRacuna.getIznos();
        }

        return ukupanIznos;
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

}
