package gg.hta.lol.util;

public class PageUtil {
	private int pageNum;
	private int startRow;
	private int endRow;
	private int totalPageCount;
	private int startPageNum;
	private int endPageNum;
	private int rowBlockCount;
	private int pageBlockCount;
	private int totalRowCount;
	public PageUtil() {}

	public PageUtil(int pageNum, int rowBlockCount, int pageBlockCount, int totalRowCount) {
		super();
		this.pageNum = pageNum;
		this.rowBlockCount = rowBlockCount;
		this.pageBlockCount = pageBlockCount;
		this.totalRowCount = totalRowCount;
		startRow=(pageNum-1)*rowBlockCount+1;
		endRow=startRow+rowBlockCount-1;
		totalPageCount=(int)Math.ceil(totalRowCount/(double)rowBlockCount);
		startPageNum=(pageNum-1)/pageBlockCount*pageBlockCount+1;
		endPageNum=startPageNum+pageBlockCount-1;
		if(totalPageCount<endPageNum) {
			endPageNum=totalPageCount;
		}
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}
	public int getRowBlockCount() {
		return rowBlockCount;
	}
	public void setRowBlockCount(int rowBlockCount) {
		this.rowBlockCount = rowBlockCount;
	}
	public int getPageBlockCount() {
		return pageBlockCount;
	}
	public void setPageBlockCount(int pageBlockCount) {
		this.pageBlockCount = pageBlockCount;
	}
	public int getTotalRowCount() {
		return totalRowCount;
	}
	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
	@Override
	public String toString() {
		return "PageUtil [pageNum=" + pageNum + ", startRow=" + startRow + ", endRow=" + endRow + ", totalPageCount="
				+ totalPageCount + ", startPageNum=" + startPageNum + ", endPageNum=" + endPageNum + ", rowBlockCount="
				+ rowBlockCount + ", pageBlockCount=" + pageBlockCount + ", totalRowCount=" + totalRowCount + "]";
	}
}