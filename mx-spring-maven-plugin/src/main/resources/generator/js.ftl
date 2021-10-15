<!--@Author: mx-maven-plugin.-->
<!--@Date: ${createTime?string("yyyy/MM/dd")}.-->
<!--@Time: ${createTime?string("HH:mm:ss")}.-->
<!--@Description: ${modelName}.js-->
//${modelComment}列表
${listUrlKey} = baseUrl + "/${listUrl}";
//添加${modelComment}
${createUrlKey} = baseUrl + "/${createUrl}";
//修改${modelComment}
${updateUrlKey} = baseUrl + "/${updateUrl}";
//${modelComment}详情
${detailUrlKey} = baseUrl + "/${detailUrl}";
//${modelComment}删除
${deleteUrlKey} = baseUrl + "/${deleteUrl}";
