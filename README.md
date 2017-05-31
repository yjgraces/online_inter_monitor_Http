# 线上接口监控

> 每天18：00,上线新接口监控，同步更新到这里

|序号| 开始监控时间| 所属模块 |接口URI|接口描述|接口添加人|
|:---:|:---:|:---:|:---|:---|:---:|
|1|2017-1-11|司机业务管理|/V1/Driver/unSignDriverByIds|将司机标记为非派单司机|yaojing|
|2|2017-1-11|司机业务管理|/V1/Driver/signDriverByIds|将司机设置为派单司机|yaojing|
|3|2017-1-11|司机业务管理|/V1/Driver/getDispatchDriverInfo|查询司机收发车poi点|yaojing|
|4|2017-1-11|司机业务管理|/V1/Driver/getDriverWithdraw|获取司机提现标记|yaojing|
|5|2017-1-11|司机业务管理|/V1/Driver/getSignedDrivers|获取已标记派单司机|yaojing|
|6|2017-1-11|司机业务管理|/V1/Driver/setDriverWithdraw|设置/解除提现标记|yaojing|
|7|2017-1-11|司机业务管理|/V1/Driver/setRechargeRCFlag|设置/解除返现风控标记|yaojing|
|8|2017-1-11|司机业务管理|/V1/Driver/setDispatchPoi|设置司机收发车poi点|yaojing|
|9|2017-1-11|司机业务管理|/V1/Driver/resetDispatchPoi|重置司机收发车poi点|yaojing|
|10|2017-1-11|司机业务管理|/V1/Driverstatread/getStatByWeek|按周统计查询|yaojing|
|11|2017-1-11|司机业务管理|/V1/Driverstatread/getStatByDay|按天统计查询|yaojing|
|12|2017-1-11|司机业务管理|/V1/Driverstatread/getStatByMonth|按月统计查询|yaojing|
|13|2017-1-11|司机业务管理|/V1/Driverstatread/getTodayStat|查询今天统计|yaojing|
|14|2017-1-11|司机业务管理|/V1/Driverstatread/getOrderRecordList|查询指定日期范围的订单流水	|yaojing|
|15|2017-1-11|司机业务管理|/V1/Driverstatread/getStatByDataBetween|查询指定时间区间的按天统计数据|yaojing|
|16|2017-1-11|司机业务管理|/V1/Driverstatread/getOrderRecordListByDay|查询某一天的订单流水|yaojing|
|17|2017-1-11|司机业务管理|/V1/Driverstatread/getDayStatByMonth|查询某一月中每天的统计数据|yaojing|
|18|2017-1-11|司机业务管理|/V1/Driverstatread/getOrderRecordListByMonth|查询某个月的订单流水|yaojing|
|19|2017-1-11|司机业务管理|/V1/Driverstatread/getDriverCompleteOrder|返回指定司机的所有完成订单数|yaojing|
|20|2017-1-11|司机业务管理|/V1/Oauthbind/unBind|根据driver_id解绑|yaojing|
|21|2017-1-11|司机业务管理|/V1/Oauthbind/getBindStatus|获取绑定状态|yaojing|
|22|2017-1-12|司机业务管理|/V1/Driver/isFieldExists|判断某个字段内容是否已经存在|yaojing|
|23|2017-1-12|合作伙伴管理|/V1/Labourcompany/getLabourCompanyById	|根据labour_company_id获取劳务公司信息|yaojing|
|24|2017-1-12|合作伙伴管理|/V1/Punishrecord/getCompanyList|获取劳务公司列表|yaojing|
|25|2017-1-12|合作伙伴管理|/V1/Company/getCompanyById	|根据company_id获取租赁公司信息|yaojing|
|26|2017-1-12|合作伙伴管理|/V1/Company/getCompanyByIds|根据租赁公司ID批量查询租赁公司名字|yaojing|
|27|2017-1-12|合作伙伴管理|/V1/Company/show|根据租赁公司ID查询租赁公司信息|yaojing|
|28|2017-1-12|合作伙伴管理|/V1/Company/searchByName|根据租赁公司名字查询ID|yaojing|
|29|2017-1-12|合作伙伴管理|/V1/Company/getReasonTextList|账户操作类型常量|yaojing|
|30|2017-1-12|合作伙伴管理|/V1/Driverlevel/getLevelInfo|根据司机Id获取司机等级所有关联信息|yaojing|
|31|2017-1-12|合作伙伴管理|/V1/Driverlevel/getLevelDetailOfDay|查询司机分级某日明细|yaojing|
|32|2017-1-12|合作伙伴管理|/V1/Driverlevel/getLevelDetailOfMonth|查询司机分级某月明细	|yaojing|
|33|2017-1-12|合作伙伴管理|/V1/Driverlevel/getRightsByDriverId|根据司机ID获取司机权益信息|yaojing|
|34|2017-1-12|合作伙伴管理|/V1/Driverlevel/deductDriverRights	|扣减司机权益|yaojing|
|35|2017-1-12|司机管理|/V1/Driver/updateDriverAndCar|创建司机车辆信息|cuikui|
|36|2017-1-12|司机管理|/V1/Driver/resetPassword|根据司机ID重置密码|cuikui|
|37|2017-1-12|司机管理|/V1/Driver/updateDriverAndCar	|更新司机和车辆信息|cuikui|
|38|2017-1-12|司机管理|/V1/Driver/updateDriverAndCar	|根据司机ID更新司机信息|cuikui|
|39|2017-1-12|司机管理|/V1/Driver/getDriver|根据司机ID获取司机基本信息|cuikui|
|40|2017-1-12|司机管理|/V1/Driver/getDriverAndCarByDids|根据driverIds批量查询司机和车辆信息|cuikui|
|41|2017-1-12|司机管理|/V1/Driver/getDriverByAccountIds|根据accountIds批量获取司机信息|cuikui|
|42|2017-1-12|司机管理|/V1/Driver/getDriverByCarIds|根据carlds批量获取司机信息|cuikui|
|42|2017-1-12|司机管理|/V1/Driver/getDriverByCellphone|根据司机cellphone获取司机基本信息|cuikui|
|44|2017-1-12|司机管理|/V1/Driver/getDriverByCellphones|根据cellphones批量获取司机信息|cuikui|
|45|2017-1-12|司机管理|/V1/Driver/getDriverByDeviceId|根据设备deveice_id获取司机基本信息|cuikui|
|46|2017-1-12|司机管理|/V1/Driver/getDriverByDids|根据driverIds批量获取司机信息|cuikui|
|47|2017-1-12|司机管理|/V1/Driver/getDriverType|	通过分页获取司机个性化标签|cuikui|
|48|2017-1-12|司机管理|/V1/Driver/getExtInfoByDriverId|根据司机ID获取司机扩展信息|cuikui|
|49|2017-1-12|司机管理|/V1/Driver/getInfo|根据司机ID获取司机所有关联信息|cuikui|
|50|2017-1-12|司机管理|/V1/Driver/getInfoById|根据司机ID获取司机信息|cuikui|
|51|2017-1-12|司机管理|/V1/Driver/getNormalDriverByCids|根据company_ids批量获取司机数量（audit_status=20）|cuikui|
|52|2017-1-12|司机管理|/V1/Driver/getNormalDriverByLcids|根据labour_company_ids批量获取司机数量（audit_status=20）|cuikui|
|53|2017-1-12|司机管理|/V1/Driver/search|根据条件搜索司机列表|cuikui|
|54|2017-1-12|司机管理|/V1/Driver/searchAuditInfo|根据条件搜索司机审核列表|cuikui|
|55|2017-1-12|司机管理|/V1/Driverregister/auditNoPass|注册司机审核不通过| cuikui|
|56|2017-1-12|司机管理|/V1/Driverregister/createAuthCode	H5|（发送验证码）|cuikui|
|57|2017-1-12|司机管理|/V1/Driverregister/search|	ERP（司机加盟搜索） |cuikui|
|60|2017-1-12|司机管理|/V1/Driverlevelupconfig/getAllConfig|	ERP（获取全部司机加盟配置） |cuikui|
|61|2017-1-12|司机管理|/V1/Driverregister/getAuditInfo|H5（根据注册司机ID获取注册司机审核不通过原因）|cuikui|
|62|2017-1-16|合作伙伴管理|/V1/LabourCompany/get|根据id取得劳务公司信息|yaojing|
|63|2017-1-16|合作伙伴管理|/V1/Labourcompany/checkDriverCount|检查劳务公司人数是否已经到达上限|yaojing|
|64|2017-1-16|合作伙伴管理|/V1/Labourcompany/searchLabourCompany|搜索劳务公司信息列表|yaojing|
|65|2017-1-16|合作伙伴管理|/V1/Company/validateCompanyByEmail|校验租赁公司邮箱|yaojing|
|66|2017-1-16|合作伙伴管理|/V1/Company/getCompanyNameById|根据租赁公司ID获取租赁公司名字|yaojing|
|67|2017-1-16|合作伙伴管理|/V1/Company/getCompanyContactById|根据租赁公司ID获取联系人信息|yaojing|
|68|2017-1-16|合作伙伴管理|/V1/Driver/searchDriverByCompany|租赁公司下司机信息|yaojing|
|69|2017-1-16|合作伙伴管理|/V1/Company/showCompanyByEmail|租赁公司详情|yaojing|
|70|2017-1-16|合作伙伴管理|/V1/Company/showAccountAmountByEmail|租赁公司账户余额|yaojing|
|71|2017-1-16|合作伙伴管理|/V1/Company/getCompanyStat|租赁公司月统计|yaojing|
|72|2017-1-16|合作伙伴管理|/V1/Company/getHistoryList|历史订单明细|yaojing|
|73|2017-1-16|合作伙伴管理|/V1/Company/getAccountListByEmail|已结算账户明细|yaojing|
|74|2017-1-16|合作伙伴管理|/V1/Company/getWaitIncomeListByEmail|结算中账户明细|yaojing|
|75|2017-1-16|司机管理-司机加盟注册|/V1/Driverlevelupconfig/getConfigByCity|ERP（根据城市获取司机加盟配置)|cuikui|
|76|2017-1-16|司机管理-司机加盟注册	|/V1/Driverregister/getDriverRegisterInfo|H5（根据司机注册ID获取加盟司机信息）|cuikui|
|77|2017-1-16|司机管理-司机加盟注册	|/V1/Driverregister/getDriverRegisterInfoByCellphone	|H5（通过手机号获取加盟司机信息）  |cuikui|
|78|2017-1-16|司机管理-司机加盟注册	|/V1/Driverregister/getFailedAuditByCellphone|根据手机号获取注册司机审核不通过原因） |cuikui|
|79|2017-1-16|司机管理-司机加盟注册	|/V1/Driverregister/registerConfirm	|H5信息填写完成后，确认操作|cuikui|
|80|2017-1-16|字典常量	 |/V1/DictConst/getDictConstListByType	|查询某类字典常量|cuikui|
|81|2017-1-16|字典常量	 |/V1/DictConst/getDictDescByKey	|根据key查询某类字典常量|cuikui|
|82|2017-1-16|字典常量	 |/V1/DictConst/getDictDescByValue	|根据值查询某类字典常量|cuikui|
|83|2017-1-16|城市经理	 |/V1/Servicemanager/getManagerById	|根据城市经理ID查询城市经理信息|cuikui|
|84|2017-1-16|城市经理	|/V1/Servicemanager/getManagerList	|获取所有正在使用的城市经理信息|cuikui|
|85|2017-1-16|城市经理	 |/V1/Servicemanager/getManagerByFields	|根据条件搜索城市经理信息|cuikui|
|86|2017-1-16|城市经理	|/V1/Servicemanager/createManager	|插入城市经理信息|cuikui|
|87|2017-1-16|城市经理	|/V1/Servicemanager/updateManager	|更新城市经理信息|cuikui|
|88|2017-1-16|城市经理	|/V1/Servicemanager/deleteManager	|删除城市经理信息|cuikui|
|89|2017-1-17|司机业务管理|V1/Driver/setDriverReport|增加司机举报订单记录|yaojing|
|90|2017-1-17|司机业务管理|/V1/Company/setCommissionRatio|批量设置分佣比例|yaojing|
|91|2017-1-17|司机业务管理|/V1/Driver/getDriverOption|根据司机id获取司机开关状态（司机接单设置表）|yaojing|
|92|2017-1-17|司机管理-司机信息修改	 |/V1/Driverhawkeye/insertDriverHawkeye	|根据司机ID添加百度鹰眼跟司机关系|cuikui|
|93|2017-1-17|司机管理-司机信息修改	 |/V1/Driver/createDriverAndCar	|创建司机和车辆信息|cuikui|
|94|2017-1-17|司机管理-司机信息修改	|/V1/Driverappstartcity/getDriverAppStartCities	|获取APP启动页面广告信息|cuikui|
|95|2017-1-17|司机管理-司机信息修改	|/V1/Driverhawkeyerecord/getHawkeyeRecordByOrderId	|根据订单ID获取百度鹰眼记录|cuikui|
|96|2017-1-17|司机管理-司机信息修改	|/V1/Driver/getDriverPersonalLabels	|通过分页获取司机个性化标签|cuikui|
|97|2017-1-17|司机管理-司机信息修改	|/V1/Driver/getInBalanceList	|获取司机在结算中的列表|cuikui|
|98|2017-1-17|司机管理-司机加盟注册	|/V1/Driverregister/driverRegister	|H5司机信息|cuikui|
|99|2017-1-17|司机管理-司机加盟注册	|/V1/Driverregister/carRegister	|H5车辆信息|cuikui|
|100|2017-1-17|司机管理-司机加盟注册	|/V1/Driverregister/imgRegister	|H5证件信息|cuikui|
|101|2017-1-17|司机管理-司机加盟注册	 |/V1/Driverregsiter/verifyAuthCode	|验证验证码|cuikui|
|102|2017-1-17|司机管理-司机加盟注册	 |/V1/Driverregister/getFailedAuditById|	根据注册司机ID获取注册司机审核不通过原因|cuikui|
|103|2017-1-17|司机管理-司机加盟注册	 |/V1/Driverregister/auditPass|	注册司机审核通过|cuikui|
|104|2017-1-17|司机管理-司机加盟注册	 |/V1/Driverregister/getAuthCode|	获取验证码|cuikui|