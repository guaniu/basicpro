@echo off 
echo *********************************************************** 
echo 清除SVN版本信息 作者：Winfans 2012年10月18日14:43:54 
echo *********************************************************** 
:start 
::启动过程，切换目录 
:set pwd=%cd% 
:cd %1 




echo 工作目录是：& chdir 
echo ----------------------------------------------------------- 


:input 
::获取输入，根据输入进行处理 
set source=: 
set /p source=确定要清除当前目录下的.svn信息吗？回车键(q=退出) 
set "source=%source:"=%" 
if "%source%"=="q" goto end 
if "%source%"=="Q" goto end 
if "%source%"=="y" goto clean 
::goto input 
:clean 
::主处理过程，执行清理工作 
@echo on 
@for /d /r %%c in (.svn) do @if exist %%c ( rd /s /q %%c & echo 删除目录%%c) 
@echo off 
echo ----------------------------------------------------------- 
echo 当前目录下的svn信息已清除 


goto end 
:noclean 
::分支过程，取消清理工作 
echo svn信息清楚操作已取消 
goto end 
:end 
::退出程序 
