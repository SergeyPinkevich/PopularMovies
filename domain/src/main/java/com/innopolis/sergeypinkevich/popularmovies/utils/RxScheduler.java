package com.innopolis.sergeypinkevich.popularmovies.utils;

import io.reactivex.Scheduler;

/**
 * @author Sergey Pinkevich
 */

public interface RxScheduler {

    Scheduler getMain();

    Scheduler getNetwork();

    Scheduler getDatabase();
}
