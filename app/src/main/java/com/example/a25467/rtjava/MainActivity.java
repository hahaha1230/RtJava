package com.example.a25467.rtjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            //将事件发射出去,持有观察者的对象
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    Thread.sleep(10000);
                } catch (Exception ee) {

                }
                e.onNext("第一次调用");
                try {
                    Thread.sleep(10000);
                } catch (Exception ee) {

                }
                e.onNext("第二次调用");
                try {
                    Thread.sleep(10000);
                } catch (Exception ee) {

                }
                e.onNext("第三次调用");

                e.onComplete();
            }
        });

        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}