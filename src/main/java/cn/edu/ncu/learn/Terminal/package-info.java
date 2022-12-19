package cn.edu.ncu.learn.Terminal;

/**
 * 综合商城信息管理系统的设计与实现
 * 用户: User [管理员 + 员工]
 * 商品: Goods(商品种类, 商品号, 商品名, 单价, 产地, 图片, 产品介绍)[实时更新 -> Observer][添加, 删除, 购买]
 * <p>
 * 人员:
 * 总经理 -管理-> 信息部门经理, 人事等其他行政部门经理, 销售部门经理
 * 信息部门经理 -管理-> 普通员工
 * 人事等其他行政部门 -管理-> 普通员工
 * 销售部门经理 -管理-> 区域经理 -管理-> 销售人员
 * <p>
 * <li>用户管理:
 * 管理员 & 员工(角色不同, 权限不同)
 * 系统管理员(信息部门) -> 创建人事部门经理账号
 * 人事部门经理 -> 添加 & 删除其他部门经理账号 / 批准添加 & 删除其他类型普通员工
 * 人事部门普通员工 -> 批准添加 & 删除其他类型的员工
 * 部门经理 -> 申请添加 & 删除员工
 * <li>员工可以查看公司的所有职工信息
 * <li>用户登录: 不同的用户登录产生不同的用户对象
 * <li>用户信息: 账号, 密码, 性别, 部门, 出生日期, 注册时间, 等级信息, 联系方式(手机, QQ, 微信, 住址)
 * <li>部门经理可以在系统中申请添加 & 删除某个员工, 可以通过复制某个已有的员工产生一个新员工
 * <p>
 * <li>商品信息: 种类号, 商品号, 商品名, 单价, 产地, 图片, 规格, 产品介绍(实时更新)[添加 & 删除]
 * 经理: 添加 & 删除 & 修改 & 查看所处部门负责的商品
 * 员工: 查看 & 修改负责商品, 查看非负责商品
 * <li>种类: 经理负责其下的产品种类更新, 删除
 * <li>商品进货:
 * 1. 库存不够[系统 -> 销售负责人员 -> 逐级申报 -> 后勤保障补货完成后 修改状态]
 * 2. 销售人员 & 区域经理 决定是否补其下的货
 * <li>商品销售: 经理可以以多种不同的方式对销售商品进行每日结账查询及报表打印, 每月的统计汇总查询.
 * <li>商品库存: 商品库存查询、按种类统计
 * <li>事务请求: 多种事务申请[请假, 开会, 会议室申请, 办公用品申请]
 * 根据事务类型, 自动将事务发送给事务处理者, 而无需事务申请者指定处理者
 * <li>特殊活动:
 * 1、有些特殊日子，比如周年庆、节假日等会搞全商城的促销活动，这需要全公司所有部门参与进来；
 * 2、电器促销、时装促销等不同的促销活动，需要涉及到部分部门；
 * 3、某公司产品活动日，需要涉及到另外些部门。
 */