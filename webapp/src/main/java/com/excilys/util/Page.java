package com.excilys.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.excilys.model.Computer;

public class Page {

	private static final int ELEMENTS_BY_PAGE = 10;
	private int currentPage;
	private int numberOfPages;
	private int size;
	private List<String> properties;
	private String textualProperties;
	private Sort sort;
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
	
	public Page (List<Computer> list, int sz) {
		currentPage = 1;
		size = sz;
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

	 public String getProperties() {
		 if (properties == null) {
			 	return null;
		 }
		 if (textualProperties == null) {
			 StringBuilder sb = new StringBuilder();
			 sb.append(properties.get(0));
			 for (int k = 1; k < properties.size(); ++k) {
				 sb.append(", ").append(properties.get(k));
			 }
			 textualProperties = sb.toString();
		 }
		 return textualProperties;
	 }

	 public void setProperties(String... properties) {
		 if (properties.length == 0) {
			 throw new IllegalArgumentException();
		 }
		 if (properties.length > 0) {
			 this.properties = Arrays.asList(properties);
		 }
	 }
	 
	 public void setProperties(List<String> properties) {
		 if (properties == null || properties.isEmpty()) {
			 throw new IllegalArgumentException();
		 }
		 this.properties = properties;
	 }
	
	 public Sort getSort() {
		 return sort;
	 }

	 public void setSort(Sort sort) {
		 if (sort == null) {
			 throw new IllegalArgumentException();
		 }
		 this.sort = sort;
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
