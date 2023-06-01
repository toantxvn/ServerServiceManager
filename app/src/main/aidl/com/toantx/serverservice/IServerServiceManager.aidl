
package com.toantx.serverservice;

import com.toantx.serverservice.MyPoint;
import com.toantx.serverservice.MyResultListener;

interface IServerServiceManager {
    String generateId(String name, int age);
    MyPoint fastDoublePoint(in MyPoint point);
    void lowTriplePoint(in MyPoint point, MyResultListener listener);
}