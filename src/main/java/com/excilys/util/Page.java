package com.excilys.util;

import java.util.ArrayList;
import java.util.List;

import com.excilys.model.Computer;

public class Page {

	private static final int ELEMENTS_BY_PAGE = 10;
	private int currentPage;
	private int numberOfPages;
	private int size;
	private boolean previous;
	private boolean next;
	
	public Page() {
		currentPage = 1;
		numberOfPages = 1;
		size = ELEMENTS_BY_PAGE;
		previous = false;
		next = false;
	}
	
	public Page (List<Computer> list) {
		currentPage = 1;
		size = ELEMENTS_BY_PAGE;
		if (!list.isEmpty()) {
			numberOfPages = list.size() / size;
			if (list.size() % size != 0) {
				numberOfPages++;
			}
		}
		else {
			numberOfPages = 1;
		}
	}

	public List<Computer> getElements(List<Computer> list, int page) {
		List<Computer> elementsList = new ArrayList<>();
		int fromIndex = (page - 1) * size;
		int toIndex = fromIndex + size;
		if (toIndex >= (list.size() )) {
			toIndex = list.size();
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
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isPrevious() {
		return previous;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
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
