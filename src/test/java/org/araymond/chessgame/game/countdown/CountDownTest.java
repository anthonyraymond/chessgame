package org.araymond.chessgame.game.countdown;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/07/2015.
 */
public class CountDownTest {
    private List<Integer> list = new ArrayList<>();

    @Test(timeout = 5000)
    public void shouldNotFailOnConcurrentAccess() throws InterruptedException, ExecutionException {
        CountDown countDown = new CountDown(new DefaultCountDownAction(), 10);
        Collection<Future> futures = new ArrayList<>();

        countDown.restart();
        for (int i = 0; i < 100; ++i) {
            futures.add(Executors.newSingleThreadExecutor().submit(new Runnable() {
                @Override
                public void run() {
                    countDown.restart();
                }
            }));
        }

        for (Future future : futures) {
            future.get();
        }
        Thread.sleep(20);
        countDown.end();
        assertThat(list).hasSize(1);
    }

    @Test
    public void shouldNotCrashWhenCallingEndWithoutEverStarted() {
        new CountDown(new DefaultCountDownAction(), 10).end();
    }

    private class DefaultCountDownAction implements CountDownEndAction {

        @Override
        public void onCountDownEnds() {
            list.add(1);
        }
    }
}
