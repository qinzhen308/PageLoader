package com.paulz.pagingload;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.paulz.pagingload.stateview.LoadStatusViewDecoratee;

import java.util.List;


/**
 * Created by Administrator on 2017/5/16.
 */

public class TestActivity extends Activity {
    DataPageLoader<Object,List<Object>> loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        init();
        request();
    }
    private void init(){
        loader=new DataPageLoader<Object, List<Object>>() {
            LoadStatusViewDecoratee loadState;
            @Override
            public void onRequst(int page) {
                request();
            }

            @Override
            public List<Object> adaptData(List<Object> object) {
                return object;
            }

            @Override
            public void onSuccess(List<Object> curPage, List<Object> all) {

            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
            }

            @Override
            public void prepare() {

            }

        };

    }

    private void initView(){

    }

    private void request(){
    }
}
