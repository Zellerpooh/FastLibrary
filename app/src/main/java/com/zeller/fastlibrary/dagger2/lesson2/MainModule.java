package com.zeller.fastlibrary.dagger2.lesson2;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zellerpooh on 17/2/3.
 */
@Module
public class MainModule {
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
