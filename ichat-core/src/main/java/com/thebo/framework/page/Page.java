package com.thebo.framework.page;

import java.util.List;

public class Page {
    
    private int iTotalRecords;
    
    private int iTotalDisplayRecords;
	
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<?> data;
    private int currentPage;
    private int pageCount;
    private int sEcho;

    /**
     * @return the data
     */
    public List<?> getData() {
        return data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(List<?> data) {
        this.data = data;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the pageCount
     */
//    public int getPageCount() {
//        return pageCount;
//    }
//
//    /**
//     * @param pageCount the pageCount to set
//     */
//    public void setPageCount(int pageCount) {
//        this.pageCount = pageCount;
//    }

    /**
     * @return the sEcho
     */
    public int getsEcho() {
        return sEcho;
    }

    /**
     * @param sEcho the sEcho to set
     */
    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
    
}
