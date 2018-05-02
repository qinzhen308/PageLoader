package com.paulz.pagingload;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Paul Z on 2017/5/15.
 * 只负责分页数据累积（或者其他），以及记录页码
 * 给子类开放网络请求函数实现
 * 流程：
 * pagelistener--->paging---->request
 *
 * 考虑到不同项目或请求实例中可能用到的callback不同，因此在这里只能保留对于联网请求处理的逻辑；
 * 对于他的子类，要实现对于联网请求的处理逻辑，并调用抽象方法onRequest,onsuccess,onfailed
 */

public abstract class DataPageLoader<B,T> implements PageChangeListener {

    public final int PAGE_FIRST = 1;
    public final int PAGE_SIZE = 10;

    protected int curPage=PAGE_FIRST;

    protected List<B> mList;

    protected boolean isLoading;

    public DataPageLoader() {
        mList=new ArrayList<>();
        prepare();
    }

    @Override
    public void next(){
        if(isLoading)return;
        loadPage(curPage++);
    }

    public boolean isLoading(){
        return isLoading;
    }

    //建议不开放加载某一页，会导致分页逻辑的错乱
    private void loadPage(int page){
        isLoading=true;
        if(page<1){
            //因为这里的处理，不用担心第一页加载失败后，curpage--后为0的问题
            curPage=page=1;
        }
        onRequst(page);
    }

    //重新刷新，无论失败与否，都清空数据
    @Override
    public void refresh(){
        if(isLoading)return;
        curPage=1;
        mList.clear();
        loadPage(curPage);
    }

    /**
     *
     * @param page 需要加载的页码
     */
    public abstract void onRequst(int page);




    /**
     * 把源数据处理为每页数据
     * @param object 源数据
     * @return 当前页加载回的数据
     */
    public abstract List<B> adaptData(T object);

    public abstract void onSuccess(List<B> curPage , List<B> all);

    public abstract void onFailed(String errorCode, String errorMessage);

    /**
     * 建议：
     * 我们希望创建本对象之后，它就具有分页加载的功能，但又不希望此类和具体的view和其监听耦合到一起
     * 例如，把listview.setOnScrollListener(...)写到这里
     */
    public abstract void prepare();

}
