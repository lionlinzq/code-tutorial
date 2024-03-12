select * from biz_model where metamodelcode = '1702205622440824832';
select * from meta_directorytype where metamodelcode = '1000000000000000090' and directorycategory = 2;

-- 查询租户对应的元数据领域编码
select * from meta_model where tenantcode = 1113524;

-- 领域分类
select * from biz_model where metamodelcode = '1702205622440824832';
-- 领域接口
select * from biz_modellogic where metamodelcode = '1702205622440824832';
-- 表单
select * from biz_page where metamodelcode = '1702205622440824832';
-- 表单协议,使用上一步查询出来的表单编码和租户的元数据编码
select * from meta_pageprotocol where pagecode in (1705100546014318691,1108210888374095965,1107455351944515677) and metamodelcode = '1702205622440824832';
-- 自定义控件，注意也要上传
select * from pl_pagewidget where cname like '%spu%';

-- 离线实体
select * from biz_offlinemodel where metamodelcode = '1702205622440824832';

-- 数据实体
select * from biz_object where metamodelcode = '1702205622440824832';

-- 数据实体的关联关系
select * from biz_objectref where metamodelcode = '1702205622440824832';
-- 目录
select * from meta_directorytype where metamodelcode = '1702205622440824832';