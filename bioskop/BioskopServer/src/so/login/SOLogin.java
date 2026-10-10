/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kasir;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Djole
 */
public class SOLogin extends AbstractSO {

    Kasir ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kasir)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kasir!");
        }

        Kasir k = (Kasir) ado;

        for (Kasir kasir : ServerController.getInstance().getUlogovaniKasiri()) {
            if (kasir.getKorisnickoIme().equals(k.getKorisnickoIme())) {
                throw new Exception("Ovaj kasir je vec ulogovan na sistem!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Kasir k = (Kasir) ado;

        ArrayList<Kasir> kasiri
                = (ArrayList<Kasir>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Kasir kasir : kasiri) {
            if (kasir.getKorisnickoIme().equals(k.getKorisnickoIme())
                    && kasir.getSifra().equals(k.getSifra())) {
                ulogovani = kasir;
                ServerController.getInstance().getUlogovaniKasiri().add(kasir);
                return;
            }
        }

        throw new Exception("Ne postoji kasir sa tim kredencijalima.");
    }

    public Kasir getUlogovani() {
        return ulogovani;
    }

}
