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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Djole
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Kasir login(Kasir kasir) throws Exception {
        return (Kasir) sendRequest(Operation.LOGIN, kasir);
    }

    public void logout(Kasir ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addMusterija(Musterija musterija) throws Exception {
        sendRequest(Operation.ADD_MUSTERIJA, musterija);
    }

    public void updateMusterija(Musterija musterija) throws Exception {
        sendRequest(Operation.UPDATE_MUSTERIJA, musterija);
    }

    public void deleteMusterija(Musterija musterija) throws Exception {
        sendRequest(Operation.DELETE_MUSTERIJA, musterija);
    }

    public ArrayList<Musterija> getAllMusterija() throws Exception {
        return (ArrayList<Musterija>) sendRequest(Operation.GET_ALL_MUSTERIJA, null);
    }

    public void addProjekcija(Projekcija projekcija) throws Exception {
        sendRequest(Operation.ADD_PROJEKCIJA, projekcija);
    }

    public void updateProjekcija(Projekcija projekcija) throws Exception {
        sendRequest(Operation.UPDATE_PROJEKCIJA, projekcija);
    }

    public void deleteProjekcija(Projekcija projekcija) throws Exception {
        sendRequest(Operation.DELETE_PROJEKCIJA, projekcija);
    }

    public ArrayList<Projekcija> getAllProjekcija() throws Exception {
        return (ArrayList<Projekcija>) sendRequest(Operation.GET_ALL_PROJEKCIJA, null);
    }

    public void addRacun(Racun racun) throws Exception {
        sendRequest(Operation.ADD_RACUN, racun);
    }

    public void cancelRacun(Racun racun) throws Exception {
        sendRequest(Operation.CANCEL_RACUN, racun);
    }

    public void updateRacun(Racun racun) throws Exception {
        sendRequest(Operation.UPDATE_RACUN, racun);
    }

    public ArrayList<Racun> getAllRacun(Musterija musterija) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.GET_ALL_RACUN, musterija);
    }

    public ArrayList<StarosnoDoba> getAllStarosnoDoba() throws Exception {
        return (ArrayList<StarosnoDoba>) sendRequest(Operation.GET_ALL_STAROSNODOBA, null);
    }

    public ArrayList<Musterija> pretraziMusterija(Musterija kriterijum) throws Exception {
        return (ArrayList<Musterija>) sendRequest(Operation.PRETRAZI_MUSTERIJA, kriterijum);
    }

    public ArrayList<Projekcija> pretraziProjekcija(Projekcija kriterijum) throws Exception {
        return (ArrayList<Projekcija>) sendRequest(Operation.PRETRAZI_PROJEKCIJA, kriterijum);
    }

    public ArrayList<Racun> pretraziRacun(Racun kriterijum) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.PRETRAZI_RACUN, kriterijum);
    }

    private synchronized Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }
    }

}
