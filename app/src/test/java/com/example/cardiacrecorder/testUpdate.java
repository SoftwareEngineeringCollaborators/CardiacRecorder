package com.example.cardiacrecorder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

public class testUpdate {
    @Test
    public void testUpdate()
    {
        dbmanager dbmanager=new dbmanager(RuntimeEnvironment.application);

        String hr,s,d,c,dt;
        hr="123";s="60";d="80";
        c="This is not good";
        dt=""+System.currentTimeMillis();
        long dataid=dbmanager.addData(hr,s,d,c,dt);
        int idataid=(int)dataid;

        String hr2,s2,d2,c2,dt2;
        hr2="111";s2="55";d2="77";
        c2="Updated comment";
        dt2=""+System.currentTimeMillis();
        long dataidUpdated=dbmanager.updateData(idataid,hr2,s2,d2,c2,dt2);

        assertTrue(dataidUpdated>0);
        dbmanager.close();

    }
}
