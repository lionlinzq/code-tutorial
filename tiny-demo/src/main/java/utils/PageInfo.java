package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author rongdi
 * @description 
 * @date 2015年11月4日
 */
public class PageInfo<T> implements Serializable {

   private static final long serialVersionUID = 5859907455479273251L;

   public static final int DEFAULT_PAGE_SIZE = 10;

   private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数

   private int pageIndex; // 当前页号，从1开始

   private List<T> data = new ArrayList<T>(); // 当前页中存放的记录,类型一般为List

   private long resultCount; // 总记录数

   /**
    * 默认构造方法.
    *
    * @param pageIndex 当前页号
    * @param totalSize 数据库中总记录条数
    * @param data 本页包含的数据
    */
   public PageInfo(int pageIndex, long totalSize, List<T> data) {
       this.pageIndex = pageIndex;
       this.resultCount = totalSize;
       this.data = data;
       if (this.data == null) {
           this.data = new ArrayList<T>();
       }
   }

    /**
    * 当前页号
    * @return 第一条记录的截取位置
    */
   public int getPageIndex() {
       return pageIndex;
   }

   /**
    * 默认构造方法.
    *
    * @param pageIndex 当前页号
    * @param totalSize 数据库中总记录条数
    * @param pageSize 本页容量
    * @param data 本页包含的数据
    */
   public PageInfo(int pageIndex, long totalSize, int pageSize, List<T> data) {
       this(pageIndex, totalSize, data);
       this.pageSize = pageSize;
   }

   /**
    * 取总记录数.
    * @return 符合查询条件的记录总数
    */
   public long getResultCount() {
       return this.resultCount;
   }

   /**
    * 取总页数.
    * @return 符合查询条件的记录总页数
    */
   public long getPageCount() {
       if (resultCount % pageSize == 0) {
           return resultCount / pageSize;
       } else {
           return resultCount / pageSize + 1;
       }
   }

   /**
    * 取每页数据容量.
    * @return 每页可容纳的记录数量
    */
   public int getPageSize() {
       return pageSize;
   }

   /**
    * 取当前页中的记录.
    * @return 当前数据页
    */
   public List<T> getData() {
       return data;
   }

//   public T getFirstData() {
//	   return ListUtil.isBlank(data)?null:data.get(0);
//   }
   
   /**
    * 该页是否有下一页.
    * @return 如果当前页是最后一页，返回false，否则返回true
    */
   public boolean hasNextPage() {
       return this.getPageIndex() < this.getPageCount() - 1;
   }

   /**
    * 该页是否有上一页.
    * @return 如果当前页码为0，返回true，否则返回false
    */
   public boolean hasPreviousPage() {
       return this.getPageIndex() > 0;
   }

   /**
    * 获取任一页第一条数据在数据集的位置.
    *
    * @param pageSize 每页的容量
    * @return 该页第一条数据在符合条件的查询结果中的位置。
    */
   public int getStartOfPage(int pageSize) {
       return (pageIndex - 1) * pageSize;
   }

}
