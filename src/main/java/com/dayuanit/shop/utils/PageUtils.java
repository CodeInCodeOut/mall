package com.dayuanit.shop.utils;

import java.util.List;

public class PageUtils<T> {
		
		public static final Integer orderSize = 2;
		
		private Integer lastPage;
		private Integer PageNum;
		private List<T> data;
		
		public List<T> getData() {
			return data;
		}

		public void setData(List<T> data) {
			this.data = data;
		}

		public Integer getLastPage() {
			return lastPage;
		}

		public Integer getPageNum() {
			return PageNum;
		}

		public PageUtils(Integer PageNum, Integer orderTotal) {
			this.PageNum = PageNum;
			this.lastPage = getTotalPageNum(orderTotal);
		}
		
		private static Integer getTotalPageNum(int orderTotal) {
			return (orderTotal % orderSize) == 0 ? orderTotal / orderSize : (orderTotal / orderSize) + 1;
		}
		
		public Integer getStartNum() {
			return (PageNum - 1) * orderSize;
		}

	}



