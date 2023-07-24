package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest implements Pageble {
	private Integer page;
	private Integer maxPageItem;
	private Sorter sorter;
	private String seachValue;

	public PageRequest(Integer page, Integer maxPageItem, Sorter sorter,String seachValue) {
		this.page = page;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
		this.seachValue=seachValue;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getOffset() {
		if (this.page != null && this.maxPageItem != null)
			return (this.page - 1) * this.maxPageItem;
		return null;
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}

	@Override
	public Sorter getSorter() {
		if (this.sorter.getSortBy()==null||this.sorter.getSortBy().isEmpty()||this.sorter.getSortName()==null||this.sorter.getSortName().isEmpty())
			return null;
		return this.sorter;
	}

	@Override
	public String getSearchValue() {
		if(seachValue!=null) {
			if(seachValue.equals("")) {
				return null;
			}
		}
		return this.seachValue;
	}



}
