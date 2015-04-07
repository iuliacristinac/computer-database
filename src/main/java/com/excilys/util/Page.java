package com.excilys.util;

import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Computer;

public class Page {

	private static final int ELEMENTS_BY_PAGE = 10;
	private int currentPage;
	private int numberOfPages;
	
	public Page() {
		currentPage = 0;
		numberOfPages = 0;
	}
	
	public Page (List<Computer> list) {
		if (!list.isEmpty()) {
			numberOfPages = list.size() / ELEMENTS_BY_PAGE;
			if (list.size() % ELEMENTS_BY_PAGE != 0) {
				numberOfPages++;
			}
			currentPage = 1;
		}
		else {
			currentPage = 0;
			numberOfPages = 0;
		}
	}

	public List<Object> getElements(List<Object> list, int page) {
		List<Object> elementsList = new ArrayList<>();
		int fromIndex = (page - 1) * ELEMENTS_BY_PAGE;
		int toIndex = fromIndex + ELEMENTS_BY_PAGE - 1;
		if (toIndex <= (list.size() - 1)) {
			toIndex = list.size() - 1;
		}
		elementsList = list.subList(fromIndex, toIndex);
		return elementsList;
	}
	
	public boolean hasNext() {
		return currentPage < numberOfPages; 
	}
	
	public boolean hasPrevious() {
		return currentPage > 1;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
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
