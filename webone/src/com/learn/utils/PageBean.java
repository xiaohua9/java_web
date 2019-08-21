package com.learn.utils;
//用于分页显示的页面数据类
public class PageBean {
    //属性：每页显示的数量，总页数，当前页数，数据库的总数据量
    private int pageSize=3;
    private int totalPages;
    private int currentPage=1;
    private int rows;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {//当赋值总行数时，总页数即可根据下方的方式计算得到
        this.rows = rows;
        if (this.rows%this.pageSize==0){
            this.totalPages=this.rows/this.pageSize;
        }else {
            this.totalPages=this.rows/this.pageSize+1;
        }
    }
}
