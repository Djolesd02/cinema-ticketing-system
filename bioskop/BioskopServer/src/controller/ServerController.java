/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Kasir;
import domain.Musterija;
import domain.Projekcija;
import domain.Racun;
import domain.StarosnoDoba;
import java.util.ArrayList;
import so.login.SOLogin;
import so.musterija.SOAddMusterija;
import so.musterija.SODeleteMusterija;
import so.musterija.SOGetAllMusterija;
import so.musterija.SOUpdateMusterija;
import so.projekcija.SOAddProjekcija;
import so.projekcija.SODeleteProjekcija;
import so.projekcija.SOGetAllProjekcija;
import so.projekcija.SOUpdateProjekcija;
import so.racun.SOAddRacun;
import so.racun.SOCancelRacun;
import so.racun.SOGetAllRacun;
import so.racun.SOUpdateRacun;
import so.starosnodoba.SOGetAllStarosnoDoba;

/**
 *
 * @author Djole
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Kasir> ulogovaniKasiri = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Kasir> getUlogovaniKasiri() {
        return ulogovaniKasiri;
    }

    public void setUlogovaniKasiri(ArrayList<Kasir> ulogovaniKasiri) {
        this.ulogovaniKasiri = ulogovaniKasiri;
    }

    public Kasir login(Kasir kasir) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(kasir);
        return so.getUlogovani();
    }

    public void logout(Kasir ulogovani) {
        ulogovaniKasiri.remove(ulogovani);
    }

    public void addMusterija(Musterija musterija) throws Exception {
        (new SOAddMusterija()).templateExecute(musterija);
    }

    public void updateMusterija(Musterija musterija) throws Exception {
        (new SOUpdateMusterija()).templateExecute(musterija);
    }

    public void deleteMusterija(Musterija musterija) throws Exception {
        (new SODeleteMusterija()).templateExecute(musterija);
    }

    public ArrayList<Musterija> getAllMusterija() throws Exception {
        SOGetAllMusterija so = new SOGetAllMusterija();
        so.templateExecute(new Musterija());
        return so.getLista();
    }

    public void addProjekcija(Projekcija projekcija) throws Exception {
        (new SOAddProjekcija()).templateExecute(projekcija);
    }

    public void updateProjekcija(Projekcija projekcija) throws Exception {
        (new SOUpdateProjekcija()).templateExecute(projekcija);
    }

    public void deleteProjekcija(Projekcija projekcija) throws Exception {
        (new SODeleteProjekcija()).templateExecute(projekcija);
    }

    public ArrayList<Projekcija> getAllProjekcija() throws Exception {
        SOGetAllProjekcija so = new SOGetAllProjekcija();
        so.templateExecute(new Projekcija());
        return so.getLista();
    }

    public void addRacun(Racun racun) throws Exception {
        (new SOAddRacun()).templateExecute(racun);
    }

    public void cancelRacun(Racun racun) throws Exception {
        (new SOCancelRacun()).templateExecute(racun);
    }

    public void updateRacun(Racun racun) throws Exception {
        (new SOUpdateRacun()).templateExecute(racun);
    }

    public ArrayList<Racun> getAllRacun(Musterija musterija) throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();

        Racun r = new Racun();
        r.setMusterija(musterija);

        so.templateExecute(r);
        return so.getLista();
    }

    public ArrayList<StarosnoDoba> getAllStarosnoDoba() throws Exception {
        SOGetAllStarosnoDoba so = new SOGetAllStarosnoDoba();
        so.templateExecute(new StarosnoDoba());
        return so.getLista();
    }

    public ArrayList<Musterija> pretraziMusterija(Musterija kriterijum) throws Exception {
        SOGetAllMusterija so = new SOGetAllMusterija();
        so.templateExecute(kriterijum);
        return so.getLista();
    }

    public ArrayList<Projekcija> pretraziProjekcija(Projekcija kriterijum) throws Exception {
        SOGetAllProjekcija so = new SOGetAllProjekcija();
        so.templateExecute(kriterijum);
        return so.getLista();
    }

    public ArrayList<Racun> pretraziRacun(Racun kriterijum) throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(kriterijum);
        return so.getLista();
    }

}
