package com.zenfer.demo.base;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.NetworkUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zenfer.demo.R;
import com.zenfer.demo.widget.emptyview.EmptyViewType;

import java.util.ArrayList;
import java.util.List;

import static com.zenfer.demo.Constants.ParamKey.PAGE_SIZE;

public abstract class BaseYsbListActivity<T> extends BaseYsbActivity implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView mRecyclerList;

    public SwipeRefreshLayout refreshLayout;
    /**
     * 数据
     */
    protected List<T> datas;
    /**
     * 适配器
     */
    public TempListAdapter mAdapter;
    /**
     * 是否在 "上拉" 或 "下拉" 中
     */
    protected boolean isRequesting;

    /**
     * 当前 page
     */
    protected int page = 1;
    /**
     * 是否显示"没有更多内容"
     */
    private boolean isShowLoadMoreEnd = true;
    /**
     * 是否第一次滑动
     */
    private boolean isFirstScroll = false;
    /**
     * 获取RecyclerView最后一个可见view的位置
     */
    private int lastItemPosition;
    /**
     * 获取RecyclerView第一个可见view的位置
     */
    private int firstItemPosition;
    /**
     * 是否可以加载更多
     */
    private boolean isCanLoadMore = true;

    /**
     * 获取RecyclerView最后一个可见view的位置
     */
    public int getLastItemPosition() {
        return lastItemPosition;
    }

    /**
     * 获取RecyclerView第一个可见view的位置
     */
    public int getFirstItemPosition() {
        return firstItemPosition;
    }

    @Override
    public int layoutId() {
        return R.layout.base_activity_list;
    }

    @Override
    public void initView(Bundle savedInstanceState, LinearLayout rootView) {
        datas = new ArrayList<>();
        initConfig();
        initViews(rootView);
    }

    @Override
    protected String initTitleContent() {
        return null;
    }

    /**
     * 用于页面布局初始化之前的配置
     */
    public void initConfig() {

    }

    public void initViews(LinearLayout rootView) {
        mRecyclerList = rootView.findViewById(R.id.base_recycler_view);
        refreshLayout = rootView.findViewById(R.id.base_refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerList.setLayoutManager(manager);
        mRecyclerList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!isFirstScroll) {
                    //关闭加载数据的时候设置lastItemPosition
                    isFirstScroll = true;
                }
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    firstItemPosition = linearManager.findFirstVisibleItemPosition();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        initAdapter();
        mRecyclerList.setAdapter(mAdapter);
    }

    private void initAdapter() {
        mAdapter = new TempListAdapter();
        //自定义加载更多Ui
//        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (!isRequesting) {
                    if (isCanLoadMore()) {
                        //加载更多
                        loadMore();
                    } else {
                        //显示 没有更多内容
                        loadMoreEnd();
                    }
                }
            }
        }, mRecyclerList);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BaseYsbListActivity.this.onItemChildClick(adapter, view, position);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseYsbListActivity.this.onItemClick(adapter, view, position);
            }
        });
    }

    /**
     * 获取列表
     */
    public List<T> getListData() {
        return mAdapter.getData();
    }

    /**
     * 是否允许下拉刷新和上拉加载
     *
     * @param enable true 可以,反之不行
     */
    public void enableRefreshAndPull(boolean enable) {
        enableLoadMoreEnd(enable);
        refreshLayout.setEnabled(enable);
        mAdapter.setEnableLoadMore(enable);
    }

    /**
     * 设置是否显示"没有更多内容"的 Bottom
     *
     * @param enable true 显示,反之隐藏
     */
    public void enableLoadMoreEnd(boolean enable) {
        isShowLoadMoreEnd = enable;
    }

    /**
     * 全部加载完 , 显示"没有更多内容"的 Bottom
     */
    public void loadMoreEnd() {
        isCanLoadMore = false;
        isRequesting = false;
        mAdapter.loadMoreEnd(!isShowLoadMoreEnd);
    }

    /**
     * 加载完成
     */
    public void loadMoreComplete() {
        isRequesting = false;
        mAdapter.loadMoreComplete();
    }

    /**
     * 加载失败
     */
    public void loadMoreFail() {
        isRequesting = false;
        mAdapter.loadMoreFail();
    }

    /**
     * 获取头部布局数量
     */
    public int getHeaderViewCount() {
        return mAdapter.getHeaderLayoutCount();
    }

    public void loadMore() {
        loadData();
    }

    /**
     * 是否能加载更多
     *
     * @return true 可以 ,反之不可以
     */
    public boolean isCanLoadMore() {
        return isCanLoadMore;
    }

    public void loadData() {
        isRequesting = true;
    }

    @Override
    public void onRefresh() {
        page = 1;
        isCanLoadMore = true;
        if (!isRequesting) {
            loadData();
        }
    }

    /**
     * 自动下拉刷新,带动画
     */
    public void autoRefresh() {
        if (refreshLayout != null) {
            // 自动下拉刷新
            refreshLayout.setRefreshing(true);
        }
    }

    /**
     * 重新加载列表数据
     *
     * @param datas      数据
     * @param tipsNoTask 无数据提示
     * @return true 数据加载成功,反之失败
     */
    public boolean validPageAndResetData(List<T> datas, String tipsNoTask, String subTitle) {
        page = 1;
        return validPageAndSetData(datas, tipsNoTask, subTitle);
    }

    /**
     * 加载列表数据 有效判断 && 加载数据
     *
     * @param datas      数据
     * @param tipsNoTask 无数据提示
     * @return true 数据加载成功,反之失败
     */
    public boolean validPageAndSetData(List<T> datas, String tipsNoTask, String subTitle) {
        boolean isDataValid = validPage(datas, tipsNoTask, subTitle);
        if (isDataValid) {
            //加载数据
            setListData(datas);
        }
        return isDataValid;
    }

    /**
     * 列表数据 有效判断
     *
     * @param datas      数据
     * @param tipsNoTask 无数据提示
     * @return true 数据有效,反之无效
     */
    public boolean validPage(List<T> datas, String tipsNoTask, String underLineText) {
        refreshComplete();
        // 返回数据为空时的处理
        if (datas == null) {
            if (page == 1) {
                //第一页数据请求时,直接显示无数据情感图
//                showNotContentError(tipsNoTask);
            } else {
                //屏蔽加载更多
                loadMoreEnd();
            }
            return false;
        }

        int size = datas.size();

        if (size <= 0 && page == 1) {
            //数据为空并且是第一页,则显示没有数据的情感图
            showNotContentError(tipsNoTask, underLineText);
            return false;
        }

        if (size <= 0 && page > 1) {
            //上拉加载无法加载更多数据,屏蔽加载更多
            loadMoreEnd();
        }

        if (size < PAGE_SIZE) {
            //请求的数据小于 PAGE_SIZE ,结束数据加载
            loadMoreEnd();
        }

        return true;
    }

    /**
     * 重新设置列表数据
     *
     * @param listData 列表数据
     */
    public void resetListData(@NonNull List<T> listData) {
        page = 1;
        setListData(listData);
    }

    /**
     * 设置列表数据
     *
     * @param listData 列表数据
     */
    public void setListData(@NonNull List<T> listData) {
        if (page++ == 1) {
            datas.clear();
        }
        datas.addAll(listData);
        mAdapter.setNewData(datas);
        mAdapter.disableLoadMoreIfNotFullPage();  // 加载第一页数据,不满一页不显示LoadMoreView,在setNewData之后调用
    }

    public void notifyAdapter() {
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 刷新当前RecyclerView可见区域的View
     */
    public void notifyVisibleRangeChange() {
        int startPosition;
        int itemCount;
        // 为了防止下滑的时候,顶部不可见的item在没有被复用或者销毁,导致界面不刷新,
        // firstItemPosition的判断要往上放大2个position
        if (firstItemPosition > 1) {
            startPosition = firstItemPosition - 2;
        } else {
            startPosition = 0;
        }
        // 为了防止上滑的时候,底部不可见的item在没有被复用或者销毁,导致界面不刷新,
        // lastItemPosition的判断要往下放大2个position
        if (lastItemPosition < datas.size() - 3) {
            itemCount = lastItemPosition - startPosition + 2;
        } else {
            itemCount = datas.size() - 1 - startPosition;
        }
        notifyItemRangeChanged(startPosition, itemCount + 1);
    }

    public void notifyItemRangeChanged(int startPosition, int itemCount) {
        mAdapter.notifyItemRangeChanged(startPosition, itemCount);
    }

    public void notifyAdapter(int position) {
        mAdapter.notifyItemChanged(position);
    }


    public void refreshComplete() {
        isRequesting = false;
        refreshLayout.setRefreshing(false);
        onEnd("");
    }

    @Override
    public void onSuccess(String tag, Object entry) {
        super.onSuccess(tag, entry);
        refreshComplete();
    }

    @Override
    public void onFailure(String tag, int code, String error) {
        super.onFailure(tag, code, error);
        if (NetworkUtils.isConnected()) {
            if (page == 1) {
                if (datas != null && datas.size() > 0) {
                    refreshComplete();
                    return;
                } else {
                    showConnectionError();
                }
            } else {
                loadMoreFail();
            }
        }
        refreshComplete();
    }

    @Override
    public void emptyViewClick(View view, int flag) {
        super.emptyViewClick(view, flag);
        switch (flag) {
            case EmptyViewType.TAG_NO_CONTENT:
                //返回首页
//                IntentManager.intentToMainActivity();
                break;
            case EmptyViewType.TAG_NETWOEK_ERROR:
                //重新刷新数据
                onBegin("");
                page = 1;
                loadData();
                break;
            case EmptyViewType.TAG_CONNECTION_ERROR:
                //重新刷新数据
                onBegin("");
                page = 1;
                loadData();
                break;
            default:
        }
    }

    /**
     * 详情见：https://github.com/CymChad/BaseRecyclerViewAdapterHelper
     */
    public class TempListAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        TempListAdapter() {
            super(getListItemLayoutId());
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, T t) {
            if (!isFirstScroll) {
                //首次进来第一次加载数据的时候设置lastItemPosition
                lastItemPosition = baseViewHolder.getAdapterPosition();
            }
            convertData(baseViewHolder, t);
        }
    }


    /**
     * 添加头部视图
     * cs
     */
    public View addHeaderView(int headerLayoutId) {
        View view = getLayoutInflater().inflate(headerLayoutId, (ViewGroup) mRecyclerList.getParent(), false);
        mAdapter.addHeaderView(view);
        return view;
    }

    /**
     * 设置头部视图
     */
    public View setHeaderView(int headerLayoutId) {
        View view = getLayoutInflater().inflate(headerLayoutId, (ViewGroup) mRecyclerList.getParent(), false);
        mAdapter.setHeaderView(view);
        return view;
    }

    /**
     * 是否能请求数据
     *
     * @return true 可以,反之不行
     */
    public boolean isRequestWork() {
        if (page == 1) {
            try {
                setNetWorkError();
            } catch (NetworkErrorException ex) {
                refreshComplete();
                return false;
            }
        }
        return true;
    }

    /**
     * 设置无网络情况
     *
     * @throws NetworkErrorException
     */
    public void setNetWorkError() throws NetworkErrorException {
        if (!NetworkUtils.isConnected()) {
            showNetWorkError();
            isRequesting = false;
            onEnd("");
            throw new NetworkErrorException();
        }
    }

    /**
     * 滚动到顶部
     */
    public void scrollToTop() {
        scrollToPosition(0);
    }

    /**
     * 滚动到置顶position
     *
     * @param position 标志位
     */
    public void scrollToPosition(int position) {
        if (position < 0 || position >= datas.size()) {
            return;
        }
        LinearLayoutManager mManager = (LinearLayoutManager) mRecyclerList.getLayoutManager();
        mManager.smoothScrollToPosition(mRecyclerList, null, position);
    }

    /**
     * item   点机事件
     */
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
    }

    /**
     * item  child  点机事件
     */
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    /**
     * 获取list item layout
     *
     * @return list的item布局id
     */
    protected abstract int getListItemLayoutId();

    /**
     * 绑定数据、绑定监听
     *
     * @param baseViewHolder item控件的holder
     * @param t              item的参数
     */
    protected abstract void convertData(BaseViewHolder baseViewHolder, T t);
}
