package com.wgl.android.library.base;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Ganlin.Wu on 2016/10/14.
 */
public class BaseService extends Service {
    private ServiceHolder mHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        mHolder = new ServiceHolder(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mHolder;
    }

    public class ServiceHolder extends Binder {
        private Service mService;

        public ServiceHolder(Service service) {
            mService = service;
        }

        public Service getService() {
            return mService;
        }
    }
}
