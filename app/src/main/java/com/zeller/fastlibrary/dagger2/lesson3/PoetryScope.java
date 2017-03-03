package com.zeller.fastlibrary.dagger2.lesson3;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Zellerpooh on 17/2/3.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PoetryScope {
}
