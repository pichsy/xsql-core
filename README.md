

# 最新地址, 该项目已移到gitee

# [https://gitee.com/pichs/xsql-core](https://gitee.com/pichs/xsql-core)


# XSql数据库框架
 极致简单好用的ORM数据库框架<br>
 可自动升级，无需操心数据库升级带来的困扰，面对对像数据库操作

最新版本：[![](https://jitpack.io/v/pichsy/xsql-core.svg)](https://jitpack.io/#pichsy/xsql-core)

### 引用方式

         // 核心包 其中已经包含xsql-base，可以不用重复引用
         implementation 'com.github.pichsy:xsql-core:1.0'
         // 表字段映射类自动生成，可选
         kapt 'com.github.pichsy:xsql-maker:1.0'

### 一、 一行代码，无需创建乱七八槽的 dao
        
    var baseDao = XSql.getDBManager(this).getBaseDao(UserInfo::class.java)
    // 获取baseDao 举个栗子。
    baseDao.insert(UserInfo)
    // 具体使用可以查看详情。



### 二、 字段随意定，支持模糊，区间，分页，排序等常用查询。

1. XSqlTable: 定义表名的注解，不可为空
2. XSqlField: 定义字段名字的注解，不可为空，该字段必须为包装类。
              不添加此注解的字段不添加到数据库。字段类型可随意。
              仅支持以下7种，Integer，Long，Double，Float，Boolean, String，byte[]
              本人以为这7种足以应对各种表，越简单，越好用。
3. XSqlPrimaryKey: 定义自增键的注解，必须为Long或Integer。与XSqlField一起用
4. XSqlUnique: 定义唯一值，与XSqlField一起用。


    
    @XSqlTable("user_info")
    public class UserInfo {
    
        @XSqlField("name")
        public String name;
    
        @XSqlField("age")
        public Integer age;
    
        public UserInfo() {
        }
    
        public UserInfo(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    
        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    

## 增删改查，一行搞定。
- 增删改查，是重中之重，所以详细介绍使用方式和规则。

### 增

1. insert(T entity): 添加一条数据
   规则限制: 优先以XSqlPrimaryKey查询，其次XSqlUnique, 都没有
   就以其他结果合并查询。
    
    
2. insertInTx(List<T> entities)：批量添加数据

    


### 删
1. delete(T entity): 删除匹配相同的数据 <br/>
(规则限制，对象内所有属性为空时不删除任何数据，保证一定的安全性。)





2. delete(Where where): 根据条件删除<br/>
 (规则限制条件为空时不删除任何数据)




3. deleteAll(): 删除所有数据



### 改
1. update(T entity): 更新数据：
（规则限制：必须满足有 唯一建或者自增id且不为null 的情况下有效。）
因为没有唯一建，无法知晓到底根据哪个字段更新更新哪些字段的数据。
全排列查询效率低下，此种情况建议使用条件更新 （2.）




2. update(Where where, T entity): 条件更新
（规则限制，where不可为null且条件必须有个一判断。entity中必须至少有一个字段的值不为null）


### 查
1. query(T entity): 根据entity查询数据
（规则限制：entity中必须至少有一个字段的值不为null）
    规则优先级: 优先以XSqlPrimaryKey查询，其次XSqlUnique, 
    都没有就以其他结果合并查询。



2. query(Where where): 条件查询
如果普通的查询无法满足你的需求，那么就是用条件查询吧，各种花里胡哨的条件查询都有。





3. queryAll(): 查询所有
