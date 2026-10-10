/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import controller.ServerController;
import domain.Kasir;
import domain.Musterija;
import domain.Projekcija;
import domain.Racun;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author Djole
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.LOGIN:
                    Kasir kasir = (Kasir) request.getData();
                    Kasir ulogovani = ServerController.getInstance().login(kasir);
                    response.setData(ulogovani);
                    break;
                case Operation.LOGOUT:
                    ServerController.getInstance().logout((Kasir) request.getData());
                    break;
                case Operation.ADD_MUSTERIJA:
                    ServerController.getInstance().addMusterija((Musterija) request.getData());
                    break;
                case Operation.UPDATE_MUSTERIJA:
                    ServerController.getInstance().updateMusterija((Musterija) request.getData());
                    break;
                case Operation.DELETE_MUSTERIJA:
                    ServerController.getInstance().deleteMusterija((Musterija) request.getData());
                    break;
                case Operation.GET_ALL_MUSTERIJA:
                    response.setData(ServerController.getInstance().getAllMusterija());
                    break;
                case Operation.ADD_PROJEKCIJA:
                    ServerController.getInstance().addProjekcija((Projekcija) request.getData());
                    break;
                case Operation.UPDATE_PROJEKCIJA:
                    ServerController.getInstance().updateProjekcija((Projekcija) request.getData());
                    break;
                case Operation.DELETE_PROJEKCIJA:
                    ServerController.getInstance().deleteProjekcija((Projekcija) request.getData());
                    break;
                case Operation.GET_ALL_PROJEKCIJA:
                    response.setData(ServerController.getInstance().getAllProjekcija());
                    break;
                case Operation.ADD_RACUN:
                    ServerController.getInstance().addRacun((Racun) request.getData());
                    break;
                case Operation.CANCEL_RACUN:
                    ServerController.getInstance().cancelRacun((Racun) request.getData());
                    break;
                case Operation.UPDATE_RACUN:
                    ServerController.getInstance().updateRacun((Racun) request.getData());
                    break;
                case Operation.GET_ALL_RACUN:
                    response.setData(ServerController.getInstance().getAllRacun((Musterija) request.getData()));
                    break;
                case Operation.GET_ALL_STAROSNODOBA:
                    response.setData(ServerController.getInstance().getAllStarosnoDoba());
                    break;
                case Operation.PRETRAZI_MUSTERIJA:
                    response.setData(ServerController.getInstance().pretraziMusterija((Musterija) request.getData()));
                    break;
                case Operation.PRETRAZI_PROJEKCIJA:
                    response.setData(ServerController.getInstance().pretraziProjekcija((Projekcija) request.getData()));
                    break;
                case Operation.PRETRAZI_RACUN:
                    response.setData(ServerController.getInstance().pretraziRacun((Racun) request.getData()));
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
