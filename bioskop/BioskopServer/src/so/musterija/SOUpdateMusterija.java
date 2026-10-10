/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.musterija;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Musterija;
import java.util.ArrayList;
import java.util.regex.Pattern;
import so.AbstractSO;

/**
 *
 * @author Djole
 */
public class SOUpdateMusterija extends AbstractSO {

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern TELEFON_PATTERN
            = Pattern.compile("^06[0-9]{6,8}$");

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Musterija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Musterija!");
        }

        Musterija m = (Musterija) ado;

        if (!EMAIL_PATTERN.matcher(m.getEmail()).matches()) {
            throw new Exception("Email nije u ispravnom formatu!");
        }

        if (!TELEFON_PATTERN.matcher(m.getKontaktTelefon()).matches()) {
            throw new Exception("Telefon mora biti u formatu 06XXXXXXXX!");
        }

        ArrayList<Musterija> musterije = (ArrayList<Musterija>) (ArrayList<?>) DBBroker.getInstance().select(new Musterija());

        for (Musterija musterija : musterije) {
            if (!musterija.getIdMusterija().equals(m.getIdMusterija())) {
                if (musterija.getEmail().equals(m.getEmail())) {
                    throw new Exception("Musterija sa tim emailom vec postoji u bazi!");
                }
                if (musterija.getKontaktTelefon().equals(m.getKontaktTelefon())) {
                    throw new Exception("Musterija sa tim telefonom vec postoji u bazi!");
                }
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
