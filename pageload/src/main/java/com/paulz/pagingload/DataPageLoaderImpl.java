package com.paulz.pagingload;

import java.util.List;

/**
 * Created by pualbeben on 17/5/17.
 */

public abstract class DataPageLoaderImpl<B,T> extends DataPageLoader<B,T> implements ApiCallBack<T>{

    @Override
    public final void onSuccess(T object) {
        List<B> curPageData=adaptData(object);
        if(curPageData!=null&&curPageData.size()>0){
            mList.addAll(curPageData);
        }else {
            curPage--;
        }
        onSuccess(curPageData,mList);
        isLoading=false;
    }

    public final void onFailure(String errorCode, String errorMessage) {
        curPage--;
        onFailed(errorCode,errorMessage);
        isLoading=false;
    }


}
