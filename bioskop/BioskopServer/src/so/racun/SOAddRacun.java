/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

/**
 *
 * @author Djole
 */
public class SOAddRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }

        Racun r = (Racun) ado;

        if (r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati barem jednu stavku!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long noviRacunID = tableKeys.getLong(1);

        Racun noviRacun = (Racun) ado;
        noviRacun.setIdRacun(noviRacunID);

        for (StavkaRacuna stavkaRacuna : noviRacun.getStavkeRacuna()) {
            stavkaRacuna.setRacun(noviRacun);
            DBBroker.getInstance().insert(stavkaRacuna);
        }
    }

}
