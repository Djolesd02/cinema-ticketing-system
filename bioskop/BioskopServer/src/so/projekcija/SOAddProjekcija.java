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
public class SOAddProjekcija extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Projekcija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Projekcija!");
        }

        Projekcija p = (Projekcija) ado;

        if (p.getCena() <= 0) {
            throw new Exception("Cena mora biti veca od nule!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
