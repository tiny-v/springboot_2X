## Transaction 事务

### 一、事务不回滚的场景
> * 非Error或RuntimeException的场景， 不回滚
> * 异常被捕获的场景，不回滚
> * 访问权限问题， 不回滚 （要求方法必须是public）
> * 方法被final修饰了
> * 方法内部调用, 不回滚
> * 表不支持事务

##### 1.异常被捕获的场景（不回滚）
```
    /**
     * 捕获异常的场景
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void catchScenario() {
        userDao.insertUser(buildUser("userA"));
        try{
            int a = 1/0; // 此处制造异常
        }catch (Exception e){
            e.printStackTrace();
        }
        userDao.insertUser(buildUser("userB"));
    }
```

##### 2.方法内部调用，不回滚（不回滚）
###### demo_1(回滚失败)
```
   //方法内部调用，不回滚
    @Override
    public void parent() {
        scenario();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void scenario() {
        userDao.insertUser(buildUser("userA"));
        int a = 1/0; // 此处制造异常
        userDao.insertUser(buildUser("userB"));
    }
```
###### demo_2(回滚成功)
```
   //在另一个Service中定义一个相同的方法scenario, 可以回滚成功
    @Override
    public void parent() {
        anotherService.scenario();
    }
```

### 二、事务的七种传播机制

#### 1.Propagation.REQUIRED
> Spring 中事务的默认传播属性， 如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务

##### 场景1(回滚成功)
单独执行 **scenario** 方法， 数据 userA 被成功回滚
```
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void scenario() {
        userDao.insertUser(buildUser("userA"));
        int a = 1/0; // 此处制造异常
        userDao.insertUser(buildUser("userB"));
    }
```


##### 场景3（回滚成功）
执行 **required** 方法， userB 成功回滚
```
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void parent() {
        scenario();
    }
    
    //取消方法事务声明， 将事务声明在父方法上
    @Override
    public void scenario() {
        userDao.insertUser(buildUser("userA"));
        int a = 1/0; // 此处制造异常
        userDao.insertUser(buildUser("userB"));
    }
```
