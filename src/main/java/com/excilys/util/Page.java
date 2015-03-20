package com.excilys.util;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private static final long ELEMENTS_BY_PAGE = 10;
	private long currentPage;
	private long numberOfPages;
	
	public Page() {
		currentPage = 0L;
		numberOfPages = 0L;
	}
	
	public void initPagination(List<Object> list){
		if (!list.isEmpty()) {
			numberOfPages = list.size() / ELEMENTS_BY_PAGE;
			if (list.size() % ELEMENTS_BY_PAGE != 0) {
				numberOfPages++;
			}
			currentPage = 1L;
		}
	}

	public List<Object> getElements(List<Object> list) {
		List<Object> elementsList = new ArrayList<>();
		return elementsList;
	}
	
	public boolean hasNext() {
		return currentPage < numberOfPages; 
	}
	
	public boolean hasPrevious() {
		return currentPage > 0L;
	}
	
	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" Page ");
		builder.append(currentPage);
		builder.append(" of ");
		builder.append(numberOfPages);
		return builder.toString();
	}
	
	
}
