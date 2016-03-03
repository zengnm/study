package com.share.common.util.base;

import com.share.common.util.PaginatedList;

import java.util.ArrayList;

/**
 * 分页记录的母体的
 */
public class PaginatedArrayList<T> extends ArrayList<T> implements PaginatedList<T> {
    /**
     * 默认每页的记录数量
     */
    public static final int PAGESIZE_DEFAULT = 20;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 当前页。第一页是1
     */
    private int index;

    /**
     * 总记录数
     */
    private int totalItem;
    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 分页后的记录开始的地方
     * 第一条记录是1
     */
    private int startRow;
    /**
     * 分页后的记录结束的地方
     */
    private int endRow;

    /**
     * 默认构造方法
     */
    public PaginatedArrayList() {
        repaginate();
    }

    /**
     * 带当前页和页大小的构造方法
     *
     * @param index    当前页
     * @param pageSize 页大小
     */
    public PaginatedArrayList(int index, int pageSize) {
        this.index = index;
        this.pageSize = pageSize;
        repaginate();
    }

    /**
     * 表示是不是第一页
     *
     * @return true 是; false 不是
     */
    public boolean isFirstPage() {
        return index <= 1;
    }


    public boolean isMiddlePage() {
        return !(isFirstPage() || isLastPage());
    }


    public boolean isLastPage() {
        return index >= totalPage;
    }


    public boolean isNextPageAvailable() {
        return !isLastPage();
    }

    public boolean isPreviousPageAvailable() {
        return !isFirstPage();
    }

    /**
     * 下一页号
     *
     * @return 取得下一页号
     */
    public int getNextPage() {
        if (isLastPage()) {
            return totalItem;
        } else {
            return index + 1;
        }
    }

    public int getPreviousPage() {
        if (isFirstPage()) {
            return 1;
        } else {
            return index - 1;
        }
    }

    /**
     * Method getPageSize returns the pageSize of this PaginatedArrayList object.
     * <p/>
     * 每页大小
     *
     * @return the pageSize (type int) of this PaginatedArrayList object.
     */

    public int getPageSize() {
        return pageSize;
    }

    /**
     * Method setPageSize sets the pageSize of this PaginatedArrayList object.
     * <p/>
     * 每页大小
     *
     * @param pageSize the pageSize of this PaginatedArrayList object.
     */

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        repaginate();
    }

    /**
     * Method getIndex returns the index of this PaginatedArrayList object.
     * <p/>
     * 当前页。第一页是1
     *
     * @return the index (type int) of this PaginatedArrayList object.
     */

    public int getIndex() {
        return index;
    }

    /**
     * Method setIndex sets the index of this PaginatedArrayList object.
     * <p/>
     * 当前页。第一页是1
     *
     * @param index the index of this PaginatedArrayList object.
     */

    public void setIndex(int index) {
        this.index = index;
        repaginate();
    }

    /**
     * Method getTotalItem returns the totalItem of this PaginatedArrayList object.
     * <p/>
     * 总记录数
     *
     * @return the totalItem (type int) of this PaginatedArrayList object.
     */

    public int getTotalItem() {
        return totalItem;
    }

    /**
     * Method setTotalItem sets the totalItem of this PaginatedArrayList object.
     * <p/>
     * 总记录数
     *
     * @param totalItem the totalItem of this PaginatedArrayList object.
     */

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
        repaginate();
    }

    /**
     * Method getTotalPage returns the totalPage of this PaginatedArrayList object.
     * <p/>
     * 总页数
     *
     * @return the totalPage (type int) of this PaginatedArrayList object.
     */

    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Method getStartRow returns the startRow of this PaginatedArrayList object.
     * <p/>
     * 分页后的记录开始的地方
     *
     * @return the startRow (type int) of this PaginatedArrayList object.
     */

    public int getStartRow() {
        return startRow;
    }

    /**
     * Method getEndRow returns the endRow of this PaginatedArrayList object.
     * <p/>
     * 分页后的记录结束的地方
     *
     * @return the endRow (type int) of this PaginatedArrayList object.
     */

    public int getEndRow() {
        return endRow;
    }

    /**
     * Method repaginate ...
     */
    private void repaginate() {
        if (pageSize < 1) { //防止程序偷懒，list和分页的混合使用
            pageSize = PAGESIZE_DEFAULT;
        }
        if (index < 1) {
            index = 1;//恢复到第一页
        }
        if (totalItem > 0) {
            totalPage = totalItem / pageSize + (totalItem % pageSize > 0 ? 1 : 0);
            if (index > totalPage) {
                index = totalPage; //最大页
            }
            endRow = index * pageSize;
            startRow = endRow - pageSize + 1;
            if (endRow > totalItem) {
                endRow = totalItem;
            }
        }
    }

}
