@echo off 
echo *********************************************************** 
echo ���SVN�汾��Ϣ ���ߣ�Winfans 2012��10��18��14:43:54 
echo *********************************************************** 
:start 
::�������̣��л�Ŀ¼ 
:set pwd=%cd% 
:cd %1 




echo ����Ŀ¼�ǣ�& chdir 
echo ----------------------------------------------------------- 


:input 
::��ȡ���룬����������д��� 
set source=: 
set /p source=ȷ��Ҫ�����ǰĿ¼�µ�.svn��Ϣ�𣿻س���(q=�˳�) 
set "source=%source:"=%" 
if "%source%"=="q" goto end 
if "%source%"=="Q" goto end 
if "%source%"=="y" goto clean 
::goto input 
:clean 
::��������̣�ִ�������� 
@echo on 
@for /d /r %%c in (.svn) do @if exist %%c ( rd /s /q %%c & echo ɾ��Ŀ¼%%c) 
@echo off 
echo ----------------------------------------------------------- 
echo ��ǰĿ¼�µ�svn��Ϣ����� 


goto end 
:noclean 
::��֧���̣�ȡ�������� 
echo svn��Ϣ���������ȡ�� 
goto end 
:end 
::�˳����� 
