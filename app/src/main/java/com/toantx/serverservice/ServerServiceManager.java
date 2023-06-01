package com.toantx.serverservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class ServerServiceManager {
    private static final String TAG = "TXTX-ServerServiceManager";

    private static final String SERVER_PACKAGE_NAME = "com.toantx.serverservice";
    private static final String SERVER_CLASS_NAME = "com.toantx.serverservice.MyServerService";

    private final Context mContext;
    private IServerServiceManager mService;
    private boolean mBound;

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: " + name + " - " + service);
            mService = IServerServiceManager.Stub.asInterface(service);
            Log.i(TAG, "onServiceConnected mService: " + mService);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: " + name);
            mService = null;
            mBound = false;
        }
    };

    public ServerServiceManager(Context context) {
        Log.i(TAG, "ServerServiceManager constructor()");
        mContext = context;
        mBound = false;
    }

    public boolean bindService() {
        if (mBound) {
            return true;
        }
        Intent intent = new Intent();
        intent.setClassName(SERVER_PACKAGE_NAME, SERVER_CLASS_NAME);
        return mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService() {
        if (!mBound) {
            return;
        }
        mContext.unbindService(mConnection);
    }

    public String generateId(String name, int age) throws RemoteException {
        if (boundToService()) {
            return mService.generateId(name, age);
        }
        return "";
    }

    public MyPoint fastDoublePoint(MyPoint point) throws RemoteException {
        if (boundToService()) {
            return mService.fastDoublePoint(point);
        }
        return null;
    }

    public void lowTriplePoint(MyPoint point, MyResultListener listener) throws RemoteException {
        if (boundToService()) {
            mService.lowTriplePoint(point, listener);
        }
    }

    private boolean boundToService() {
        if (!mBound) {
            Log.i(TAG, "Service is not bound");
            return false;
        }
        return true;
    }
}
