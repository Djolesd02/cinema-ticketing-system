/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domain.Racun;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Djole
 */
public class TableModelRacuni extends AbstractTableModel {

    private ArrayList<Racun> lista = new ArrayList<>();
    private String[] kolone = {"ID", "Musterija", "Kasir", "Datum i vreme", "Ukupna cena", "Storniran"};

    public TableModelRacuni() {
    }

    public void postaviListu(ArrayList<Racun> lista) {
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
        Racun r = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        switch (column) {
            case 0:
                return r.getIdRacun();
            case 1:
                return r.getMusterija();
            case 2:
                return r.getKasir();
            case 3:
                return sdf.format(r.getDatumVreme());
            case 4:
                return String.format("%.2f din", r.getUkupnaCena());
            case 5:
                return r.isStorniran() ? "DA" : "NE";

            default:
                return null;
        }
    }

    public Racun getSelectedRacun(int row) {
        return lista.get(row);
    }

}
