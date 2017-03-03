package com.zeller.fastlibrary.dagger2.lesson2;

import com.zeller.fastlibrary.dagger2.Dagger2Activity;
import com.zeller.fastlibrary.dagger2.lesson3.PoetryModule;
import com.zeller.fastlibrary.dagger2.lesson3.PoetryScope;

import dagger.Component;

/**
 * Created by Zellerpooh on 17/2/3.
 */
@PoetryScope
@Component(modules = {MainModule.class, PoetryModule.class})
public abstract class MainComponent {
    /**
     * 需要用到这个连接器的对象，就是这个对象里面有需要注入的属性
     * （被标记为@Inject的属性）
     * 这里inject表示注入的意思，这个方法名可以随意更改，但建议就
     * 用inject即可。
     */
    public abstract void inject(Dagger2Activity activity);

    public abstract void inject(OtherActivity activity);

    private static MainComponent sComponent;

    public static MainComponent getInstance() {
        if (sComponent == null) {
            sComponent = DaggerMainComponent.builder().build();
        }
        return sComponent;
    }
}
