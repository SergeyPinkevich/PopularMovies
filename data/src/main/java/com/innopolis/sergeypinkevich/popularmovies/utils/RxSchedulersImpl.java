package com.innopolis.sergeypinkevich.popularmovies.utils;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Sergey Pinkevich
 */

public class RxSchedulersImpl implements RxScheduler {

    @Inject
    public RxSchedulersImpl() {}

    @Override
    public Scheduler getMain() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler getNetwork() {
        return Schedulers.io();
    }

    @Override
    public Scheduler getDatabase() {
        return Schedulers.io();
    }
}
