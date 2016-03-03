package com.share.common.util;

import java.util.List;
public interface PaginatedList<T> extends List<T> {
    /**
     * 表示是不是中间的页 <br/>
     * 即不是开头，也不是结尾，就是中间的
     * @return
     */
    boolean isMiddlePage();


    /**
     * 表示是不是最后一页
     *
     * @return true 是, false 不是
     */
    boolean isLastPage();

    /**
     * 后面还有页吗？
     *
     * @return true 有, false 没有
     */
    boolean isNextPageAvailable();

    /**
     * 前面还有页吗？
     *
     * @return true 有, false 没有
     */
    boolean isPreviousPageAvailable();

    /**
     * 取得当前页大小
     *
     * @return 页大小，
     */
    int getPageSize();

    /**
     * 设置当前页大小。会触发重新计算
     */
    void setPageSize(int pageSize);

    /**
     * 当前页
     *
     * @return 当前页
     */
    int getIndex();

    /**
     * 设置当前页。会触发重新计算
     *
     * @param index 当前页
     */
    void setIndex(int index);

    /**
     * 取得总记录数
     *
     * @return 总记录
     */
    int getTotalItem();

    /**
     * 设置总记录数。会触发重新计算
     *
     * @param totalItem
     */
    void setTotalItem(int totalItem);

    /**
     * 取得总页数
     *
     * @return 总页
     */
    int getTotalPage();

    /**
     * 取得开始的记录号。辅助方法。
     * 如果在第1页，每页记录数是20。则返回 1
     *
     * @return 开始
     */
    int getStartRow();

    /**
     * 取得开始的记录号。辅助方法。
     * 如果在第1页，每页记录数是20。则返回 20
     *
     * @return 结束
     */
    int getEndRow();

    /**
     * 是否有下一页
     *
     * @return
     */
    int getNextPage();

    /**
     * 是否有上一页
     *
     * @return
     */
    int getPreviousPage();

    /**
     * 是否是第一页
     *
     * @return
     */
    boolean isFirstPage();
}
