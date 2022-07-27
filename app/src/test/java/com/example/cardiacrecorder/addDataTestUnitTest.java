package com.example.cardiacrecorder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


@RunWith(RobolectricTestRunner.class)
public class addDataTestUnitTest {
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

}
