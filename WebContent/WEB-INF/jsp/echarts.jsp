<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
<script src="${ pageContext.request.contextPath }/js/echarts.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
</head>
<body>
	<div id="main" style="width: 600px;height:800px;"></div>
	<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var categorys = new Map();
        // 指定图表的配置项和数据
        <c:forEach items="${categorys}" var="category">
        	if(categorys.has("${category}")){
        		var value=categorys.get("${category}")+1;
        		categorys.delete("${category}");
        		categorys.set("${category}",value);
        	}else{
        		categorys.set("${category}",1);
        	}
        </c:forEach>
        var keys = categorys.keys();
        let arr1 = Array.from(keys);
        var values = categorys.values();
        let arr2 = Array.from(values);
		var arr3 = [];
		for(var i=0;i<arr1.length;i++){
			arr3.push([arr1[i],arr2[i]]);
		}
		console.log(arr1);
		console.log(arr2);
		console.log(arr3);
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
            	data:arr1
            },
            yAxis: {
            },
            dataset:{
            	source:arr3
            },
            
            series: [{
                type: 'pie',
                center:['50%','80%'],
                encode:{itemName:0,value:1},
                height:'50%'
                
            },
            {
                type: 'bar',
                encode:{x:0,y:1},
                height:'400',
                top:'400',
                width:'400'
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>