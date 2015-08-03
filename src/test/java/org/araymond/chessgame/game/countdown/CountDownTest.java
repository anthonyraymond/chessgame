package org.araymond.chessgame.game.countdown;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anthony on 13/07/2015.
 */
public class CountDownTest {

    private List<Integer> list;

    @Before
    public void setUp() {
        this.list = new ArrayList<>();
    }

    @After
    public void tearDown() {
        this.list.clear();
    }

    @Test(timeout = 200)
    public void shouldNotFailOnConcurrentAccess() throws InterruptedException, ExecutionException {
        int threadCount = 100;
        CountDown countDown = new CountDown(new DefaultCountDownAction(), 20);
        Collection<Callable<String>> threads = new ArrayList<>(threadCount);
        Collection<Future> futures = new ArrayList<>();

        // create 100 thread that will reset countdown.
        for (int i = 0; i < threadCount; ++i) {
            threads.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    countDown.restart();
                    return "i don't care about the return value.";
                }
            });
        }

        //start the countdown
        countDown.restart();
        // launch all thread (in a poll of 10).
        futures.addAll(Executors.newFixedThreadPool(10).invokeAll(threads));

        // wait for all thread to over.
        for (Future future : futures) {
            future.get();
        }

        Thread.sleep(50);
        countDown.end();
        // The timeout must end once and only once when a reset is multiple times by threads at the same time.
        assertThat(list).hasSize(1);
    }

    @Test
    public void shouldNotCrashWhenCallingEndWithoutEverStarted() {
        new CountDown(new DefaultCountDownAction(), 10).end();
    }

    private class DefaultCountDownAction implements CountDownEndAction {

        @Override
        public void onCountDownEnds() {
            list.add(129);
        }
    }
}
