package com.hzy;

import junit.framework.TestCase;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     *  distinct 和 filter操作
     */
    public void test01(){
        // 打印所有年龄小于18的作家的名字，并且要注意去重
        // distinct    filter
        List<Author> authors = Author.getAuthors();
        authors.stream() // 将authors转化为stream流
                .distinct() // 去重
                .filter(author -> author.getAge() < 18) // 筛选小于18的
                .forEach(author -> System.out.println(author.getName())); // 遍历打印姓名
    }

    /**
     *  map操作
     */
    public void test02(){
        List<Author> authors = Author.getAuthors();
        // map
        authors.stream()
                .map(author -> author.getAge())
                .map(age->age+10)
                .forEach(age-> System.out.println(age));
    }

    /**
     *  sorted 操作
     */
    public void test03(){
        List<Author> authors = Author.getAuthors();
        // sorted
        //  对流中的元素按照年龄进行升序排序，并且要求不能有重复的元素。
         authors.stream()
                .distinct()
                .sorted((o1, o2) -> o1.getAge()-o2.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    /**
     *  limit 操作
     */
    public void test04(){
        List<Author> authors = Author.getAuthors();
        // limit
        authors.stream()
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
    }
    public void test05(){

    }

    /**
     *  skip 操作
     */
    public void test06(){
        // 打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序
        List<Author> authors = Author.getAuthors();
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));
    }

    /**
     *  flatMap 操作
     */
    public void test010(){
        List<Author> authors1 = Author.getAuthors();
        // 获取authors的book集合
        // 用map
        authors1.stream()
                .distinct()
                .map(author -> author.getBooks())
                .collect(Collectors.toList());

        // 用flatMap
        List<Author> authors2 = Author.getAuthors();
        authors2.stream()
                .distinct()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toList());

    }
}
