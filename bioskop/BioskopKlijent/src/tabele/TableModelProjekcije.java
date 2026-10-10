/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domain.Projekcija;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Djole
 */
public class TableModelProjekcije extends AbstractTableModel {

    private ArrayList<Projekcija> lista = new ArrayList<>();
    private String[] kolone = {"ID", "Naziv filma", "Cena", "Tip projekcije", "Zanr", "Trajanje", "Datum i vreme"};

    public TableModelProjekcije() {
    }

    public void postaviListu(ArrayList<Projekcija> lista) {
        this.lista = lista;
        fireTableDataChanged();
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
        Projekcija p = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        switch (column) {
            case 0:
                return p.getIdProjekcija();
            case 1:
                return p.getNazivFilma();
            case 2:
                return String.format("%.2f", p.getCena());
            case 3:
                return p.getTipProjekcije();
            case 4:
                return p.getZanr();
            case 5:
                return p.getTrajanje() + " min";
            case 6:
                return sdf.format(p.getDatumVreme());

            default:
                return null;
        }
    }

    public Projekcija getSelectedProjekcija(int row) {
        return lista.get(row);
    }

}
