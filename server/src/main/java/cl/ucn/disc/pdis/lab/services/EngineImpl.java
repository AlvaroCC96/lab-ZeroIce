/*
 * Copyright (c) 2020 Diego Urrutia-Astorga. http://durrutia.cl.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package cl.ucn.disc.pdis.lab.services;

import cl.ucn.disc.pdis.lab.zeroice.model.Engine;
import com.zeroc.Ice.Current;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The implementation of {@link cl.ucn.disc.pdis.lab.zeroice.model.Engine}.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class EngineImpl implements Engine {

    /**
     * @see Engine#getDate(Current)
     */
    @Override
    public String getDate(Current current) {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    /**
     * Checked rut
     * @return DV
     * */
    @Override
    public String getCheckDigit(String rut, Current current) {

        try {

            rut = rut.replace(".", "");
            rut = rut.replace("-", "");

            if (rut.contains("[a-zA-Z]+")) {
                return "";
            }

            String rutReverse = "";
            for(int i = rut.length() - 1; i >= 0; i--) {
                rutReverse = rutReverse + rut.charAt(i);
            }
            int aux =2;
            int value =0;
            for (int i =0; i < rut.length(); i++) {
                value = value + ( Integer.parseInt(String.valueOf(rutReverse.charAt(i))) * aux );
                aux++;
                if (aux > 7) {
                    aux = 2;
                }
            }

            aux = (int) value/11;
            int aux2 = aux*11;
            int dif= value - aux2;
            int dv = 11-dif;
            if (dv == 11 ) {
                return "0";
            } else if ( dv == 10) {
                return "K";
            } else {
                return String.valueOf(dv);
            }

        } catch (NumberFormatException e) {
            return "Formato del rut mal ingresado";
        } catch (NullPointerException e ){
            return "rut is null";
        }

    }

}
