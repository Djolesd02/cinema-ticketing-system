/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabele;

import domain.Musterija;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Djole
 */
public class TableModelMusterije extends AbstractTableModel {

    private ArrayList<Musterija> lista = new ArrayList<>();
    private String[] kolone = {"ID", "Ime", "Prezime", "Ime i prezime", "Email", "Telefon", "Starosno doba"};

    public TableModelMusterije() {
    }

    public void postaviListu(ArrayList<Musterija> lista) {
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
        Musterija m = lista.get(row);

        switch (column) {
            case 0:
                return m.getIdMusterija();
            case 1:
                return m.getIme();
            case 2:
                return m.getPrezime();
            case 3:
                return m.getImePrezime();
            case 4:
                return m.getEmail();
            case 5:
                return m.getKontaktTelefon();
            case 6:
                return m.getStarosnoDoba();

            default:
                return null;
        }
    }

    public Musterija getSelectedMusterija(int row) {
        return lista.get(row);
    }

}
