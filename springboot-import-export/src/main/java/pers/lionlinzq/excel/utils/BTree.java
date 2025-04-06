package pers.lionlinzq.excel.utils;

import java.util.List;

public  interface BTree<T>{

     List<T> getChildren();

      void setChildren(List<T> t);

      String getValue();

    String getParentValue();

      void setValue(String t);
      void setParentValue(String t);

      void  add(T  b);

}
