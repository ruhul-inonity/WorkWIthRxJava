package com.inonitylab.workwithrxandroid;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/*
* tutorial : https://code.tutsplus.com/tutorials/getting-started-with-rxjava-20-for-android--cms-28345?_ga=2.32740286.1857810055.1499605407-1676444021.1497890239
* */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> observableEmitter) throws Exception {
                //use onNext to emit each item in the stream
                       observableEmitter.onNext(1);
                       observableEmitter.onNext(2);
                       observableEmitter.onNext(3);
                       observableEmitter.onNext(4);
                //once the Observable has emitted al the items in sequence, call onComplete
                observableEmitter.onComplete();
            }
        });

*/

        //just operator to convert any object into an observable
        //result observable will emit the original object and complete
        Observable<String> observable = Observable.just("Hello World!");

        //convert any array into an Observable using Observable.fromArray
        //convert Callable into an Observable using Observable.fromCallable
        //convert Iterable into an Observable using using Observable.fromIterable

        //use the range() operator to emit a range of sequential integers
        Observable<Integer> observableRange = Observable.range(0, 7);

        //interval() operator creates an Observable that emits an infinite sequence of ascending integers
        Observable<Long> observableInterval = Observable.interval(1, TimeUnit.SECONDS);

        // empty() operator creates an Observable that emtis no items but terminates normally
        // useful for creating quick Observable
        Observable<String> observableEmpty = Observable.empty();


        //Observer for Long type
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull Long integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: All done!");
            }
        };

        //Observer for String type
        Observer<String> observer1 = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: All done!");
            }
        };

        //create subscription
        //observable.subscribe(observer1);
        //observableRange.subscribe(observer);
        observableInterval.subscribe(observer);

    }
}
