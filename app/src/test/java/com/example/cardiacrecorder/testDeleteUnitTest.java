package com.example.cardiacrecorder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
/**
 * data deletion test
 */
@RunWith(RobolectricTestRunner.class)


public class testDeleteUnitTest {
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

}
