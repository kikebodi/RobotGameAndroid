package com.kikebodi.assignment;

import com.kikebodi.assignment.objects.Robot;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class TDD {

    @Test
    public void getRobot(){
        Robot myRobot = new Robot(0,0,"N");
        Assert.assertNotNull(myRobot);
    }
}
