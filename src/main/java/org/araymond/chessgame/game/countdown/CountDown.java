package org.araymond.chessgame.game.countdown;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Anthony on 12/07/2015.
 */
public class CountDown {
    private final int countDownTimeout;
    private final CountDownEndAction countDownAction;
    private final Lock lock;
    private ScheduledFuture future;

    public CountDown(CountDownEndAction countDownAction, int countDownTimeout) {
        this.countDownTimeout = countDownTimeout;
        this.countDownAction = countDownAction;
        this.lock = new ReentrantLock();
    }

    public void end() {
        if (future == null) {
            return;
        }
        this.lock.lock();
        try {
            if (future != null) {
                future.cancel(true);
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void restart() {
        this.lock.lock();
        try {
            if (future != null) {
                future.cancel(true);
            }
            future = Executors.newSingleThreadScheduledExecutor().schedule(new InternalCountDownAction(), countDownTimeout, TimeUnit.MILLISECONDS);
        } finally {
            this.lock.unlock();
        }
    }

    private class InternalCountDownAction implements Runnable {
        @Override
        public void run() {
            countDownAction.onCountDownEnds();
        }
    }
}
