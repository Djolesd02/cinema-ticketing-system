/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.projekcija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Projekcija;
import so.AbstractSO;

/**
 *
 * @author Djole
 */
public class SODeleteProjekcija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Projekcija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Projekcija!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
