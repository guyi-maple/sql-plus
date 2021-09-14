## 运行时修改SQL

实现接口 [SqlPlusExecutor](./src/main/java/tech/guyi/component/sql/plus/executor/SqlPlusExecutor.java) , 并将实现类放入Spring容器中, 即可在运行时修改SQL。

* 重构`execute(SqlPlusSelect select)`, 可修改查询语句
* 重构`execute(SqlPlusUpdate update)`, 可修改更新语句
* 重构`execute(SqlPlusDelete delete)`, 可修改删除语句
* 重构`execute(SqlPlusInsert insert)`, 可修改插入语句

## 字段及表名映射

在实际使用中, 可能出现SQL中的字段名与实体不一致的情况, 此时可以实现接口 [EntityNameSupplier](./src/main/java/tech/guyi/component/sql/plus/suppliper/EntityNameSupplier.java) 提供名称映射。

此工具中默认提供了基于Hibernate的名称映射提供者, 在Hibernate环境下无需自行添加.

## 说明

当前只实现了简单查询, 还未支持子查询、join等语句。
