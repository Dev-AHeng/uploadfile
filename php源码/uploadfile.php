<?php
header('Content-type: application/json;charset=utf-8');

if(empty($_FILES)) die('{"状态":false,"状态信息":"错误提交"}');

$dirPath = './file/';//设置文件保存的目录
$url = 'http://212.64.122.76/uploadfile/file/';
if(!is_dir($dirPath))
{
    // 目录不存在则创建目录
    @mkdir($dirPath);
}

$data = array();

$count = count($_FILES);//所有文件数

// if($count<1) die('{"状态":false,"状态信息":"错误提交"}');//没有提交的文件

$success = $failure = 0;

foreach($_FILES as $key => $value)
{
	// 获取上传文件名
	$tmp = $value['name'];
	// 上传文件大小限制10M, 单位BYTE
	$max_file_size = 10485760;
	// 取后缀
	$filename0 = pathinfo($tmp, PATHINFO_EXTENSION);
	// 统一小写
	$filename1 = strtolower($filename0);
	
    // 拿到文件
    $file = $_FILES["img_2"];
    // 检查文件大小
    if ($max_file_size < $file["size"])
    {
        // echo '上传失败，文件太大（上限1MB）';
        $arr['状态']  = false;
    	$arr['状态信息'] = '上传失败，文件太大（为了节省空间上传上限10MB）';
    	$arr['本地文件大小'] = $file["size"] . 'byte';
    	$returnJson = json_encode($arr, JSON_UNESCAPED_UNICODE);
    	// stripslashes($String)去除返回json的\
    	exit(stripslashes($returnJson));
        
    }
    else if ($filename1 == 'php')
    {
    	// echo '不能上传php文件';
    	$arr['状态']  = false;
    	$arr['状态信息'] = '不能上传php文件';
    	$returnJson = json_encode($arr, JSON_UNESCAPED_UNICODE);
    	// stripslashes($String)去除返回json的\
    	// echo stripslashes($returnJson);
    	exit(stripslashes($returnJson));
    }
    else if ($filename1 == 'js')
    {
    	// echo '不能上传js文件';
    	$arr['状态']  = false;
    	$arr['状态信息'] = '不能上传js文件';
    	$returnJson = json_encode($arr, JSON_UNESCAPED_UNICODE);
    	// stripslashes($String)去除返回json的\
    	// echo stripslashes($returnJson);
    	exit(stripslashes($returnJson));
    }
    else if ($filename1 == 'html' || $filename1 == 'hml' || $filename1 == 'shtml' || $filename1 == 'htm')
    {
    	// echo '不能上传html文件';
    	$arr['状态']  = false;
    	$arr['状态信息'] = '不能上传html文件';
    	$returnJson = json_encode($arr, JSON_UNESCAPED_UNICODE);
    	// stripslashes($String)去除返回json的\
    	// echo stripslashes($returnJson);
    	exit(stripslashes($returnJson));
    }
    else 
    {
    	// 循环遍历数据
    	$tmpName = $value['tmp_name'];//临时文件路径
    	$time = date('YmdHis');
    	// 上传的文件会被保存到php临时目录，调用函数将文件复制到指定目录
    	$date = $dirPath. $time. '_'. $tmp;
    	if(move_uploaded_file($tmpName, $date))
    	{
        	$success++;
    	}
    	else
    	{
        	$failure++;
    	}
    	 $lookUrl = $url. $time. '_'.$tmp;
    	 $arr['状态']  = true;
    	 $arr['状态信息'] = '提交成功！';
    	 $arr['成功个数'] = $success;
    	 $arr['失败个数'] = $failure;
    	 $arr['图片路径'] = $lookUrl;
    	 $returnJson = json_encode($arr, JSON_UNESCAPED_UNICODE);
    	 // stripslashes($String)去除返回json的\
    	 // echo stripslashes($returnJson);
    	 exit(stripslashes($returnJson));
    	 
    }
    
}

?>