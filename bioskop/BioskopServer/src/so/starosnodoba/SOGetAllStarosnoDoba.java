/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.starosnodoba;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StarosnoDoba;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Djole
 */
public class SOGetAllStarosnoDoba extends AbstractSO {

    private ArrayList<StarosnoDoba> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StarosnoDoba)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StarosnoDoba!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> sd = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StarosnoDoba>) (ArrayList<?>) sd;
    }

    public ArrayList<StarosnoDoba> getLista() {
        return lista;
    }

}
