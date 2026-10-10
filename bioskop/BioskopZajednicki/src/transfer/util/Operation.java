/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transfer.util;

/**
 *
 * @author Djole
 */
public interface Operation {
    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_MUSTERIJA = 2;
    public static final int DELETE_MUSTERIJA = 3;
    public static final int UPDATE_MUSTERIJA = 4;
    public static final int GET_ALL_MUSTERIJA = 5;

    public static final int ADD_PROJEKCIJA = 6;
    public static final int DELETE_PROJEKCIJA = 7;
    public static final int UPDATE_PROJEKCIJA = 8;
    public static final int GET_ALL_PROJEKCIJA = 9;

    public static final int ADD_RACUN = 10;
    public static final int CANCEL_RACUN = 11;
    public static final int UPDATE_RACUN = 12;
    public static final int GET_ALL_RACUN = 13;

    public static final int GET_ALL_STAROSNODOBA = 14;

    public static final int PRETRAZI_MUSTERIJA = 15;
    public static final int PRETRAZI_PROJEKCIJA = 16;
    public static final int PRETRAZI_RACUN = 17;
}
