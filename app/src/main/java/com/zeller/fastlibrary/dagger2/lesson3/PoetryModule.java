package com.zeller.fastlibrary.dagger2.lesson3;

import com.zeller.fastlibrary.dagger2.Poetry;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zellerpooh on 17/2/3.
 */
@Module
public class PoetryModule {

    @PoetryScope
    @Provides
    public Poetry providePoetry(String poems) {
        return new Poetry(poems);
    }

    // 这里提供了一个生成String的方法，在这个Module里生成Poetry实例时，会查找到这里
    // 可以为上面提供String类型的参数
    @Provides
    public String providePoems() {
        return "只有意志坚强的人，才能到达彼岸";
    }
}
