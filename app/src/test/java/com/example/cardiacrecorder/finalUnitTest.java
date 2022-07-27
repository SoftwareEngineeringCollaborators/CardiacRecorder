package com.example.cardiacrecorder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

@RunWith(RobolectricTestRunner.class)
public class finalUnitTest {
    @Test
    public void addDataTest()
    {
        dbmanager dbmanager=new dbmanager(RuntimeEnvironment.application);
        String hr,s,d,c,dt;
        hr="123";s="60";d="80";
        c="This is not good";
        dt=""+System.currentTimeMillis();
        long dataid=dbmanager.addData(hr,s,d,c,dt);
        assertTrue(dataid>0);
        dbmanager.close();
    }



    @Test
    public void testDelete()
    {
        dbmanager dbmanager=new dbmanager(RuntimeEnvironment.application);

        String hr,s,d,c,dt;
        hr="123";s="60";d="80";
        c="This is not good";
        dt=""+System.currentTimeMillis();
        long dataid=dbmanager.addData(hr,s,d,c,dt);
        int intdataid=(int)dataid;
        long p=dbmanager.deleteData(intdataid);
        assertTrue(p>0);
        dbmanager.close();
    }

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
