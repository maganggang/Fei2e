<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script src="iview/vue.min.js"></script>
<link rel="stylesheet" href="iview/iview.css">
<script src="iview/iview.min.js"></script>
<style type="text/css">
[v-cloak] {
		  display: none;
		}
body{background:url(image/ChMkJlqYtymIb9saAAd25E40JYgAAlEMgEfzWkAB3b8371.jpg)no-repeat fixed top;text-align:center;}
div{width:778px;margin:0 auto}
.container {
     max-width: 32rem;
     margin-left: auto;
     margin-right: auto;
}
.aplayer .aplayer-info .aplayer-music .aplayer-author{
	color: #fff;
}
</style>
<script>
	$(function () {	
		var vm = new Vue({
			el: '#app',
			data:{
				title:'讯飞语音合成',
				message: '享受科技，热爱生活'
			},
			methods: {
				convert: function () {
					if(vm.message===""){vm.open("请输入文字");return}
					$.ajax({
						url : "xunfeiConvert",
						async : false,
						type : 'post',
						data : {'time' : (new Date()).toString(),'message':vm.message},
						success : function(result) {
						    $("#audio").attr("src",result);
						    document.getElementById("audio").play();
						},
						error : function() {
							alert("网络异常");
						}
					});
				},open: function (nodesc) {
	                this.$Notice.open({
	                    title: '温馨提示',
	                    desc: nodesc
	                });
	            },
	         // 附件通用
	            handleFormatError(file) {
	              this.$Notice.warning({
	                title: '文件格式不正确',
	                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg、png、pdf、zip、rar、xls、docx、txt、格式的附件。'
	              });
	            },
	            handleMaxSize(file) {
	              this.$Notice.warning({
	                title: '超出文件大小限制',
	                desc: '文件 ' + file.name + ' 太大，不能超过 100M。'
	              });
	            },
	         // 新增附件
	            handleBeforeUpload(file) {
	                // 创建一个 FileReader 对象
	                var reader = new FileReader();
	                reader.readAsDataURL(file);
	            },
	            // 附件上传成功返回
	            handleSuccess(response,file){
	              var reader = new FileReader();
	                const _this = this;
	                reader.formatBytes = function(a,b){
	                  if(0==a)return"0 Bytes";
	                  var c = 1024,
	                      d = b||2,
	                      e = ["Bytes","KB","MB","GB","TB","PB","EB","ZB","YB"],
	                      f = Math.floor(Math.log(a)/Math.log(c));
	                  return parseFloat((a/Math.pow(c,f)).toFixed(d))+" "+e[f];
	                };
	                //reader.onloadend = function (e) {
	                  // 限制100MB
	                  if(file.size < 104857600){
	                    file.sizes = reader.formatBytes(file.size);
	                    file.url = reader.result;
	                    console.log(file)
	                  };
	                //};
	              this.responseData = response;
	              this.$Message.success('附件上传成功');
	              this.message=response.result;
	            },
	            // 新增失败
	            handleError(error,file){
	              console.log(file);
	            },
			}
		});						
	});
</script>
</head>
<body>
<div id="app" v-cloak>
	<div>
		<h1 v-text="title"></h1>
		<i-input v-model="message" type="textarea" :autosize="{minRows: 8,maxRows: 20}" placeholder="请输入..."></i-input>
		<Upload
		    class="mar-t"  
		    ref="upload"
		    :show-upload-list="false" 
		    :format="['pcm','wav','png', 'pdf', 'zip', 'rar', 'xls', 'docx', 'txt']"
		    :before-upload="handleBeforeUpload" 
		    :on-format-error="handleFormatError" 
		    :on-exceeded-size="handleMaxSize"
		    :on-success="handleSuccess"
		    :on-error="handleError"
		    action="baiduConvert">
		    <Button type="primary" style="width:768px;margin-bottom: 20px;">添加附件</Button>
		</Upload>
	</div>
	<div style="margin-top:10px;">
	   <i-button @click="convert" type="primary">合成播放</i-button>
	</div>
	<div style="display: none">
	   <audio id="audio" controls="controls" ></audio>
	</div>
</div>
</body>
</html>
