/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.projekcija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Projekcija;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Djole
 */
public class SOGetAllProjekcija extends AbstractSO {

    private ArrayList<Projekcija> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Projekcija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Projekcija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> projekcije = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Projekcija>) (ArrayList<?>) projekcije;
    }

    public ArrayList<Projekcija> getLista() {
        return lista;
    }

}
