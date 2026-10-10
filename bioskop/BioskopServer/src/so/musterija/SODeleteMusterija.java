/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.musterija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Musterija;
import so.AbstractSO;

/**
 *
 * @author Djole
 */
public class SODeleteMusterija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Musterija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Musterija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
