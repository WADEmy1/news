package tool;

public class PageTool {
	

	int currentPage=1;
	int totalPages=0;
	int pageSize=3;
	int startIndex=0;
	int rowCount=0;
	String type="";
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTotalPages() throws Exception {
		//initDate( sql);
		return totalPages;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
